import com.google.apphosting.api.ApiProxy
import com.google.apphosting.api.ApiProxy.Environment
import eu.bitwalker.useragentutils.UserAgent

UserAgent userAgent = UserAgent.parseUserAgentString(headers['User-Agent'])

println '''
<html><head><title>Info</title><head><body><pre>
'''

String s = '-', schar = '-'; int noOfChars = 80;
println s.padRight(noOfChars, schar)

try {
    println "App Info    = ${app}"
    println "Device Type = $userAgent.operatingSystem.deviceType"

    println s.padRight(noOfChars, schar)

    println "Request URI = ${request.requestURI}"
    println "Request URL = ${request.requestURL}"
    headers.each { header -> println "${header.key} = ${header.value}" }

    println s.padRight(noOfChars, schar)

    Environment env = ApiProxy.getCurrentEnvironment();
    env.getAttributes().each { attr -> println "${attr.key} = ${attr.value}" }
}
catch (Throwable t) {
    t.printStackTrace(out)
}

println s.padRight(noOfChars, schar)

println '''
</pre></body></html>
'''
