package src;

import java.lang.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class Viewcart extends HttpServlet
{
	    Connection con=null;
		Statement stmt,cs=null;
		Statement ps=null;	
		ResultSet rs=null;
		RequestDispatcher rd;
		String abc="null";
		int kris;
               
	HttpSession ses=null;
         
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
            System.out.println("hhhhhh");
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	ses=req.getSession(true); 
        pw.println(ses.getAttribute("cuser_id"));
 		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");       
		    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");	
			cs=con.createStatement();		 
	        pw.println("<html>");          
            pw.println("<body>");
            
			
			//pw.println("<img src='sup1.JPG' width=970 height=195>");
			
	        int i=1;
	                             
            int p=0;
		  
		    ResultSet vs=cs.executeQuery("SELECT count(user_id) from user_details where user_id='"+ses.getAttribute("cuser_id")+"'");
			 vs.next();
		    int kri=vs.getInt("count(user_id)");   
		     if(kri!=0)

				kris=1;
			
			else
				kris=0;
				vs.close();
				cs.close();
		
			stmt=con.createStatement();
			ResultSet rs2=stmt.executeQuery("select name from user_details where user_id='"+ses.getAttribute("cuser_id")+"'");
			while(rs2.next())
			{
				 abc=rs2.getString(1);
			}
			ResultSet rs11=stmt.executeQuery("select distinct products.pro_price,cart.pro_id from products,cart where cart.user_id='"+ses.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");
		    int ccnt=0; 
			while(rs11.next()){
				ccnt++;
				}
			ccnt++;
 
			 int sum[]=new int[ccnt];
			
			 int tqty[]=new int[ccnt];


  
			 stmt.close();
			if(kris==1)
			{
               

               pw.println("<Table width=980 border=0 cellpadding=0 cellspacing=0><tr><td><br><br><br><br><br><b>Company Name:-"+abc+"<br><br>Customer ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:-"+ses.getAttribute("cuser_id")+"<br><br>Date and Time&nbsp;&nbsp;:-:"+new java.util.Date()+"</b></td></tr></table>");
		       pw.print("<form name=del method=GET action=./Deleteprod>");

			
				i=0;p=0;
				int	q;
	           String a,b,r;
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select distinct products.path,products.description,cart.pro_qty,products.pro_price,cart.pro_id from products,cart where cart.user_id='"+ses.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");
			    //pw.print("select distinct products.path,products.description,cart.pro_qty,products.pro_price,cart.pro_id from products,cart where cart.user_id='"+ses.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");
				pw.println("<p><Table cellpadding=1 cellspacing=1 align=center border=1><th>Select</th><th >IMAGE</th><th>product Id</th><th>Item Description</th><th>Quantity</th><th>Price</th><th>Amount</th>");
               
				
		        while(rs.next())
				{
				
				a=rs.getString(1);
				//pw.print(a);
				b=rs.getString(2);
				//pw.print(b);
	            p=Integer.parseInt(rs.getString(3));
				//pw.print(p);
		        q=Integer.parseInt(rs.getString(4));
				//pw.print(q);
	            r=rs.getString(5);
				//pw.print(r);
			
				sum[i]=q*p; tqty[i]=p;

				
	            
				pw.println("<tr bgcolor=white><td><center><input type=checkbox name='n' value="+r+"></td><td align=center><img src='"+a+"'  width=50 height= 50 border=0></td><td align=center>"+r+"</td><td align=center>"+b+"</td><td align=center><input type=text name=pquantity value="+p+" size=5></input></td><td align=center>"+q+"</td><td align=center>"+sum[i]+"</td></tr>");
				i++;		        
			 }
            
				 pw.println("</table>");   	            

				 int sm=0,tq=0,x;
				 for(sm=0,tq=0,x=0;x<i;x++)
				{

					sm=sm+sum[x];
					tq=tq+tqty[x];
				}
			for(i=0;i<57;i++)
				pw.println("&nbsp;");
			pw.println("<strong>Total Quantity&nbsp;:</strong>"+tq+"&nbsp;&nbsp;<strong>Total Amount :</strong>"+sm);
			
			pw.println("<br><br>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.print("<td><td>");
			pw.print("<td><input type='submit' name='butt' value='DELETE'>");	
			pw.print("<td><td>");
			pw.print("<td><input type='submit' name='butt' value='UPDATE'>");	
			pw.print("<td><td>");			
			pw.print("<td><input type='submit' value='CONTINUESHOPPING' name='butt'>");
			pw.print("<td><td>");			
			pw.print("<td><input type='submit' value='ORDER' name='butt'>");
		    pw.println("</tr></table>");
			pw.print("</form>");
			pw.print("</body></html>");               
			rs.close();		
	        ps.close();
			con.close();
		}

	else
	{	
	pw.println("<br><br><br><center><b>");
	pw.println("UNAUTHORIZED ACCESS<br><br>");
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
		
		}
  }
        public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
            doGet(req,res);
        }
}