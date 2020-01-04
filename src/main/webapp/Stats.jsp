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
			<h1 class="header center orange-text">RApP21 soft skills stats</h1>
			<div class="row center">
				<h5 class="header col s12 light">Di seguito si trovano le statistiche dei badge ESCO assegnati</h5>
			</div>
			<div class="row">
				<div class="row">
					<div class="col s12" id="projectTopicBadgeCount">
						<div class="preloader-wrapper big active">
							<div class="spinner-layer spinner-blue">
								<div class="circle-clipper left">
									<div class="circle"></div>
								</div>
								<div class="gap-patch">
									<div class="circle"></div>
								</div>
								<div class="circle-clipper right">
									<div class="circle"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawCharts);
      
      function drawCharts() {
            var opts = {sendMethod: 'auto'};
            // Replace the data source URL on next line with your data source URL.

            var charts = [
                {
                    id: 'projectTopicBadgeCount',
                    type: 'PieChart',
                    title: 'ESCO OpenBadge per progetto'
                }
            ];

            for(var i=0; i < charts.length; i++) {
                var chart = charts[i];
                var statsApiUrl = window.location.origin + '/api/stats?chart=' + chart.id;
                var query = new google.visualization.Query(statsApiUrl, opts);
                query.send(handleQueryResponseW(chart.title, chart.id, chart.type));
            }   
      }

      function handleQueryResponseW(title, id, chartType) {
        return function (response) {
            if (response.isError()) {
                console.error('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage(), {title: title, id: id});
                return;
            }

            var data = response.getDataTable();
        
            var width = $('#' + id).width();
            var options = {
                title: title,
                is3D: true,
                width: width,
                height: width // square
            };
            
            var chart = new google.visualization[chartType](document.getElementById(id));
            chart.draw(data, options);
      }}
	</script>
</body>

</html>