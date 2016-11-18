

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class Checkstock extends HttpServlet
{
    Connection con=null;
	Statement st,st1,st2,st3,st4,st5,st6=null;	
	Statement ps2,ps7,ps8,ps9,ps10=null;
	ResultSet rs=null;
	ResultSet rs2,rs8,rs10,rs11,rs12,rs13,rs14=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	      pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		pw.println("before prep Checkstock");
                 
				 ps7=con.createStatement();
				 ps8=con.createStatement();
				 ps9=con.createStatement();
				 ps10=con.createStatement();
				 
				 st1=con.createStatement();
				 ps2=con.createStatement();
				 st4=con.createStatement();
				 st5=con.createStatement();
				 st6=con.createStatement();
				 st3=con.createStatement();
				 st=con.createStatement();
		pw.println("after prep");
		  HttpSession ses=req.getSession();
		RequestDispatcher rd=null;	

int orderid=Integer.parseInt(req.getParameter("id"));
pw.println("Helllo"+orderid);
int count,c=0;
pw.println("select pro_id,pro_qty from orderdetails where orderid='"+orderid+"' order by pro_id");
rs11=st5.executeQuery("select pro_id,pro_qty from orderdetails where orderid='"+orderid+"' order by pro_id");
pw.println("select count(pro_id) from orderdetails where orderid='"+orderid+"'");
rs14=st4.executeQuery("select count(pro_id) from orderdetails where orderid='"+orderid+"'");
rs14.next();
count=Integer.parseInt(rs14.getString(1));
String pid="",bid="";
int qty1,qty2=0,buff,x=0,t,y,max=0;
pw.println("count"+count);
while(rs11.next())
	{
	pw.println("while");
	pid=rs11.getString(1);
	qty1=Integer.parseInt(rs11.getString(2));
	pw.println("HELLO"+qty1);
	pw.println("select branch_id from orderdetails where orderid='"+orderid+"' and pro_id='"+pid+"'");
	rs12=st4.executeQuery("select branch_id from orderdetails where orderid='"+orderid+"' and pro_id='"+pid+"'");
	rs12.next();
	bid=rs12.getString(1);
	pw.println("select num_of_items,buffer from  products where pro_id='"+pid+"'  and branch_id='"+bid+"'");
	rs13=st4.executeQuery("select num_of_items,buffer from products where pro_id='"+pid+"'  and branch_id='"+bid+"'");
	rs13.next();
	qty2=Integer.parseInt(rs13.getString(1));
	buff=Integer.parseInt(rs13.getString(2));

    qty2=qty2-buff; 
	if(qty2 >= buff)
		{
		 pw.println("if1"+qty2);

		pw.println("if1"+qty2);
		if(qty2 >= qty1)
			{
                   pw.println("if2");
				   x=(qty2-qty1)+buff; 
				   pw.println("if2"+x);
				   pw.println("update products set num_of_items="+x+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
				   st6.executeUpdate("update products set num_of_items="+x+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
				   c++;
				   if(c==count)
				{
					   pw.println("count"+count);
					   pw.println("update orders set ord_status='processsed' where orderid='"+orderid+"'");
					   st4.executeUpdate("update orders set ord_status='processsed' where orderid='"+orderid+"'");
				       
				}
					con.commit();
					//rd= req.getRequestDispatcher("thanks.jsp");
					//rd.forward(req,res);
			}
           else
			{
			    pw.println("else2"+qty2);
			   if((qty2 + buff)>=qty1)
				{
					x=qty1-qty2;
		            y=buff-x;
					pw.println("y"+y);

					pw.println("update products set buffer="+y+" set num_of_items="+0+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
		            st4.executeUpdate("update products set buffer="+y+",num_of_items="+0+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
					c++;
					if(c==count)
					{
					   pw.println("count"+c);
					   st4.executeUpdate("update orders set ord_status='processsed' where orderid='"+orderid+"'");
					   
					}
					con.commit();
					//rd= req.getRequestDispatcher("thanks.jsp");
					//rd.forward(req,res);
				}
				else
				{
                             pw.println("else Max"); 
							 pw.println("update orders set ord_status='Hold' where orderid='"+orderid+"'");
							 st4.executeUpdate("update orders set ord_status='Hold' where orderid='"+orderid+"'");
							 pw.println("select Max from products where branch_id ='"+bid+"' and pro_id='"+pid+"'");
							 rs14=st4.executeQuery("select Max from products where branch_id ='"+bid+"' and pro_id='"+pid+"'"); 
							rs14.next();
							max=Integer.parseInt(rs14.getString(1));
                             if(qty1>max)
					{
                               req.setAttribute("mqty",qty1);
							   req.setAttribute("id",pid);
							   req.setAttribute("f",1);
                               req.setAttribute("bid",bid); 
							   rd= req.getRequestDispatcher("./Supplierorder");
  							   rd.forward(req,res);
					}
					  else
					{
                               req.setAttribute("id",pid);
							   req.setAttribute("f",2);
							   req.setAttribute("bid",bid);
							   rd= req.getRequestDispatcher("./Supplierorder");
  							   rd.forward(req,res);
					}

							
					
				}
			}	
		
		}
else
		{
	           req.setAttribute("id",pid);
				req.setAttribute("f",0);
				req.setAttribute("bid",bid);
				rd= req.getRequestDispatcher("./Supplierorder");
				rd.forward(req,res);           

		}

	}

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