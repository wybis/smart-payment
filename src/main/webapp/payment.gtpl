<!DOCTYPE html>
<html lang="en">
<head>
    <title>Smart Payment</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="bower_components/skeleton/css/normalize.css">
    <link rel="stylesheet" href="bower_components/skeleton/css/skeleton.css">

    <link rel="stylesheet" href="modules/default.css">
</head>

<body>

<div class="container">
    <br>

    <div class="text-center">
        <h1>Smart Payment</h1>
    </div>
</div>

<hr class="hr-xtra-small"/>

<div id="page-body">

    <br/><br/>

    <% if (request.messageSuccess == null) { %>

    <form <% if (request.message) {
        println('class="hide"')
    } %> method="post" action="/paymentProcess">

        <div class="container">
            <div clas="row">
                <div class="six columns offset-by-three">
                    <% if (request.messageError) { %>
                    <div class="text-center">
                        <strong><% println(request.message) %></strong>
                    </div>
                    <% } %>
                    <div>
                        <label>Merchant Name</label>
                        <input class="u-full-width" type="text" name="merchantName" value="<%=request.merchantName%>"
                               disabled/>
                    </div>

                    <div>
                        <label>Amount</label>
                        <input class="u-full-width" type="text" name="tranAmount" value="<%=request.tranAmount%>"
                               disabled/>
                    </div>

                    <div class="text-center">
                        <input class="button-primary" type="submit" value="Submit">
                    </div>
                </div>
            </div>
        </div>

        <input type="hidden" name="merchantId" value="<%=request.merchantId%>">
        <input type="hidden" name="appTranId" value="<%=request.appTranId%>">
    </form>

    <% } else { %>

    <br/><br/>

    <div class="container">
        <div clas="row">
            <div class="eight columns offset-by-two">
                <div class="text-center">
                    <h4>Transaction Id : <% println(request.pytTranId) %></h4>
                </div>
                <br/>

                <div class="text-center">
                    <h5><% println(request.messageSuccess) %></h5>
                </div>
            </div>
        </div>
    </div>

    <% } %>

</div>

<hr class="hr-xtra-small"/>

<div class="container text-center">
    <p>Payment Gateway Simulator made with <a href="http://www.getskeleton.com" target="_blank">Skeleton CSS</a></p>
</div>

<% if (request.messageSuccess != null) { %>

<script type="application/javascript">

    function processCallBack() {
        console.log('processing callback started...');

        var form = document.createElement('form');

        form.setAttribute('method', 'post');
        form.setAttribute('action', '/paymentCallBack');

        var hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', 'appTranId');
        hiddenField.setAttribute('value', '<%print(request.appTranId)%>');
        form.appendChild(hiddenField);

        hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', 'pytTranId');
        hiddenField.setAttribute('value', '<%print(request.pytTranId)%>');
        form.appendChild(hiddenField);

        document.body.appendChild(form);

        console.log('processing callback finished...');

        form.submit();
    }

    document.addEventListener('DOMContentLoaded', function () {
        console.log('processing callback is going to start...');
        window.setTimeout(processCallBack, 3000);
    });

</script>

<% } %>

</body>
</html>