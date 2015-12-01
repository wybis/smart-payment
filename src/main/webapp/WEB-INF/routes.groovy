email						to : '/receiveEmail.groovy'

jabber	chat,	 			to : '/receiveJabberMessage.groovy'
jabber 	presence,			to : '/receiveJabberPresence.groovy'
jabber	subscription, 		to : '/receiveJabberSubscription.groovy'

get 	'/favicon.ico',		redirect : '/assets/favicon.png'
get     '/',				redirect : '/index'
get     '/index',			forward  : '/index.groovy'
get 	'/info',			forward  : '/info.groovy'
get 	'/build-info',		forward  : '/build-info.groovy'
get		'/ping',			forward  : '/ping.groovy'
get		'/forbidden',		forward  : '/forbidden.groovy'
all 	'/_ah/warmup',		forward  : '/ping.groovy'

// payment gateway simulator
get	    '/paymentRequest',                              forward : '/paymentRequest.groovy'
post    '/paymentProcess',                              forward : '/paymentProcess.groovy'
post    '/paymentCallBack',                             forward : '/paymentCallBack.groovy'