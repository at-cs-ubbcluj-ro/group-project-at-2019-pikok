var myApp = angular.module('myApp', [
  'ngRoute',
]);

myApp.config(function ($routeProvider) {
  $routeProvider
    .when('/conditions', {
      templateUrl: 'views/main.html',
    });
});
