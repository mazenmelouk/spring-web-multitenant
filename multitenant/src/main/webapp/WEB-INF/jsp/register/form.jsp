<%-- 
    Document   : register/form
    Created on : Feb 27, 2016, 1:44:35 AM
    Author     : Mazen
    Company    : WeMake{}Stuff
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   


<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Register</title>
   </head>
   <body>
      <div align="center">
         <form:form action="register" method="post" commandName="tenantForm">
            <table border="0">
               <tr>
                  <td colspan="2" align="center"><h2>Multitenant-Registration</h2></td>
               </tr>
               <tr>
                  <td>Company Name:</td>
                  <td><form:input path="name" /></td>
               </tr>
               <tr>
                  <td>Description</td>
                  <td><form:textarea path="description" /></td>
               </tr>
               <tr>
                  <td>Address:</td>
                  <td><form:input path="address" /></td>
               </tr>

               <tr>
                  <td colspan="2" align="center"><input type="submit" value="Register" /></td>
               </tr>
            </table>
         </form:form>
      </div>
   </body>
</html>
