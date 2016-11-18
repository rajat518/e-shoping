package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class Suppsend extends HttpServlet
{
    Connection con=null;
	Statement ps,ps1,ps2,ps3,ps4,ps5=null;
	
	ResultSet rs,rs1,rs2,rs3,rs4,rs5=null;
	HttpSession ses=null;

	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	      pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		pw.println("before prep Supp");
              ps=con.createStatement();   
			  ps1=con.createStatement();   
			  ps2=con.createStatement();   
			  ps3=con.createStatement();   
			  ps4=con.createStatement(); 
			  ps5=con.createStatement();
		pw.println("after prep");
		 ses=req.getSession(true);
		
		 RequestDispatcher rd=null;	

		 String pid=req.getParameter("pid");
		 String bid=req.getParameter("bid");
		 int qty=Integer.parseInt(req.getParameter("qty"));
		 int qty1=0,pqty=0,dqty=0,x=0,count=0,c=0;
		 String orderid="";
          pw.println("qty"+qty);
         pw.println("select num_of_items from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
		 rs1=ps1.executeQuery("select num_of_items from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
		 rs1.next();
		 qty1=rs1.getInt(1);
		 qty1 += qty;
		 pw.println("qty1"+qty1);
         ps1.executeUpdate("update products set num_of_items='"+qty1+"' where pro_id='"+pid+"' and branch_id='"+bid+"'");
		 ps2.executeUpdate("delete  from sup_order where pro_id='"+pid+"' and branch_id='"+bid+"'");
          con.commit();
		  



		 
		 pw.println("select orderdetails.orderid from orderdetails,orders where ord_status='Hold' and orderdetails.pro_id='"+pid+"' and branch_id='"+bid+"' and orders.orderid=orderdetails.orderid order by orders.orderid");		 
		 rs=ps.executeQuery("select orderdetails.orderid from orderdetails,orders where ord_status='Hold' and orderdetails.pro_id='"+pid+"' and branch_id='"+bid+"' and orders.orderid=orderdetails.orderid order by orders.orderid");
		 while(rs.next())
			{
			 pw.println("while");
			 orderid=rs.getString(1);
			 pw.println("select PRO_QTY,DPRO_QTY from orderdetails where orderid='"+orderid+"'");
			 rs2=ps2.executeQuery("select PRO_QTY,DPRO_QTY from orderdetails where orderid='"+orderid+"'");
			 rs2.next();
			 pqty=rs2.getInt(1);
	         dqty=rs2.getInt(2);	
			 x=(pqty-dqty);
			 		 pw.println("X"+x);
			 pw.println("select num_of_items from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
		 	 rs1=ps1.executeQuery("select num_of_items from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
			 rs1.next();
			 qty1=rs1.getInt(1);
			 qty1=qty1-x;
			 		 pw.println("qty1"+qty1);
			// pw.println("update products set num_of_items='"+qty1+"' where pro_id='"+pid+"' and branch_id='"+bid+"'");
			 ps3.executeUpdate("update products set num_of_items='"+qty1+"',status='0' where pro_id='"+pid+"' and branch_id='"+bid+"'");
			// pw.println("update products set num_of_items='"+qty1+"',status='0' where pro_id='"+pid+"' and branch_id='"+bid+"'");
			 pw.println("update orderdetails set DPRO_QTY="+pqty+" where pro_id='"+pid+"' and branch_id='"+bid+"'");
		     ps1.executeUpdate("update orderdetails set DPRO_QTY="+pqty+" where pro_id='"+pid+"' and branch_id='"+bid+"'");
			 con.commit();
             pw.println("select count(pro_id) from orderdetails where orderid='"+orderid+"'");
			 rs3=ps3.executeQuery("select count(pro_id) from orderdetails where orderid='"+orderid+"'");
			 rs3.next();
			 count=rs3.getInt(1);
			 pw.println("COUNT"+count);
			 pw.println("select pro_id from orderdetails where orderid='"+orderid+"' order by pro_id");
			 rs4=ps4.executeQuery("select pro_id from orderdetails where orderid='"+orderid+"' order by pro_id");

			 while(rs4.next())
				{
				 pw.println("while1");
				 pw.println("select PRO_QTY,DPRO_QTY from orderdetails where pro_id='"+pid+"' and orderid='"+orderid+"' order by pro_id");
				 rs5 = ps2.executeQuery("select PRO_QTY,DPRO_QTY from orderdetails where pro_id='"+pid+"' and orderid='"+orderid+"' order by pro_id");
                    rs5.next();
				   qty=rs5.getInt(1);
				   dqty=rs5.getInt(2);
				   pw.println("qty"+qty);
				   pw.println("dqty"+dqty);
				   if(dqty == qty)
					{
					   pw.println("if"+c); 
					   c++;
					}
                     pw.println("hello");  
				}
				pw.println("hello1");
				if(c==count)
				{
					//pw.println("update orders set ord_status='processsed' where orderid='"+orderid+"'");
					ps1.executeUpdate("update orders set ord_status='processsed' where orderid='"+orderid+"'");
					//ps5.executeUpdate("update products set status=0 where pro_id='"+pid+"'"); 
					con.commit();
				}
				c=0;

			}
         rd= req.getRequestDispatcher("suppsend.jsp");
        rd.forward(req,res);
          

		}
	catch(ClassNotFoundException e)
		{
		pw.println("exception"+e);
		}
		catch(SQLException e)
		{
			pw.println("exception"+e);	
		}
		     	catch(Exception e)
		{
		pw.println("can't load driver"+e.getMessage());
		}

		}
	

}	