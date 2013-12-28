
var webAnsibleApp = angular.module('webAnsibleApp', [ 'ngRoute', 'webAnsibleApp.controllers' ]);

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

var webControllers = angular.module('webAnsibleApp.controllers', []);

webControllers.controller('GroupsCtrl',
		  function($scope, $location, $rootScope) {
	var findActiveGroup = function(id){
		for(i in $scope.groups){
			if($scope.groups[i].id == id) return $scope.groups[i];
		}
		return $scope.groups[0].id;
	}
	$scope.groups = groupMockup;
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
		
		//alert($rootScope.groupId);
		//mockuped!!!!!
		for(i in hostMockup) if(hostMockup[i].id == $rootScope.groupId) return hostMockup[i].hosts;
	}
	
	$scope.change = function(){
		$rootScope.groupName = $scope.activeGroup.name;
		$rootScope.groupId = $scope.activeGroup.id;
		$rootScope.hosts = $scope.getHosts();
		//alert("changed group function change()  grouId = " + $rootScope.groupId + " \n"+  $rootScope.hosts );
	}
	$scope.ajaxSave = function(){
		
	}	
	$scope.ajaxAlter = function(){
		
	}
	$scope.change();
});

webControllers.controller('UserCtrl',  
		  function($scope, $location, $rootScope) {
	var userName = "";
	$rootScope.userName = userName;
	var userPassword = "";
	$rootScope.userPassword = userPassword;
});


//not implemented ///////////////////

webControllers.controller('FilesCtrl',
  function($scope, $location,  $rootScope) {
   
  });
webControllers.controller('UsersCtrl',
  function($scope, $location,  $rootScope) {
   
  });
webControllers.controller('PlaybooksCtrl',
  function($scope, $location,  $rootScope) {
   
  });
webControllers.controller('AptSourcesCtrl', 
  function($scope, $location,  $rootScope) {
   
  });
webControllers.controller('AptPackagesCtrl', 
  function($scope, $location,  $rootScope) {
   
  });



 
