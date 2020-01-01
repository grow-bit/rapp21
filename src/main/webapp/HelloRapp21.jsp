<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Hello RApP21</title>
</head>
<body>
    <p>Username from servlet: <%= request.getAttribute("username") %></p>
    <p>Logout with <a href='<%= request.getAttribute("logoutUrl") %>'>this link</a></p>
</body>
</html>
