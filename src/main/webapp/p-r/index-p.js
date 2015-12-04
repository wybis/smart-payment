phonon.options({
    navigator: {
        defaultPage: 'home',
        animatePages: true,
        enableBrowserBackButton: true,
        templateRootDirectory: './p-r/tpl'
    },
    i18n: null
});


var app = phonon.navigator();

app.on({page: 'home', preventClose: false, content: 'home.html'});

app.on({
        page: 'test-payment',
        preventClose: true,
        content: 'test-payment.html',
        readyDelay: 1
    }, function testPaymentController(activity) {

        activity.onCreate(function () {
            console.log('testPaymentController init started...');

            console.log('testPaymentController init finished...');
        });

        activity.onClose(function (self) {
            self.close();
        });

    }
);

app.on({
        page: 'test-bed',
        preventClose: true,
        content: 'test-bed.html',
        readyDelay: 1
    }, function testBedController(activity) {

        activity.onCreate(function () {
            console.log('testBedController init started...');

            console.log('testBedController init finished...');
        });

        activity.onClose(function (self) {
            self.close();
        });

    }
);

app.start();