<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Angular</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script>

        var app = angular.module("firstApp", []);

        app.controller("firstController", function($scope) {
            $scope.identification = 1;
            $scope.fullName = "Peter Griffin";
        });

        app.controller("secondController", function($scope) {
            $scope.identification = 2;
            $scope.fullName = "Lois Griffin";
        });

        app.controller("numbersController", function($scope) {
            $scope.numbers = [2, 4, 6, 7, 99, 0];
        });

        app.directive("testDirective", function() {
            return {
                template : "TEST__DIRECTIVE"
            };
        });

        app.controller("personCtrl", function($scope) {
            $scope.persons = [
                {name: "Peter", country: "Uruguay"},
                {name: "Lois", country: "Russia"},
                {name: "Bryan", country: "Afghanistan"},
                {name: "Chris", country: "Italy"},
                {name: "Meg", country: "America"},
                {name: "Stewie", country: "United Kingdom"},
                {name: "Glenn", country: "Peru"},
                {name: "Joe", country: "Norway"},
                {name: "Cleveland", country: "Bangladesh"}
            ];
            $scope.orderByMe = function(orderType) {
                $scope.order = orderType;
            }
        });

        app.controller("personController", function($scope, $http) {
            $scope.categories = ["firstName", "middleName", "lastName"];
            $http.get("/rest/persons")
            .then(function(response) {
                $scope.people = response.data;
            }, function(response) {
                alert("Error");
            });
        });

    </script>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div ng-app="firstApp">
        <p>FILTER : <input type="text" ng-model="theFilter"></p>
        <p ng-controller="personController">
            ORDER BY:
            <select ng-model="selectedOrder">
                <option ng-repeat="category in categories">{{ category }}</option>
            </select>
        </p>
        <table border="1" ng-controller="personController">
            <tr>
                <th>FIRST NAME</th>
                <th>MIDDLE NAME</th>
                <th>LAST NAME</th>
                <th>GWA</th>
                <th>DELETE</th>
            </tr>
            <tr ng-repeat="person in people | filter: theFilter">
                <td>{{ person.name.firstName }}</td>
                <td>{{ person.name.middleName }}</td>
                <td>{{ person.name.lastName }}</td>
                <td>{{ person.gwa }}</td>
                <td></td>
            </tr>
        </table>
    </div>

</body>
</html>
