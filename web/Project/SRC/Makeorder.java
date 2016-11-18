package Project.SRC;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class Makeorder extends HttpServlet
{
    Connection con=null;
	Statement ps,stmt,st,st1=null;	
	Statement ps2,ps7,ps8,ps9,ps10=null;
	ResultSet rs=null;
	ResultSet rs2,rs8,rs10=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	      pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:PROJECT","scott","tiger");		
		pw.println("before prep");
                 ps=con.createStatement();
				 ps7=con.createStatement();
				 ps8=con.createStatement();
				 ps9=con.createStatement();
				 ps10=con.createStatement();
				 stmt=con.createStatement();
				 st1=con.createStatement();
				 ps2=con.createStatement();
				 st=con.createStatement();
		pw.println("after prep");
		  HttpSession ses=req.getSession();
		RequestDispatcher rd;	
		String s1=req.getParameter("add");
		String s2=req.getParameter("city");
		String s3=req.getParameter("state");
		String productid="";
		int s5=Integer.parseInt(req.getParameter("pin"));
        java.util.Date d=new java.util.Date();
		Format dat=new SimpleDateFormat("dd-MMM-yyyy");   
		Format tim=new SimpleDateFormat("HH-MM");     
		int p=0,row=0,row1=0,row2;
		int p1,tqty,ks=0;
		int orderid=0,qty=0;
		float sum=0,price=0,q1=0;
		double sum1=0.00;
		pw.println(""+dat.format(d)+""+tim.format(d));
		rs8=st1.executeQuery("select distinct cart.pr_qty,product.price from product,cart where cart.cust_id='"+ses.getAttribute("cust_id")+"' and cart.pid=product.pid");
while(rs8.next())
{
			  p1=rs8.getInt(1);
              q1=rs8.getInt(2);
            
sum1=sum1+q1*p1; tqty=p1;
}

				row=ps.executeUpdate("insert into orders1 values(ordid.NextVal,'"+s1+"','"+s2+"','"+s3+"',"+s5+",'"+dat.format(d)+"','"+tim.format(d)+"','new',"+sum1+")");
				con.commit();
		pw.println("inserted order values");
		ResultSet rs=stmt.executeQuery("select orderid from orders1 order by orderid");
		while(rs.next())
			{
				orderid=rs.getInt(1);
				p=1;
			
			}
		
		pw.println(""+orderid);
pw.println("select distinct cart.pr_qty,product.price,product.pid from product,cart where cart.cust_id='"+ses.getAttribute("cust_id")+"' and cart.pid=product.pid");
		ResultSet rs2=st.executeQuery("select distinct cart.pr_qty,product.price,product.pid from product,cart where cart.cust_id='"+ses.getAttribute("cust_id")+"' and cart.pid=product.pid");

while(rs2.next())
{				sum=0;
			    qty=rs2.getInt(1);
				price=rs2.getInt(2);
				productid=rs2.getString(3);	
				sum=sum+price*qty;
	
				row1=ps2.executeUpdate("insert into orderdetails values('"+orderid+"','"+productid+"','"+ses.getAttribute("cust_id")+"',"+qty+","+sum+")");
				pw.println("executed update orderdetails");
				p=2;	con.commit();
}	
pw.println(""+p);
if(p==2)
			{
				
				String cno=req.getParameter("ccno");
				String cpin=req.getParameter("ccno2");
				float amt=Float.parseFloat(req.getParameter("amt"));
				pw.println("select crd_amt from bank where crd_num='"+cno+"' and crd='"+cpin+"';");
				String qwe="select crd_amt from bank where crd_num='"+cno+"' and crd='"+cpin+"'";
				rs10=ps10.executeQuery(qwe);
			

				pw.println("yes "+rs10.next());
				if(rs10.getRow()!=0)
				{			
					pw.println("card present");
					double avamt=rs10.getDouble(1);
						if(amt<avamt)

						{								
									avamt=avamt-amt;pw.println(""+avamt);
									pw.println("update bank set crd_amt="+avamt+" where crd_num='"+cno+"'");
									ps9.executeUpdate("update bank set crd_amt="+avamt+" where crd_num='"+cno+"'");													
									p=3;
													
	
						}
					else
						{pw.println("NO VALID AMOUNT");
							rd=req.getRequestDispatcher("iamt.jsp");
							rd.forward(req,res);pw.close();
						}

			}
			else
				{
							pw.println("NOT VALID NO");
							rd=req.getRequestDispatcher("iamt.jsp");
							rd.forward(req,res);pw.close();
				}	







			}

			con.commit();
		
	pw.println("totally executed");	

          if(p==3){  pw.println("deleting");
				ps7.executeUpdate("delete from cart where cust_id='"+ses.getAttribute("cust_id")+"'");
				con.commit();
				req.setAttribute("id",orderid);
				rd= req.getRequestDispatcher("thanks.jsp");
				rd.forward(req,res);pw.close();
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