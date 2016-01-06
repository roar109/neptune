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


neptuneApp.service('LayoutService', ['gridLayout', function(gridLayout) {
	var this_ = this;
	
	this.getRandomColor = function(){
		var hex = Math.floor(Math.random() * 0xFFFFFF);
		return "#" + ("000000" + hex.toString(16)).substr(-6);
	};
	
	this.buildEventElement = function(innerText){
		var cssClasses = [/*"grid-item--width2","grid-item--width3","grid-item--width4",*/
		                  "grid-item--height2","grid-item--height3","grid-item--height4"];
		var cssClass = 'grid-item '+cssClasses[Math.floor(Math.random() * cssClasses.length)];
		//TODO build this in a better way
		return "<div class='"+cssClass+"' style='font-size: 100%;background:"+this_.getRandomColor()+"'>"+innerText+"</div>";
	};
	
	this.configureEventLayout = function(){
		var $gridLayout = gridLayout().masonry({
		    itemSelector: '.grid-item',
		    columnWidth: 200,
		    gutter: 20
	   });

	  $gridLayout.on('click', '.grid-item', function() {
		  $(this).toggleClass('grid-item--gigante');
		  $gridLayout.masonry();
	  });
	  
	  return $gridLayout;
	};
}]);