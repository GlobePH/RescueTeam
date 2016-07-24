<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="resources/img/favicon.png">

    <title>#RescuePH</title>
	<!-- javascripts -->
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery-ui-1.10.4.min.js"></script>
    <script src="resources/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
    <!-- Bootstrap CSS -->    
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="resources/css/bootstrap-theme.css" rel="stylesheet">
    <!-- Bootbox: Used for Alerts -->
    <script src="resources/js/plugin/bootbox.min.js"></script>
    <!-- Bootstrap notify: Used for Awesome notifications -->
    <script src="resources/js/plugin/bootstrap-notify.min.js"></script>
    <!--external css-->
    <!-- font icon -->
    <link href="resources/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="resources/css/font-awesome.min.css" rel="stylesheet" />    
    <!-- full calendar css-->
    <link href="resources/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
	<link href="resources/assets/fullcalendar/fullcalendar/fullcalendar.css" rel="stylesheet" />
    <!-- easy pie chart-->
    <link href="resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <!-- owl carousel -->
    <link rel="stylesheet" href="resources/css/owl.carousel.css" type="text/css">
	<link href="resources/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
    <!-- Custom styles -->
	<link rel="stylesheet" href="resources/css/fullcalendar.css">
	<link href="resources/css/widgets.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/style-responsive.css" rel="stylesheet" />
	<link href="resources/css/xcharts.min.css" rel=" stylesheet">	
	<link href="resources/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
    
    <!-- bootstrap -->
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- Knockout.js -->
    <script src="resources/js/plugin/knockout-3.4.0.js"></script>
    <script src="resources/js/plugin/knockstrap.min.js"></script>
    
  </head>

  <body>
  <!-- container section start -->
  <section id="container" class="">
     
      
      <header class="header dark-bg">
            <!--logo start-->
            <a href="index.html" class="logo"><img src="resources/img/rescuelogo.png">#RescuePH</a>
            <!--logo end-->
            <div class="top-nav notification-row">                
                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt="" src="resources/img/avatar1_small.jpg">
                            </span>
                            <span class="username">System Admin</span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li class="eborder-top">
                            
                                <a href="<c:url value='/' />"><i class="icon_profile"></i> Dashboard</a>
                            </li>
                            <li>
                                <a href="<c:url value='/approval' />"><i class="icon_mail_alt"></i> Approval</a>
                            </li>
                            <li>
                                <a href="<c:url value='/emergency' />"><i class="icon_clock_alt"></i> Emergency</a>
                            </li>
                            <li>
                                <a href="login.html"><i class="icon_key_alt"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!-- notificatoin dropdown end-->
            </div>
      </header>      
      <!--header end-->
          </div>
      </aside>
      <!--sidebar end-->
      </section>
   </body>
</html>