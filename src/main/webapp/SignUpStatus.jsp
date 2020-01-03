<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <jsp:include page="fragments/Head.jsp" />
</head>
<body>
    <jsp:include page="fragments/Nav.jsp" />
  <div class="section no-pad-bot" id="index-banner">
    <div class="container">
      <br><br>
      <h1 class="header center orange-text">SignUp Status</h1>
      <form action="/acl-check" method="post">
          <input type="submit" value="invia" />
      </form>
    </div>
  </div>
</body>
</html>