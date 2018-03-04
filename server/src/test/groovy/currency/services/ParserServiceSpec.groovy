package currency.services

import currency.api.ParserService
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class ParserServiceSpec extends Specification implements ServiceUnitTest<ParserService> {

    def setup() {

    }

    def cleanup() {
    }

    void "parses an example html from cbr"() {
        given:
        String htmlPage = new File("src/test/groovy/resources/cbr_example.html").text

        when:
        def records = service.toRecords(htmlPage)

        then:
        records.size() == 3
        records["03.02.2018"] == "56,0408"
        records["06.02.2018"] == "56,6278"
        records["07.02.2018"] == "57,2196"
    }

    void "parses an empty html from cbr"() {
        given:
        String htmlPage = ""

        when:
        def records = service.toRecords(htmlPage)

        then:
        records.size() == 0
    }
}
