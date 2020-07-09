package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

 public class DerbyDB 
{
     static String protocol = "jdbc:derby:";
     static Connection conn;
     String dbName = "electricalDB"; // the name of the database
     static ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
     ResultSet rs;
     PreparedStatement custInsert = null;
     PreparedStatement custUpdate = null;
     PreparedStatement custDelete = null;    
     PreparedStatement phoneInsert = null;
     PreparedStatement phoneUpdate = null;
     PreparedStatement phoneDelete = null;
     PreparedStatement tvInsert = null;
     PreparedStatement tvUpdate = null;
     PreparedStatement tvDelete = null;
     PreparedStatement orderInsert = null;
     PreparedStatement orderUpdate = null;
     PreparedStatement orderDelete = null;
     PreparedStatement orderDetailsInsert = null;
     PreparedStatement orderDetailsUpdate = null;
     PreparedStatement orderDetailsDelete = null;
     static PreparedStatement select = null;
     static ResultSetMetaData resultSetMD = null;
     Statement s;
     String tableName;

    public DerbyDB(){
    {
    	
        System.out.println("DerbyDB starting in embedded mode");
        Properties props = new Properties(); // connection properties
        
            props.put("user", "root");
            props.put("password", "root");
  
            try {
            	 Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				conn = DriverManager.getConnection(protocol + dbName
				        + ";create=true", props);
				
			 	s = conn.createStatement();
			 	statements.add(s);			
			 	
            System.out.println("Connected to and created database " + dbName);
            conn.setAutoCommit(false);
            

            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            finally {
            }}
    
    	try {
		createCustomerTable();
	    	createOrderTable();
	    	createOrderDetailsTable();
	    	createPhoneTable();
	    	createOrderTable();
	    	createTvTable();
	    			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
    		showPhoneTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    		
   	public void createCustomerTable() throws SQLException {
    	
    	tableName = "customer";
        
       rs = conn.getMetaData().getTables(null, null , tableName.toUpperCase(), null);
       
       if(rs.next()) 
       {
    	   System.out.println("customer table created, ready to go!");
       }
       else
       {       
        s.execute("create table customer(cust_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,"
        		+ " firstName VARCHAR(20), lastName VARCHAR(20), address VARCHAR(100), telNum CLOB)");
       }
    }
    	public void createOrderTable() throws SQLException {
        tableName = "orders";
        rs = conn.getMetaData().getTables(null, null , tableName.toUpperCase(), null);
       
       if(rs.next()) {
    	   System.out.println("orders table created, ready to go!");
       }
       else
       {
           s.execute("create table orders(order_ID INT not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
              		+ "product VARCHAR(20), price DOUBLE)");
      
       }
    }
    	public void createOrderDetailsTable() throws SQLException {
    		tableName= "orderdetails";
    	       rs = conn.getMetaData().getTables(null, null , tableName.toUpperCase(), null);
       
       if(rs.next()) {
    	   System.out.println("orderDetails table created, ready to go!");
       }
       else
       {
           s.execute("create table orderDetails(orderDetails_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
              		+ "productName VARCHAR(20), quantity INT, total DOUBLE)");      
       }
    }
    	public void createPhoneTable() throws SQLException{
    		tableName = "phone";
    		
    	       rs = conn.getMetaData().getTables(null, null , tableName.toUpperCase(), null);
           
           if(rs.next()) {
        	   System.out.println("phone table created, ready to go!");
           }
           else
           {
               s.execute("create table phone(phone_ID INT,"
                  		+ " make VARCHAR(20), model VARCHAR(20), price DOUBLE, storage INT)");     
           }
    	}
    	public void createTvTable() throws SQLException{
    		tableName = "tv";
    		
    	       rs = conn.getMetaData().getTables(null, null , tableName.toUpperCase(), null);
           
           if(rs.next()) {
        	   System.out.println("tv table created, ready to go!");
           }
           else
           {
               s.execute("create table tv(tv_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
               		+ "make VARCHAR(20), model VARCHAR(20), price DOUBLE, screenSize INT)");    
           }
    	}

    		public int addCustomer(int custNo, String firstName,String lastName,String address,long telNum) {
    		int res =0;
			try {
				custInsert = conn.prepareStatement(
					     "insert into customer values (default, ?, ?, ?, ?)");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
    		try {
				custInsert.setString( 1, firstName );
	    		custInsert.setString( 2, lastName );
	    		custInsert.setString( 3, address );
	    		custInsert.setLong( 4, telNum );

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

    	         try {
					res = custInsert.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
    	        try {
					conn.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	      return res;
    	}
     		public int updateCustomer(int custNo, String firstName,String lastName,String address,long telNum) {
        	int res = 0;
        	
			try {
				custUpdate = conn.prepareStatement(
				        "update customer set firstName="+firstName+", lastName=" +lastName + ", address=" +address+ ","
				        		+ " telNum=" +telNum + "where cust_ID= " +custNo);
				 res = custUpdate.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            		try {
						conn.commit();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

        	return res;	
    	}
    		public void deleteCustomer(int custNo) throws SQLException {
    			
    			    custDelete = conn.prepareStatement(
    	           		"delete from customer where cust_ID=" +custNo);
    	           custDelete.executeUpdate();
    	           conn.commit();
    	           
    		}
    		public int addOrder(int orderID, String product, double price) throws SQLException {
    			 int res =0;
    	    	 
    	    	 orderInsert = conn.prepareStatement(
    	                 "insert into orders values (default, ?, ?)");
    	    	      orderInsert.setString( 1, product );
    	    	      orderInsert.setDouble( 2, price );

    		         res = orderInsert.executeUpdate(); 
    		         conn.commit();
    		      return res;
    		}
    		public int updateOrder(int orderID, String product, double price) throws SQLException {
    			int res=0;
    			 orderUpdate = conn.prepareStatement(
    		           "update orders set = product"+product+", price=" +price + "where order_ID= " +orderID);
    		   		res = orderUpdate.executeUpdate();
    		   		conn.commit();
    			return res;	 
    		}
    		public void deleteOrder(int orderID) throws SQLException {
    			    orderDelete = conn.prepareStatement(
    			      		"delete from orders where order_ID=" +orderID);
    			      orderDelete.executeUpdate();
    			      conn.commit(); 
    		}
    		public int addOrderDetails(int orderDetailsNo, String productName, int quantity, double total) throws SQLException 
    		   {
    	    	int  res=0;
    	         orderDetailsInsert = conn.prepareStatement(
    	                "insert into orderDetails values (default, ?, ?, ?)");

    		        orderDetailsInsert.setString( 1, productName );
    		        orderDetailsInsert.setInt( 2, quantity );
    		        orderDetailsInsert.setDouble( 3, total );

    						res = orderDetailsInsert.executeUpdate(); 
    						conn.commit();
    						return res;	  
    						}
    		public int updateOrderDetails(int orderDetailsNo, String productName, int quantity, double total) throws SQLException 
    		   {
    	    	int res = 0;	
    	    	 orderDetailsUpdate = conn.prepareStatement(
    	                "update orderDetails set productName="+productName+", quantity=" +quantity + ", total=" +total+ ","
    	                		+ "where orderDetails_ID= " +orderDetailsNo);
    	        		res = orderDetailsUpdate.executeUpdate();
    	        		conn.commit();
    	    	return res;	    
    		   }
    		public void deleteOrderDetails(int orderDetailsNo) throws SQLException {
    			   orderDetailsDelete = conn.prepareStatement(
    			      		"delete from orderDetails where orderDetails_ID=" + orderDetailsNo);
    			      orderDetailsDelete.executeUpdate();
    			      conn.commit();
    		}
    		public int addPhone(int phoneNo, String make, String model, double price, int storage) throws SQLException {
    			int res =0;
    			
    	    phoneInsert = conn.prepareStatement(
    	                 "insert into phone values (default, ?, ?, ?, ?)");

    	    	      phoneInsert.setString( 1, make );
    	    	      phoneInsert.setString( 2, model );
    	    	      phoneInsert.setDouble( 3, price );
    	    	      phoneInsert.setInt( 4, storage );
    		         res = phoneInsert.executeUpdate(); 
    		         conn.commit();
    		         
    		     return res;
    		}
    		public int updatePhone(int phoneNo, String make, String model, double price, int storage) throws SQLException {
    			int res = 0;
    			 phoneUpdate = conn.prepareStatement(
    		           "update phone set make="+make+", model=" +model + ", price=" +price+ ","
    		           		+ " storage=" +storage + "where phone_ID= " +phoneNo);
    		   		res = phoneUpdate.executeUpdate();
    		   		conn.commit();
    			return res;	
    		}
    		public void deletePhone(int phoneNo) throws SQLException {
    			    phoneDelete = conn.prepareStatement(
    			      		"delete from phone where PHONE_ID =" + phoneNo);
    			   			phoneDelete.executeUpdate();
    			   			conn.commit();
    		}
    		public int addTv(int tvNo, String make, String model, double price, int screenSize) throws SQLException 
    		   {
    			 int res =0;
    	    	  tvInsert = conn.prepareStatement(
    	                 "insert into tv values (default, ?, ?, ?, ?)");

    	    	      tvInsert.setString( 1, make );
    	    	      tvInsert.setString( 2, model );
    	    	      tvInsert.setDouble( 3, price );
    	    	      tvInsert.setInt( 4, screenSize );

    		         res = tvInsert.executeUpdate(); 
    		         conn.commit();
    		      return res;	  
    		      }
    		public int updateTv(int tvNo, String make, String model, double price, int screenSize) throws SQLException {
    			int res = 0;
    			
    			 tvUpdate = conn.prepareStatement(
    		           "update tv set make="+make+", model=" +model + ", price=" +price+ ","
    		           		+ " screenSize=" +screenSize + "where tv_ID= " +tvNo);
    		   		res = tvUpdate.executeUpdate();
    		   		conn.commit();
    			return res; 
    		}
    		public void deleteTv(int tvNo) throws SQLException {
    			    tvDelete = conn.prepareStatement(
    			      		"delete from tv where tv_ID=" + tvNo);
    			   		tvUpdate.executeUpdate();
    			   		conn.commit();
    		}
    	
    	/* public static void shutDownDerby(){
   try {
	   if (framework.equals("embedded"))
   
            {
                try
                {
                    // the shutdown=true attribute shuts down Derby
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");

                }
                catch (SQLException se)
                {
                    if (( (se.getErrorCode() == 50000)
                            && ("XJ015".equals(se.getSQLState()) ))) {
                        // we got the expected exception
                        System.out.println("Derby shut down normally");
                        
                    } else {
                    
                        System.err.println("Derby did not shut down normally");
                        printSQLException(se);
                    }
                }
            }
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        } finally {
            // release all open resources to avoid unnecessary memory usage
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }

            // Statements and PreparedStatements
            int i = 0;
            while (!statements.isEmpty()) {
                // PreparedStatement extend Statement
                Statement st = (Statement)statements.remove(i);
                try {
                    if (st != null) {
                        st.close();
                        st = null;
                    }
                } catch (SQLException sqle) {
                    printSQLException(sqle);
                }
            }

            //Connection
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }}
        }
    
    */

    	public void showPhoneTable() throws SQLException {	
    	//	ArrayList<String> tables = new ArrayList<String>();
    	//	tables.add("customer");
    	//	tables.add("orders");    		
    	//	tables.add("orderDetails");
    	//	tables.add("phone");
    	//	tables.add("tv");
    		
    	//	int x = 0;
    	//	while(x < tables.size()) {
			select = conn.prepareStatement(
			         "select * from phone");
    	      statements.add(select);
    	      rs = select.executeQuery(); ;
    	      resultSetMD = rs.getMetaData();
    	      int columnCount = resultSetMD.getColumnCount();
    	      for (int i = 1; i <= columnCount; i++) 
    	    	  System.out.format("%20s", resultSetMD.getColumnName(i).toString() + "|");
    	      while (rs.next()) {
    	    	  System.out.println("  ");
    	    	  for (int i = 1; i <= columnCount; i++) {
    	    		  System.out.format("%20s", rs.getString(i) + "|");
    	    	  }
    	      }
    	  //    x ++;
	         conn.commit();
    		//}
	}


	 public static void printSQLException(SQLException e)
    {
        while (e != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());

            e = e.getNextException();
        }
    }



}
