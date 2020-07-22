package yddb;

import java.util.ArrayList;

import dto.UserDTO;

public class MainClass {

	public static void main(String[] args) {
		
		OperClass op = new OperClass();
		ArrayList<UserDTO> ranklist = DBClass.rank();
		
		
		op.start();
		
		System.out.println("<기록>");
		for (UserDTO rank : ranklist) {
			System.out.print(rank.getName() + ": ");
			System.out.println(rank.getScore() +"점");
		}

	}

}
