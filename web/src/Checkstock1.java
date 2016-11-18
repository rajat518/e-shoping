package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class Checkstock1 extends HttpServlet
{
    Connection con=null;
	Statement st,st1,st2,st3,st4,st5,st6=null;	
	Statement ps2,ps7,ps8,ps9,ps10,ps,ps1=null;
	ResultSet rs=null;
	ResultSet rs2,rs8,rs10,rs11,rs12,rs13,rs14,rs15=null;
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
		pw.println("before prep Checkstock");
                 
				 ps7=con.createStatement();
				 ps8=con.createStatement();
				 ps9=con.createStatement();
				 ps10=con.createStatement();
				 ps1=con.createStatement();
				ps=con.createStatement();	
				 
				 st1=con.createStatement();
				 ps2=con.createStatement();
				 st4=con.createStatement();
				 st5=con.createStatement();
				 st6=con.createStatement();
				 st3=con.createStatement();
				 st=con.createStatement();
			 	
		pw.println("after prep");
		   ses=req.getSession(true);
   int orderid=Integer.parseInt(req.getParameter("id"));
		RequestDispatcher rd=null;	


pw.println("Helllo"+orderid);
ses.setAttribute("oid",orderid);
int mcount=0,c=0,qty=0,rw,count=0;
pw.println("select pro_id,pro_qty from orderdetails where orderid='"+orderid+"' order by pro_id");
rs11=st5.executeQuery("select pro_id,pro_qty from orderdetails where orderid='"+orderid+"' order by pro_id");
pw.println("select count(pro_id) from orderdetails where orderid='"+orderid+"'");
rs14=st4.executeQuery("select count(pro_id) from orderdetails where orderid='"+orderid+"'");
rs14.next();
mcount=Integer.parseInt(rs14.getString(1));
String pid="",bid="", pname="",sid="";
int qty1=0,qty2=0,buff=0,x=0,t=0,y=0,max=0,kmax=0,k=0;
pw.println("count"+mcount);
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
	rs15=st4.executeQuery("select num_of_items,buffer from products where pro_id='"+pid+"'  and branch_id='"+bid+"'");
	rs15.next();
    pw.println("before"+qty2);
	qty2=rs15.getInt("num_of_items");
	buff=Integer.parseInt(rs15.getString("buffer"));
    qty2=qty2-buff; 
	pw.println("QUAnTITY"+qty2);
	if(qty2 >= qty1)
		{
				 pw.println("if1"+qty2);
                   pw.println("if2");
				   x=(qty2-qty1)+buff; 
				   pw.println("if2"+x);
				   pw.println("update products set num_of_items="+x+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
				   st6.executeUpdate("update products set num_of_items="+x+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
				   pw.println("update orderdetails set DPRO_QTY='"+qty1+"' where orderid  ='"+orderid+"'");
				   st4.executeUpdate("update orderdetails set DPRO_QTY='"+qty1+"' where orderid  ='"+orderid+"'");
				   con.commit();
				   c++;
				   if(c==mcount)
				{
					   pw.println("count"+mcount);
                       
					   pw.println("update orders set ord_status='processsed' where orderid='"+orderid+"'");
					   st4.executeUpdate("update orders set ord_status='processsed' where orderid='"+orderid+"'");
				       
				}
					con.commit();
					
					rd = req.getRequestDispatcher("thanks.jsp");
					rd.forward(req,res);		
         
		}
else if((qty2 + buff)>=qty1)
		{


					x=qty1-qty2;
		            y=buff-x;
					pw.println("y"+y);
					pw.println("update products set num_of_items="+y+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
		            st4.executeUpdate("update products set num_of_items="+y+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
					pw.println("update orderdetails set DPRO_QTY='"+qty1+"' where orderid  ='"+orderid+"'");
				   st4.executeUpdate("update orderdetails set DPRO_QTY='"+qty1+"' where orderid  ='"+orderid+"'");
				   con.commit();
					c++;
					pw.println("c"+c);
					pw.println("count"+mcount);
					if(c==mcount)
					{
					   pw.println("count1"+c);
					   st4.executeUpdate("update orders set ord_status='processsed' where orderid='"+orderid+"'");

						con.commit();   
					}
					
					
				/*ses.setAttribute("oid",orderid);
	            req.setAttribute("id",pid);
				req.setAttribute("f",0);
				req.setAttribute("bid",bid);
				rd= req.getRequestDispatcher("./Supplierorder");
				rd.forward(req,res); */          
pw.println("select  pro_name,supp_id,max from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
rs2=st6.executeQuery("select  pro_name,supp_id,max from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
rs2.next();
 pname=rs2.getString("pro_name");
 sid=rs2.getString("supp_id");
 kmax = Integer.parseInt(rs2.getString("max"));
 qty=kmax;
 pw.println(" SELECT count(pro_id) from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'"); 
		String query=("SELECT count(pro_id) from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'");
		ResultSet rs=ps.executeQuery(query);
		rs.next();
		 count=rs.getInt("count(pro_id)");
        if(count!=0)
		{
         pw.println("select quantity from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'); ");
		rs=ps2.executeQuery("select quantity from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"' ");
		while(rs.next())
		{		
		qty += rs.getInt("quantity");
		pw.println("update sup_order set quantity="+qty+" pro_id='"+pid+"' and branch_id='"+bid+"'");
		rw=ps.executeUpdate("update sup_order set quantity="+qty+" pro_id='"+pid+"' and branch_id='"+bid+"'");
        con.commit();		
		}
		}      

	else{
		pw.println("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+pid+"','"+bid+"','"+sid+"',"+qty+");");
		rw=ps1.executeUpdate("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+pid+"','"+bid+"','"+sid+"',"+qty+");");
		con.commit();

	     }
		}
else
				{
                             pw.println("else Max"); 
							 pw.println("update orders set ord_status='Hold' where orderid='"+orderid+"'");
							 st4.executeUpdate("update orders set ord_status='Hold' where orderid='"+orderid+"'");
							 con.commit();
							 pw.println("select Max from products where branch_id ='"+bid+"' and pro_id='"+pid+"'");
							 rs14=st4.executeQuery("select Max from products where branch_id ='"+bid+"' and pro_id='"+pid+"'"); 
							rs14.next();
							max=Integer.parseInt(rs14.getString(1));
                             if(qty1>max)
					{
                				/*ses.setAttribute("oid",orderid);
							   req.setAttribute("mqty",qty1);
							  req.setAttribute("id",pid);
							  req.setAttribute("f",1);
                               req.setAttribute("bid",bid); 
							   rd= req.getRequestDispatcher("./Supplierorder");
  							   rd.forward(req,res);*/
 pw.println("select  pro_name,supp_id,max from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
rs2=st6.executeQuery("select  pro_name,supp_id,max from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
rs2.next();
 pname=rs2.getString("pro_name");
 sid=rs2.getString("supp_id");
 kmax = Integer.parseInt(rs2.getString("max"));
 pw.println("yahoo"+kmax);
 qty=qty1;
         pw.println(" SELECT count(pro_id) from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'"); 
		String query=("SELECT count(pro_id) from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'");
		ResultSet rs=ps.executeQuery(query);
		rs.next();
		count=rs.getInt("count(pro_id)");
        if(count!=0)
		{
         pw.println("select quantity from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'");
		 rs=ps2.executeQuery("select quantity from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"' ");
		rs.next();
		qty += rs.getInt("quantity");
		pw.println("update sup_order set quantity="+qty+" where pro_id='"+pid+"' and branch_id='"+bid+"'");
		rw=ps.executeUpdate("update sup_order set quantity="+qty+" where pro_id='"+pid+"' and branch_id='"+bid+"'");
        con.commit();		
		
		}      

	else{
		pw.println("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+pid+"','"+bid+"','"+sid+"',"+qty+");");
		rw=ps1.executeUpdate("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+pid+"','"+bid+"','"+sid+"',"+qty+");");
		
		con.commit();

	     }
	}
					  else
					{
                			   /*ses.setAttribute("oid",orderid);
							   req.setAttribute("id",pid);
							   req.setAttribute("f",2);
							   req.setAttribute("bid",bid);
							   rd= req.getRequestDispatcher("./Supplierorder");
  							   rd.forward(req,res);*/

 pw.println("select  pro_name,supp_id,max from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
 rs2=st6.executeQuery("select pro_name,supp_id,max from products where pro_id='"+pid+"' and branch_id='"+bid+"'");
 rs2.next();
 pname=rs2.getString("pro_name");
 sid=rs2.getString("supp_id");
if(qty2 > 0)
						{
	 kmax = (qty1-qty2); 
	 pw.println("update products set num_of_items="+buff+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
     st4.executeUpdate("update products set num_of_items="+buff+" where branch_id ='"+bid+"' and pro_id='"+pid+"'");
	 pw.println("update orderdetails set DPRO_QTY="+qty2+" where orderid  ='"+orderid+"'");
     st4.executeUpdate("update orderdetails set DPRO_QTY="+qty2+" where orderid  ='"+orderid+"'");
     con.commit();
			}
else
	kmax =(qty1+buff);

	qty=kmax;
	    
        pw.println("SELECT count(pro_id) from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'"); 
		String query=("SELECT count(pro_id) from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"'");
		ResultSet rs=ps.executeQuery(query);
		rs.next();
		 count=rs.getInt("count(pro_id)");
        if(count!=0)
		{
         pw.println("select quantity from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"' ");
		rs=ps2.executeQuery("select quantity from sup_order where  pro_id='"+pid+"' and branch_id='"+bid+"' ");
		while(rs.next())
		{		
		qty += rs.getInt("quantity");
		pw.println("update sup_order set quantity="+qty+" where pro_id='"+pid+"' and branch_id='"+bid+"'");
		rw=ps.executeUpdate("update sup_order set quantity="+qty+" where pro_id='"+pid+"' and branch_id='"+bid+"'");
        con.commit();		
		}
		}      

	else{
		pw.println("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+pid+"','"+bid+"','"+sid+"',"+qty+");");
		rw=ps1.executeUpdate("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+pid+"','"+bid+"','"+sid+"',"+qty+");");
		
		con.commit();

	     }
	}

}
		
		}
		
	
		
		rd= req.getRequestDispatcher("thanks.jsp");
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