'use strict';


describe('webAnsibleApp.controllers', function() {
    beforeEach(module('webAnsibleApp'));
     var rootScope, scope, http;
    beforeEach(inject(function ($rootScope, $httpBackend, $controller) {
    	scope = $rootScope.$new();
         rootScope = $rootScope;
         http = $httpBackend;
    }));
    
    describe('UserCtrl', function(){
    	var controller, location;
    	var ctrl ;
    	beforeEach(inject(function($controller) {
    		ctrl = $controller('UserCtrl', { $scope: scope, $location : location, $rootScope:rootScope });
    	}));
    
   
   it('should rootScope name be empty', function() {
    expect(rootScope.userName.length).toBe(0);
    });

    it("Have to change user name affter edit input username ",function(){
		return true;
	});
   
   it("Have to change password affter edit input password ", function(){
		return true;
	});
    
    });
    
    
    describe('HostCtrl', function() {
	   
	    
	    it("should  show actual hosts", function(){
			return true;
		});
		
		it("should  change hosts list after change group in GroupController", function(){
			return true;
		});
		
	it("should  move hosts to available hosts after  click on button", function(){
		return true;
		});
		
	it("should remove available host after click on button", function(){
		return true;
		});
	    
  });
    
    
    });

