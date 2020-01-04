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
                <p>I dati dei grafici si aggiornano automaticamente ogni 5 secondi</p>
			</div>
			<div class="row">
				<div class="row">
					<div class="col s12 l6" id="RApPStats0">
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
                    <div class="col s12 l6" id="RApPStats1">
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
                    <div class="col s12 l6" id="RApPStats2">
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
                    <div class="col s12 l6" id="RApPStats3">
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
                    <div class="col s12 l6" id="RApPStats4">
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
                    <div class="col s12 l6" id="RApPStats5">
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
                    <div class="col s12 l6" id="RApPStats6">
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

      var topics = ${topicsJson};
      
      function drawCharts() {
            var opts = {sendMethod: 'auto'};
            // Replace the data source URL on next line with your data source URL.

            // aggiungo il grafico che non e' un topic
            topics.unshift({
                    id_topic: 0,
                    title: 'Numero di ESCO OpenBadges',
                    description: 'Percorso formativo RApP'
            });

            for(var i=0; i < topics.length; i++) {
                var topic = topics[i];
                var chartId = "RApPStats" + topic.id_topic;
                var statsApiUrl = window.location.origin + '/api/stats?chartId=' + chartId;
                var query = new google.visualization.Query(statsApiUrl, opts);
                var chartType = 'PieChart';
                // https://developers.google.com/chart/interactive/docs/reference#querysetrefreshinterval
                query.setRefreshInterval(5);
                var chartTitle = topic.title + " / " + topic.description;
                query.send(handleQueryResponseW(chartTitle, chartId, chartType));
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