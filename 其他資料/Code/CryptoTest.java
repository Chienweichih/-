import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import javax.xml.bind.DatatypeConverter;

public class CryptoTest
{
	static void messageDigestExmple(String input) throws Exception
	{
		//!! throw UnsupportedEncodingException
		byte[] plainText = input.getBytes("UTF8");
		
		//!! throw NoSuchAlgorithmException
		//!! import java.security.MessageDigest;
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		
		System.out.println(messageDigest.getProvider().getInfo());
		
		messageDigest.update(plainText);
		System.out.println("Digest: ");
		
		System.out.println(DatatypeConverter.printBase64Binary(messageDigest.digest()));
		//DatatypeConverter.parseBase64Binary
	}
	
	static void messageAuthenticationCodeExample(String input) throws Exception
	{
		byte[] plainText = input.getBytes("UTF8");
		//
		// get a key for the HmacMD5 algorithm
		System.out.println( "\nStart generating key" );
		//!! import javax.crypto.KeyGenerator;
		//!! throw NoSuchAlgorithmException
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
		//!! import javax.crypto.SecretKey;
		SecretKey MD5key = keyGen.generateKey();
		System.out.println( "Finish generating key" );
		//
		// get a MAC object and update it with the plaintext
		//!! throw NoSuchAlgorithmException
		//!! import javax.crypto.Mac;
		Mac mac = Mac.getInstance("HmacMD5");
		//throw InvalidKeyException
		mac.init(MD5key);
		mac.update(plainText);
		//
		// print out the provider used and the MAC
		System.out.println( "\n" + mac.getProvider().getInfo() );
		System.out.println( "\nMAC: " );
		//!! throw UnsupportedEncodingException
		System.out.println( DatatypeConverter.printBase64Binary( mac.doFinal() ) );
	}

	static void privateExample(String input) throws Exception
	{
		byte[] plainText = input.getBytes("UTF8");
		//
		// get a DES private key
		System.out.println( "\nStart generating DES key" );
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		//!! import java.security.Key;
		Key key = keyGen.generateKey();
		System.out.println( "Finish generating DES key" );
		//
		// get a DES cipher object and print the provider
		//!! import javax.crypto.Cipher;
		//!! throw NoSuchPaddingException
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		System.out.println( "\n" + cipher.getProvider().getInfo() );
		//
		// encrypt using the key and the plaintext
		System.out.println( "\nStart encryption" );
		cipher.init(Cipher.ENCRYPT_MODE, key);
		//!! throw BadPaddingException
		byte[] cipherText = cipher.doFinal(plainText);
		System.out.println( "Finish encryption: " );
		//!! throw UnsupportedEncodingException
		System.out.println( DatatypeConverter.printBase64Binary(cipherText) );
		//
		// decrypt the ciphertext using the same key
		System.out.println( "\nStart decryption" );
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] newPlainText = cipher.doFinal(cipherText);
		System.out.println( "Finish decryption: " );
		System.out.println( DatatypeConverter.printBase64Binary(newPlainText) );
	}
	
	static void PublicExample(String input) throws Exception
	{
		byte[] plainText = input.getBytes("UTF8");
		//
		// generate an RSA key
		System.out.println( "\nStart generating RSA key" );
		//!!import java.security.KeyPairGenerator;
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		//!!import java.security.KeyPair;
		KeyPair key = keyGen.generateKeyPair();
		System.out.println( "Finish generating RSA key" );
		//
		// get an RSA cipher object and print the provider
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		System.out.println( "\n" + cipher.getProvider().getInfo() );
		//
		// encrypt the plaintext using the public key
		System.out.println( "\nStart encryption" );
		cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
		byte[] cipherText = cipher.doFinal(plainText);
		System.out.println( "Finish encryption: " );
		System.out.println( DatatypeConverter.printBase64Binary(cipherText) );
		//
		// decrypt the ciphertext using the private key
		System.out.println( "\nStart decryption" );
		cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
		byte[] newPlainText = cipher.doFinal(cipherText);
		System.out.println( "Finish decryption: " );
		System.out.println( DatatypeConverter.printBase64Binary(newPlainText) );

	}
	
	static void DigitalSignature1Example(String input) throws Exception
	{
		byte[] plainText = input.getBytes("UTF8");
		//
		// get an MD5 message digest object and compute the plaintext digest
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		System.out.println( "\n" + messageDigest.getProvider().getInfo() );
		messageDigest.update( plainText );
		byte[] md = messageDigest.digest();
		System.out.println( "\nDigest: " );
		System.out.println( DatatypeConverter.printBase64Binary(md) );
		//
		// generate an RSA keypair
		System.out.println( "\nStart generating RSA key" );
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();
		System.out.println( "Finish generating RSA key" );
		//
		// get an RSA cipher and list the provider
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		System.out.println( "\n" + cipher.getProvider().getInfo() );
		//
		// encrypt the message digest with the RSA private key
		// to create the signature
		System.out.println( "\nStart encryption" );
		cipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());
		byte[] cipherText = cipher.doFinal(md);
		System.out.println( "Finish encryption: " );
		System.out.println( DatatypeConverter.printBase64Binary(cipherText) );
		//
		// to verify, start by decrypting the signature with the
		// RSA private key
		System.out.println( "\nStart decryption" );
		cipher.init(Cipher.DECRYPT_MODE, key.getPublic());
		byte[] newMD = cipher.doFinal(cipherText);
		System.out.println( "Finish decryption: " );
		System.out.println( DatatypeConverter.printBase64Binary(newMD) );
		//
		// then, recreate the message digest from the plaintext
		// to simulate what a recipient must do
		System.out.println( "\nStart signature verification" );
		messageDigest.reset();
		messageDigest.update(plainText);
		byte[] oldMD = messageDigest.digest();
		//
		// verify that the two message digests match
		int len = newMD.length;
		if (len > oldMD.length) {
			System.out.println( "Signature failed, length error");
			System.exit(1);
		}
		for (int i = 0; i < len; ++i)
			if (oldMD[i] != newMD[i]) {
				System.out.println( "Signature failed, element error" );
				System.exit(1);
			}
		System.out.println( "Signature verified" );

	}
	
	public static void main(String[] args) throws Exception
	{
		if (args.length !=1)
		{
			System.err.println("Usage: java MessageDigestExample text");
			System.exit(1);
		}
		
		messageDigestExmple(args[0]);
		messageAuthenticationCodeExample(args[0]);
		
		privateExample(args[0]);
		PublicExample(args[0]);
		
		DigitalSignature1Example(args[0]);
	}
}
