package src;

	import javax.servlet.*;
	import java.io.*;
	import javax.servlet.http.*;
	import java.util.*;
	import java.text.*;
	import java.sql.*;
	import java.lang.String.*;
	public class Checkqty extends HttpServlet
	{
	    Connection con=null;
		Statement ps,ps1,ps2=null;	
		ResultSet rs=null;
		int flag=0,rw=0,qty=0,abc=0,pid=0,minqty=0;
		String cid="";
	 
		public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
		{
		res.setContentType("text/html");
		HttpSession ses=req.getSession();
		
		PrintWriter pw=res.getWriter();

	    try
		{

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
       
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
		
        ps=con.createStatement();
		
		
		String cat=req.getParameter("cat");
		qty=Integer.parseInt(req.getParameter("t1"));
        String idee=req.getParameter("id"); 

				
           
				String query=("SELECT minqty from  minqty where CATEGORY='"+cat+"'");
        rs=ps.executeQuery(query);
		while(rs.next())
		{
      	 
		 minqty = Integer.parseInt(rs.getString("minqty"));
		}
       	pw.println(minqty);  
		 if(qty < minqty)
		{   
			  
          RequestDispatcher rd= req.getRequestDispatcher("Alertqty.jsp");
          rd.forward(req,res);		  
		}   
		else
		{
        //  pw.println("<form><input type=hidden name=id value="+idee+"></form>");
           
     	  RequestDispatcher rd= req.getRequestDispatcher("./Addcart?id="+idee+"");
          rd.forward(req,res); 
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