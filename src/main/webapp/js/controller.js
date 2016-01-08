neptuneApp.controller('HomeController', ['$scope','ownerId', 'GridLayoutElement','EntityService', 'EventService','LayoutService', 
                                 function($scope, ownerId, GridLayoutElement, EntityService, EventService, LayoutService) {
	  $scope.owner = ownerId();
	  $scope.selectedEntity = {};
	  $scope.entity_ = {};
	  $scope.events = [];
	  $scope.event_ = {};
	 
	  $scope.clearEntitySelection = function(){
		  $scope.selectedEntity = {};
		  $scope.events = [];
		  $scope.resetEventLayout();
	  };
	  
	  $scope.loadEventsAndEntityDetails = function(entityId){
		  if(entityId == undefined || entityId == null){
			  return;
		  }
		  EventService.loadEventsByEntity(entityId, function(arrayData){
			  $scope.events = arrayData;
			  $scope.createEventsLayout();
		  });
		  
		  EntityService.loadEntityById(entityId, function(data){
			  $scope.selectedEntity = data;
		  });
	  };
	  
	  $scope.addEvent = function(){
		  if(!$scope.selectedEntity.hasOwnProperty('id'))return;
		  $scope.event_ = {};
		  $('#addEventModal').modal('toggle');
	  };
	  
	  $scope.addEntity = function(){
		  $scope.entity_ = {};
		  $('#addEntityModal').modal('toggle');
	  };
	  
	  $scope.saveEvent = function(event){
		  event.entityId = $scope.selectedEntity.id;
		  EventService.saveEvent(event, function(data){
			  $('#addEventModal').modal('toggle');
			  $scope.loadEventsAndEntityDetails($scope.selectedEntity.id);
		  });
	  };
	  
	  $scope.saveEntity = function(entity){
		  entity.creator = $scope.owner;
		  EntityService.saveEntity(entity, function(data){
			  $('#addEntityModal').modal('toggle');
			  $scope.loadEntitiesByOwner();
		  });
	  };
	  
	  $scope.loadEntitiesByOwner = function(){
		  EntityService.loadEntitiesByOwner($scope.owner, function(data){
			  $scope.entities = data;
		  });
	  };
	  
	  $scope.createEventsLayout = function(){
		  $scope.resetEventLayout();
		  LayoutService.addEventElementsToEventLayout($scope.events);
	  };
	  
	  $scope.resetEventLayout = function(){
		  GridLayoutElement().empty();
	  }
	  
	  $scope.addStartupListeners = function(){
		  $('#addEventModal').on('shown.bs.modal', function () {
			  $('#eventTitle').focus()
		   });
		  
		  $('#addEntityModal').on('shown.bs.modal', function () {
			  $('#entityDescription').focus()
		  });
	  };

	  $scope.loadEntitiesByOwner();
	  $scope.addStartupListeners();
	  LayoutService.configureEventLayout();
}]);