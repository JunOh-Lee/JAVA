package lotto; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 

public class LottoMain { 
	// developed by commin 
	
	// Lotto Program 
	// ver 1.0.0 
	// 2016.11.21  
	
	// 전역변수선언 
	private int command = 0; 
	private BufferedReader in; 
	private LottoPro program = null; 
	
	//CODE 값 
	public final int RECOMMANDATION_NUMBER=1; 
	public final int PRINT_NUMBER=2; 
	public final int EXIT=3; 
	
	public LottoMain() { 
		// main 함수에서 LottoMain의 인스턴스를 호출하였으므로 제일 먼저 생성자인 이곳이 실행되게됩니다. 
		
		// 생성자에서 필요한 변수를 초기화 합니다. 
		in = new BufferedReader(new InputStreamReader(System.in)); 
		
		//주석을 순서대로 풀면서 결과를 확인 해 봅시다. 
		program = new LottoPro(true);
		// program = new LottoPro(false);
		// program = new LottoPro(); 
		
		start();// 메인함수 내에서 실행되는것이 아닌, 메인함수가 생성한 자기자신의 인스턴스에 의해 실행되므로, static 메서드가 아닌 start를 실행 할 수있습니다. 
		} 
	
	public void start() { 
		// 이렇게 프로그램을 동작시킬 메서드를 하나 만들어서 관리하는것이 편합니다. 
		System.out.println("로또 프로그램이 시작됩니다."); 
		for (int i = 1; i < 11; i++) { 
			System.out.println(i * 10 + "%"); 
			if (i == 10) 
				System.out.println("환영합니다!!"); 
			} 
		System.out.println(); 
		do { 
			System.out.println("-----------MENU----------"); 
			System.out.println("1.추첨 2.보기 3.끝내기"); 
			System.out.println("-------------------------"); 
			System.out.print(">> "); 
			try { 
				this.command = Integer.parseInt(in.readLine()); 
				} 
			catch (IOException e) { 
				System.out.println("(System)IOException !! try again.."); 
				continue; 
				} 
			switch(this.command){ 
				case RECOMMANDATION_NUMBER: 
					program.start_recommandation(); 
					break; 
				case PRINT_NUMBER: 
					program.printNumber(); 
					break; 
				case EXIT: 
					System.out.println("(System)bye!"); 
					return; 
				} 
			} while (true); 
		} 
	public static void main(String[] ar) {
		new LottoMain();// 객체가 딱히 필요없을때는 인스턴스만 만들어줍니다. 
		}
	}
