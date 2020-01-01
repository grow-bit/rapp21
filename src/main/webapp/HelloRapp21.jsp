<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <title>Hello RApP21</title>
</head>
<body>
    <p>Username from servlet: ${username}</p>
    <p>Logout with <a href='${logoutUrl}'>this link</a></p>
</body>
</html>
