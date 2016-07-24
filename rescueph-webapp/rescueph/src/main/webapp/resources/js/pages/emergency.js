$(function() {
	/**
	 * Start of knockout.js
	 * 
	 */
	function Message(message) {
	    this.messageId = ko.observable(message.messageid);
	    this.userId = ko.observable(message.userid);
	    this.fullname = ko.observable(message.fullname);
	    this.message = ko.observable(message.message);
	    this.coordinates = ko.observable(message.xcoord + ", " + message.ycoord);
	    this.status = ko.observable(message.status);
	    this.contactNumber = ko.observable(message.contactnumber);
	    this.sendHelp = ko.observable("Send help to user " + message.userid);
	    this.closeCase = ko.observable("Close case for user " + message.userid);
	}
	// Overall viewmodel for this screen, along with initial state
	function EmergencyViewModel() {
		var self = this;
	    
		//array of messages
		self.messages = ko.observableArray([]);
		self.messagesCopy = ko.observableArray([]);
	    
		//for paging
	    self.pageSize = ko.observable(10);
	    self.NumberPages = ko.observable(1);
	    self.currPage = ko.observable(1);
	    self.pagesArray = ko.observableArray([]);
	    self.maxNumberPages = ko.observable(1);
	    
		// get the list of emergencies on first load from DB, using REST web service
	    $.getJSON("http://localhost:8080/rescueph/rest/messages", function(messages) {
	    	var mappedItems = $.map(messages, function(message) { return new Message(message); });
	        
	    	//make a copy of the members
	        self.messagesCopy(mappedItems);
	        
	        //Do paging on first load
	        self.doPaging(self.pageSize());
	       
	    });
	    
	    self.sendHelp = function(message, event) {
	    	
	    	// get itemId of the row
			var messageId = event.currentTarget.id;
			
			bootbox.confirm({
				message: "You are about to deploy help.\nDo you want to proceed?",
				closeButton: false,
				size: "small",
			    callback: function(result){
			    	if(result) {
						var url = 'http://localhost:8080/rescueph/rest/messages/update/'+messageId+'/1';
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
	    
	    self.closeCase = function(message, event) {
	    	
	    	// get itemId of the row
			var messageId = event.currentTarget.id;
			
			bootbox.confirm({
				message: "You are about to close help.\nDo you want to proceed?",
				closeButton: false,
				size: "small",
			    callback: function(result){
			    	if(result) {
						var url = 'http://localhost:8080/rescueph/rest/messages/update/'+messageId+'/2';
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
	    	
	    	var messagesArray = ko.observableArray([]);
	    	
	    	messagesArray(self.messagesCopy().slice()); 
	    	
	    	//clear messages
	    	self.messages.removeAll();
	    	
	    	//set current page as next page if nextPage is defined
	    	if(nextPage)
	    		self.currPage(nextPage);
	    	
	    	//set page size
	    	self.pageSize(pageSize);
	    	
	    	//calculate number of pages
	        self.NumberPages(Math.ceil(messagesArray().length/self.pageSize()));
	        
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
	    		if(messagesArray()[i]) {
	    			self.messages.push(messagesArray()[i]);
	    		}
	    		else {
	    			break;
	    		}
	    		
	    	}
	    };  
	    
	    
	    
	}
	ko.applyBindings(new EmergencyViewModel());
});