import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class des{
	
	public static SecretKey key;
	
	public static void main(String[] args){
		byte[] k = {(byte)0x01, (byte)0x23, (byte) 0x45,
				(byte)0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
		//키값
		
		key = new SecretKeySpec(k, 0, k.length, "DES");
		
		String plainText = 
			"Now is the time for";
		// 평문
		
	
		try {
			System.out.println("plain Text : " + plainText);
			String cipherText = DES_encrypt(plainText);
			System.out.println("encrypted Data Text : " + cipherText);
			String decryptText = DES_decrypt(cipherText);
			System.out.println("decrypted Data : " + decryptText);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String DES_encrypt(String data) throws Exception{
		if (data == null || data.length() == 0)
			return "";
		
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] plainToByte = data.getBytes();
		
		byte[] encryptedByte; 
		
		//제로패딩 후 암호화된 바이트 값을 16진수로 출력
		encryptedByte = cipher.doFinal(zeroPadding(plainToByte));	//제로패딩된 평문을 암호화하여 Byte배열에 저장
		System.out.println("Zero padding : " + byteArrayToHex(encryptedByte));
		
		encryptedByte = cipher.doFinal(AnsiPadding(plainToByte));	//ANSI X9.23기법 패딩 후 암호화한다.
		System.out.println("ANSI X9.23 : " + byteArrayToHex(encryptedByte));
		
		encryptedByte = cipher.doFinal(PkcsPadding(plainToByte));	//PKCS5기법 패딩 후 암호화한다.
		System.out.println("PKCS 5 : " + byteArrayToHex(encryptedByte));
	
	
		byte[] encryptedBase64 = java.util.Base64.getEncoder().encode(encryptedByte);
		
		return new String(encryptedBase64);
	}
	
	public static String DES_decrypt(String data) throws Exception{
		if (data == null || data.length() == 0)
			return "";
		
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DES/ECB/NoPadding");
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
		
		byte[] decodeBase64 = java.util.Base64.getDecoder().decode(data);
		byte[] decryptedByte = cipher.doFinal(decodeBase64);
		String byteToPlain = new String(decryptedByte);
		
		return byteToPlain;
	}

	public static byte[] zeroPadding(byte[] plainToByte) throws Exception{	//제로 패딩 함수
		int cnt = 8 - (plainToByte.length % 8); //부족한 공간 = 8 - 나머지 바이트
		byte[] newPlainToByte = new byte[plainToByte.length + cnt];	//새로운 평문 생성
		System.arraycopy(plainToByte, 0, newPlainToByte, 0, plainToByte.length);	//새로운 평문에 기존 평문 복사
		
		for(int i = plainToByte.length; i < newPlainToByte.length; i++) {	//배열의 나머지를 0으로 초기화
			newPlainToByte[i] = 0x00;
		}

		return newPlainToByte;
		
	}
	public static byte[] AnsiPadding(byte[] plainToByte) throws Exception{	//제로 패딩 함수
		int cnt = 8 - (plainToByte.length % 8); //부족한 공간 = 8 - 나머지 바이트
		byte[] newPlainToByte = new byte[plainToByte.length + cnt];	//새로운 평문 생성
		
		System.arraycopy(plainToByte, 0, newPlainToByte, 0, plainToByte.length);	//새로운 평문에 기존 평문 복사
		
		for(int i = plainToByte.length; i < newPlainToByte.length - 1; i++) {	//배열의 나머지를 0으로 초기화, i값을 newPlainToByte.length - 1까지 해서 마지막 인덱스값 비워둔다 
			newPlainToByte[i] = 0x00;
		}
		newPlainToByte[newPlainToByte.length -1] = (byte)cnt;	//마지막 바이트로 padding크기를 나타냄

		return newPlainToByte;
		
	}
	public static byte[] PkcsPadding(byte[] plainToByte) throws Exception{	//제로 패딩 함수
		int cnt = 8 - (plainToByte.length % 8); //부족한 공간 = 8 - 나머지 바이트
		byte[] newPlainToByte = new byte[plainToByte.length + cnt];	//새로운 평문 생성
		
		System.arraycopy(plainToByte, 0, newPlainToByte, 0, plainToByte.length);	//새로운 평문에 기존 평문 복사
		
		for(int i = plainToByte.length; i < newPlainToByte.length; i++) {	//배열의 나머지를 0으로 초기화, i값을 newPlainToByte.length - 1까지 해서 마지막 인덱스값 비워둔다 
			newPlainToByte[i] = (byte)cnt;
		}

		return newPlainToByte;
		
	}
	public static String byteArrayToHex(byte[] a) {	//byte배열을 16진수 문자열로 전환하는 함수
	    StringBuilder sb = new StringBuilder(); //String객체 끼리 더할 때 새로운 객체를 생성하지 않고 기존의 데이터에 더하는 방식을 쓰는 class
	    
	    for(final byte b: a)
	        sb.append(String.format("%02X ", b&0xff));	//16진수의 문자열 형식으로 저장.
	    return sb.toString();	//String값으로 리턴
	}

}
