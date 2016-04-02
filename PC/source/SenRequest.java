import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Random;

import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

public class SenRequest
{
	private MessageDigest currentAlgorithm;
	private String dataFilePath;
	private byte[] hash_;
	private String algorithm_;
	private TSResponse response;
	private byte[] tokenBajty;
	private PKCS7 tokenNormal;
	private X509Certificate ca;
	
	private String MessageStatus;
	
	public SenRequest(String RdataFilePath)
	{
		algorithm_ = "MD5";
		setAlgorithm(algorithm_);
		dataFilePath = RdataFilePath;
	}

	public byte[] SendingRequest()
	{// GEN-FIRST:event_SendRequestActionPerformed
		String url = "http://time.certum.pl";
		if (!url.startsWith("http://"))
			url = "http://" + url;
		if (exists(url))
		{
			try
			{
				MessageStatus = "";
				
				hash_ = dataFilePath.getBytes();
				setAlgorithm(algorithm_);
				hash_ = computeDigest2(hash_);
				
				HttpTimestamper timestamper = new HttpTimestamper(url);
				TSRequest query = new TSRequest(hash_, algorithm_);
				query.requestCertificate(true);
				query.setNonce(new BigInteger(32, new Random()));
				query.setVersion(1);
				response = timestamper.generateTimestamp(query);

				if (response.getStatusCode() != 0)
					MessageStatus = MessageStatus.concat(response.getFailureCodeAsText());
				else
				{
					tokenNormal = response.getToken();
					tokenBajty = response.getEncodedToken();
					TimestampToken tst_inf = new TimestampToken(tokenNormal.getContentInfo().getData());
					MessageStatus = MessageStatus.concat(response.getStatusCodeAsText() + "\n");
					MessageStatus = MessageStatus.concat(tst_inf.getDate().toString() + "\n");
					MessageStatus = MessageStatus.concat("Timestamp serial number: " + tst_inf.getSerialNumber().toString() + "\n");
					MessageStatus = MessageStatus.concat("Hash algorithm: " + tst_inf.getHashAlgorithm().getName() + "\n");
					SignerInfo[] si = tokenNormal.getSignerInfos();
					if (si != null)
					{
						MessageStatus = MessageStatus.concat("Issuer Name: " + si[0].getIssuerName().toString());
						PKCS7 ts_token_fromBytes = new PKCS7(tokenBajty);
						ca = ts_token_fromBytes.getCertificates()[0];
					}					
					
					System.out.println(MessageStatus);
					//saveTokenToFile(dataFilePath + ".tst",tokenBajty);
					return tokenBajty;
				}
			}
			catch (NoSuchAlgorithmException ex)
			{
				System.err.println("TimestampStatus:Failed");
				System.err.println("Wrong Timestamp Token file. \n\nSelected file is not Timestamp Token or you should use another program to read this file.");
			}
			catch (IOException ex)
			{
				System.err.println("TimestampStatus:Failed");
				System.err.println("Wrong Timestamp Token file. \n\nSelected file is not Timestamp Token or you should use another program to read this file.");
			}
		} else
			System.err.println("URL does not exsist.");
		return null;
	}// GEN-LAST:event_SendRequestActionPerformed

	public static boolean exists(String URLName)
	{
		try
		{
			HttpURLConnection.setFollowRedirects(false);
			// note : you may also need
			// HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection con = (HttpURLConnection) new URL(URLName)
					.openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void saveTokenToFile(String path, byte[] token)
	{
	    try
	    {
	        FileOutputStream fos = new FileOutputStream(path);
	        fos.write(token);
	        fos.close();
	    }
	    catch(FileNotFoundException ex)
	    {
	        System.out.println("FileNotFoundException : " + ex);
	    }
	    catch(IOException ioe)
	    {
	        System.out.println("IOException : " + ioe);
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