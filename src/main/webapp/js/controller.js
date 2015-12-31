neptuneApp.controller('HomeController', ['$scope','ownerId', 'EntityService', 
                                 function($scope, ownerId, EntityService) {
	  $scope.owner = ownerId();
	  $scope.selectedEntity = {};
	 
	  $scope.loadEventsFromEntity = function(id){
		  EntityService.loadEntityById(id, function(data){
			  $scope.selectedEntity = data;
		  });
	  };
	  
	  $scope.clearEntitySelection = function(){
		  $scope.selectedEntity = {};
	  };
	  
	  //Load entities by owner
	  EntityService.loadEntitiesByOwner($scope.owner, function(data){
		  $scope.entities = data;
	  });
}]);