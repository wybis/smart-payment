function testPaymentController($log, $rootScope, $scope, wydNotifyService, sweet, $window, $location) {
    var cmpId = 'testPaymentController', cmpName = 'Test Payment';
    $log.debug(cmpId + '...');
    $rootScope.viewName = cmpName;

    var vm = this;
    vm.uiState = {isReady: false};

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

    function init() {
        var urlPrefix = $location.protocol() + '://' + $location.host();
        if ($location.port() > 0 && $location.port() != 80) {
            urlPrefix += ':' + $location.port();
        }
        defaultPr.callBackUrl = urlPrefix + '/paymentCallBack';
        $log.info('Payment CallBackUrl : ' + defaultPr.callBackUrl);

        vm.reset();
    }

    init();
}
appControllers.controller('testPaymentController', testPaymentController);



