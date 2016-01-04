neptuneApp.factory('entityFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/entity/:entityId', {entityId : '@id', creator:'@creator'},
			{query:{method:'GET', isArray:true, url  : '/neptune/rv1/entity/owner/:creator'}});
}]);

neptuneApp.factory('ownerFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/owner/:ownerId', {ownerId : '@id'});
}]);

neptuneApp.factory('eventFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/event/:id', {id:'@id', entityId:'@entityId'},
			{query:{method:'GET', isArray:true, url  : '/neptune/rv1/event/entity/:entityId'}});
}]);

neptuneApp.service('EntityService', ['entityFactory','$http', function(entityFactory, $http) {
	this.loadEntityById = function(id, callback) {
		entityFactory.get({entityId : id}, function(data) {
			callback(data);
		});
	};
	
	this.loadEntitiesByOwner = function(ownerId, callback) {
		entityFactory.query({creator : ownerId}, function(data){
			callback(data);
		});
	};
	
	this.saveEntity = function(entity, callback){
		entityFactory.save(entity, function(data){
			callback(data);
		});
	};
} ]);

neptuneApp.service('EventService', ['eventFactory', function(eventFactory) {
	this.saveEvent = function(event, callback){
		eventFactory.save(event, function(data){
			callback(data);
		});
	};
	
	this.loadEventsByEntity = function(id, callback){
		eventFactory.query({entityId : id}, function(data){
			callback(data);
		});
	};
} ]);
