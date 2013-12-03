'use strict';

/* jasmine specs for controllers go here */
describe('App controllers', function() {

  beforeEach(function(){
    this.addMatchers({
      toEqualData: function(expected) {
        return angular.equals(this.actual, expected);
      }
    });
  });

  var userCtrl = webControllers.controller('UserCtrl');
  
  describe("User Controller", function(){
		var scope =  {};
		var rootScope = {};
		
		var userCtrl = new UserCtrl(scope, rootScope);
		/*it("Have to change user name affter edit input username ", function(){
			
			 var newUser = "Nowy_user";
			 scope.userName = newUser;
			 scope.userChange();
			 expect(globalObj.user).toBe(newUser);
		});
		it("Have to change password affter edit input password ", function(){
			 var newPassword = "tajne";
			 scope.userPassword = newPassword;
			 scope.passChange();
			 expect(globalObj.password).toBe(newPassword);
		});*/
	});
  
  describe("Groups Controller",  function(){
		var scope =  {};
		var rootScope = {};
		var location ={};
		var groupCtrl = webControllers.controller(GroupsCtrl(scope, location, rootScope));
		
	it('should change globalObject.groupName after change in select', function() {
		scope.change();
		expect(rootScope.groupName).toBe(scope.activeGroup.name);
		expect(rootScope.groupId).toBe(scope.activeGroup.id);
		});
	});

	describe("Hosts Controller", function(){
		it("should  show actual hosts", function(){
			
		});
		
		it("should  change hosts list after change group in GroupController", function(){
			
		});
		
	it("should  move hosts to available hosts after  click on button", function(){
			
		});
		
	it("should remove available host after click on button", function(){
			
		});


	});
  
  

});




