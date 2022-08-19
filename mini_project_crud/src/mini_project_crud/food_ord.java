package mini_project_crud;
import java.util.*;
import java.sql.*;
public class food_ord {

	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		 food_ord food=new food_ord();
		Scanner select=new Scanner(System.in);
		while(true)
		{
		System.out.println("\n");
		System.out.println("Welcome to our Food store");
		System.out.println(" 1.Ordering the Foods\n 2.Check which type of Food\n 3.Update Delivery Date\n 4.Cancel food Order \n 5.Exit");
		int ch=select.nextInt();
		switch(ch)
		{
		case 1: food.Insert();
		break;
		case 2: food.Read();
		break;
		case 3: food.Update();
		break;
		case 4: food.Delete();
		break;
		case 5: System.out.print("Thank you for Ordering");
		System.exit(0);
		default: System.out.println("Invalid number enter between 1 to 4");
		}
	}
	}
	
	public void Insert() throws Exception {
		
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_project","root","Anjuammu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Food name");
		String pname =sc.nextLine();
		System.out.println("Enter Amount in Kg name");
		String dname =sc.nextLine();
		System.out.println("Enter customer contact details");
		Long contact =sc.nextLong();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Delivary date in (yyyy-mm-dd)");
		String strdate =input.nextLine();
		
		String query = "INSERT INTO Foods_order(cname,Amount,phone,Deldate) VALUES (?, ?, ?, ?)";
	    PreparedStatement pstmt = con.prepareStatement(query);
	    
	    pstmt.setString(1, pname);
	    pstmt.setString(2, dname);
	    pstmt.setLong(3, contact);
	    pstmt.setString(4, strdate);
	    
	    int i= pstmt.executeUpdate();
	    
		if(i==1)
		{
			System.out.println("Order Booked Successfully");
		}
		else 
		{
			System.out.println("Booking Unsuccessfully");
		}
		con.close();  
	}
	
	public void Read() throws Exception
	{
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_pro","root","Anjuammu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc1= new Scanner(System.in);
		System.out.println("Enter you mobile number to check Order");
		Long contact =sc1.nextLong();
		
		Statement pstmt=con.createStatement();
		String query = "Select cname,Amount,phone,Deldate from Foods_order where phone ="+contact;
		ResultSet rs=pstmt.executeQuery(query);
		if(rs.next()==false)
		{
			System.out.println("No Food  Available");
		}
		else 
		{
			do
			{	
				System.out.println("Food Name = " +rs.getString(1));
				System.out.println("Amount in kg = " +rs.getString(2));
				System.out.println("customer Number = " +rs.getString(3));
				System.out.println("Delivery Date = " +rs.getString(4));
					
			}while(rs.next());
		}
		System.out.println();
		con.close();  
	}
	
	public void Update() throws Exception
	{
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_pro","root","Anjuammu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc1= new Scanner(System.in);
		System.out.println("Enter you mobile number to update Delivery");
		Long contact =sc1.nextLong();
		Scanner input = new Scanner(System.in);
		System.out.println("Change Delivery date in (yyyy-mm-dd)");
		String strdate =input.nextLine();
			
		String query = "Update Foods_order set Deldate='"+strdate+"'where phone="+contact;
		PreparedStatement pstmt = con.prepareStatement(query);
		    
		int u= pstmt.executeUpdate();
		if(u==1)
		{
			System.out.println("Delivery Date updated Successfully");
		}
		else 
		{
			System.out.println("Can't Update Date. \n Please try after sometime.");
		}
		
		Statement st=con.createStatement();
		String query1 = "Select cname,amount,phone,deldate from foods_order where phone ="+contact;
		ResultSet rs=st.executeQuery(query1);
		while(rs.next())
		{
			System.out.println("Food Name = " +rs.getString(1));
			System.out.println("Amount in kg = " +rs.getString(2));
			System.out.println("customer Number = " +rs.getString(3));
			System.out.println("Delivery Date = " +rs.getString(4));
		}
		con.close();  
	}
	
	public void Delete() throws Exception
	{
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mini_pro","root","Anjuammu@123");
		System.out.println("Connected Successfully");
		
		Scanner sc1= new Scanner(System.in);
		System.out.println("Enter you mobile number to Cancel food Delivery");
		Long contact =sc1.nextLong();
		
		String query = "Delete from Foods_order where phone="+contact;
		PreparedStatement pstmt = con.prepareStatement(query);
		int d =pstmt.executeUpdate();
		if(d==1)
		{
			System.out.println("Order Deleted Successfully");
		}
		else 
		{
			System.out.println("Deletion Unsuccessfully");
		}
		con.close(); 
	}

}
