/**
 * This is the Jquery file for the ecc.jsp page
 */
$(function() {
	/**
	 * Start of knockout.js
	 * 
	 */
	
	function ECCViewModel() {
		var self = this;
		
		//for timer
		var gTimer = null;
		var gStatuses = [false, false];
		
		//global variables for use in high charts
		self.currentView = ko.observable("Philippines");
		
		//counters for users and emergencies
		self.userCounter = ko.observable(0);
		self.emergencyCounter = ko.observable(0);
		
		//text equivalent for counters
		self.userCounterText = ko.observable(self.userCounter() + " unverified users");
		self.emergencyCounterText = ko.observable(self.emergencyCounter() + " open emergency cases");
		
		//markers
		self.markers = ko.observableArray([]);
		// marker properties
		var cartMarker = L.AwesomeMarkers.icon({
			markerColor: 'red',
			prefix: 'glyphicon',
		    icon: 'thumbs-down',
		    iconColor: 'black'
		});
		
	    var customControl = L.Control.extend({
			  options: {
			    position: 'topleft' 
			    //control position - allowed: 'topleft', 'topright', 'bottomleft', 'bottomright'
			  },
			  onAdd: function (map) {
				    var container = L.DomUtil.create('div', 'leaflet-control leaflet-control-custom');
				    return container;
			  }
		});
	    
		//initialise the map
		var mymap = L.map('map', { zoomControl: true });
		// Disable drag and zoom handlers.
		//mymap.touchZoom.disable();
		//mymap.scrollWheelZoom.disable();
		//mymap.keyboard.disable();
		//mymap.dragging.disable();
		//mymap.doubleClickZoom.disable(); 
		//add custom control
		mymap.addControl(new customControl());
		
		
		// load a tile layer
		L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
		    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, Imagery © <a href="http://mapbox.com">Mapbox</a>',
		    minZoom: 6,
		    maxZoom: 18,
		    id: 'roldanreal.pmm3gdhh',
		    accessToken: 'pk.eyJ1Ijoicm9sZGFucmVhbCIsImEiOiJjaW15OXZkNGswM3p3djdrazdmbHVrdHl2In0.Q1oLza6ZqNpyKDiGs24wbg'
		}).addTo(mymap);
		
		//GeoJSON Layer Manila
		new L.GeoJSON.AJAX('getManilaJson', {color: '#FFFFFF'}).addTo(mymap);
		
		self.getEmergencies = function() {
			var statusIdx = 1 - 1;
			if (gStatuses[statusIdx]) {
				console.log("Report 1: waiting for response, skip request");
				return;
			}
			
			gStatuses[statusIdx] = true;
			//get messages using REST
			var messagesURL = "http://localhost:8080/rescueph/rest/messages";
			$.ajax({
				url: messagesURL,
				dataType: 'json',
				success: function(messages) {
					gStatuses[statusIdx] = false;
					//set count of emergency
					self.emergencyCounter(messages.length);
					self.emergencyCounterText(self.emergencyCounter() + " open emergency cases");
					
					for(var x in messages) {
						//extend the marker to add the branch name in the options
						var lat = messages[x].xcoord;
						var long = messages[x].ycoord;
						
	    				var customMarker = L.Marker.extend({
	    					options: { 
	    						message: messages[x].message
	    					}
	    				});
	    				var marker = new customMarker([lat,long], {icon: cartMarker}).addTo(mymap)
	    			    .bindPopup(messages[x].message + '<br /><b>'
	    			    		, {autoPan:false})
	    			    .on('mouseover', function() { this.openPopup(); })
	    				.on('mouseout', function() { this.closePopup(); })
	    				.on('click', function(e) {
	    					//reset all icons
	    					for(var y in self.markers()) {
	    						var indivMarker = self.markers()[y];
	    						indivMarker.setIcon(cartMarker);
	    					}
	    				});
	    				
	    				
	    				//self.markers.push(marker);
					}
				},
				error: function() {
					gStatuses[statusIdx] = false;
				}
			});
		};
		
		self.getUsers = function() {
			var statusIdx = 2 - 1;
			if (gStatuses[statusIdx]) {
				console.log("Report 1: waiting for response, skip request");
				return;
			}
			
			gStatuses[statusIdx] = true;
			//get messages using REST
			var messagesURL = "http://localhost:8080/rescueph/rest/members";
			$.ajax({
				url: messagesURL,
				dataType: 'json',
				success: function(members) {
					gStatuses[statusIdx] = false;
					//set count of emergency
					self.userCounter(members.length);
					self.userCounterText(self.userCounter() + " unverified users");
				},
				error: function() {
					gStatuses[statusIdx] = false;
				}
			});
		};
		
		self.viewPhils = function() {
			mymap.setView([14.547024, 121.017371],11); //San Lorenzo Village, Makati as center
			
			getAllReports();
			
		};
		
		//first view
		self.viewPhils();
		
		//get reports
		function getAllReports() {
			if (gTimer) {
				clearTimeout(gTimer);
			}
			console.log("getting all reports");
			self.getEmergencies();
			self.getUsers();
			gTimer = setTimeout(function() {getAllReports();}, 15000);
		};
	}
	ko.applyBindings(new ECCViewModel());
});