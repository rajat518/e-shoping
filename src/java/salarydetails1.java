/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author G5
 */
public class salarydetails1 extends HttpServlet {
    Connection con=null;
	Statement ps=null;	
	ResultSet rs=null;
	RequestDispatcher rd;
	

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");
            
              
           out.println("<table border='0' cellspacing='0' cellpadding='0'><tr><td><img src='s.gif' width=300 height=135></td><td background='s55_1.gif' width=700></td></tr></table>");
			out.println("<table align='right'><tr>");
			out.println("<td><a href='./products'><img src='back.gif' border=0></a></td>");
			out.println("</tr></table>");
                        
            
            ps=con.createStatement();
            out.println("<p><table cellpadding=1 cellspacing=1 border=1><tr><th>user_id</th>"
                        + "<th>name</th><th>password</th><th>company</th><th>ph_no</th><th>mob_n0</th>"
                        + "<th>address</th><th>city</th><th>state</th><th>country</th><th>question</th>"
                        + "<th>ans</th><th>birth</th><th>pin</th></tr>");
            rs=ps.executeQuery("select * from user_details");
            while(rs.next())
            {
                String user_id=rs.getString("user_id");
                 String name=rs.getString("name");
                  String password=rs.getString("password");
             String company=rs.getString("company");
              String PH_NO=rs.getString("PH_NO");
              String MOB_NO=rs.getString("MOB_NO");
              String ADDRESS=rs.getString("ADDRESS");
              String CITY=rs.getString("CITY");
              String STATE=rs.getString("STATE");
               String COUNTRY=rs.getString("COUNTRY");
                String QUES=rs.getString("QUES");
                 String ANS=rs.getString("ANS");
                  String BIRTHDAY=rs.getString("BIRTHDAY");
                   String PINCODE=rs.getString("PINCODE");
             
             
             
               out.println("<tr bgcolor=white><td>"+user_id+"</td><td>"+name+"</td><td>"+password+"</td><td>"+company+"</td>"
                       + "<td>"+PH_NO+"</td><td>"+MOB_NO+"</td><td>"+ADDRESS+"</td><td>"+CITY+"</td><td>"+STATE+"</td>"
                       + "<td>"+COUNTRY+"</td><td>"+QUES+"</td><td>"+ANS+"</td><td>"+BIRTHDAY+"</td><td>"+PINCODE+"</td></tr>");
                  
            }
            
            
        } catch(Exception e) {            
            out.print(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(salarydetails1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(salarydetails1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(salarydetails1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(salarydetails1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
