'use strict';

function rootController($log, $rootScope, $scope, $window) {
    $log.debug('rootController...');

    $rootScope.$on('session:invalid', function (event, data) {
        $log.debug('session invalid started...');

        //var alert = $mdDialog.alert({
        //    title: 'Invalid Session!',
        //    content: 'Your session is invalid. Please sign in and continue.',
        //    ok: 'Sign In'
        //});
        //$mdDialog.show(alert).finally(function () {
        //    alert = undefined;
        //    $log.info('redirecting to sign in...');
        //    $window.location = 'index.html#/sign-in';
        //});

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
//dependents.push('blockUI');
//dependents.push('presence');
//dependents.push('angular.panels');
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
        templateUrl: 's-a/home/d-index.html',
        controller: 'indexController as vm'
    });

    $routeProvider.when('/testPayment', {
        templateUrl: 's-a/testPayment/d-default.html',
        controller: 'testPaymentController as vm'
    });

    $routeProvider.when('/testBed', {
        templateUrl: 's-a/testBed/d-default.html',
        controller: 'testBedController as vm'
    });

    $routeProvider.when('/message', {
        templateUrl: 's-a/zgeneral/d-message.html',
        controller: 'messageController as vm'
    });

    $routeProvider.when('/not-found', {
        templateUrl: 's-a/modules/d-notFound.html'
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
        $sessionStorage.smartPaymentCLPX = curLocPath;
        //$log.info('Stored Location : ', $sessionStorage.smartPaymentCLPX);

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
        //$mdSidenav('left').toggle();
    });

    var path = $location.path();
    $log.info('Actual Location : ', path);
    if (path == '') {
        var spath = $sessionStorage.smartPaymentCLPX;
        $log.info('Stored Location : ', spath);
        if (!spath || spath == '/not-found') {
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
