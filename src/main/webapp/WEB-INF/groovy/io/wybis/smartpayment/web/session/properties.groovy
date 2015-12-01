package io.wybis.smartpayment.web.session

import io.wybis.smartpayment.dto.ResponseDto
import io.wybis.smartpayment.dto.SessionDto
import io.wybis.smartpayment.service.SessionService

ResponseDto responseDto = request.responseDto

def props = sessionService.properties(session), model = [:]

if (responseDto) {
    responseDto.data = props
} else {
    responseDto = new ResponseDto(data: props)
}

SessionDto sessionDto = session[SessionService.SESSION_USER_KEY]

if (sessionDto) {

    model['events'] = sessionService.events(sessionDto)

    responseDto.model = model;
}

jsonCategory.respondWithJson(response, responseDto)

