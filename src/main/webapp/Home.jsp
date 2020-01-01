<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
  <title>RApP21 | Home</title>
  <!--Import Google Icon Font-->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!--Import materialize.css-->
  <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css"  media="screen,projection"/>
  
  <!--Let browser know website is optimized for mobile-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
    <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">RApP21</a>
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
      <br><br>
      <h1 class="header center orange-text">RApP21 soft skills endorsment app</h1>
      <div class="row center">
        <h5 class="header col s12 light">Benvenuto nell'app che ti permette di creare endorsment in merito a soft skills acquisite dai ragazzi</h5>
      </div>
      <div class="row center">
        <a href="/topics" class="btn-large waves-effect waves-light orange">INIZIA</a>
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
