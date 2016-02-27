<%-- 
    Document   : tenant/product/form
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
      <title>Add Product</title>
   </head>
   <body>
      <div align="center">
         <form:form action="add" method="post" commandName="productForm">
            <table border="0">
               <tr>
                  <td colspan="2" align="center"><h2>Add Product</h2></td>
               </tr>
               <tr>
                  <td>Product Id:</td>
                  <td><form:input path="productId" /></td>
               </tr>
               <tr>
                  <td>Product Name:</td>
                  <td><form:input path="name" /></td>
               </tr>
               <tr>
                  <td>Price:</td>
                  <td><form:input path="price" /></td>
               </tr>
               <tr>
                  <td>Description</td>
                  <td><form:textarea path="description" /></td>
               </tr>
               
               <tr>
                  <td colspan="2" align="center"><input type="submit" value="Add" /></td>
               </tr>
            </table>
         </form:form>
      </div>
   </body>
</html>
