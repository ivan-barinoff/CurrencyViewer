package currency.services

import currency.CacheService
import currencyviewer.CurrencyRecord
import currencyviewer.enums.CurrencyCode
import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

import java.time.LocalDate

import static currencyviewer.utils.Utils.patternDDmmYYYY

class CacheServiceSpec extends HibernateSpec implements ServiceUnitTest<CacheService> {

    List<Class> getDomainClasses() { [CurrencyRecord] }

    def setup() {
    }

    def cleanup() {
    }

    void "invokes basic cache methods"() {
        given:
        service.saveRecord(LocalDate.parse("11.02.2018", patternDDmmYYYY()), "56.2222", "75.2222")
        service.saveRecord(LocalDate.parse("12.02.2018", patternDDmmYYYY()), "57.2222", "74.2222")
        service.saveRecord(LocalDate.parse("13.02.2018", patternDDmmYYYY()), "55.2222", "76.2222")
        service.saveRecord(LocalDate.parse("14.02.2018", patternDDmmYYYY()), "58.2222", "73.2222")

        when:
        def lastUpdateDate = service.findLastUpdateDate()

        then:
        lastUpdateDate == LocalDate.parse("14.02.2018", patternDDmmYYYY())

        when:
        def maxUsdDate = service.findDateWithMaxValue(CurrencyCode.USD)

        then:
        maxUsdDate == "14.02.2018"

        when:
        def maxEurDate = service.findDateWithMaxValue(CurrencyCode.EUR)

        then:
        maxEurDate == "13.02.2018"

        when:
        def minUsdDate = service.findDateWithMinValue(CurrencyCode.USD)

        then:
        minUsdDate == "13.02.2018"

        when:
        def minEurDate = service.findDateWithMinValue(CurrencyCode.EUR)

        then:
        minEurDate == "14.02.2018"
    }
}
