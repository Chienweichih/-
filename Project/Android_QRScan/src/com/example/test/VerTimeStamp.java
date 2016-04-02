package com.example.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

public class VerTimeStamp {
	private MessageDigest currentAlgorithm;
	private X509Certificate ca;
	private String timeStampPath;
	private String dataFilePath;

	public VerTimeStamp(String RtimeStampPath, String RdataFilePath)
			throws NoSuchAlgorithmException {
		setAlgorithm("MD5");

		timeStampPath = RtimeStampPath;
		dataFilePath = RdataFilePath;
	}

	public void VerifyTimeStamp() {
		try {
			// load bytes and convert it to token
			byte[] token_from_file = loadBytes(timeStampPath);
			PKCS7 ts_token_fromFile = new PKCS7(token_from_file);

			// get information from token
			TimestampToken tst_inf = new TimestampToken(ts_token_fromFile.getContentInfo().getData());
			byte[] hash_from_token = tst_inf.getHashedMessage();

			// compute hash from file related to timestamp token
			// hash algotihm must be the same as pointed in timestamp token
			byte[] hash_from_file = loadBytes(dataFilePath);
			setAlgorithm(tst_inf.getHashAlgorithm().getName());
			hash_from_file = computeDigest2(hash_from_file);

			ca = ts_token_fromFile.getCertificates()[0];

			// compare hash messages from token and from file related to
			// token
			if (!Arrays.equals(hash_from_token, hash_from_file)) {
				System.out.println("TimestampStatus:Failed");
				System.out.println("Hashed message from Timestamp Token and computed from file are not equal.\n\nPossibly file was changed or Timestamp Token is not related to this file.");
			} else {
				try {
					// verify integrity of timestamp token
					SignerInfo[] si3 = ts_token_fromFile.verify();
					if (si3[0] == null) {
						System.out.println("TimestampStatus:Failed");
						System.out.println("The Timestamp Token is malformed or it is not valid any more.");
					} else {
						System.out.println("TimestampStatus:OK");
						System.out.println("Timestamp: " + tst_inf.getDate()+ "\n");
						System.out.println("Timestamp serial number: "+ tst_inf.getSerialNumber().toString() + "\n");
						System.out.println("Hash algorithm: "+ tst_inf.getHashAlgorithm().getName() + "\n");
						System.out.println("Issuer Name: "+ si3[0].getIssuerName().toString());
					}
				} catch (SignatureException ex) {
					System.err.println("Caught Signature exception "+ ex.toString());
				}
			}

		} catch (NoSuchAlgorithmException ex) {
			System.err.println("TimestampStatus:Failed");
			System.err.println("Wrong Timestamp Token file. \n\nSelected file is not Timestamp Token or you should use another program to read this file.");
		}
		catch (IOException ex){
			System.err.println("TimestampStatus:Failed");
			System.err.println("Wrong Timestamp Token file. \n\nSelected file is not Timestamp Token or you should use another program to read this file.");
		}

	}

	public static byte[] loadBytes(String name) {
		FileInputStream in = null;

		try {
			in = new FileInputStream(name);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1)
				buffer.write(ch);
			return buffer.toByteArray();
		} catch (IOException e) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e2) {
				}
			}
			return null;
		}
	}

	public byte[] computeDigest2(byte[] b) throws NoSuchAlgorithmException {

		currentAlgorithm.reset();
		currentAlgorithm.update(b);
		byte[] hash = currentAlgorithm.digest();

		return hash;
	}

	public void setAlgorithm(String alg) {
		try {
			currentAlgorithm = MessageDigest.getInstance(alg);

		} catch (NoSuchAlgorithmException e) {

		}
	}
}