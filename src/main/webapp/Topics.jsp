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
          <div class="row center">
            <h5 class="header col s12 light">Seleziona un argomento</h5>
          </div>
          <div class="row">
            <c:forEach var="topic" items="${topics}">
                <div class="col s12 m6">
                    <div class="card">
                        <div class="card-image">
                            <img src="${topic['image']}">
                            <span class="card-title">${topic['title']}</span>
                            <a
                                href="/skills?id_topic=${topic['id_topic']}"
                                class="btn-floating btn-large halfway-fab waves-effect waves-light red"><i class="material-icons">add</i>
                            </a>
                        </div>
                        <div class="card-content">
                            <p>${topic['description']}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
  </div>

</body>
</html>
