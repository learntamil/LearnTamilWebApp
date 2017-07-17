(function()
	{
		'use strict'
		angular.module('mainApp',['ngRoute','app.controller'])
		.config(applicationConfig)
		.config(routeConfig)
		.run(preventUnauthorisedAccess);

		applicationConfig.$inject=["$httpProvider","$compileProvider"];
		function applicationConfig($httpProvider,$compileProvider)
		{
			$httpProvider.useApplyAsync(true);
			//TODO: commented in case of production
			$compileProvider.debugInfoEnabled(true);
			//TODO: needs to be uncomented in case of production
			/*$compileProvider.commentDirectivesEnabled(false);
			$compileProvider.cssClassDirectivesEnabled(false);*/
		}
		routeConfig.$inject=["$routeProvider","$locationProvider"];
		function routeConfig($routeProvider,$locationProvider)
		{
			$routeProvider.when('/',{
				controller:'HomeScreenController',
				controllerAs:'homeScreenCtrl'
			}).otherwise('/');
		}
		preventUnauthorisedAccess.$inject=['$rootScope','$location','$log'];
		function preventUnauthorisedAccess($rootScope,$location,$log)
		{
			$log.debug("preventUnauthorisedAccess");
			/*$rootScope.on()*/
		}
	})()