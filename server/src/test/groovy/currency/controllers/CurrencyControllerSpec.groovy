package currency.controllers

import currency.CacheService
import currency.CurrencyService
import currency.api.CbrApiService
import currencyviewer.CurrencyController
import currencyviewer.CurrencyRecord
import currencyviewer.enums.CurrencyCode
import grails.test.hibernate.HibernateSpec
import grails.testing.web.controllers.ControllerUnitTest

class CurrencyControllerSpec extends HibernateSpec implements ControllerUnitTest<CurrencyController> {

    def setup() {

    }

    def cleanup() {
    }

    void "fetches currency records and compares with the required json"() {
        given:
        String requiredJsonResponse = new File("src/test/groovy/resources/response_fetch.json").text
        controller.currencyService = Spy(CurrencyService)

        def cacheServiceSpy = Spy(CacheService)
        controller.currencyService.cacheService = cacheServiceSpy
        controller.cacheService = cacheServiceSpy

        def cbrApiServiceMock = Spy(CbrApiService)
        cbrApiServiceMock.fetchRecords(_, _, CurrencyCode.USD) >> ["11.02.2018": "56,2222", "12.02.2018": "55,2222", "13.02.2018": "54,2222", "14.02.2018": "53,2222"]
        cbrApiServiceMock.fetchRecords(_, _, CurrencyCode.EUR) >> ["11.02.2018": "76,2222", "12.02.2018": "77,2222", "13.02.2018": "78,2222", "14.02.2018": "79,2222"]

        controller.currencyService.cbrApiService = cbrApiServiceMock

        when:
        controller.fetch()

        then:
        CurrencyRecord.count() == 4
        response.contentAsString == requiredJsonResponse
    }
}
