package currency

import currency.api.CbrApiService
import currencyviewer.CurrencyRecord

import java.time.LocalDate

import static currencyviewer.enums.CurrencyCode.EUR
import static currencyviewer.enums.CurrencyCode.USD
import static currencyviewer.utils.Utils.patternDDmmYYYY

class CurrencyService {

    CbrApiService cbrApiService
    CacheService cacheService

    List<CurrencyRecord> findInCacheOrFetchRecords() {
        def now = LocalDate.now()
        def monthBefore = now.minusMonths(1)
        def lastUpdateDate = cacheService.findLastUpdateDate()

        if (!lastUpdateDate) {
            fetchRecordsByDateRange(monthBefore)
        } else if (now > lastUpdateDate) {
            fetchRecordsByDateRange(lastUpdateDate.plusDays(1))
        }

        cacheService.findRecords(monthBefore, now)
    }

    private void fetchRecordsByDateRange(LocalDate start, LocalDate end = LocalDate.now()) {
        def usdRecords = cbrApiService.fetchRecords(start, end, USD)
        def eurRecords = cbrApiService.fetchRecords(start, end, EUR)

        if (usdRecords && eurRecords) {
            usdRecords.each { date, value ->
                cacheService.saveRecord(LocalDate.parse(date, patternDDmmYYYY()),
                                        value.replaceAll(",", "."),
                                        eurRecords[date].replaceAll(",", "."))
            }
        } else {
            log.error("Currency records are empty")
        }
    }
}
