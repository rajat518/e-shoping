package src;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;
public class Makeorder extends HttpServlet
{
    Connection con=null;
	Statement ps,stmt,st,st1,st2,st3,st4,st5,st6,st7=null;	
	Statement ps2,ps7,ps8,ps9,ps10=null;
	ResultSet rs=null;
	ResultSet rs2,rs8,rs10,rs3,rs11,rs12,rs13,rs14=null;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
	
 		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
	      pw.println("connection");
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");		
		pw.println("before prep  Makeorder");
                 ps=con.createStatement();
				 ps7=con.createStatement();
				 ps8=con.createStatement();
				 ps9=con.createStatement();
				 ps10=con.createStatement();
				 stmt=con.createStatement();
				 st1=con.createStatement();
				 st2=con.createStatement();
                 st3=con.createStatement();
				 st4=con.createStatement();
				 st5=con.createStatement();
				 st6=con.createStatement();
				 st7=con.createStatement();

				 ps2=con.createStatement();
        st=con.createStatement();
		pw.println("after prep");
		  HttpSession ses=req.getSession();
		RequestDispatcher rd;	
		String s1=req.getParameter("add");
		String s2=req.getParameter("city");
		String s3=req.getParameter("state");
		rs3=st2.executeQuery("select branch_id from subbranch where nearby='"+s3+"'");
		 rs3.next();
		String	br1=rs3.getString(1);
		pw.println(br1);
		String productid="";
		int s5=Integer.parseInt(req.getParameter("pin"));
        java.util.Date d=new java.util.Date();
		Format dat=new SimpleDateFormat("dd-MMM-yyyy");   
		Format tim=new SimpleDateFormat("HH-MM");     
		int p=0,row=0,row1=0,row2;
		int p1,tqty,ks=0;
		int orderid=0,qty=0,k=0;
		float sum=0,price=0,q1=0;
		double sum1=0.00;
		pw.println(""+dat.format(d)+""+tim.format(d));
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
									con.commit();
									p=2;

													
	
						}
					else
						{
						
						    pw.println("NO VALID AMOUNT");
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


if(p==2)
			{
		pw.println("select distinct cart.pro_qty,products.pro_price from products,cart where cart.user_id='"+ses.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");
		rs8=st1.executeQuery("select distinct cart.pro_qty,products.pro_price from products,cart where cart.user_id='"+ses.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");
	while(rs8.next())
	{
				  p1=rs8.getInt(1);
	              q1=rs8.getInt(2);
            
				sum1=sum1+q1*p1; tqty=p1;
	}
				pw.println("insert into orders(orderid,ord_add,ord_city,ord_state,ord_pincode,ord_time,ord_status,ord_price) values(seq4.NextVal,'"+s1+"','"+s2+"','"+s3+"',"+s5+",'"+tim.format(d)+"','NEW',"+sum1+")");
				row=ps.executeUpdate("insert into orders(orderid,ord_add,ord_city,ord_state,ord_pincode,ord_time,ord_status,ord_price) values(seq4.NextVal,'"+s1+"','"+s2+"','"+s3+"',"+s5+",'"+tim.format(d)+"','NEW',"+sum1+")");
				con.commit();
		pw.println("inserted order values");
		ResultSet rs=stmt.executeQuery("select orderid from orders order by orderid ");
		while(rs.next())
			{
				orderid=rs.getInt(1);
				p=1;
			
			}
		
		pw.println(""+orderid);
pw.println("select distinct cart.pro_qty,products.pro_price,products.pro_id from products,cart where cart.user_id='"+ses.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");
		ResultSet rs2=st.executeQuery("select distinct cart.pro_qty,products.pro_price,products.pro_id from products,cart where cart.user_id='"+ses.getAttribute("cuser_id")+"' and cart.pro_id=products.pro_id");

while(rs2.next())
{				sum=0;
			    qty=rs2.getInt(1);
				price=rs2.getInt(2);
				productid=rs2.getString(3);	
				sum=sum+price*qty;
				pw.print(orderid);
				pw.println("insert into orderdetails(orderid,pro_id,user_id,pro_price,pro_qty,branch_id,dpro_qty) values("+orderid+",'"+productid+"','"+ses.getAttribute("cuser_id")+"',"+sum+","+qty+",'"+br1+"',"+k+");");
				row1=ps2.executeUpdate("insert into orderdetails(orderid,pro_id,user_id,pro_price,pro_qty,branch_id,dpro_qty) values("+orderid+",'"+productid+"','"+ses.getAttribute("cuser_id")+"',"+sum+","+qty+",'"+br1+"',"+k+");");
				pw.println("executed update orderdetails");
				p=3;	con.commit();
}
if(p==3)
st7.executeUpdate("delete from cart where user_id='"+ses.getAttribute("cuser_id")+"'");
 
  pw.println("update orders set ord_status='Accepted' where orderid="+orderid+"");
  con.commit();


rd= req.getRequestDispatcher("./Checkstock1?id="+orderid+"");
rd.forward(req,res);
}
pw.println(""+p);


			con.commit();
		
	pw.println("totally executed");	

        
               
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