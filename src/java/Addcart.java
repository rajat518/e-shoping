

	import javax.servlet.*;
	import java.io.*;
	import javax.servlet.http.*;
	import java.util.*;
	import java.text.*;
	import java.sql.*;
	import java.lang.String.*;
	public class Addcart extends HttpServlet
	{
	    Connection con=null;
		Statement ps,ps1,ps2=null;	
		ResultSet rs=null;
		int flag=0,rw=0,qty=0,abc=0,pid=0;
		String cid="";
	
		public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
		{
		res.setContentType("text/html");
		HttpSession ses=req.getSession();
		RequestDispatcher rd;
		PrintWriter pw=res.getWriter();
	    try
		{

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
       
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
		
        ps=con.createStatement();
		ps1=con.createStatement();		
		ps2=con.createStatement();
		
		String idee=req.getParameter("id");
		qty=Integer.parseInt(req.getParameter("t1"));
		RequestDispatcher rr;
		
		int cnt=0;
java.util.Date d=new java.util.Date();
Format tim=new SimpleDateFormat("HH-MM");

           
		
		String query=("SELECT count(pro_id) from cart where user_id='"+ses.getAttribute("cuser_id")+"' and pro_id='"+idee+"'");
		ResultSet rs=ps.executeQuery(query);
		rs.next();
		int count=rs.getInt("count(pro_id)");
        if(count!=0)
		{
		rs=ps2.executeQuery("select pro_id,pro_qty from cart where user_id='"+ses.getAttribute("cuser_id")+"' and pro_id='"+idee+"'");
		while(rs.next())
		{		
		qty += rs.getInt("pro_qty");
		rw=ps.executeUpdate("update cart set pro_qty="+qty+" where user_id='"+ses.getAttribute("cuser_id")+"' and pro_id='"+idee+"'");
        con.commit();flag=1;
		rd= req.getRequestDispatcher("glue.jsp");
		rd.forward(req,res);
		}
		}      

	else{
		//pw.print("insert into cart(user_id,pro_id,cr_time,cr_id) values('"+ses.getAttribute("cuser_id")+"','"+idee+"','"+tim.format(d)+"',"+qty+");");
		rw=ps1.executeUpdate("insert into cart(user_id,pro_id,cr_time,pro_qty) values('"+ses.getAttribute("cuser_id")+"','"+idee+"','"+tim.format(d)+"',"+qty+")");
		pw.print("hello");
		con.commit();
		flag=1;
		rd= req.getRequestDispatcher("glue.jsp");
		rd.forward(req,res);pw.close();
	     }
					if(flag==0)
					{
					pw.println("<html><body>");		
					pw.println("<br><centeR><b>UNABLE TO UPDATE");
					pw.println("TRY AGAIN");
					pw.println("</b><a href='glue.jsp'>TRY AGAIN</a>");
					pw.println("</body></html>");
	 			    rd= req.getRequestDispatcher("glue.jsp");
					rd.forward(req,res);
					pw.close();
					}
		}   
		catch(ClassNotFoundException e)
		{
		pw.println("exception"+e);
		}
		catch(SQLException e)
		{
			pw.println("INVALID ENTRY"+e);	
			pw.println("TRY AGAIN");
		}
     	catch(Exception e)
		{
		pw.println("can't load driver"+e.getMessage());
		}
  }
}