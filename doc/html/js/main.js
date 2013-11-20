


var phonecatApp = angular.module('app', [ 'ngRoute',
		'webControllers' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
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

webControllers.controller('HostsCtrl', []);
webControllers.controller('CommandsCtrl', []);
webControllers.controller('FilesCtrl', []);
webControllers.controller('UsersCtrl', []);
webControllers.controller('PlaybooksCtrl', []);
webControllers.controller('AptSourcesCtrl', []);
webControllers.controller('AptPackagesCtrl', []);

function GroupsController($scope, $location) {
	var findActiveGroup = function(id){
		for(i in $scope.groups){
			if($scope.groups[i].id == id) return $scope.groups[i];
		}
		return $scope.groups[0].id;
	}
	$scope.groups = [{name:"Klasa",id:23}, {name:"Sala",id:22},{name:"Hala",id:34}];
	//var arrayURL = $location.absUrl().split('#')[0].split('/')
	//var groupId = arrayURL[arrayURL.length -1];
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
 