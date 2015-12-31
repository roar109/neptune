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

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script src="//code.angularjs.org/1.4.8/angular-resource.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/services.js"></script>
<script type="text/javascript" src="js/controller.js"></script>
</head>
<body ng-controller='HomeController as home'>
	<div class="container-narrow">
		<div class=".container">
			<div class="row">
				<div class="col-md-6">
					<table class="table">
						<caption>Entities</caption>
						<tbody>
							<tr>
								<td>Description</td>
								<td>Details</td>
							</tr>
							<tr ng-repeat="obj in entities">
								<td>{{obj.description}}</td>
								<td><a href="#" ng-click="loadEntityDetails(obj.id)">+</a></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td></td>
								<td><a href="#" ng-click="clearEntitySelection()">Clear
										Selection</a></td>
							</tr>
						</tfoot>
					</table>
				</div>
				<div class="col-md-6">
					<table class="table table-bordered">
						<tr>
							<td><strong>Id</strong></td>
							<td><div>{{selectedEntity.id}}</div> <br></td>
						</tr>
						<tr>
							<td><strong>Description</strong></td>
							<td><div>{{selectedEntity.description}}</div></td>
						</tr>
						<tr>
							<td><strong>Creation</strong></td>
							<td><div>{{selectedEntity.creation}}</div></td>
						</tr>
					</table>
				</div>
			</div>
			<input type="hidden" id="owner"
				value="<%=request.getSession().getAttribute("ownerid")%>" />
		</div>
	</div>
</body>
</html>