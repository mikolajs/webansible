
var globalObj = new Object();
globalObj.groupName = ""
globalObj.groupId = 0;
globalObj.user = "";
globalObj.password = "";

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

webControllers.controller('HostsCtrl',  
  function($scope, $location) {
   
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

function GroupsCtrl($scope, $location) {
	var findActiveGroup = function(id){
		for(i in $scope.groups){
			if($scope.groups[i].id == id) return $scope.groups[i];
		}
		return $scope.groups[0].id;
	}
	$scope.groups = [{name:"Klasa",id:23}, {name:"Sala",id:22},{name:"Hala",id:34}];
	// var arrayURL = $location.absUrl().split('#')[0].split('/')
	// var groupId = arrayURL[arrayURL.length -1];
	$scope.activeGroup = findActiveGroup(22);
	$scope.openEdit = false;
	$scope.openAdd = false;
	$scope.add = function() {
		$scope.openEdit = false;
		$scope.openAdd = true;
		
	}
	$scope.edit = function() {
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
		
		$scope.openAdd = false;
	}
	$scope.alter = function() {
		
		$scope.openEdit = false;
	}
	$scope.close = function(){
		$scope.openAdd = false;
		$scope.openEdit = false;
	}
		
}

function UserCtrl($scope) {
	$scope.userName = "";
	$scope.userPassword = "";
	$scope.passChange = function(){
		globalObj.password = $scope.userPassword;
	}
	$scope.userChange = function(){
		globalObj.user = $scope.userName;
	}
	
}


 
