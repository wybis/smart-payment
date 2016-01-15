function indexController($rootScope, $log) {
    var cmpId = 'indexController', cmpName = 'Home';
    $log.debug(cmpId + '...');
    $rootScope.viewName = cmpName;

    var vm = this;
    vm.uiState = {isReady: false};

    function init() {
    }

    init();
}
appControllers.controller('indexController', indexController);



