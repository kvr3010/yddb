package yddb;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import dto.*;

public class OperClass {
	Scanner sc;
	UserDTO userdto;  //유저정보
	NumDTO numdto; //숫자

	public OperClass() {
		sc = new Scanner(System.in);
		userdto = new UserDTO();
	}
	
	// 1 선택
	
	public void start() {
		game_Nums();
		for(;;) {
			try {
				System.out.println("<메뉴 선택>");
				System.out.print("1.더하기 2.빼기 3.곱하기 4.나누기 0.종료 번호선택 :");
				int select = sc.nextInt();
				if(select ==0) {
					System.out.println("종료합니다.");
					break;
				}
				game_oper(select);
				
			}
			catch (Exception e) {
				System.out.println("번호(정수)를 입력하세요");
				continue;
			}
			
			
		}
	}
	
	// 1.5. 사용자 정보 입력받아서 user_join_insert 메소드로 DB에 저장
	
	public void userJoin() {
		System.out.print("사용자 이름 입력 :");
		userdto.setName(sc.next());
	}
	
	

	//2. 랜덤 숫자 2개씩 10개 생성 하고 num_insert메소드로 디비에 저장
	public void game_Nums() {
			
			for(int i=1; i<11; i++) {
				ArrayList<Integer> numlist = new ArrayList<Integer>();
				Random rand = new Random();
				numlist.add(rand.nextInt(90)+10);
				numlist.add(rand.nextInt(90)+10);
				DBClass.num_insert(numlist.get(0),numlist.get(1));
			}
			
		}
	
	
	//3. select메소드로 DB에서 숫자 불러와서 리스트에 저장
	public void game_savelist() {
		
		
	}
	
	
	// 4. DB에서 불러온 숫자 연산,답비교, 점수 수정
		public void game_oper(int select) {
			int o = 0;
			ArrayList<NumDTO> numdtolist = DBClass.select();
			userJoin();
			 
			System.out.println("<게임 시작>");
			for (int j = 0; j < 10; j ++) {
				NumDTO nums = numdtolist.get(j);	
					int corr = 0;
					if(select == 1) {
						System.out.print(nums.getNum1() + "+" + nums.getNum2() + "=" );
						corr = nums.getNum1() + nums.getNum2();
					}else if(select ==2) {
						System.out.print(nums.getNum1() + "-" + nums.getNum2() + "=" );
						corr = nums.getNum1() + nums.getNum2();
					}else if(select ==3) {
						System.out.print(nums.getNum1() + "*" + nums.getNum2() + "=" );
						corr = nums.getNum1() + nums.getNum2();
					}else if(select ==4) {
						System.out.print(nums.getNum1() + "/" + nums.getNum2() + "=" );
						corr = nums.getNum1() + nums.getNum2();
					}
	
					if(corr == asw()) {
						System.out.println("정답입니다.");
						o++;
					}
					else {
							System.out.println("땡");
					}
			}
			userdto.setScore(o);
			System.out.println(userdto.getName());
			System.out.println(userdto.getScore());
			DBClass.user_insert(userdto);
			}
	


	//사용자 답	입력
	public int asw() {
		int asw = sc.nextInt();
		return asw;
	}
}
	
	
	