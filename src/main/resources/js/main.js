
var hostMockup = [{id:23, hosts:[{name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]}, 
		                                 {id:22, hosts:[{name:"KOM1", ip:"10.0.2.1", firm:"Nie", ping:"23.12.2014 14:30"},
		       		                                 {name:"KOM2", ip:"10.0.2.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		       		                                 {name:"KOM3", ip:"10.0.2.4", firm:"Nie", ping:"23.12.2014 14:30"}]},
		                  {id:34,  hosts:[{name:"HAL1", ip:"199.0.0.1", firm:"Tak", ping:"23.12.2014 14:30"},
			                                 {name:"HAL2", ip:"199.0.0.3", firm:"Tak", ping:"23.12.2014 14:30"}]} ];

var webAnsibleApp = angular.module('webAnsibleApp', [ 'ngRoute',
		'webControllers' ]);

webAnsibleApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/hosts', {
		templateUrl : 'hosts.html',
		controller : 'HostsCtrl'
	}).when('/commands', {
		templateUrl : 'commands.html',
		controller : 'CommandsCtrl'
	}).when('/files', {
		templateUrl : 'files.html',
		controller : 'FilesCtrl'
	}).when('/users', {
		templateUrl : 'users.html',
		controller : 'UsersCtrl'
	}).when('/playbooks', {
		templateUrl : 'playbooks.html',
		controller : 'PlaybooksCtrl'
	}).when('/apt-sources', {
		templateUrl : 'apt-sources.html',
		controller : 'AptSourcesCtrl'
	}).when('/apt-packages', {
		templateUrl : 'apt-packages.html',
		controller : 'AptPackagesCtrl'
	}).otherwise({
		redirectTo : '/hosts'
	});
} ]);

var webControllers = angular.module('webControllers', []);

webControllers.controller('GroupsCtrl',
		  function($scope, $location, $rootScope) {
	var findActiveGroup = function(id){
		for(i in $scope.groups){
			if($scope.groups[i].id == id) return $scope.groups[i];
		}
		return $scope.groups[0].id;
	}
	$scope.groups = [{name:"Klasa",id:23}, {name:"Sala",id:22},{name:"Hala",id:34}];
	// its for production
	// var arrayURL = $location.absUrl().split('#')[0].split('/')
	// var groupId = arrayURL[arrayURL.length -1];
	$scope.activeGroup = findActiveGroup(23);
	$scope.inputGroupName = "";
	$scope.openEdit = false;
	$scope.openAdd = false;
	
	$scope.add = function() {
		$scope.inputGroupName = "";
		$scope.openEdit = false;
		$scope.openAdd = true;
		
	}
	
	$scope.edit = function() {
		$scope.inputGroupName = $scope.activeGroup.name;
		$scope.openAdd = false;
		$scope.openEdit = true;
	}
	
	$scope.delete = function(){
		if(confirm("Chcesz usunąć grupę " + $scope.activeGroup.name + "?\n" +  
				"Wszelkie informacje o grupie zostaną utracone!")) {
			alert("Kasuję");
		}
				
	}
	
	$scope.save = function() {
		$scope.activeGroup.name = $scope.inputGroupName;
		$scope.openAdd = false;
	}
	
	$scope.alter = function() {
		
		$scope.openEdit = false;
	}
	
	$scope.close = function(){
		$scope.openAdd = false;
		$scope.openEdit = false;
	}
	
	$scope.getHosts = function() {
		
		alert($rootScope.groupId);
		//mockuped!!!!!
		for(i in hostMockup) if(hostMockup[i].id == $rootScope.groupId) return hostMockup[i].hosts;
	}
	
	$scope.change = function(){
		$rootScope.groupName = $scope.activeGroup.name;
		$rootScope.groupId = $scope.activeGroup.id;
		$rootScope.hosts = $scope.getHosts();
		alert("changed group function change()  grouId = " + $rootScope.groupId + " \n"+  $rootScope.hosts );
	}
	$scope.ajaxSave = function(){
		
	}	
	$scope.ajaxAlter = function(){
		
	}
	$scope.change();
});

webControllers.controller('UserCtrl',  
		  function($scope, $location, $rootScope) {
	$rootScope.userName = "";
	$rootScope.userPassword = "";
});


webControllers.controller('HostsCtrl',  
  function($scope, $location, $rootScope) {
	$scope.hostAvail = [];
	
	//alert("hostCtrl  host 0 = "  + $rootScope.hosts[0].name );
	
	$scope.removeHostFromGroup = function(hostName){
		alert("removeHostFromGroup - want to remove" + hostName);
	}
	
	$scope.editAvailableHost = function(hostName){
		alert("editHost - want to remove" + hostName);
	}
	
	$scope.addToGroup = function(){
		
	}
  });
webControllers.controller('CommandsCtrl',
  function($scope, $location) {
   
  });
webControllers.controller('FilesCtrl',
  function($scope, $location) {
   
  });
webControllers.controller('UsersCtrl',
  function($scope, $location) {
   
  });
webControllers.controller('PlaybooksCtrl',
  function($scope, $location) {
   
  });
webControllers.controller('AptSourcesCtrl', 
  function($scope, $location) {
   
  });
webControllers.controller('AptPackagesCtrl', 
  function($scope, $location) {
   
  });



 
