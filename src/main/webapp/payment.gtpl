<!DOCTYPE html>
<html lang="en">
<head>
    <title>Smart Payment</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="modules_bower/skeleton/css/normalize.css">
    <link rel="stylesheet" href="modules_bower/skeleton/css/skeleton.css">
    <link rel="stylesheet" href="modules_manual/wyd_skeleton/skeleton-extended.css">

    <link rel="stylesheet" href="app/default.css">
</head>

<body>

<header>
    <div class="container">
        <div class="text-center">
            <h3>Smart Payment</h3>
        </div>
    </div>
</header>

<hr class="hr-xtra-small"/>

<main>

    <br/><br/>

    <% if (request.messageSuccess == null) { %>

    <form <% if (request.messageSuccess) { println('class="hide"') } %> id="form0" method="post"
        action="/paymentProcess" enctype="application/x-www-form-urlencoded">
    <fieldset>
        <div class="container">
            <div clas="row">
                <div class="six columns offset-by-three">
                    <% if (request.messageError) { %>
                    <div class="text-center">
                        <strong class="text-danger"><% println(request.messageError) %></strong>
                    </div>
                    <% } %>
                    <div>
                        <label>Merchant Name</label>
                        <input class="u-full-width" type="text" name="merchantName" value="<%=request.merchantName%>" readonly>
                    </div>

                    <div>
                        <label>Amount</label>
                        <input class="u-full-width" type="text" name="tranAmount" value="<%=request.tranAmount%>" readonly>
                    </div>

                    <div class="text-center">
                        <input type="submit" name="paymentType" value="Cancel Payment"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="submit" name="paymentType" value="Make Payment" class="button-primary"/>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="merchantId" value="<%=request.merchantId%>">
        <input type="hidden" name="appTranId" value="<%=request.appTranId%>">
        <input type="hidden" name="callBackUrl" value="<%=request.callBackUrl%>">
    </fieldset>
    </form>

    <% } else { %>

    <div class="container">
        <div class="row">
            <div class="eight columns offset-by-two">
                <% if(request.pytTranId) { %>
                <div class="text-center">
                    <h3 class="text-success">Transaction Id : <% println(request.pytTranId) %></h4>
                </div>
                <br/>
                <% } %>
                <div class="text-center">
                    <h5 class="text-success"><% println(request.messageSuccess) %></h5>
                </div>
            </div>
        </div>
    </div>

    <% } %>

</main>

<footer class="bg--off-white">
    <hr class="hr-xtra-small"/>
    <div class="container">
        <div class="text-center">
            <p>Smart Payment - Payment Gateway Simulator. &nbsp;&nbsp;&nbsp;&nbsp; &copy; Wybis India Pvt. Ltd.</p>
        </div>
    </div>
</footer>

<% if (request.messageSuccess != null) { %>

<script type="application/javascript">

    function processCallBack() {
        console.log('processing callback started...');

        var form = document.createElement('form');

        form.setAttribute('method', 'post');
        form.setAttribute('action', '<%print(request.callBackUrl)%>');

        var hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', 'pytStatus');
        hiddenField.setAttribute('value', '<%print(request.pytStatus)%>');
        form.appendChild(hiddenField);

        hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', 'appTranId');
        hiddenField.setAttribute('value', '<%print(request.appTranId)%>');
        form.appendChild(hiddenField);

        <% if(request.pytStatus == 'success') { %>

        hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', 'pytTranId');
        hiddenField.setAttribute('value', '<%print(request.pytTranId)%>');
        form.appendChild(hiddenField);

        <% } %>

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
