package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	public Connection connect() {

		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	public String insertHospital(String address, String city, String phone, String name, String rooms) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into hospitals(`HospitalID`,`Address`,`City`,`Phone`,`Name`,`Rooms`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, city);
			preparedStmt.setInt(4, Integer.parseInt(phone));
			preparedStmt.setString(5, name);
			preparedStmt.setString(6, rooms);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting Hospital Details";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospitals()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>HospitalID</th>"+"<th>Address</th><th>City</th>"+ "<th>Phone</th>"+ "<th>Name</th>"+ "<th>Rooms</th>"+"<th>Update</th><th>Remove</th></tr>";
			String query = "select * from hospitals";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String hospitalid = Integer.toString(rs.getInt("HospitalID"));
				String address = rs.getString("Address");
				String city = rs.getString("City");
				String phone = Integer.toString(rs.getInt("Phone"));
				String hospitalname = rs.getString("Name");
				String rooms = rs.getString("Rooms");
				// Add into the html table
				output += "<tr><td>" + hospitalid + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + city + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + hospitalname + "</td>";
				output += "<td>" + rooms + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" "+ " type=\"button\" value=\"Update\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"+ "<input name=\"hospitalid\" type=\"hidden\" "+ " value=\"" + hospitalid + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
			catch (Exception e)
		{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateHospital(String id,String address, String city, String phone, String name, String room)
	{
		String output = "";
	try
	{
		Connection con = connect();
			if (con == null)
				{return "Error while connecting to the database for updating."; }
	// create a prepared statement
			String query = "UPDATE hospitals SET Address=?,City=?,Phone=?,Name=?,Rooms=?WHERE HospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
			preparedStmt.setString(1, address);
			preparedStmt.setString(2, city);
			preparedStmt.setInt(3, Integer.parseInt(phone));
			preparedStmt.setString(4, name);
			preparedStmt.setString(5, room);
			preparedStmt.setInt(6, Integer.parseInt(id));
	// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
	}
	catch (Exception e)
	{
		output = "Error while updating the Hospital Details";
		System.err.println(e.getMessage());
	}
		return output;
	}
	
	public String deleteHospital(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from hospitals where HospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Hospital";
			System.err.println(e.getMessage());
		}
		return output;
	}
}