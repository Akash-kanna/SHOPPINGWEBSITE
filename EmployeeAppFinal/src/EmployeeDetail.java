/*
 * Title - Employee management system.
 * Author - Akash
 * Created at - 06-10-2021
 * Updated at - 15-11-2021
 * Reviewed by - Akshaya
 */

import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Employee{
	private String employeeID;
	private String employeeName;
	private String employeeEmail;
	private String employeeDob;
	private String employeeDoj;
	private String employeeMobileNumber;
	
	Employee(String employeeID,String employeeName,String employeeEmail,String employeeDob,String employeeDoj,String employeeMobileNumber){
		this.employeeID=employeeID;
		this.employeeName=employeeName;
		this.employeeEmail=employeeEmail;
		this.employeeDob=employeeDob;
		this.employeeDoj=employeeDoj;
		this.employeeMobileNumber=employeeMobileNumber;
	}	

	public String getEmployeeID() {
		return employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public String getEmployeeDob() {
		return employeeDob;
	}

	public String getEmployeeDoj() {
		return employeeDoj;
	}

	public String getEmployeeMobileNumber() {
		return employeeMobileNumber;
	}
	
	public String toString()
	{
		return employeeID+" "+employeeName+" "+employeeEmail+" "+employeeDob+" "+employeeDoj+" "+employeeMobileNumber;
	}
	
}

abstract class Print{
	abstract void printDetails(); 
}

class PrintStatement extends Print{
	void printDetails() {
		System.out.println("-----------------Welcome to employee management system-----------------");
		
	}
}

public class EmployeeDetail extends Employee{
	
	EmployeeDetail(String employeeID, String employeeName, String employeeEmail, String employeeDob, String employeeDoj,
			String employeeMobileNumber) {
		super(employeeID, employeeName, employeeEmail, employeeDob, employeeDoj, employeeMobileNumber);		
	}
	
	Scanner scan1 = new Scanner(System.in);
	Scanner scan = new Scanner(System.in);
	
	public String employeeId() {
		
		String employeeID = scan.nextLine();
		if(employeeID.startsWith("ACE") && (employeeID.substring(3)).length()==4) {
			return employeeID;
			
		}
		else {
			System.out.println("-->No special characters allow, EmployeeId should begin with ACE followed by 4 digits.");
			System.out.println("Enter employee ID:");			
		}
		return employeeId();
	}
	
	public String employeeName() {
		
		String employeeName = scan.nextLine();
		Pattern my_pattern = Pattern.compile("^(?=.{2,20}$)(([a-zA-Z_\s])\\2?(?!\\2))+$",Pattern.CASE_INSENSITIVE);
		Matcher my_match = my_pattern.matcher(employeeName);
		boolean check = my_match.find();
		if(!check) {
			System.out.println("-->Employee name should contain only alphabets donot include special characters or numerics.");
			System.out.println("Enter employee name: ");			
		}
		else {
			return employeeName;
		}
		return employeeName();
	}
	
	public String employeeEmail() {
		
		String employeeEmail = scan.nextLine();
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+@+[a-zA-Z]+(.com)$",Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(employeeEmail);
		boolean check = match.find();
		if(check) {
			return employeeEmail;
		}
		else {
			System.out.println("-->Please enter the valid mail Example: email@domain.com \n domain name should contains only the alphabets.");
			System.out.println("Enter employee email: ");			
		}
		return employeeEmail();
	}
	
	public String employeeDob() {
		
		String employeeDob = scan.nextLine();
		SimpleDateFormat dob=new SimpleDateFormat("dd/MM/yyyy");
		dob.setLenient(false);
		try
		{
			Date valid=dob.parse(employeeDob);
			long dateCalculation = System.currentTimeMillis()-valid.getTime();
			long age=(long)((long)dateCalculation/(1000.0*60*60*24*365));			
			if(age>=18 && age<=60) return employeeDob;
			else {
				System.out.println("-->Please enter the valid date of birth with dd/mm/yyyy format and the age should between 18 to 60.");
				System.out.println("Enter the employee DOB: ");
				
			}
		}
		catch(ParseException e){
			System.out.println("-->Please enter the valid date of birth with dd/mm/yyyy format and the age should between 18 to 60.");
			System.out.println("Enter the employee DOB: ");
			
		
		}	
		return employeeDob();
	}
	
	public String employeeDoj() {
		
		String employeeDoj = scan.nextLine();
		SimpleDateFormat doj = new SimpleDateFormat("dd/MM/yyyy");
		doj.setLenient(false);
		try {
			Date valid=doj.parse(employeeDoj);
			long dateCalculation=System.currentTimeMillis()-valid.getTime();
			if(dateCalculation>0) {
				return employeeDoj;
			}
			else {
				System.out.println("-->Please enter the valid date of birth with dd/mm/yyyy format and donot enter the future dates.");
				System.out.println("Enter the employee DOJ: ");
				
			}
		}
		catch(ParseException e) {
			System.out.println("-->Please enter the valid date of birth with dd/mm/yyyy format and donot enter the future dates.");
			System.out.println("Enter the employee DOJ: ");
			

		}
		return employeeDoj();
	}
	
	public String employeeMobileNumber() {
		
		String employeeMobileNumber = scan.nextLine();
		Pattern pattern = Pattern.compile("[^6-9]+[0-9]{9}");
		Matcher match = pattern.matcher(employeeMobileNumber);
		boolean check = match.find();
		if(check) {
			System.out.println("-->Please enter the valid Mobile number.The mobile number contains 10 digits.");
			System.out.println("Enter employee mobile number: ");
			
		}
		else {			
			return employeeMobileNumber;
		}
		return employeeMobileNumber();
	}	
	
	public void desision() throws Exception {
		ArrayList<Employee> list=new ArrayList<Employee>();
		int choice;
		do {	
			System.out.println("1.Add new employee details\n2.Display employee detail\n3.Update employee detail\n4.Delete Employee detail\n5.Exit");
			System.out.println("Enter your choice:");					
			choice =scan1.nextInt();			
			scan1.nextLine();
			switch(choice) {
			case 1:
			{				
				System.out.println("--------------------------------------------------------------");
				System.out.println("Add new employee details");
				System.out.println("Enter employee ID:");
				String employeeID = employeeId();
				System.out.println("Enter employee name: ");
				String employeeName = employeeName();
				System.out.println("Enter employee email: ");
				String employeeEmail = employeeEmail();
				System.out.println("Enter the employee DOB: ");
				String employeeDob = employeeDob();
				System.out.println("Enter the employee DOj: ");
				String employeeDoj = employeeDoj();
				System.out.println("Enter employee mobile number: ");
				String employeeMobileNumber = employeeMobileNumber();
				
				String url="jdbc:mysql://127.0.0.1:3306/jdbcassignment";
				String username="root";
				String password="12345akkanna";
				
				try {
					Connection connection= DriverManager.getConnection(url,username,password);
					Statement statement=connection.createStatement();
					String sql1="SELECT * FROM employeedatabase WHERE ID='"+employeeID+"' or Email='"+employeeEmail+"' or Number='"+employeeMobileNumber+"'";
					ResultSet rs=statement.executeQuery(sql1);
					
					if(!rs.next()) {
						try {							
							String sql="INSERT INTO employeedatabase (ID,Name,Email,DOB,DOJ,Number) VALUES(?,?,?,?,?,?)";
							PreparedStatement statementin=connection.prepareStatement(sql);
							statementin.setString(1,employeeID);
							statementin.setString(2,employeeName);
							statementin.setString(3,employeeEmail);
							statementin.setString(4,employeeDob);
							statementin.setString(5,employeeDoj);
							statementin.setString(6,employeeMobileNumber);
							int rows=statementin.executeUpdate();
							if(rows>0) {
								System.out.println("\n>> A new user has been inserted successfully");
							}
							
						}catch(Exception exception) {
							System.out.println(exception);
						}
						System.out.println("--------------------------------------------------------------");
					}else {
						System.out.println("\n>>Record already exist in the database.");
						System.out.println("--------------------------------------------------------------\n");
					}
					
				}catch(Exception exception){
					System.out.println("Please check the entered valid details");
				}
				list.add(new Employee(employeeID,employeeName,employeeEmail,employeeDob,employeeDoj,employeeMobileNumber));
				break;
			}
			case 2:
			{
				System.out.println("--------------------------------------------------------------");
				System.out.println("Display employee details");
				System.out.println("Enter the employee ID for display the details:");
				String empid=scan.next();
				
				for(Employee s:list) {
					int flag=0;
					String validate=s.getEmployeeID();
					if(validate.equals(empid)) {
						System.out.println("Employee ID: "+s.getEmployeeID());
						Thread.sleep(500);
						System.out.println("Employee Name: "+s.getEmployeeName());
						Thread.sleep(500);
						System.out.println("Employee Email: "+s.getEmployeeEmail());
						Thread.sleep(500);
						System.out.println("Employee Dob: "+s.getEmployeeDob());
						Thread.sleep(500);
						System.out.println("Employee Doj: "+s.getEmployeeDoj());
						Thread.sleep(500);
						System.out.println("Employee Mobile Number: "+s.getEmployeeMobileNumber());
						System.out.println("--------------------------------------------------------------");
						flag=0;
						break;
					}			
					else {
						flag=1;
					}if(flag==1) {
						System.out.println(">> Record not found");
						System.out.println("--------------------------------------------------------------");
					}
				}					
				break;
			}
			case 3:
			{
				System.out.println("--------------------------------------------------------------");				
				System.out.println("Update employee details");	
				System.out.println("Enter the employee ID for update the details:");
				String empid=scan.nextLine();
				int choice1;
				
				String url1="jdbc:mysql://127.0.0.1:3306/jdbcassignment";
				String username1="root";
				String password1="12345akkanna";
				
				try {
					Connection connection= DriverManager.getConnection(url1,username1,password1);
					Statement statement=connection.createStatement();
					String sql1="SELECT * FROM employeedatabase WHERE ID='"+empid+"'";
					ResultSet rs=statement.executeQuery(sql1);
					if(rs.next()) 
					{
						do {
							System.out.println("\n1.Update employee ID\n2.Update employee Name\n3.Update employee Email\n4.Update employee DOB\n5.Update employee DOJ\n6.Update employee mobile number\n7.Exit");
							System.out.println("Enter your choice:");					
							choice1 =scan1.nextInt();			
							scan1.nextLine();
							System.out.println("--------------------------------------------------------------");
							
							switch(choice1) {
							case 1:
							{
								System.out.println("Enter employee ID:");
								String employeeID = employeeId();
								try {
									String sql="UPDATE employeedatabase SET ID=? WHERE ID=?";
									PreparedStatement statement1=connection.prepareStatement(sql);
									statement1.setString(1,employeeID);
									statement1.setString(2,empid);
									statement1.executeUpdate();
									System.out.println(">> Record update sucessfully done.");
									System.out.println("--------------------------------------------------------------");
									
								}catch(Exception exception) {
									System.out.println(exception);
									System.out.println(">> Record not found");
								}	
								break;
							}
							case 2:
							{								
								System.out.println("Enter employee name: ");
								String employeeName = employeeName();
								try {
									String sql="UPDATE employeedatabase SET Name=? WHERE ID=?";
									PreparedStatement statement2=connection.prepareStatement(sql);
									statement2.setString(1,employeeName);
									statement2.setString(2,empid);
									statement2.executeUpdate();
									System.out.println(">> Record update sucessfully done.");
									System.out.println("--------------------------------------------------------------");
									
								}catch(Exception exception) {
									System.out.println(exception);
									System.out.println(">> Record not found");
								}	
								break;
							}
							case 3:
							{						
								System.out.println("Enter employee email: ");
								String employeeEmail = employeeEmail();
								try {
									String sql="UPDATE employeedatabase SET Email=? WHERE ID=?";
									PreparedStatement statement3=connection.prepareStatement(sql);
									statement3.setString(1,employeeEmail);
									statement3.setString(2,empid);
									statement3.executeUpdate();
									System.out.println(">> Record update sucessfully done.");
									System.out.println("--------------------------------------------------------------");
									
								}catch(Exception exception) {
									System.out.println(exception);
									System.out.println(">> Record not found");
								}
								break;
							}
							case 4:
							{								
								System.out.println("Enter the employee DOB: ");
								String employeeDob = employeeDob();
								try {
									String sql="UPDATE employeedatabase SET DOB=? WHERE ID=?";
									PreparedStatement statement4=connection.prepareStatement(sql);
									statement4.setString(1,employeeDob);
									statement4.setString(2,empid);
									statement4.executeUpdate();
									System.out.println(">> Record update sucessfully done.");
									System.out.println("--------------------------------------------------------------");
									
								}catch(Exception exception) {
									System.out.println(exception);
									System.out.println(">> Record not found");
								}
								break;
								
							}
							case 5:
							{							
								System.out.println("Enter the employee DOJ: ");
								String employeeDoj = employeeDoj();
								try {
									String sql="UPDATE employeedatabase SET DOJ=? WHERE ID=?";
									PreparedStatement statement5=connection.prepareStatement(sql);
									statement5.setString(1,employeeDoj);
									statement5.setString(2,empid);
									statement5.executeUpdate();
									System.out.println(">> Record update sucessfully done.");
									System.out.println("--------------------------------------------------------------");
									
								}catch(Exception exception) {
									System.out.println(exception);
									System.out.println(">> Record not found");
								}
								break;
								
							}
							case 6:
							{								
								System.out.println("Enter employee mobile number: ");
								String employeeMobileNumber = employeeMobileNumber();
								try {
									String sql="UPDATE employeedatabase SET Number=? WHERE ID=?";
									PreparedStatement statement6=connection.prepareStatement(sql);
									statement6.setString(1,employeeMobileNumber);
									statement6.setString(2,empid);
									statement6.executeUpdate();
									System.out.println(">> Record update sucessfully done.");
									System.out.println("--------------------------------------------------------------");
									
								}catch(Exception exception) {
									System.out.println(exception);
									System.out.println(">> Record not found");
								}	
								break;
							}
							case 7:
							{								
								System.out.println(">> Exit");
								System.out.println("--------------------------------------------------------------");
								break;
							}
							default:
							{
								System.out.println(">> You didnot choose any of this options.");
								break;
							}
							}
							
						}while(choice1!=7);		
						
						break;
					}
					else {
						System.out.println("\n>> record not found");
						System.out.println("--------------------------------------------------------------\n");
					}
					connection.close();
				}catch(Exception exception){
					System.out.println("Please check the entered employee id for deleting the record");
				}
				break;
			}
				
				
			
			case 4:
			{	
				
				System.out.println("--------------------------------------------------------------");
				System.out.println("Delete employee details");
				System.out.println("Enter the employee ID for delete the details:");
				String empid1=scan.next();
				
				String url="jdbc:mysql://127.0.0.1:3306/jdbcassignment";
				String username="root";
				String password="12345akkanna";
				
				try {
					Connection connection= DriverManager.getConnection(url,username,password);
					Statement statement=connection.createStatement();
					String sql1="SELECT * FROM employeedatabase WHERE ID='"+empid1+"'";
					ResultSet rs=statement.executeQuery(sql1);
					if(rs.next()) {
						try {
							Connection connection1= DriverManager.getConnection(url,username,password);
							String sql="DELETE FROM employeedatabase WHERE ID=?";
							PreparedStatement statement1=connection1.prepareStatement(sql);
							statement1.setString(1,empid1);
							statement1.executeUpdate();
							System.out.println("\n>> Record deleted sucessfully done.");
							System.out.println("--------------------------------------------------------------\n");
							connection.close();
						}catch(Exception exception){
							System.out.println("Please check the entered employee id for deleting the record");							
						}
					}
					else {
						System.out.println("\n>> record not found");
						System.out.println("--------------------------------------------------------------\n");
					}
					connection.close();
				}catch(Exception exception){
					System.out.println("Please check the entered employee id for deleting the record");
				}
				
				break;
			}
			case 5:
			{
				System.out.println("--------------------------------------------------------------\n");
				System.out.println(">> Exit");
				System.out.println("--------------------------------------------------------------");
				break;
			}
			
			default:
			{
				System.out.println(">> You didnot choose any of this options.");
				break;
			}
			}
		}while(choice!=5);
		
		
	}
}
