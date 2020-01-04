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
            <h5 class="header col s12 light">Seleziona uno studente al quale assegnare la skill <b>${skill_title}</b> per l'argomento <b>${topic_title}</b></h5>
          </div>
          <div class="row">
            <div class="col s12">
                <div class="collection">
                    <a href="/endorsement?id_topic=${id_topic}&id_skill=${id_skill}&id_student=1" class="collection-item">Alan</a>
                    <a href="/endorsement?id_topic=${id_topic}&id_skill=${id_skill}&id_student=2" class="collection-item">Margot</a>
                    <a href="/endorsement?id_topic=${id_topic}&id_skill=${id_skill}&id_student=3" class="collection-item">Eli</a>
                </div>
            </div>
        </div>
    </div>
  </div>
</body>
</html>
