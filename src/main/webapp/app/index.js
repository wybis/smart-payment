'use strict';

function rootController($log, $rootScope, $scope, wydNotifyService, $window) {
    $log.debug('rootController...');
    $rootScope.appName = 'Smart Payment';

    $rootScope.$on('session:invalid', function (event, data) {
        $log.debug('session invalid started...');

        $log.debug('session invalid finished...');
    });
}
appControllers.controller('rootController', rootController);

var dependents = ['ngRoute', 'ngSanitize'];
dependents.push('ngStorage');
dependents.push('green.inputmask4angular');
dependents.push('ngNotify');
dependents.push('hSweetAlert');
dependents.push('selectize');
dependents.push('blockUI');
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
        templateUrl: 'app/home/indexTemplate.html',
        controller: 'indexController as vm'
    });

    $routeProvider.when('/test-payment', {
        templateUrl: 'app/testPayment/testPaymentTemplate.html',
        controller: 'testPaymentController as vm'
    });

    $routeProvider.when('/message', {
        templateUrl: 'app/zgeneral/messageTemplate.html',
        controller: 'messageController as vm'
    });

    $routeProvider.when('/not-found', {
        templateUrl: 'app/modules/notFoundTemplate.html'
    });

    $routeProvider.otherwise({
        redirectTo: '/not-found'
    });
};
app.config(appConfig);

function appInit($log, $rootScope, $location, $sessionStorage) {
    $log.debug('Initialization started...');

    $rootScope.$on("$routeChangeStart", function (event, next, current) {
        //$rootScope.isLoading = true;

        // $log.debug('Location : ', $location.path());
        var curLocPath = $location.path();
        // $log.debug('Before Current Location : ', curLocPath);
        if (curLocPath == '/not-found' || curLocPath == '/sign-out') {
            return;
        }
        $sessionStorage.smartPaymentCLPX = curLocPath;
        //$log.debug('Stored Location : ', $sessionStorage.smartPaymentCLPX);

        var srcUrl = $location.absUrl().indexOf('index');
        srcUrl = $location.absUrl().substring(0, srcUrl);
        srcUrl = srcUrl + next.templateUrl;
        $rootScope.currentViewSrcUrl = srcUrl;
        // $log.debug('srcUrl = ' + srcUrl);
    });

    $rootScope.$on("$routeChangeSuccess", function (event, next, current) {
        //$rootScope.isLoading = false;

        // $log.debug('Location : ', $location.path());
        var curLocPath = $location.path();
        // $log.debug('After Current Location : ', curLocPath);
    });

    var path = $location.path();
    $log.debug('Actual Location : ', path);
    if (path == '') {
        var spath = $sessionStorage.smartPaymentCLPX;
        $log.debug('Stored Location : ', spath);
        if (!spath || spath == '/not-found') {
            path = '/home';
        }
        else {
            path = spath;
        }
    }
    $log.debug('Computed Location : ', path);
    $location.path(path);

    $log.debug('Initialization finished...');
}
app.run(['$log', '$rootScope', '$location', '$sessionStorage', appInit]);
