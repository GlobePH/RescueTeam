<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<jsp:include page="header.jsp" />
	</head>
	<body>
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">            
              <!--overview start-->
			  <div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">
						<li style="color:red"><i class="fa fa-laptop"></i><a href="<c:url value='/' />"><span style="color:red">Emergency Command Center</span></a></li>
						<li><i class="fa fa-table"></i><a href="<c:url value='/approval' />">Registration Approval</a></li>
						<li><i class="fa fa-th-list"></i><a href="<c:url value='/emergency' />">Emergency List</a></li>					  	
					</ol>
				</div>
			</div>
              
           <div class="row" style="margin-top:-15px;">
		    <div class="col-lg-9 col-md-12">
					
					<div class="panel panel-default">
						<div class="panel panel-default">
							<div id="map" style="height:550px; width:100%"></div>	
						</div>
					</div>
				</div>
              <div class="col-md-3">
              <!-- List starts -->
				<ul class="today-datas">
                <!-- List #1 -->
				<li>
                  <!-- Text -->
                  <a href="<c:url value='/approval' />" class="datas-text" data-bind="text: userCounterText()"></a>
                </li>
                <li>
                  
                  <a href="<c:url value='/emergency' />" class="datas-text" data-bind="text: emergencyCounterText()"></a>
                </li>                                                                                                             
              </ul>
              </div>
              
			 
           </div>  
          </section>
      </section>
      <!--main content end-->
  </section>
  <!-- container section start -->
    <!-- nice scroll -->
    <script src="resources/js/jquery.scrollTo.min.js"></script>
    <script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script>
    <!-- charts scripts -->
    <script src="resources/assets/jquery-knob/js/jquery.knob.js"></script>
    <script src="resources/js/jquery.sparkline.js" type="text/javascript"></script>
    <script src="resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
    <script src="resources/js/owl.carousel.js" ></script>
    <!-- jQuery full calendar -->
    <<script src="resources/js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
	<script src="resources/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
    <!--script for this page only-->
    <script src="resources/js/calendar-custom.js"></script>
	<script src="resources/js/jquery.rateit.min.js"></script>
    <!-- custom select -->
    <script src="resources/js/jquery.customSelect.min.js" ></script>
	<script src="resources/assets/chart-master/Chart.js"></script>
   
    <!--custome script for all page-->
    <script src="resources/js/scripts.js"></script>
    <!-- custom script for this page-->
    <script src="resources/js/sparkline-chart.js"></script>
    <script src="resources/js/easy-pie-chart.js"></script>
	<script src="resources/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="resources/js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="resources/js/xcharts.min.js"></script>
	<script src="resources/js/jquery.autosize.min.js"></script>
	<script src="resources/js/jquery.placeholder.min.js"></script>
	<script src="resources/js/gdp-data.js"></script>	
	<script src="resources/js/morris.min.js"></script>
	<script src="resources/js/sparklines.js"></script>	
	<script src="resources/js/charts.js"></script>
	<script src="resources/js/jquery.slimscroll.min.js"></script>
	
    <!-- Custom page for the page -->
	<script src="resources/js/pages/ecc.js"></script>
	<!-- Mapbox JS and CSS -->
	<script src='https://api.mapbox.com/mapbox.js/v2.4.0/mapbox.js'></script>
	<link href='https://api.mapbox.com/mapbox.js/v2.4.0/mapbox.css' rel='stylesheet' />
	
	<!-- Leaflet AJAX -->
  	<script src="resources/js/plugin/leaflet.ajax.min.js"></script>
	
	<!-- Ionicons CSS -->
	<link href='resources/css/ionicons.min.css' rel='stylesheet' />
	
	<!-- Awesome Markers -->
	<script src="resources/js/plugin/leaflet.awesome-markers.min.js"></script>
	<link rel="stylesheet" href="resources/css/leaflet.awesome-markers.css">
  </body>
</html>
