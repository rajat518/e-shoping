package src;

import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import javax.servlet.http.*;
public class Deleteprod extends HttpServlet
{
              Connection con=null; 
              RequestDispatcher rd;
              Statement s,ps,ps1,ps2=null;
			  HttpSession ses=null;
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
    PrintWriter pw = res.getWriter();    
    res.setContentType("text/html");
    int j=0;
    try
    {
        
		String id[]=req.getParameterValues("n");
        String id1[]=req.getParameterValues("pquantity");
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
         con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
         s=con.createStatement();
		 ps=con.createStatement();
		 ps1=con.createStatement();
 		 ps2=con.createStatement();
		 ResultSet vs,rs1,rs2=null;
		 String but1=req.getParameter("butt");
		ses=req.getSession(true);
		int i=0,k=0;


pw.println(""+but1);
      if(but1.equals("DELETE"))
	{
				
            for( i=0;i<id.length;i++)
			{ 

				 String qry="delete from cart where cart.pro_id='"+id[i]+"'";     
                  s.executeUpdate(qry);
		     }//for			
		 rd= req.getRequestDispatcher("./Viewcart");
		rd.forward(req,res);	
	}//if
	

  else if(but1.equals("UPDATE"))
		{

		  vs=s.executeQuery("SELECT count(user_id) from cart where user_id='"+ses.getAttribute("cuser_id")+"'");
		  vs.next();
	      k=vs.getInt("count(user_id)"); 
			
		  int id2[]=new int[k];	

		  int l=0,minqty=0;
		  i=0;

		  
          ResultSet rs=ps.executeQuery("select distinct cart.pro_id from products,cart where cart.pro_id=products.pro_id;");
          String s1=" ",cate;		  
          
		   while(rs.next())
          { 
 			s1=rs.getString(1);
			id2[i]=Integer.parseInt(id1[i]);
            l=id2[i];
			rs1=ps1.executeQuery("select category from products where pro_id='"+s1+"'");
            rs1.next();
			cate = rs1.getString(1);
            rs2 = ps2.executeQuery("select minqty from minqty where category='"+cate+"'");
			rs2.next();
            minqty=Integer.parseInt(rs2.getString("minqty"));			
          
			if(l<minqty)
			  {
			     rd=req.getRequestDispatcher("./Viewcart1");
		        rd.forward(req,res);
			  }	  
		  }//while
			String qry="update cart set pro_qty="+l+" where  user_id='"+ses.getAttribute("cuser_id")+"' and pro_id='"+s1+"'";
            s.executeUpdate(qry);
			con.commit();
			i++;
		 //elseif
		
		 rd=req.getRequestDispatcher("./Viewcart");
		 rd.forward(req,res);
		}
		else if(but1.equals("CONTINUESHOPPING"))
		{
			pw.println("Hello");
		  rd=req.getRequestDispatcher("glue.jsp");
        rd.forward(req,res);
        } //elseif
		 else if(but1.equals("ORDER"))
		{
		pw.println("Hello");	
		 rd=req.getRequestDispatcher("pay.jsp");
         rd.forward(req,res);
        } //elseif
	      
	
	
        
    }//try
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
		pw.println("exception"+e);
		}
   }//void
}//main