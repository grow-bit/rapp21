<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="/" class="brand-logo">RApP21</a>
      <ul class="right hide-on-med-and-down">
        <jsp:include page="Menu.jsp" />
      </ul>

      <ul id="nav-mobile" class="sidenav">
          <jsp:include page="Menu.jsp" />
      </ul>
      <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    </div>
  </nav>
  <script>
        (function($){
            $(function(){
                $('.sidenav').sidenav();
            }); // end of document ready
        })(jQuery); // end of jQuery name space
    </script>