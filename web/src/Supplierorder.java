package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.lang.*;
public class Supplierorder extends HttpServlet
{
    Connection con=null;
	Statement st,st1,st2,st3,st4,st5,st6=null;	
	Statement ps,ps1,ps2,ps7,ps8,ps9,ps10=null;
	ResultSet rs=null;
	ResultSet rs2,rs8,rs10,rs11,rs12,rs13,rs14=null;
	 HttpSession ses=null;
int rw=0;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	      pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		pw.println("before prep Supplierorder");
                  ps1=con.createStatement();
				   ps=con.createStatement();
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
				 ses=req.getSession(true);
		pw.println("after prep");

		RequestDispatcher rd=null;	

//String id=req.getAttribute("id");
//String f=req.getAttribute("f");
//String bid=req.getAttribute("bid");


pw.println(req.getAttribute("id"));
pw.println(req.getAttribute("f"));
pw.println(req.getAttribute("bid"));
String id=rs13.getString("pro_id");
String bid=rs13.getString("bid");
String pname=rs13.getString("pro_name");
 String sid=rs13.getString("supp_id");
 int max = Integer.parseInt(rs13.getString("max"));
int qty=rs.getInt("quantity");
int x=0,qty1=0;
pw.println("select  pro_name,supp_id,max from products where pro_id='"+id+"' and branch_id='"+bid+"'");
rs2=st6.executeQuery("select  pro_name,supp_id,max from products where pro_id='"+id+"' and branch_id='"+bid+"'");
rs2.next();
 
//pw.println(kmax);
//int qty;

if((x==0) || (x==2 ))
{
	pw.println("f"+x);
	qty=max;
}
else if(x==1)
	{
		pw.println("f"+x);
        qty=qty1;
	}


        pw.println(" SELECT count(pro_id) from sup_order where  pro_id='"+id+"' and branch_id='"+bid+"'"); 
		String query=("SELECT count(pro_id) from sup_order where  pro_id='"+id+"' and branch_id='"+bid+"'");
		ResultSet rs=ps.executeQuery(query);
		rs.next();
		int count=rs.getInt("count(pro_id)");
        if(count!=0)
		{
         pw.println("select quantity from sup_order where  pro_id='"+id+"' and branch_id='"+bid+"'); ");
		rs=ps2.executeQuery("select quantity from sup_order where  pro_id='"+id+"' and branch_id='"+bid+"'); ");
		while(rs.next())
		{		
		//qty= rs.getInt("qunatity");
		pw.println("update sup_order set quantity="+qty+" pro_id='"+id+"' and branch_id='"+bid+"');");
		rw=ps.executeUpdate("update sup_order set quantity="+qty+" pro_id='"+id+"' and branch_id='"+bid+"');");
        con.commit();;		
		}
		}      

	else{
		pw.println("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+id+"','"+bid+"','"+sid+"',"+qty+");");
		rw=ps1.executeUpdate("insert into sup_order(pro_name,pro_id,branch_id,supp_id,Quantity) values('"+pname+"','"+id+"','"+bid+"','"+sid+"',"+qty+");");
		
		con.commit();

	     }
					

       // rd= req.getRequestDispatcher("thanks.jsp");
		//rd.forward(req,res);



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