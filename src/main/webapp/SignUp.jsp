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
      <h1 class="header center orange-text">Utente non registrato</h1>
      <div class="row center">
        <p>Compila il form seguente per registrati come professore del progetto RApP21</p>
      </div>
        <div class="row">
            <form action="/signup" method="post" class="col s12">
                <div class="row">
                    <div class="input-field col s6">
                    <input placeholder="Nome" id="first_name" name="first_name" type="text" class="validate">
                    <label for="first_name">Nome</label>
                    </div>
                    <div class="input-field col s6">
                    <input placeholder="Cognome" id="last_name" name="last_name" type="text" class="validate">
                    <label for="last_name">Cognome</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                    <input disabled id="disabled" type="text" class="validate" value="${userEmail}">
                    <label for="disabled">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                    <input placeholder="Scuola" id="school" name="school" type="text" class="validate">
                    <label for="school">Scuola</label>
                    </div>
                </div>
                <div class="row center">
                    <button class="btn waves-effect waves-light" type="submit">Registrati
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>
    </div>
  </div>
</body>
</html>
