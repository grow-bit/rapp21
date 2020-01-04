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
            <h5 class="header col s12 light">Endorsement della skill <b>${skill_title}</b> per l'argomento <b>${topic_title}</b> allo studente <i>${student_name}</i> completato!</h5>
          </div>
          <div class="row center">
            <div class="col s12">
                <a href="/topics" class="btn-large waves-effect waves-light orange">DA CAPO</a>
            </div>
          </div>
          <div class="row center">
            <div class="col s12">
                <a href="/skills?id_topic=${id_topic}" class="btn-large waves-effect waves-light green">STESSO ARGOMENTO</a>
            </div>
          </div>
          <div class="row center">
            <div class="col s12">
                <a href="/students?id_topic=${id_topic}&id_skill=${id_skill}" class="btn-large waves-effect waves-light blue">STESSA SKILL</a>
            </div>
        </div>
    </div>
  </div>
</body>
</html>