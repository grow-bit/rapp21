<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <title>RApP21 | Endorsment</title>
  <!--Import Google Icon Font-->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!--Import materialize.css-->
  <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"  media="screen,projection"/>
  
  <!--Let browser know website is optimized for mobile-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
    <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
      <a id="logo-container" href="/" class="brand-logo">RApP21</a>
      <ul class="right hide-on-med-and-down">
        <li><a href="${logoutUrl}">${username} Logout</a></li>
      </ul>

      <ul id="nav-mobile" class="sidenav">
        <li><a href="${logoutUrl}">${username} Logout</a></li>
      </ul>
      <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
  </nav>

  <div class="section no-pad-bot" id="index-banner">
      <div class="container">
          <div class="row center">
            <h5 class="header col s12 light">Endorsment della skill ${id_skill} per l'argomento ${id_topic} allo studente ${id_student} completato!</h5>
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

    <!-- jQuery never die -->
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <!--JavaScript at end of body for optimized loading-->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script>
        (function($){
            $(function(){
                $('.sidenav').sidenav();
            }); // end of document ready
        })(jQuery); // end of jQuery name space
    </script>
</body>
</html>