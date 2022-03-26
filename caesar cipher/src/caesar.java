import java.util.Scanner;

public class caesar {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String plain = "";
		int key;
		
		System.out.print("평문을 입력해주세요: ");
		plain = scan.nextLine();
		System.out.print("키를 입력해주세요: ");
		key = scan.nextInt();
		
		
		
		System.out.println("암호문 : " + encryption(plain, key));
		
		
	}
	public static String encryption(String p, int k) {
		String cipher = "";
		
		for(int i = 0; i<p.length(); i++) {
			
			if(p.charAt(i) >= 97 && p.charAt(i) <= 122) {//소문자일 경우
				if(p.charAt(i) + k > 122)	//숫자 값을 넘긴 경우
					cipher += (char)(p.charAt(i) - 26 - 32 + k );
				// -26을 해서 A부터 시작하게 한 후 - 32를 해서 대문자화 한 후 key값을 더한다 
				else
					cipher += (char)(p.charAt(i) - 32 + k );
				//아닌 경우 대문자화 후 key값을 더한다
				
			}
			else if(p.charAt(i) >= 65 && p.charAt(i) <= 90) {//대문자인 경우
				if(p.charAt(i) + k > 90)	//숫자 값을 넘긴 경우
					cipher += (char)(p.charAt(i) - 26 + k );
				// -26으로 대문자화 후 key값 더하기
				else
					cipher += (char)(p.charAt(i) + k );
				// 아닌 경우 키값만 더하기
			}
			else {//영 이외의 문자인 경우 그대로 출
				cipher += (char)(p.charAt(i));
			}
			
		}
		return cipher;
	}

}
