neptuneApp.factory('entityFactory', ['$resource', function($resource) {
	return $resource('http://localhost:8080/neptune/rv1/entity/:entityId', {entityId : '@id'});
}]);

neptuneApp.factory('ownerFactory', ['$resource', function($resource) {
	return $resource('http://localhost:8080/neptune/rv1/owner/:ownerId', {ownerId : '@id'});
}]);

neptuneApp.service('EntityService', ['entityFactory','$http', function(entityFactory, $http) {
	this.loadEntityById = function(id, callback) {
		entityFactory.get({entityId : id}, function(data) {
			callback(data);
		});
	};
	this.loadEntitiesByOwner = function(ownerId, callback) {
		$http.get('http://localhost:8080/neptune/rv1/entity/owner/'+ ownerId)
		.success(function(data) {
				callback(data);
		});
	};
} ]);