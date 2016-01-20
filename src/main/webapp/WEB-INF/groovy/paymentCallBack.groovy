import io.wybis.smartpayment.util.Helper

//Helper.dumpRequest(request)

String path = '/index-s-a.html#/message?'

if (params.pytStatus == 'canceled') {
    path += "pytStatus=${params.pytStatus}"
    path += "&warningMessage=Your recently raised payment has been canceled."
    //systemout.println path
    redirect path
    return
}

if (params.appTranId == null || params.pytTranId == null) {
    path += "errorMessage=Oops, something went wrong... Your payment is failed."
    //systemout.println path
    redirect path
}

long appTranId = params.appTranId as Long
long pytTranId = params.pytTranId as Long
path += "pytStatus=${params.pytStatus}"
path += "&description=Your transaction id is $appTranId and the payment gateway transaction id is $pytTranId";
path += "&successMessage=Your payment is successful";
//systemout.println path

redirect path
