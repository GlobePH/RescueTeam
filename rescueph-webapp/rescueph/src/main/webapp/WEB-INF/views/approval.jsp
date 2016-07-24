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
		  <div class="row">
				<div class="col-lg-12">
					<ol class="breadcrumb">
						<li><i class="fa fa-laptop active"></i><a href="<c:url value='/' />">Emergency Command Center</a></li>
						<li style="color:red"><i class="fa fa-table"></i><a href="<c:url value='/approval' />"><span style="color:red">Registration Approval</span></a></li>
						<li><i class="fa fa-th-list"></i><a href="<c:url value='/emergency' />">Emergency List</a></li>					  	
					</ol>
				</div>
			</div>
              <!-- page start-->
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                              Registration Approval
                          </header>
                          
                          <table class="table table-striped table-advance table-hover">
                              <tr>
                                 <th><i class="icon_profile"></i> Full Name</th>
                                 <th><i class="icon_mail_alt"></i> Email</th>
                                 <th><i class="icon_mobile"></i> Contact Number</th>
                                 <th><i class="icon_cogs"></i> Action</th>
                              </tr>
                              <tbody data-bind="foreach: members">
      							<td data-bind="text: fullName"></td>
            					<td data-bind="text: email"></td>
            					<td data-bind="text: contactNumber"></td>
            					<td>
            						<a class="btn btn-success" data-bind="tooltip: {title: verify, placement: 'left' }, attr: {id: userId}, click: $parent.approveUser"" href="" data-toggle="tooltip" data-placement="top"><span class="icon_check_alt2" aria-hidden="true"></span></a>
            						<a class="btn btn-danger" data-bind="tooltip: {title: deny, placement: 'left' }, attr: {id: userId}, click: $parent.removeUser" href="" data-toggle="tooltip" data-placement="top"><span class="icon_close_alt2" aria-hidden="true"></span></a>
            					</td>
    						  </tbody>
                              <tbody data-bind="visible: members().length == 0">
      							<td></td>
            					<td></td>
            					<td>No member for approval</td>
            					<td></td>
            					<td></td>
    						  </tbody>                         
                        </table>
                      </section>
                  </div>
              </div>
              <div class="text-center col-md-4 col-md-offset-4">
  				<ul class="pagination pagination-md">
					<li data-bind="css: { disabled: (currPage() === 1) }"><a data-bind="css: { disabled: (currPage() === 1) }, click: function() { if(currPage() != 1) { doPaging(pageSize(), currPage()-1)}}" href="">&laquo;</a></li>
				</ul>
    			<ul data-bind="foreach: pagesArray" class="pagination pagination-md">
					<li data-bind="css: { active: $parent.currPage() === pageNumber()}"><a data-bind="text: pageNumber, click: function() { $parent.doPaging($parent.pageSize(), pageNumber())}" href=""></a></li>
				</ul>
				<ul class="pagination pagination-md">
					<li data-bind="css: { disabled: (currPage() === maxNumberPages() || maxNumberPages() === 0) }"><a data-bind="click: function() { if(currPage() != maxNumberPages()) { doPaging(pageSize(), currPage()+1)}}" href="">&raquo;</a></li>
				</ul>
  			 </div>
              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
  </section>
  <!-- container section end -->
    <!-- javascripts -->
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- nicescroll -->
    <script src="resources/js/jquery.scrollTo.min.js"></script>
    <script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script>
    <!--custome script for all page-->
    <script src="resources/js/scripts.js"></script>
     <!-- Custom page for the page -->
	<script src="resources/js/pages/approval.js"></script>


  </body>
</html>
