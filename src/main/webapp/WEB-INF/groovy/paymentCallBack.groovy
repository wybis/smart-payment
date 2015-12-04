import io.wybis.smartpayment.util.Helper

//Helper.printRequestDetails(request)

String path = '/index-s-a.html#/message?'

if(params.appTranId == null || params.pytTranId == null ) {
    path += 'errorMessage=Oops, something went wrong... Your payment is failed.'
}
else {
    long appTranId = params.appTranId as Long
    long pytTranId = params.pytTranId as Long
    path += "successMessage=Your payment is successful";
    path += "&description=Your transaction id is $appTranId and the payment gateway transaction id is $pytTranId";
}
//console.println path

redirect path
