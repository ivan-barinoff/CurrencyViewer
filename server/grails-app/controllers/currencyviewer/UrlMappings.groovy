package currencyviewer

import grails.util.Environment

class UrlMappings {

    static mappings = {
        "/api/fetch"(controller: 'currency') {
            [GET: 'fetch']
        }

        if ( Environment.current == Environment.PRODUCTION ) {
            '/'(uri: '/index.html')
        } else {
            '/'(controller: 'application', action:'index')
        }

//        "/"(controller: 'application', action: 'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
