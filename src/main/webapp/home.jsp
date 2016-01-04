<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="neptuneApp">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/home.css">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="//code.angularjs.org/1.4.8/angular-resource.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/services.js"></script>
<script type="text/javascript" src="js/controller.js"></script>
</head>
<body ng-controller='HomeController as home'>
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#" ng-click="clearEntitySelection()">Neptune</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Entities<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li ng-repeat="obj in entities"><a href="#"
							ng-click="loadEventsAndEntityDetails(obj.id)">{{obj.description}}</a></li>
					</ul></li>
				<li>
					<button type="button" class="btn btn-default navbar-btn"
						ng-click="addEntity()">Add Entity</button>
				</li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container">
		<div class="starter-template">
			<h3>{{selectedEntity.description}}</h3>
			<table class="table table-striped">
				<caption style="text-align: right;">
					<a class="glyphicon glyphicon-plus" aria-hidden="true" href="#"
						ng-click="addEvent()"></a>
				</caption>
				<thead>
					<tr>
						<td width="80%" align="left">Description</td>
						<td width="20%" align="left">Creation</td>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="obj in events">
						<td align="left">{{obj.description}}</td>
						<td align="left">{{obj.creation | date:"MM/dd/yyyy"}}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<input type="hidden" id="owner"
			value="<%=request.getSession().getAttribute("ownerid")%>" />
	</div>

	<div class="modal fade" tabindex="-1" role="dialog" id="addEventModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Add Event</h4>
				</div>
				<div class="modal-body">
					<form novalidate class="simple-form">
						Description: <input type="text" ng-model="event_.description"
							id="eventDescription" /><br />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						ng-click="saveEvent(event_)">Save</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" tabindex="-1" role="dialog" id="addEntityModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Add Entity</h4>
				</div>
				<div class="modal-body">
					<form novalidate class="simple-form">
						Description: <input type="text" ng-model="entity_.description"
							id="entityDescription" /><br />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						ng-click="saveEntity(entity_)">Save</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>
</body>
</html>