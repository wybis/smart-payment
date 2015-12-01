
//String merchantId = params.merchantId
//String merchantName = params.merchantName
//String tranAmount = params.tranAmount
//String appTranId = params.appTranId
//console.println "$merchantId $merchantName $tranAmount $appTranId"

if(params.merchantId == null || params.merchantName == null || params.tranAmount == null || params.appTranId == null ) {
    request.message = 'Missing parameters... Invalid payment request!'
}
else {

    request.merchantId = params.merchantId

    request.merchantName = params.merchantName

    request.tranAmount = params.tranAmount

    request.appTranId = params.appTranId
}

forward '/payment.gtpl'