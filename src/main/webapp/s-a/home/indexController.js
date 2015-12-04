function indexController($rootScope, $log) {
    $log.debug('indexController...');
    $rootScope.viewName = 'Home';

    var vm = this; vm.uiState = {isReady: false};
}
appControllers.controller('indexController', indexController);



