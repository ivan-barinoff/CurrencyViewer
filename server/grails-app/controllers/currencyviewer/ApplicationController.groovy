package currencyviewer

import grails.converters.JSON

class ApplicationController {

    def index() {
        def json = [title: 'Currency Viewer backend'] as JSON
        render json
    }
}
