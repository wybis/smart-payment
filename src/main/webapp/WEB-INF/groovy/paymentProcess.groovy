import io.wybis.smartpayment.dto.SessionDto
import io.wybis.smartpayment.service.SessionService

//String merchantId = params.merchantId
//String tranAmount = params.tranAmount
//String appTranId = params.appTranId
//console.println "$merchantId $merchantName $tranAmount $appTranId"

if(params.merchantId == null || params.tranAmount == null || params.appTranId == null ) {
    request.messageError = 'Missing parameters... Invalid payment request!'
}
else {

    SessionDto sessionUserDto = session[SessionService.SESSION_USER_KEY]

    request.merchantId = params.merchantId

    request.tranAmount = params.tranAmount

    request.appTranId = params.appTranId

    long pytTranId = autoNumberService.getNextNumber(sessionUserDto, 'paymentTranId')

    request.pytTranId = pytTranId as String

    request.messageSuccess = 'Payment successful! Please wait while redirecting to merchant site.'
}

forward '/payment.gtpl'