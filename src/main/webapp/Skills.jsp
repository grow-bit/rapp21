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
            <h5 class="header col s12 light">Seleziona una soft skill per l'argomento <b>${topic_title}</b></h5>
          </div>
          <div class="row">
              <c:forEach var="skill" items="${skills}">
                <div class="col s12 m6">
                    <div class="card">
                        <div class="card-image">
                            <img src="${skill['image']}">
                            <span class="card-title">
                                <span style="background-color: #D13; padding: 2px;">${skill['title']}</span>
                            </span>
                            <a
                                href="/students?id_topic=${id_topic}&id_skill=${skill['id_skill']}"
                                class="btn-floating btn-large halfway-fab waves-effect waves-light red"><i class="material-icons">add</i>
                            </a>
                        </div>
                        <div class="card-content">
                            <p>${skill['description']}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
  </div>

</body>
</html>
