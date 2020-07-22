package yddb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.*;

public class DBClass {
	
	
	

	//DB연결
	public static Connection connTest() throws Exception {
		

        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/dbdb";
        conn = DriverManager.getConnection(url, "root", "1234");
        System.out.println("연결 성공");

        return conn;
	}
	
	
	// 데이터 입력(사용자 정보)
		public static void user_insert(UserDTO user) {
			
			Connection conn = null; // DB연결 
	        PreparedStatement pstmt = null;
	        
	        try{
	        	conn = connTest();
	            String sql = "INSERT INTO user_tb (name, score) VALUES (?,?)";
	            pstmt = conn.prepareStatement(sql);

	            // 4. 데이터 binding
	            pstmt.setString(1, user.getName());
	            pstmt.setInt(2, user.getScore());
	            
	            int count = pstmt.executeUpdate();
	            if( count == 0 ){
	                System.out.println("사용자 입력 실패");
	            }
	            else{
	                System.out.println("사용자 입력 성공");
	            }
	        

	        }
	        
	        catch(Exception e){
	            System.out.println("데이터 입력 에러2: " + e);
	        }
	        finally{
	            try{
	                if( conn != null && !conn.isClosed()){
	                    conn.close();
	                	}
	                if( pstmt != null && !pstmt.isClosed()){
	                        pstmt.close();
	                    }        
	            	}
	            catch( SQLException e){
	                e.printStackTrace();
	            		}
	        	}
			
		}
	

	// 데이터 입력(숫자2개)
	public static void num_insert(int num1 ,int num2) {
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        
        try{
        	conn = connTest();// DB연결 
            String sql = "INSERT INTO num_tb (num1, num2) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);

            // 4. 데이터 binding
            pstmt.setInt(1, num1);
            pstmt.setInt(2, num2);
            
            int count = pstmt.executeUpdate();
            if( count == 0 ){
                System.out.println("데이터 입력 실패");
            }
            else{
                System.out.println("데이터 입력 성공");
            }
        

        }
        
        catch(Exception e){
            System.out.println("데이터 입력 에러3: " + e);
        }
        finally{
            try{
                if( conn != null && !conn.isClosed()){
                    conn.close();
                	}
                if( pstmt != null && !pstmt.isClosed()){
                        pstmt.close();
                    }        
            	}
            catch( SQLException e){
                e.printStackTrace();
            		}
        	}
		
	}
	
	
	
	// 데이터 불러오기(저장된 숫자2개)
		public static ArrayList<NumDTO> select() {
			ArrayList<NumDTO> numdtolist = new ArrayList<NumDTO>();
			
			
			Connection conn = null; 
	        Statement stmt = null;
	        ResultSet rs = null;
	        
	        try {
	        	conn = connTest();
	        	String sql = "SELECT * FROM num_tb;";
	        	stmt = conn.createStatement();
	        	rs = stmt.executeQuery(sql);

	            
	            while(rs.next()){
	            	NumDTO numdto = new NumDTO();
	            	numdto.setNum1(rs.getInt(1)); 
	            	numdto.setNum2(rs.getInt(2));
	            	numdtolist.add(numdto);
	            }
	        }
	        catch(Exception e){
	            System.out.println("에러: " + e);
	        }
	        finally{
	            try{
	            	if( conn != null && !conn.isClosed()){
	                    conn.close();
	            	}
	            	if( stmt != null && !stmt.isClosed()){
	                      stmt.close();
	                }
	            	if( rs != null && !rs.isClosed()){
	                      rs.close();
	                  }           
	            	}
	            catch( SQLException e){
	                e.printStackTrace();
	            	}
	        }
	
	        return numdtolist;
	
	
		}
	
		// 데이터 불러오기(사용자 정보)
				public static ArrayList<UserDTO> rank() {
					ArrayList<UserDTO> ranklist = new ArrayList<UserDTO>();
					
					
					Connection conn = null; 
			        Statement stmt = null;
			        ResultSet rs = null;
			        
			        try {
			        	conn = connTest();
			        	String sql = "SELECT * FROM user_tb;";
			        	stmt = conn.createStatement();
			        	rs = stmt.executeQuery(sql);

			            
			            while(rs.next()){
			            	UserDTO userdto = new UserDTO();
			            	userdto.setName(rs.getString(1)); 
			            	userdto.setScore(rs.getInt(2));
			            	ranklist.add(userdto);
			            }
			        }
			        catch(Exception e){
			            System.out.println("에러: " + e);
			        }
			        finally{
			            try{
			            	if( conn != null && !conn.isClosed()){
			                    conn.close();
			            	}
			            	if( stmt != null && !stmt.isClosed()){
			                      stmt.close();
			                }
			            	if( rs != null && !rs.isClosed()){
			                      rs.close();
			                  }           
			            	}
			            catch( SQLException e){
			                e.printStackTrace();
			            	}
			        }
			
			        return ranklist;
			
				}
}
