phonon.options({
    navigator: {
        defaultPage: 'home',
        animatePages: true,
        enableBrowserBackButton: true,
        templateRootDirectory: './tpl'
    },
    i18n: null
});

var app = phonon.navigator();

app.on({page: 'home', preventClose: false, content: null});
app.on({page: 'message', preventClose: true, content: null, readyDelay: 1});
app.on({page: 'test-payment', preventClose: true, content: null, readyDelay: 1});
app.on({page: 'test-bed', preventClose: true, content: null, readyDelay: 1});

app.start();