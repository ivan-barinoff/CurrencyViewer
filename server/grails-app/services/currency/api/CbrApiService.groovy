package currency.api

import currencyviewer.enums.CurrencyCode
import org.apache.http.client.fluent.Request
import org.xml.sax.SAXException

import java.time.LocalDate

import static currencyviewer.utils.Utils.patternDDmmYYYY

class CbrApiService {

    private static final String CBR_URI = 'https://www.cbr.ru/currency_base'
    public static final int SECONDS_10 = 1000 * 10

    ParserService parserService

    Map<String, String> fetchRecords(LocalDate start, LocalDate end, CurrencyCode code) {
        def formatter = patternDDmmYYYY()
        def uri = "${CBR_URI}/dynamics.aspx?VAL_NM_RQ=${code.value}&date_req1=${formatter.format(start)}&date_req2=${formatter.format(end)}"

        def httpPage = null
        try {
            httpPage = Request.Get(uri)
                              .connectTimeout(SECONDS_10)
                              .socketTimeout(SECONDS_10)
                              .execute()
                              .returnContent()
                              .asString()
        } catch (IOException e) {
            log.error("Cannot fetch currency records", e)
        }
        parserService.toRecords(httpPage)
    }
}
