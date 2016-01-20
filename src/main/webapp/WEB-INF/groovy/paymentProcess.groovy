import io.wybis.smartpayment.dto.SessionDto
import io.wybis.smartpayment.util.Helper

//Helper.dumpRequest(request)

request.merchantId = params.merchantId
request.merchantName = params.merchantName
request.tranAmount = params.tranAmount
request.appTranId = params.appTranId
request.callBackUrl = params.callBackUrl

if (params.paymentType == 'Cancel Payment') {
    request.pytStatus = 'canceled'
    request.messageSuccess = 'Payment canceled! Please wait while redirecting to merchant site.'
    forward '/payment.gtpl'
    return
}

if (params.merchantId == null
        || params.merchantName == null
        || params.tranAmount == null
        || params.appTranId == null
        || params.callBackUrl == null
        || params.paymentType == null) {
    request.messageError = 'Missing parameters... Invalid payment request!'
    forward '/payment.gtpl'
    return
}

SessionDto sessionUserDto = new SessionDto(id = 1)

long pytTranId = autoNumberService.getNextNumber(sessionUserDto, 'paymentTranId')
request.pytTranId = pytTranId as String
request.pytStatus = 'success'

request.messageSuccess = 'Payment successful! Please wait while redirecting to merchant site.'

//systemout.println request.messageError
//systemout.println request.messageSuccess

forward '/payment.gtpl'