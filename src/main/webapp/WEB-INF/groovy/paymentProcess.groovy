import io.wybis.smartpayment.dto.SessionDto
import io.wybis.smartpayment.util.Helper

//Helper.printRequestDetails(request)

request.merchantId = params.merchantId
request.merchantName = params.merchantName
request.tranAmount = params.tranAmount
request.appTranId = params.appTranId
request.callBackUrl = params.callBackUrl

if(params.merchantId == null || params.tranAmount == null || params.appTranId == null || params.callBackUrl == null ) {
    request.messageError = 'Missing parameters... Invalid payment request!'
}
else {

    //SessionDto sessionUserDto = session[SessionService.SESSION_USER_KEY]
    SessionDto sessionUserDto = new SessionDto(id = 1)

    long pytTranId = autoNumberService.getNextNumber(sessionUserDto, 'paymentTranId')

    request.pytTranId = pytTranId as String

    request.messageSuccess = 'Payment successful! Please wait while redirecting to merchant site.'
}
console.println request.messageError
console.println request.messageSuccess

forward '/payment.gtpl'