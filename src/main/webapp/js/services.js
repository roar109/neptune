neptuneApp.factory('EntityFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/entity/:entityId', {entityId : '@id', creator:'@creator'},
			{query : { method:'GET', isArray:true, url  : '/neptune/rv1/entity/owner/:creator'}});
}]);

neptuneApp.factory('ownerFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/owner/:ownerId', {ownerId : '@id'});
}]);

neptuneApp.factory('eventFactory', ['$resource', function($resource) {
	return $resource('/neptune/rv1/event/:id', {id:'@id', entityId:'@entityId'},
			{query : { method:'GET', isArray:true, url  : '/neptune/rv1/event/entity/:entityId'}});
}]);

neptuneApp.service('ErrorService', function() {
	this.handleRequestError = function(error){
		//TODO too ugly :)
		var txt = 'An error has ocurred in the request. Please try again.';
		var contain = $('<div>', {class:'alert alert-warning alert-dismissible', role:'alert'});
		var but = $('<button>',{type:'button', class:'close'});
		
		contain.attr('data-dismiss','alert');
		contain.attr('arial-label','Close');
		but.append($('<span>',{}).text('x').attr('aria-hidden','true'));
		
		contain.append(but);
		contain.append($('<div>', {}).text(txt));
		
		$('#mainContainer').prepend(contain);
	};
});

neptuneApp.service('EntityService', ['EntityFactory','$http','ErrorService', function(EntityFactory, $http, ErrorService) {
	this.loadEntityById = function(id, callback) {
		EntityFactory.get({entityId : id}, function(data) {
			callback(data);
		}, ErrorService.handleRequestError);
	};
	
	this.loadEntitiesByOwner = function(ownerId, callback) {
		EntityFactory.query({creator : ownerId}, function(data){
			callback(data);
		}, ErrorService.handleRequestError);
	};
	
	this.saveEntity = function(entity, callback){
		EntityFactory.save(entity, function(data){
			callback(data);
		}, ErrorService.handleRequestError);
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


neptuneApp.service('LayoutService', ['GridLayoutElement', function(GridLayoutElement) {
	var this_ = this;
	this.cssClasses = ["grid-item--height2","grid-item--height3","grid-item--height4"];
	
	this.getRandomColor = function(){
		var hex = Math.floor(Math.random() * 0xFFFFFF);
		return "#" + ("000000" + hex.toString(16)).substr(-6);
	};
	
	this.buildEventElement = function(event){
		var cssClass = 'grid-item '+this_.cssClasses[Math.floor(Math.random() * this_.cssClasses.length)];
		return $("<div>", {class: cssClass, style : 'background:'+this_.getRandomColor()}).append(this_.buildEventText(event));
	};
	
	this.configureEventLayout = function(){
		var gridLayout = GridLayoutElement().masonry({
		    itemSelector: '.grid-item',
		    columnWidth: 200,
		    gutter: 20
	   });

		gridLayout.on('click', '.grid-item', function() {
		  $(this).toggleClass('grid-item--gigante');
		  gridLayout.masonry();
		});
	  
	  this_.$gridLayout = gridLayout;
	};
	
	this.addEventElementsToEventLayout = function(eventList){
		$.each(eventList, function(index, event){
			  GridLayoutElement().append(this_.buildEventElement(event));
		});
		this_.$gridLayout.masonry();
	};
	
	this.buildEventText = function(event){
		var text = '';
		if(event.title != null && event.title != undefined){
			text+= '<h4 class="text-center">'+event.title+'</h4>';
		}
		text+= '<p class="text-center">'+event.description+'</p>';
		return text;
	};
}]);