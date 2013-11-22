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

  //beforeEach(module('webAnsibleApp'));

  describe('HostsCtrl', function(){
   
  });

});

describe("User Controller", function(){
	var scope =  {};
	var userCtrl = new UserCtrl(scope);
	it("Have to change user name affter edit input username ", function(){
		
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
	});
});
