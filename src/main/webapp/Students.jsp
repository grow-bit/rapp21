<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <title>RApP21 | Students</title>
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
            <h5 class="header col s12 light">Seleziona uno studente al quale assegnare la skill ${id_skill} per l'argomento ${id_topic}</h5>
          </div>
          <div class="row">
            <div class="col s12">
                <div class="collection">
                    <a href="/endorsment?id_topic=${id_topic}&id_skill=${id_skill}&id_student=1" class="collection-item">Alan</a>
                    <a href="/endorsment?id_topic=${id_topic}&id_skill=${id_skill}&id_student=2" class="collection-item">Margot</a>
                    <a href="/endorsment?id_topic=${id_topic}&id_skill=${id_skill}&id_student=3" class="collection-item">Eli</a>
                </div>
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
