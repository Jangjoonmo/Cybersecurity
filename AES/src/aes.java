import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class aes{
	
	public static SecretKey key;
	
	public static void main(String[] args){
		byte[] k = {(byte)0xFD, (byte)0xE8, (byte)0xF7, (byte)0xA9, (byte)0xB8, (byte)0x6C
				, (byte)0x3B, (byte)0xFF, (byte)0x07, (byte)0xC0, (byte)0xD3, (byte)0x9D
				, (byte)0x04, (byte)0x60, (byte)0x5E, (byte)0xDD};
		
		key = new SecretKeySpec(k, 0, k.length, "AES");
		
		String plainText = 
			"The Advanced Encryption Standard (AES), "
			+ "also referenced as Rijndael(its original name), "
			+ "established by the U.S. National Institute of Standards and Technology (NIST) in 2001.";
		
		try {
			System.out.println("plain Text : " + plainText);
			String cipherText = AES_encrypt(plainText);
			System.out.println("encrypted Data : " + cipherText);
			String decryptText = AES_decrypt(cipherText);
			System.out.println("decrypted Data : " + decryptText);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static String AES_decrypt(String data) throws Exception{
		if (data == null || data.length() == 0)
			return "";
		
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
		
		byte[] decodeBase64 = java.util.Base64.getDecoder().decode(data);
		byte[] decryptedByte = cipher.doFinal(decodeBase64);
		String byteToPlain = new String(decryptedByte);
		
		return byteToPlain;
	}

	public static String AES_encrypt(String data) throws Exception{
		if (data == null || data.length() == 0)
			return "";
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] plainToByte = data.getBytes();
		byte[] encryptedByte = cipher.doFinal(plainToByte);
		
		byte[] encryptedBase64 = java.util.Base64.getEncoder().encode(encryptedByte);
		
		return new String(encryptedBase64);
	}
	
	
}