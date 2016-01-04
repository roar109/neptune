neptuneApp.controller('HomeController', ['$scope','ownerId', 'EntityService', 
                                 function($scope, ownerId, EntityService) {
	  $scope.owner = ownerId();
	  $scope.selectedEntity = {};
	  $scope.events = [];
	 
	  $scope.clearEntitySelection = function(){
		  $scope.selectedEntity = {};
		  $scope.events = [];
	  };
	  
	  $scope.loadEventsAndEntityDetails = function(entityId){
		  EntityService.loadEventsByEntity(entityId, function(arrayData){
			  $scope.events = arrayData;
		  });
		  
		  EntityService.loadEntityById(entityId, function(data){
			  $scope.selectedEntity = data;
		  });
	  };
	  
	  $scope.addEvent = function(){
		  //console.log($scope.selectedEntity.id);
	  };
	  
	  EntityService.loadEntitiesByOwner($scope.owner, function(data){
		  $scope.entities = data;
	  });
}]);