package M;
// print the results

import java.sql.*;
import java.util.ArrayList;

import common.GlobalData;

public class CustomerManager
	{

		public static ArrayList<CustomerDB> getAllCustomer()
			{
				ArrayList<CustomerDB> list = new ArrayList<CustomerDB>();

				try
					{

						// create our mysql database connection
//			      String myDriver = "org.gjt.mm.mysql.Driver";
						// download from
						// https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.25
						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT
								+ "/" + GlobalData.DATABASE_DATABASE_NAME;
						Class.forName(myDriver);
						Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
								GlobalData.DATABASE_PASSWORD);

						// our SQL SELECT query.
						// if you only need a few columns, specify them by name instead of using "*"
						String query = "SELECT * FROM customers";

						// create the java statement
						Statement st = conn.createStatement();

						// execute the query, and get a java resultset
						ResultSet rs = st.executeQuery(query);

						// iterate through the java resultset
						while (rs.next())
							{
								int id = rs.getInt("id");
								String firstname = rs.getString("name");
								String lastname = rs.getString("surname");
								String phone = rs.getString("phone");

								CustomerDB cc = new CustomerDB(id, firstname, lastname, phone);
								list.add(cc);

								System.out.format("%s, %s, %s, %s \n", id, firstname, lastname, phone);
							}
						st.close();
					} catch (Exception e)
					{
						System.err.println("Got an exception! ");
						System.err.println(e.getMessage());
						System.err.println(e);
					}

				return list;
			}

		public static void saveNewCustomer(CustomerDB x)
			{
				try
					{

						// create our mysql database connection
//			      String myDriver = "org.gjt.mm.mysql.Driver";
						// download from
						// https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.25
						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT
								+ "/" + GlobalData.DATABASE_DATABASE_NAME;
						Class.forName(myDriver);
						Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
								GlobalData.DATABASE_PASSWORD);

						// our SQL SELECT query.
						// if you only need a few columns, specify them by name instead of using "*"
						String query = "INSERT INTO customers VALUE (0, '" + x.name + "','" + x.surname + "','"
								+ x.phone + "')";

						// create the java statement
						Statement st = conn.createStatement();
						st.executeUpdate(query);

						st.close();
					} catch (Exception e)
					{
						System.err.println("Got an exception! ");
						System.err.println(e.getMessage());
						System.err.println(e);
					}

			}

		public static void editCustomer(CustomerDB x)
			{
				try
					{

						// create our mysql database connection
//			      String myDriver = "org.gjt.mm.mysql.Driver";
						// download from
						// https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.25
						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT
								+ "/" + GlobalData.DATABASE_DATABASE_NAME;
						Class.forName(myDriver);
						Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
								GlobalData.DATABASE_PASSWORD);

						// our SQL SELECT query.
						// if you only need a few columns, specify them by name instead of using "*"
						String query = "UPDATE customers SET name ='" + x.name + "', surname = '" + x.surname
								+ "', phone ='" + x.phone + "'WHERE id = " + x.id + "";

						// create the java statement
						Statement st = conn.createStatement();
						st.executeUpdate(query);

						st.close();
					} catch (Exception e)
					{
						System.err.println("Got an exception! ");
						System.err.println(e.getMessage());
						System.err.println(e);
					}
			};

		public static void deleteCustomer(CustomerDB x)
			{
				try
					{

						// create our mysql database connection
//			      String myDriver = "org.gjt.mm.mysql.Driver";
						// download from
						// https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.25
						String myDriver = "com.mysql.cj.jdbc.Driver";
						String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT
								+ "/" + GlobalData.DATABASE_DATABASE_NAME;
						Class.forName(myDriver);
						Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
								GlobalData.DATABASE_PASSWORD);

						/// SQL DELETE query to remove a customer by id
						String query = "DELETE FROM customers WHERE id = " + x.id;

						// create the java statement
						Statement st = conn.createStatement();
						st.executeUpdate(query);

						st.close();
					} catch (Exception e)
					{
						System.err.println("Got an exception! ");
						System.err.println(e.getMessage());
						System.err.println(e);
					}
			};

		public static void main(String[] args)
			{
				ArrayList<CustomerDB> ll = CustomerManager.getAllCustomer();
				System.out.println(ll.size());
			}

	}
