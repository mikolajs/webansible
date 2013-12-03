


webControllers.controller('HostsCtrl',  
  function($scope, $location, $rootScope) {
	$scope.hostsAvail = availHostsMockup;
	$scope.editHostName = "";
	$scope.editHostIp = "";
	$scope.editHostFirm = false;
	$scope.editHostId= -9999;
	
	$scope.removeHostFromGroup = function(index){
		var removeHost = $rootScope.hosts.splice(index,1)[0];
		$scope.hostsAvail.push(removeHost);
		
		alert("removeHostFromGroup - want to remove" + h[0].name);//	$rootScope.hosts[index].name);
	}
	
	$scope.editHost = function(index){
		var host =  $scope.hostsAvail[index];
		$scope.editHostName = host.name;
		$scope.editHostIp = host.ip;
		$scope.editHostFirm = (host.firm == 'Tak') ;
		$scope.editHostId = host.id;
	}
	
	$scope.addToGroup = function(index){
		var removeHost = $scope.hostsAvail.splice(index,1)[0];
		$rootScope.hosts.push(removeHost);
		$scope._clearEdit();
	}
	
	$scope.addHost = function(){
			var firmTemp = 'Nie';
			if($scope.editHostFirm) firmTemp = 'Tak' ;
			 $scope.hostsAvail.push({id:Math.floor(Math.random()*-1000) ,
				 name:$scope.editHostName, 
				ip:$scope.editHostIp,
				firm:firmTemp, 
				ping:"------"});
			$scope._clearEdit();
	
	}
	
	$scope.alterHost = function(){
			 for(i in $scope.hostsAvail){
				   if($scope.hostsAvail[i].id == $scope.editHostId ){
					   $scope.hostsAvail[i].name  = $scope.editHostName;
					   $scope.hostsAvail[i].ip = $scope.editHostIp;
					   if($scope.editHostFirm) $scope.hostsAvail[i].firm = 'Tak' ;
					   else $scope.hostsAvail[i].firm = 'Nie' ;
					   $scope._clearEdit();
					   return;
				   }			   
			   }
			alert("Błąd, nie znaleziono elementu do zmiany");l
		}
	
	$scope.delHost = function(){
		if( $scope.editHostId){$scope._clearEdit(); return;}
		if (confirm("Na pewno chcesz usunąć hosta  " + $scope.editHostId + " : " + $scope.editHostName + "?" )) {
			 for(i in $scope.hostsAvail){
				 if($scope.hostsAvail[i].name == $scope.editHostNameOrgin ){
				 $scope.hostsAvail.splice(i,1);
					$scope._clearEdit();
					 return;
				   }			   
			   }
			$scope._clearEdit();
		}
	}
	
	
	$scope._clearEdit = function() {
		$scope.editHostName = "";
		$scope.editHostIp = "";
		$scope.editHostFirm = false;
		$scope.editHostNameId = -9999;
	}
  });