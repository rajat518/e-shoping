package Project.SRC;

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
       
	    con=DriverManager.getConnection("jdbc:odbc:PROJECT","scott","tiger");
		
        ps=con.createStatement();
		ps1=con.createStatement();		
		ps2=con.createStatement();
		
		String idee=req.getParameter("id");
		qty=Integer.parseInt(req.getParameter("t1"));
		RequestDispatcher rr;
		
		int cnt=0;

           
		
		String query=("SELECT count(pid) from cart where cust_id='"+ses.getAttribute("cust_id")+"' and pid='"+idee+"'");
		ResultSet rs=ps.executeQuery(query);
		rs.next();
		int count=rs.getInt("count(pid)");
        if(count!=0)
		{
		rs=ps2.executeQuery("select pid,pr_qty from cart where cust_id='"+ses.getAttribute("cust_id")+"' and pid='"+idee+"'");
		while(rs.next())
		{		
		qty += rs.getInt("pr_qty");
		rw=ps.executeUpdate("update cart set pr_qty="+qty+" where cust_id='"+ses.getAttribute("cust_id")+"' and pid='"+idee+"'");
        con.commit();flag=1;
		rd= req.getRequestDispatcher("glue.jsp");
		rd.forward(req,res);
		}
		}      

	else{
		rw=ps1.executeUpdate("insert into cart values('"+ses.getAttribute("cust_id")+"','"+idee+"','25-Jan-2000','05.00',"+qty+",crt1.NextVal);");
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