angular.module('myApp').controller('mainController', [
  '$scope', '$http',
  function ($scope, $http) {
     $http({
      url: 'http://localhost:8100/conditions',
      method: 'GET',
    }).then(function (res) {
      $scope.conditions = res.data;
    });

    //$scope.conditions = [{
    //  id: 1,
    //  name: 'condition1',
    //  humidity: '5.69',
    //  temperature: '28°C',
    //  x: '7.312',
    //  y: '4.234',
    //}, {
    //  id: 2,
    //  name: 'condition2',
    //  humidity: '5.69',
    //  temperature: '28°C',
    //  x: '7.312',
    //  y: '4.234',
    //  selected: true,
    //}, {
    //  id: 3,
    //  name: 'condition3',
    //  humidity: '5.69',
    //  temperature: '28°C',
    //  x: '7.312',
    //  y: '4.234',
    //}];

    $scope.selectCondition = function (condition) {
      $scope.conditions.forEach(function (condition) {
        condition.selected = false;
      });

      condition.selected = true;

      $http.put('http://localhost:8100/conditions/' + condition.id + '/select', condition, {
	  });
    }
  }
]);
