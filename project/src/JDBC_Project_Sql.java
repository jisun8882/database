import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

public class JDBC_Project_Sql extends JDBC_Project{
	
	public JDBC_Project_Sql() throws SQLException {
		super();
	}

	private static Connection dbTest;
	static int i = 0;
	public static void connectDB(){
		try {
	      // JDBC Driver Loading
	      Class.forName("oracle.jdbc.OracleDriver");
	      dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "system", "system");
	      random();
	      dropT();
	      createT_consumer();
	      } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("SQLException:"+e);
	      } catch (Exception e) {
	      System.out.println("Exception : "+e);
	      }
	}
	
	private static String sqlStr = null ;
	private static Statement stmt = null;
	private static String[] array = null;
	
	public static void dropT() throws SQLException{
		stmt = dbTest.createStatement();
		try{
			sqlStr= "drop table consumer";
	        stmt.executeUpdate(sqlStr);
	        sqlStr= "drop table staff";
	        stmt.executeUpdate(sqlStr);
	        sqlStr= "drop table menu";
	        stmt.executeUpdate(sqlStr);
	        sqlStr= "drop table m_order";
	        stmt.executeUpdate(sqlStr);
	        sqlStr= "drop table pay";
	        stmt.executeUpdate(sqlStr);
	        sqlStr= "drop table menucount";
	        stmt.executeUpdate(sqlStr);
		}
		catch(SQLException e){
		}
	}
	public static void createT_consumer() throws SQLException, IOException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr= "CREATE TABLE consumer (c_name varchar2(30),c_id int,c_birth int,c_phone varchar2(10),c_level varchar2(10),c_total int,primary key(c_name))";
	         stmt.executeUpdate(sqlStr);
	         createT_staff();
	      }catch(SQLException e){
	         createT_staff();
	      }
	   }
	   
	   public static void createT_staff() throws SQLException, IOException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr = "CREATE TABLE staff (s_name varchar2(30),s_id int,s_level varchar2(10),s_total int, primary key(s_name))";
	         stmt.executeUpdate(sqlStr);
	         createT_menu();
	      }catch(SQLException e){
	         createT_menu();
	      }
	   }
	   
	   public static void createT_menu() throws SQLException, IOException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr = "CREATE TABLE menu (m_name varchar2(50), m_id int, m_price int, primary key(m_name))";
	         stmt.executeUpdate(sqlStr);
	         createT_order();
	      }catch(SQLException e){
	         createT_order();
	      }
	   }
	   
		public static void createT_order() throws SQLException {
		      stmt = dbTest.createStatement();
		      try{
		         sqlStr= "CREATE TABLE m_order (t_num int ,c_name varchar2(10), m_name varchar2(50),m_price int,dt varchar2(10))";
		         stmt.executeUpdate(sqlStr);
		         createT_pay();
		      }catch(SQLException e){
		         createT_pay();
		      }
		   }
		
		public static void createT_pay() throws SQLException {
		      stmt = dbTest.createStatement();
		      try{
		         sqlStr= "CREATE TABLE pay (t_num int ,c_name varchar2(30),s_name varchar2(30),tot_price int,dt varchar2(10))";
		         stmt.executeUpdate(sqlStr);
		         createT_menucount();
		      }catch(SQLException e){
		         createT_menucount();
		      }
		   }
		
		public static void createT_menucount() throws SQLException {
		      stmt = dbTest.createStatement();
		      try{
		         sqlStr= "CREATE TABLE menucount (m_name varchar2(50),m_count int,primary key(m_name))";
		         stmt.executeUpdate(sqlStr);
		         createT_menucount();
		      }catch(SQLException e){
		      }
		   }
	
	   static ArrayList<Integer> r_int = new ArrayList<Integer>();
	   
	   public static void random() {
		    for(int i = 1000; i<9999; i++){
		    	r_int.add(i);
		    }
		    Collections.shuffle(r_int);
		 }
	   
	public static void insertConsumer(String data1,int data2,int data3,String data4) throws SQLException, IOException{
	  	  int data5 = 0;
	  	  if(data4.equals("Gold"))
	  	  {
	  		  data5 = 1000000;
	  	  }
	  	  else if(data4.equals("Silver"))
	  	  {
	  		  data5 = 500000;
	  	  }
	  	  else if(data4.equals("Bronze"))
	  	  {
	  		  data5 = 300000;
	  	  }
	  	  

	  	  stmt = dbTest.createStatement();
	      try{
	         sqlStr = "insert into consumer values ('"+ data1+"'," + r_int.get(i) + ","+data2+","+data3+",'"+data4+"', "+data5+")";
	         stmt.executeUpdate(sqlStr);
	         i++;
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	   }
	
	public static void insertStaff(String data1,String data2) throws SQLException, IOException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr = "insert into staff values ('" + data1+"',"+r_int.get(i)+",'"+data2+"',0 )";
	         stmt.executeUpdate(sqlStr);
	         i++;
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	   }
	
	public static void insertMenu(String data1,int data2) throws SQLException, IOException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr = "insert into menu values ('" + data1+"',"+r_int.get(i)+","+ data2 +")";
	         stmt.executeUpdate(sqlStr);
	         i++;
	         insertMenucount(data1);
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	}
	
	public static void insertOrder(int data1,String data2, String data3, int data4) throws SQLException {
	      stmt = dbTest.createStatement();
	      try{
	    	  Date date = new Date();
	    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	  String dt = formatter.format(date);
	         sqlStr = "insert into m_order values (" + data1+", '"+ data2 + "' , '"+ data3 + "',"+data4+",'"+dt+"')";
	         stmt.executeUpdate(sqlStr);
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	}
	
	public static void insertMenucount(String data1) throws SQLException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr = "insert into menucount values ('" + data1+"', 0)";
	         stmt.executeUpdate(sqlStr);
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	}
	
	public static void del_order(int data) throws SQLException {
	      try{
		         sqlStr = "DELETE from m_order WHERE t_num = " +data;
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         stmt.executeUpdate(sqlStr);
		         stmt.close();
		    	 JOptionPane.showMessageDialog(null,"주문이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		      }catch(SQLException e){
		    	  e.printStackTrace();
		      }
	   }
	public static void insertpay(int data1,String data2, String data3, int tot_price) throws SQLException {
	      stmt = dbTest.createStatement();
	      try{
	    	  Date date = new Date();
	    	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	  String dt = formatter.format(date);
	         sqlStr = "insert into pay values (" + data1+", '"+ data2 + "' , '"+ data3 + "',"+tot_price+",'"+dt+"')";
	         stmt.executeUpdate(sqlStr);
	         stmt.close();
	         
		        sqlStr = "select m_name from m_order where t_num = "+data1;
		        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		        ResultSet rs = stmt.executeQuery();
		        while(rs.next())
		        {
		        	String sqlStr1 = "UPDATE menucount SET m_count = m_count + 1 WHERE m_name = '"+rs.getString("m_name")+"'";
			        PreparedStatement stmt1 = dbTest.prepareStatement(sqlStr1);
			        stmt1.executeUpdate();
			        stmt1.close();
		        }
		        
		        rs.close();
		        stmt.close();
		        
		        sqlStr = "DELETE from m_order WHERE t_num = " +data1;
		        stmt = dbTest.prepareStatement(sqlStr);
		        stmt.executeUpdate(sqlStr);
		        stmt.close();
		        
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	}
	
	public static void insertPath(String path) throws IOException, SQLException{
	      try{
	         FileInputStream fis = new FileInputStream(path);
	         Scanner s = new Scanner(fis);
	         while(s.hasNext()){
	            String c = s.nextLine();
	               for(int i = 0; i<6;i++){
	           	      c= s.nextLine();
	                  array = c.split("\t");
	                  
	                  int array1_int = Integer.parseInt(array[1]);
	                  int array2_int = Integer.parseInt(array[2]);
	                  
	                  insertConsumer(array[0],array1_int, array2_int, array[3]);
	               }
	            c = s.nextLine();
	               for(int i = 0; i<6;i++){
	                  c= s.nextLine();
	                  array = c.split("\t");
	                  insertStaff(array[0],array[1]);
	               }
	            c = s.nextLine();
	               for(int i = 0; i<10;i++){
	                  c= s.nextLine();
	                  array = c.split("\t");
	                  int array1_int = Integer.parseInt(array[1]);
	                  insertMenu(array[0],array1_int);
	               }
	         }
	         s.close();
	         fis.close();
	      }
	      catch(IOException e){
	         e.printStackTrace();
	      }
	      catch(SQLException e)
	      {
	    	  e.printStackTrace();
	      }
	   }
	

	public static String menu[] = new String[20];
	public static void ar_menu(){
		for(int i = 0; i< menu.length;i++){
			menu[i] = "";
		}
	}
	
	public static void get_menu() throws SQLException{
		ar_menu();
		stmt = dbTest.createStatement();
		try
		{
			sqlStr = "select m_name from menu";
			PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
	        ResultSet rs = stmt.executeQuery();
	        
	        int num = 0;
	        if(num == 10)
	        {
	        	JOptionPane.showMessageDialog(null,"메뉴 등록 개수를 초과하였습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	        }
	        else
	        {
		        while(rs.next()){
		        	menu[num] = rs.getString("m_name");
		        	num++;
				}
		        
		        rs.close();
		        stmt.close();
			
		        }
			}
		catch (SQLException e){
		}
	}
	
	public static void input_menu(String data1,int data2) throws SQLException{
	     
		try{
	         sqlStr = "insert into menu values ('" + data1+"',"+r_int.get(i)+","+ data2 +")";
	         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
	         stmt.executeUpdate(sqlStr);
	         i++;
	         stmt.close();
	      }catch(SQLException e){
	    	  e.printStackTrace();
	    	  JOptionPane.showMessageDialog(null,"메뉴가 이미 등록되어있습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	      }
	}
	
	public static void input_consumer(String data1,int data2,int data3) throws SQLException{
	      try{
		         sqlStr = "insert into consumer values ('"+ data1+"'," + r_int.get(i) + ","+data2+","+data3+",'Normal', 0)";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         stmt.executeUpdate(sqlStr);
		         i++;
		         stmt.close();
		      }catch(SQLException e){
		    	  e.printStackTrace();
		    	  JOptionPane.showMessageDialog(null,"고객이 이미 등록되어있습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		      }
	}
	
	public static void input_staff(String data1,String data2) throws SQLException{
	      try{
		         sqlStr = "insert into staff values ('" + data1+"',"+r_int.get(i)+",'"+data2+"', 0)";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         stmt.executeUpdate(sqlStr);
		         i++;
		         stmt.close();
		      }catch(SQLException e){
		    	  e.printStackTrace();
		    	  JOptionPane.showMessageDialog(null,"직원이 이미 등록되어있습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		      }
	}
	
	public static void check_consumer(String data) throws SQLException{
		  try{
			     
		         sqlStr = "select * from consumer where c_name = '"+data+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		         rs.next();
				 String specification = "고  객  명 : "+rs.getString("C_NAME") + "\n"+ "고객 ID : "+rs.getInt("C_ID")+"\n"
					+"생  일  : "+rs.getInt("C_BIRTH")+"\n"+"전화번호 : "+rs.getInt("C_PHONE")+"\n"+"고객등급 : "+rs.getString("C_LEVEL")+"\n"
					+"총구매금액 : "+rs.getInt("C_TOTAL");
		         rs.close();
		         stmt.close();
		        
		        check_area_b.setText(specification);
		        
		      }catch(SQLException e){
		    	  JOptionPane.showMessageDialog(null,"등록된 고객이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		      }
	}
	
	public static boolean check_c(String data) throws SQLException{
		  try{
		         sqlStr = "select * from consumer where c_name = '"+data+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		         rs.next();
		         String chk = rs.getString("c_name");	         
		 		 rs.close();
		         stmt.close();
		        
		        return true;
		      }catch(SQLException e){
		    	  return false;
		      }

	}
	
	public static String check_level(String data) throws SQLException{
		String level = "";
		  try{
		         sqlStr = "select c_level from consumer where c_name = '"+data+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		         rs.next();
		         level = rs.getString("c_level");
		 		rs.close();
		        stmt.close();
		      }catch(SQLException e){
		    	  e.printStackTrace();
		      }
		  return level;
	}
	
	public static void Updateconsumer(int tot_price,String data2) throws SQLException, IOException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr = "UPDATE consumer SET c_total = c_total +" + tot_price+ " WHERE c_name = '"+data2+"'";
	         stmt.executeUpdate(sqlStr);
	         stmt.close();
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	}
	
	public static void Updatestaff(int tot_price,String data2) throws SQLException, IOException{
	      stmt = dbTest.createStatement();
	      try{
	         sqlStr = "UPDATE staff SET s_total = s_total +" + tot_price+ " WHERE s_name = '"+data2+"'";
	         stmt.executeUpdate(sqlStr);
	         stmt.close();
	      }catch(SQLException e){
	    	  e.printStackTrace();
	      }
	}
	
	public static void check_staff(String data) throws SQLException{
		  try{
		         sqlStr = "select * from staff where s_name = '"+data+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		 		 rs.next();
				 String	specification = "직원명 : "+rs.getString("S_NAME") + "\n"+ "직급 : "+rs.getString("S_LEVEL")+"\n"
					+"직원실적  : "+(rs.getInt("S_TOTAL"));
		         
				check_area_d.setText(specification);
		        rs.close();
		        stmt.close();
		        
		      }catch(SQLException e){
		    	  JOptionPane.showMessageDialog(null,"등록된 직원이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		      }
	}
	
	public static void check_menu(String data) throws SQLException{
		  try{
		         sqlStr = "select * from menu where m_name = '"+data+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		 		 rs.next();
		 		 String specification = "메뉴명 : "+rs.getString("M_NAME") + "\n"+ "가격 : "+rs.getInt("M_PRICE");
		         check_area_e.setText(specification);   
		         rs.close();
		         stmt.close();
		        
		      }catch(SQLException e){
		    	  JOptionPane.showMessageDialog(null,"등록된 메뉴가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		      }
	}
	
	static int total = 0;	
	public static String specification_m="";
	
	public static void order_menu(String data) throws SQLException{
		  try{
		         sqlStr = "select * from menu where m_name = '"+data+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();

		         while(rs.next()){
					specification_m += rs.getString("M_NAME") + "\t"+ rs.getInt("M_PRICE") + "\n";
					total += rs.getInt("M_PRICE");
					check_area.setText(specification_m+"\n\n-------------------------------\n총합계 : "+total);
					
		         }
		        
		        rs.close();
		        stmt.close();
		        
		      }catch(SQLException e){
		    	  e.printStackTrace();
		      }
	}
	
	public static void check_table(int data) throws SQLException{
		String specification1 = "";
		int check_total = 0;
		
		  try{
		         sqlStr = "select * from m_order where t_num = '"+data+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();

		         while(rs.next()){
					specification1 += rs.getString("M_NAME") + "\t"+ rs.getInt("M_PRICE") + "\n";
					check_total += rs.getInt("M_PRICE");
					check_area.setText(specification1+"\n\n-------------------------------\n총합계 : "+check_total);
					
		         }
		        
		        rs.close();
		        stmt.close();
		        
		      }catch(SQLException e){
		    	  e.printStackTrace();
		      }
	}

	public static String current_staff = "";
	public static int level_chk = 0;
	
	public static void login_staff(String data1, int data2) throws SQLException{
		  try{
		         sqlStr = "select s_id, s_level from staff where s_name = '"+data1+"'";
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		 		
		         while(rs.next()){
		        	 
		        	 if(data2 != rs.getInt("S_ID"))
		        	 {
		        		 JOptionPane.showMessageDialog(null,"사원번호가 맞지 않습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		        		 break;
		        	 }
		        	 else
		        	 {
		        		 JOptionPane.showMessageDialog(null,data1 +"님 로그인 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		        		 current_staff = data1;
		        		 if(rs.getString("S_LEVEL").equals("Supervisor")){
		        			 level_chk = 1;
		        		 }
		        	 }

		         }
		        
		        rs.close();
		        stmt.close();
		        
		      }catch(SQLException e){
		    	  e.printStackTrace();
		      }
	}
	
	public static int get_total(int data) throws SQLException{
		int g_total = 0;
		  try{
		         sqlStr = "select sum(m_price) from m_order where t_num = "+data;
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		         rs.next();
		         g_total = rs.getInt("SUM(M_PRICE)");
		         rs.close();
			     stmt.close();
		        
		      }catch(SQLException e){
		    	  e.printStackTrace();
		      }
		return g_total;
	}
	
	public static void check_pay(String data) throws SQLException{
		int today_total = 0;
		int total = 0;
		String max_menu = ""; 
		String min_menu = "";
		  try{
			  	sqlStr = "select sum(tot_price) from pay where dt = '"+data+"'";
			  	
		         PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		         ResultSet rs = stmt.executeQuery();
		         while(rs.next()){
					today_total = rs.getInt("SUM(TOT_PRICE)");
		         }
		        rs.close();
		        stmt.close();
			  
			  	sqlStr = "select sum(c_total) from consumer ";
			  	
			  	System.out.println(sqlStr);
		         stmt = dbTest.prepareStatement(sqlStr);
		         rs = stmt.executeQuery();
		         while(rs.next()){
					total = rs.getInt("SUM(C_TOTAL)");
		         }
		        rs.close();
		        stmt.close();
		        
		        String sqlStr1 = "select sum(tot_price) from pay ";
		        PreparedStatement stmt1 = dbTest.prepareStatement(sqlStr1);
		        ResultSet rs1 = stmt1.executeQuery();
		         while(rs1.next()){
					total += rs1.getInt("SUM(TOT_PRICE)");
		         }
		        rs1.close();
		        stmt1.close();
		        
		        System.out.println(total);
		       
			  	sqlStr = "select m_name from menucount where m_count = some(select max(m_count) from menucount )";
		         stmt = dbTest.prepareStatement(sqlStr);
		         rs = stmt.executeQuery();
		         while(rs.next()){
					max_menu += rs.getString("m_name")+"\n";
		         }
		        rs.close();
		        stmt.close();
		        
		        sqlStr = "select m_name from menucount where m_count = some(select min(m_count) from menucount )";
		         stmt = dbTest.prepareStatement(sqlStr);
		         rs = stmt.executeQuery();
		         while(rs.next()){
					min_menu += rs.getString("m_name")+"\n";
		         }
		        rs.close();
		        stmt.close();
		        
		        
		        check_area_c.setText("일 매출 : "+today_total+"\n-------------------------------\n가장 많이 팔린 메뉴: \n"+max_menu+
		        "\n-------------------------------\n가장 적게 팔린 메뉴: \n"+min_menu+"\n\n-------------------------------\n누적매출 : "+total);
		        
		      }catch(SQLException e){
		    	  e.printStackTrace();
		      }
	}
}

