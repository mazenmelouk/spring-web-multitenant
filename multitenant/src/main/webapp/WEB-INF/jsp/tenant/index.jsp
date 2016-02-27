<%-- 
    Document   : tenant/index
    Created on : Feb 27, 2016, 1:45:02 PM
    Author     : Mazen
    Company    : WeMake{}Stuff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Welcome</title>
   </head>
   <body>
      <h1>Welcome ${tenantName}</h1>
      <div align="center">
         <button id="register" onclick="location.href = 'products';">View Products</button>
         <button id="register" onclick="location.href = 'add';">Add Product</button>
      </div>
   </body>
</html>
