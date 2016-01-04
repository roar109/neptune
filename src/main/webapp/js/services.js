neptuneApp.factory('entityFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/entity/:entityId', {entityId : '@id'});
}]);

neptuneApp.factory('ownerFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/owner/:ownerId', {ownerId : '@id'});
}]);

neptuneApp.factory('eventFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/event/entity/:entityId', {entityId : '@id'}, {query : {method:'GET', isArray:true}});
}]);

neptuneApp.service('EntityService', ['entityFactory','$http','eventFactory', function(entityFactory, $http, eventFactory) {
	this.loadEntityById = function(id, callback) {
		entityFactory.get({entityId : id}, function(data) {
			callback(data);
		});
	};
	
	this.loadEntitiesByOwner = function(ownerId, callback) {
		$http.get('/neptune/rv1/entity/owner/'+ ownerId)
		.success(function(data) {
				callback(data);
		});
	};
	
	this.loadEventsByEntity = function(id, callback){
		eventFactory.query({entityId : id}, function(data){
			callback(data);
		});
	};
} ]);