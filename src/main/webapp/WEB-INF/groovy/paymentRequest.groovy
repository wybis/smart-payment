import io.wybis.smartpayment.util.Helper

//Helper.printRequestDetails(request)

if(params.merchantId == null || params.merchantName == null || params.tranAmount == null || params.appTranId == null || params.callBackUrl == null ) {
    String message = 'Missing parameters... Invalid payment request!'
    println message
    return
}

request.merchantId = params.merchantId

request.merchantName = params.merchantName

request.tranAmount = params.tranAmount

request.appTranId = params.appTranId

request.callBackUrl = params.callBackUrl

forward '/payment.gtpl'