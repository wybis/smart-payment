function testBedController($rootScope, $log) {
    $log.debug('testBedController...');
    $rootScope.viewName = 'Test Bed';

    var vm = this; vm.uiState = {isReady: false};

}
appControllers.controller('testBedController', testBedController);



