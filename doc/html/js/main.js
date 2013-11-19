

function ListCtrl($scope) {
	$scope.hosts = {
		group : 'klasa',
		host : 'PC1',
		IP : '10.0.0.2'
	};
}

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

function GroupsController($scope) {
	$scope.groups = [{name:"Klasa",
		id:23}, {name:"Sala",id:22}];
	//$('.selectpicker').selectpicker();
}
 