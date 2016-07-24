$(function() {
	/**
	 * Start of knockout.js
	 * 
	 */
	function Member(member) {
	    this.userId = ko.observable(member.userid);
	    this.fullName = ko.observable(member.fullname);
	    this.email = ko.observable(member.email);
	    this.isVerified = ko.observable(member.isverified);
	    this.isLoggedIn = ko.observable(member.isloggedin);
	    this.contactNumber = ko.observable(member.contactnumber);
	    this.verify = ko.observable("Verify user " + member.userid);
	    this.deny = ko.observable("Remove user " + member.userid);
	}
	// Overall viewmodel for this screen, along with initial state
	function ApprovalViewModel() {
		var self = this;
		
		//array of members
		self.members = ko.observableArray([]);
		self.membersCopy = ko.observableArray([]);
	    
		//for paging
	    self.pageSize = ko.observable(10);
	    self.NumberPages = ko.observable(1);
	    self.currPage = ko.observable(1);
	    self.pagesArray = ko.observableArray([]);
	    self.maxNumberPages = ko.observable(1);
	    
		// get the list of members on first load from DB, using REST web service
	    $.getJSON("http://192.168.171.235:8080/rescueph/rest/members", function(members) {
	    	var mappedItems = $.map(members, function(member) { return new Member(member); });
	        
	    	//make a copy of the members
	        self.membersCopy(mappedItems);
	        
	        //Do paging on first load
	        self.doPaging(self.pageSize());
	       
	    });
	    
	    self.removeUser = function(message, event) {
	    	
	    	// get userId of the row
			var userId = event.currentTarget.id;
			
			bootbox.confirm({
				message: "You are about to remove the user from the system.\nDo you want to proceed?",
				closeButton: false,
				size: "small",
			    callback: function(result){
			    	if(result) {
						var url = 'http://192.168.171.235:8080/rescueph/rest/members/delete/'+userId;
						$.ajax({
							url: url,
							type: 'POST',
							headers: {'Access-Control-Allow-Origin':'*'},
							crossDomain: true,
							success: function() {
								alert("Tapos!");
								//remove the element from the table
//								if(self.searchString() != '') {
//									//remove from searchResultsArray
//									for(var x in self.searchResultsArray()) {
//										if(self.searchResultsArray()[x].areaId() == areaId) {
//											self.searchResultsArray.remove(self.searchResultsArray()[x]);
//											break;
//										}
//									}
//						    	}
//						    	else {
//						    		self.areas.remove(area);
//						    	}
//								//remove also from the copy
//								for(var x in self.areasCopy()) {
//									if(self.areasCopy()[x].areaId() == areaId) {
//										self.areasCopy.remove(self.areasCopy()[x]);
//										break;
//									}
//								}
//								//Do paging
//						        self.doPaging(self.pageSize(), self.currPage());
						        
								$.notify({
									// options
									icon: 'glyphicon glyphicon-ok',
									message: 'Help is on the way' 
								},{
									// settings
									type: 'success',
									delay: 1000,
									offset: 55,
								});
							},
							error: function(jqXHR, textStatus, errorThrown) {
							    alert("error:" + textStatus + " exception:" + errorThrown);
							},
						});
					}
			    }
			});
	    };
	    
		self.approveUser = function(message, event) {
	    	
	    	// get userId of the row
			var userId = event.currentTarget.id;
			
			bootbox.confirm({
				message: "You are about to approve the user to use the system.\nDo you want to proceed?",
				closeButton: false,
				size: "small",
			    callback: function(result){
			    	if(result) {
						var url = 'http://192.168.171.235:8080/rescueph/rest/members/update/'+userId+'/true';
						$.ajax({
							url: url,
							type: 'POST',
							headers: {'Access-Control-Allow-Origin':'*'},
							crossDomain: true,
							success: function() {
								alert("Tapos!");
								//remove the element from the table
//								if(self.searchString() != '') {
//									//remove from searchResultsArray
//									for(var x in self.searchResultsArray()) {
//										if(self.searchResultsArray()[x].areaId() == areaId) {
//											self.searchResultsArray.remove(self.searchResultsArray()[x]);
//											break;
//										}
//									}
//						    	}
//						    	else {
//						    		self.areas.remove(area);
//						    	}
//								//remove also from the copy
//								for(var x in self.areasCopy()) {
//									if(self.areasCopy()[x].areaId() == areaId) {
//										self.areasCopy.remove(self.areasCopy()[x]);
//										break;
//									}
//								}
//								//Do paging
//						        self.doPaging(self.pageSize(), self.currPage());
						        
								$.notify({
									// options
									icon: 'glyphicon glyphicon-ok',
									message: 'Help is on the way' 
								},{
									// settings
									type: 'success',
									delay: 1000,
									offset: 55,
								});
							},
							error: function(jqXHR, textStatus, errorThrown) {
							    alert("error:" + textStatus + " exception:" + errorThrown);
							},
						});
					}
			    }
			});
	    };
	    
	    self.doPaging = function(pageSize, nextPage) {
	    	
	    	var membersArray = ko.observableArray([]);
	    	
	    	membersArray(self.membersCopy().slice()); 
	    	
	    	//clear members
	    	self.members.removeAll();
	    	
	    	//set current page as next page if nextPage is defined
	    	if(nextPage)
	    		self.currPage(nextPage);
	    	
	    	//set page size
	    	self.pageSize(pageSize);
	    	
	    	//calculate number of pages
	        self.NumberPages(Math.ceil(membersArray().length/self.pageSize()));
	        
	        //clear pages array
	        self.pagesArray.removeAll();
	        
	        //populate pagesArray
	        for(var i = 0; i < self.NumberPages(); i++) {
	        	self.pagesArray.push({
	        		pageNumber: ko.observable((i+1))
	        	});
	        }
	        
	        //set max number of pages
	        self.maxNumberPages(self.NumberPages());
	        
	        //if current page is greater than max number of pages, set currPage = maxNumberPages
	        if(self.currPage() > self.maxNumberPages())
	        	self.currPage(self.maxNumberPages());
	        
	        //if maxNumberPages is less than 1, set currPage to 1
	        if(self.maxNumberPages() < 1)
	        	self.currPage(1);
	        
	        var startIndex = (self.currPage()-1)*self.pageSize();

	    	for(var i = startIndex; i < (self.pageSize() + startIndex); i++) {
	    		if(membersArray()[i]) {
	    			self.members.push(membersArray()[i]);
	    		}
	    		else {
	    			break;
	    		}
	    		
	    	}
	    };
	}
	ko.applyBindings(new ApprovalViewModel());
});