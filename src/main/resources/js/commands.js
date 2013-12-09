
webControllers.controller('CommandsCtrl',
  function($scope, $location,  $rootScope) {
	$scope.editId = -1;
   $scope.editName = "";
   $scope.editCommand = "";
   $scope.editDescription = "";
   $scope.toRunId = -1;
   $scope.toRunName = "";
   $scope.toRunHosts = [];
   $scope.history = [];
   $scope.showErrorMessage = false;
	$scope.errorMessage = "";
	$scope.commands = commandsMockup;
	$scope.commandStories = commandStoryMockup;
	
	$scope.getToEdit = function(index){
		var command = $scope.commands[index];
		$scope.editId = command.id;
		$scope.editName = command.name;
		$scope.editCommand = command.command;
		$scope.editDescription = command.description;
	}
	
	$scope.getToRun = function(index){
		var command = $scope.commands[index];
		$scope.toRunId = command.id;
		$scope.toRunName = command.name;
	}
	
   $scope.alterCommand = function(){
	   if(!$scope._isValid()) return;
	   for(i in $scope.commands){
		   if($scope.commands[i].id == $scope.editId){
			   $scope.commands[i].name = $scope.editName;
			   $scope.commands[i].command = $scope.editCommand;
			   $scope.commands[i].description = $scope.editDescirption;
			   $scope._clearEdit();
			   return;
		   }
	   }
	   alert("Nie znaleziono polecenia id= " + scope.editId);
   }
   
   $scope.deleteCommand = function(){
	   if(confirm("Na pewno chcesz usunąć polecenie " + $scope.editName)){
		   for(i in $scope.commands){
			   if($scope.commands[i].id == $scope.editId){
				   $scope.commands.splice(i, 1);
				   $scope._clearEdit();
				   return;
			   }
		   }
		   alert("Nie znaleziono polecenia id= " + scope.editId);
	   }
   }
	
   $scope.addCommand = function() {
	   if(!$scope._isValid()) return;
	   var command = {id:Math.floor(Math.random()*-1000),
			   name:$scope.editName,
			   command:$scope.editCommand,
			   description:$scope.editDescription
	   };
	   $scope.commands.push(command);
	   $scope._clearEdit();
   }
   
   $scope.runCommand = function(){
	   alert("run command: " + $scope.toRunId + " " + $scope.toRunName +  " "  + $scope.toRunHosts.join(':'));
   }
   
   $scope._isValid = function() {
		var valid = true;
		if($scope.editName < 3) {
			$scope.showErrorMessage = true;
			$scope.errorMessage = "Nazwa musi być dłuższa. ";
			valid = false;
		}
		if( $scope.editCommand < 2){
			$scope.showErrorMessage = true;
			$scope.errorMessage += "  Polecenie musi być  dłuższa.";
			valid = false;
		}
		if( $scope.editDescription < 4){
			$scope.showErrorMessage = true;
			$scope.errorMessage += " Opis musi być  dłuższy.";
			valid = false;
		}
		return valid;
	}
   
   $scope._clearEdit = function(){
	   $scope.editId = -1;
	   $scope.editName = "";
	   $scope.editCommand = "";
	   $scope.editDescription = "";
	   $scope.showErrorMessage = false;
		$scope.errorMessage = "";
   }
  });