package currency.services

import currency.CacheService
import currency.CurrencyService
import currency.api.CbrApiService
import currencyviewer.CurrencyRecord
import currencyviewer.enums.CurrencyCode
import grails.test.hibernate.HibernateSpec
import grails.testing.services.ServiceUnitTest

import java.time.LocalDate

import static currencyviewer.utils.Utils.patternDDmmYYYY

class CurrencyServiceSpec extends HibernateSpec implements ServiceUnitTest<CurrencyService> {

    List<Class> getDomainClasses() { [CurrencyRecord] }

    def setup() {

    }

    def cleanup() {
    }

    void "saves two records to cache from CBR mock and then updates the cache"() {
        given:
        service.cacheService = Spy(CacheService)

        def cbrApiServiceMock = Spy(CbrApiService)
        cbrApiServiceMock.fetchRecords(_, _, CurrencyCode.USD) >> ["11.02.2018": "56,2222", "12.02.2018": "55,2222"]
        cbrApiServiceMock.fetchRecords(_, _, CurrencyCode.EUR) >> ["11.02.2018": "76,2222", "12.02.2018": "75,2222"]
        service.cbrApiService = cbrApiServiceMock

        when:
        service.findInCacheOrFetchRecords()

        then:
        CurrencyRecord.count() == 2

        when:
        cbrApiServiceMock = Spy(CbrApiService)
        cbrApiServiceMock.fetchRecords(_, _, CurrencyCode.USD) >> ["13.02.2018": "56,2222", "14.02.2018": "55,2222"]
        cbrApiServiceMock.fetchRecords(_, _, CurrencyCode.EUR) >> ["13.02.2018": "76,2222", "14.02.2018": "75,2222"]
        service.cbrApiService = cbrApiServiceMock

        service.findInCacheOrFetchRecords()
        LocalDate lastUpdateDate = service.cacheService.findLastUpdateDate()

        then:
        CurrencyRecord.count() == 4
        lastUpdateDate == LocalDate.parse("14.02.2018", patternDDmmYYYY())
    }
}
