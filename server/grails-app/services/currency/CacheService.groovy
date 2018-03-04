package currency

import currencyviewer.CurrencyRecord
import currencyviewer.enums.CurrencyCode

import java.time.LocalDate

import static currencyviewer.utils.Utils.patternDDmmYYYY

class CacheService {

    String findDateWithMaxValue(CurrencyCode code) {
        LocalDate now = LocalDate.now()
        LocalDate monthBefore = now.minusMonths(1)

        def record = CurrencyRecord.findByDateBetween(monthBefore, now, [max: 1, sort: code.name().toLowerCase(), order: "desc"])
        record?.date?.format(patternDDmmYYYY())
    }

    String findDateWithMinValue(CurrencyCode code) {
        LocalDate now = LocalDate.now()
        LocalDate monthBefore = now.minusMonths(1)

        def record = CurrencyRecord.findByDateBetween(monthBefore, now, [max: 1, sort: code.name().toLowerCase(), order: "asc"])
        record?.date?.format(patternDDmmYYYY())
    }

    LocalDate findLastUpdateDate() {
        CurrencyRecord.createCriteria().get {
            projections {
                max "date"
            }
        } as LocalDate
    }

    void saveRecord(LocalDate date, String usd, String eur) {
        CurrencyRecord record = new CurrencyRecord()
        record.date = date
        record.usd = new BigDecimal(usd)
        record.eur = new BigDecimal(eur)
        record.save(failOnError: true)
    }

    List<CurrencyRecord> findRecords(LocalDate start, LocalDate end) {
        CurrencyRecord.findAllByDateBetween(start, end, [sort: 'date', order: "asc"])
    }
}
