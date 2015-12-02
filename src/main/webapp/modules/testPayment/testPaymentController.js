function testPaymentController($log, $rootScope, $scope, wydNotifyService, sweet, $window) {
    $log.debug('testPaymentController...');
    $rootScope.viewName = 'Test Payment';

    var vm = this; vm.uiState = {isReady: false};
    var defaultPr = {
        merchantId: 'testMerchant',
        merchantName: 'Test Merchant',
        tranAmount: 5000,
        appTranId: 1,
        callBackUrl: 'http://localhost:1131/paymentCallBack'
    };

    vm.paymentRequest = {};

    vm.reset = function () {
        _.assign(vm.paymentRequest, defaultPr);
    };

    vm.makePayment = function () {
        sweet.show({
            title: 'Confirm',
            text: 'Are you sure to make payment?',
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#DD6B55',
            confirmButtonText: 'Yes, do it!',
            closeOnConfirm: false
        }, function () {
            var url = '/paymentRequest';
            url += '?merchantId=' + vm.paymentRequest.merchantId;
            url += '&merchantName=' + vm.paymentRequest.merchantName;
            url += '&tranAmount=' + vm.paymentRequest.tranAmount;
            url += '&appTranId=' + vm.paymentRequest.appTranId;
            url += '&callBackUrl=' + vm.paymentRequest.callBackUrl;
            $window.location = url;
        });
    };

    vm.reset();
}
appControllers.controller('testPaymentController', testPaymentController);



