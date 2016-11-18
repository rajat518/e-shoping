
<html>
<head>
<link REL="StyleSheet" HREF="jdeveloper.css">
</head>

<body>
<table border="0" cellspacing="0" cellpadding="0">
<tr>
<td><img src="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\s.gif" width=300 height=135></td>
<td background="C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\swapna\emp.gif" width=700></td>
</tr>
</table>
<center>
<form name="emp" action="./empins" method="post">
            <table border="0">
			
               <tr>
                  <td>Empno</td>
                  <td>
                                         
                     <INPUT TYPE="TEXT" SIZE="6" NAME="EmployeeId">
                  </td>
               </tr>
               <tr>
                  <td>EmployeeName</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="20" NAME="ename">
                  </td>
               </tr>
			    <tr>
                  <td>JobId</td>
                  <td>
             

             <SELECT WIDTH="15" VALUE="AD_PRES" MAXLENGTH="10" NAME="JobId" HEIGHT CLASS="clsJobId">
                <OPTION VALUE="AC_ACCOUNT">AC_ACCOUNT
                <OPTION VALUE="AC_MGR">AC_MGR
                <OPTION VALUE="AD_ASST">AD_ASST
                <OPTION SELECTED VALUE="AD_PRES">AD_PRES
                <OPTION VALUE="AD_VP">AD_VP
                <OPTION VALUE="FI_ACCOUNT">FI_ACCOUNT
                <OPTION VALUE="FI_MGR">FI_MGR
                <OPTION VALUE="HR_REP">HR_REP
                <OPTION VALUE="IT_PROG">IT_PROG
                <OPTION VALUE="MK_MAN">MK_MAN
                <OPTION VALUE="MK_REP">MK_REP
                <OPTION VALUE="PR_REP">PR_REP
                <OPTION VALUE="PU_CLERK">PU_CLERK
                <OPTION VALUE="PU_MAN">PU_MAN
                <OPTION VALUE="SA_MAN">SA_MAN
                <OPTION VALUE="SA_REP">SA_REP
                <OPTION VALUE="SH_CLERK">SH_CLERK
                <OPTION VALUE="ST_CLERK">ST_CLERK
                <OPTION VALUE="ST_MAN">ST_MAN
            </SELECT>

                   
                  </td>
               </tr>
 <tr>
                  <td>Branchid</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="25" NAME="branchid">
                  </td>
               </tr>
<tr>
                  <td>address</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="25" NAME="address">
                  </td>
               </tr>
<tr>
                  <td>city</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="25" NAME="city">
                  </td>
               </tr>

 <tr>
                  <td>state</td>
                  <td>
                     <INPUT TYPE="TEXT" SIZE="25" NAME="state">
                  </td>
               </tr>


 <tr>
                  <td>country</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="20" NAME="country">
                  </td>
               </tr>
<tr>
                  <td>mobile no</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="20" NAME="mobileno">
                  </td>
               </tr>

               

              
<tr>
                  <td><p>Hiredate<br>
			            (mon/dd/year)	  
				  </p></td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="8" NAME="mon">
					 
                      <INPUT TYPE="TEXT" SIZE="8" NAME="dd">
					 
                      <INPUT TYPE="TEXT" SIZE="8" NAME="year">
             
                
               </tr>
<tr>
                  <td>eamil id</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="8" NAME="emailid">
                  </td>
               </tr>
<tr>
                  <td>password</td>
                  <td>
                      <INPUT TYPE="password" SIZE="8" NAME="password">
                  </td>
               </tr>			   
               <tr>
                  <td>Salary</td>
                  <td>
                      <INPUT TYPE="TEXT" SIZE="8" NAME="Salary">
                  </td>
               </tr>

 


            </table>


<br>
<input type="submit"  name="submit" value="Insert">
            <input type="reset" value="Reset">
         </form>
      </center>
   </body>

</html>
