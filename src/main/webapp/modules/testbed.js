'use strict';

function rootController($log, $rootScope, $scope, $window) {
    $log.debug('rootController...');

    $scope.toggleSideNavBar = function (menuId) {
        $mdSidenav(menuId).toggle();
    };

    $rootScope.$on('session:invalid', function (event, data) {
        $log.debug('session invalid started...');

        $log.debug('session invalid finished...');
    });


    $scope.historyBack = function () {
        $window.history.back();
    };

    $scope.viewSource = function () {
        var s = 'view-source:' + $rootScope.currentViewSrcUrl;
        $log.info(s);
        $window.open(s);
    };
}
appControllers.controller('rootController', rootController);

function homeController($log, $rootScope, $scope) {
    $log.debug('homeController...');
    $rootScope.viewName = 'Home';

    var vm = this;
    vm.uiState = {isReady: false};

    vm.items = [];

    $scope.$on('session:properties', function (event, data) {
        init();
    });

    function init() {
        vm.uiState.isReady = true;
    }

    init();
}
appControllers.controller('homeController', homeController);

var dependents = ['ngRoute', 'ngSanitize'];
dependents.push('ngStorage');
dependents.push('green.inputmask4angular');
dependents.push('app.filters');
dependents.push('app.directives');
dependents.push('app.services');
dependents.push('app.controllers');
var app = angular.module('app', dependents);

app.config(function ($httpProvider) {
    $httpProvider.interceptors.push('generalHttpInterceptor');
});

function appConfig($routeProvider, $locationProvider) {
    $routeProvider.when('/', {
        redirectTo: '/home'
    });

    $routeProvider.when('/home', {
        templateUrl: 'home.html',
        controller: 'homeController as vm'
    });

    $routeProvider.when('/not-found', {
        templateUrl: 'notFound.html'
    });

    $routeProvider.otherwise({
        redirectTo: '/not-found'
    });
};
app.config(appConfig);

function appInit($log, $rootScope, $location, $sessionStorage) {
    $log.info('Initialization started...');

    $rootScope.$on("$routeChangeStart", function (event, next, current) {
        //$rootScope.isLoading = true;

        // $log.info('Location : ', $location.path());
        var curLocPath = $location.path();
        // $log.info('Before Current Location : ', curLocPath);
        if (curLocPath == '/not-found' || curLocPath == '/sign-out') {
            return;
        }
        $sessionStorage.eventAndGiftsCLP = curLocPath;
        //$log.info('Stored Location : ', $sessionStorage.eventAndGiftsCLP);

        var srcUrl = $location.absUrl().indexOf('index');
        srcUrl = $location.absUrl().substring(0, srcUrl);
        srcUrl = srcUrl + next.templateUrl;
        $rootScope.currentViewSrcUrl = srcUrl;
        // $log.info('srcUrl = ' + srcUrl);
    });

    $rootScope.$on("$routeChangeSuccess", function (event, next, current) {
        //$rootScope.isLoading = false;

        // $log.info('Location : ', $location.path());
        var curLocPath = $location.path();
        // $log.info('After Current Location : ', curLocPath);
    });

    $log.info('Actual Location : ', $location.path());
    var path = '/home';
    if($location.path() == '') {
        var spath = $sessionStorage.eventAndGiftsCLP;
        $log.info('Stored Location : ', spath);
        if (!spath) {
            path = '/home';
        }
        else {
            path = spath;
        }
    }
    $log.info('Computed Location : ', path);
    $location.path(path);

    $log.info('Initialization finished...');
}
app.run(['$log', '$rootScope', '$location', '$sessionStorage', appInit]);
