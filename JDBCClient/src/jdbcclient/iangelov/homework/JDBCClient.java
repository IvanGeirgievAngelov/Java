package jdbcclient.iangelov.homework;

import java.sql.*;

public class JDBCClient {

	public static void main(String args[]){

		String url="jdbc:mysql://localhost:3306/mysql";
		String user ="root";
		String password ="";

		//initialize variables
		Connection connection = null;
		ResultSet res = null;
		ResultSet resNext = null;
		Statement stt = null;
		PreparedStatement prep = null;



		try{

			System.out.println("Connecting to database");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection=DriverManager.getConnection(url,user,password);


			//create statement 
			stt=connection.createStatement();

			//create and sellect db
			stt.execute("CREATE DATABASE IF NOT EXISTS test");
			stt.execute("USE test");

			//create table if it not exist;
			stt.execute("DROP TABLE IF EXISTS employee");
			stt.execute("CREATE TABLE employee (" 
					+ "id BIGINT NOT NULL AUTO_INCREMENT,"
					+ "Name VARCHAR(32),"
					+ "Surename VARCHAR(32),"
					+ "BirthDate DATE,"
					+ "PRIMARY KEY(ID)"
					+ ")");

			//insert some data into table but we have to validate date;
			stt.execute("INSERT INTO employee (Name,Surename,BirthDate) VALUES"
					+"('John' , 'Doe','19890101')," +			    	 
					"('Marry','Popins','19231201')," +
					"('Harry', 'Potter','19451010')," +
					"('Big', 'Foot','20000101')," +
					"('Doctor','Who','20100301')");

			//set queries 
			String SQL = "SELECT * FROM employee WHERE BirthDate=(SELECT MAX(birthDate) FROM employee)";
			String sql ="SELECT * FROM employee WHERE BirthDate=(SELECT MIN(birthDate) FROM employee)";

			//execute first query
			prep=connection.prepareStatement(SQL);
			res=prep.executeQuery();

			//execute second
			prep=connection.prepareStatement(sql);
			resNext=prep.executeQuery();

			//print results from both queries
			while(res.next()){
				System.out.println(res.getString("id").toString() + " " + res.getString("Name") + res.getString("SureName") + " " + res.getString("BirthDate").toString() + " as yangest");
			}
			while(resNext.next()){
				System.out.println(resNext.getString("id").toString() + " " + resNext.getString("Name") + resNext.getString("SureName") + " " + resNext.getString("BirthDate").toString()+" as oldest");	
			}

			//closing resources
			if(res!=null){
				res.close();
			}
			if(resNext!=null){
				resNext.close();
			}
			if(stt!=null){
				stt.close();
			}
			if(prep!=null){
				prep.close();
			}
			if(connection!=null){
				connection.close();
			}

		}catch(SQLException sqlE){
			sqlE.printStackTrace();
		}catch(InstantiationException ie){

			ie.printStackTrace();
		}catch(IllegalAccessException iea){
			iea.printStackTrace();
		}catch (ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}finally{

		}

	}

}


