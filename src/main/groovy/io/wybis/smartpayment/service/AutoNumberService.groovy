package io.wybis.smartpayment.service;

import io.wybis.smartpayment.dto.SessionDto

interface AutoNumberService {

    long getNextNumber(SessionDto sessionUser, String key)
}
