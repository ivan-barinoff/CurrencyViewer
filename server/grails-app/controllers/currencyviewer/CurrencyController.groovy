package currencyviewer

import currency.CacheService
import currency.CurrencyService
import grails.converters.JSON

import static currencyviewer.enums.CurrencyCode.EUR
import static currencyviewer.enums.CurrencyCode.USD
import static currencyviewer.utils.Utils.patternDDmmYYYY

class CurrencyController {

    CurrencyService currencyService
    CacheService cacheService

    def fetch() {
        def records = currencyService.findInCacheOrFetchRecords()

        def jsonResponse = [
                data: [
                        records   : records.collect { CurrencyRecord record ->
                            [
                                    date: record.date.format(patternDDmmYYYY()),
                                    usd : record.usd,
                                    eur : record.eur
                            ]
                        },
                        properties: [
                                minUsd: cacheService.findDateWithMinValue(USD),
                                maxUsd: cacheService.findDateWithMaxValue(USD),
                                minEur: cacheService.findDateWithMinValue(EUR),
                                maxEur: cacheService.findDateWithMaxValue(EUR)
                        ]
                ]
        ] as JSON
        render jsonResponse
    }
}
