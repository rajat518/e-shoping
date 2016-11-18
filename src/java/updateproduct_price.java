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
public class updateproduct_price extends HttpServlet {
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
            
        int b=0;
	
	
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       
	    con=DriverManager.getConnection("jdbc:odbc:project","scott","tiger");	
            String s1[]=request.getParameterValues("n");
            System.out.print("susmay");
           for(int i=0;i<s1.length;i++){
            out.print(s1[i]);
           String var=s1[i]+"s";
           String var1=s1[i]+"t";
           out.println(var);
        
        System.out.print(s1[i]);
        
          int c=Integer.parseInt(request.getParameter(var));
           int c1=Integer.parseInt(request.getParameter(var1));
         out.println(c);
          out.println(c1);
         // String c=request.getParameter("p1");
        //out.print(a);
        out.print(c);
           ps=con.createStatement();
           
           b=ps.executeUpdate("update products set pro_price='"+c+"',num_of_items='"+c1+"'where pro_id='"+s1[i]+"'");
           
          
          
        }
         if(b>0)
           {
              response.sendRedirect("./products");                       
           }
        }
                  
         catch(Exception e) {            
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
            Logger.getLogger(updateproduct_price.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(updateproduct_price.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(updateproduct_price.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(updateproduct_price.class.getName()).log(Level.SEVERE, null, ex);
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
