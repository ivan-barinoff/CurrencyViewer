package currency.api

import groovy.util.slurpersupport.NodeChild
import org.ccil.cowan.tagsoup.Parser
import org.xml.sax.SAXException

class ParserService {

    private static final String TAG_TR = 'tr'

    Map<String, String> toRecords(String httpPage) {
        if (!httpPage) {
            [:]
        }

        def parser = new Parser()
        def xmlSlurper = new XmlSlurper(parser)
        def result = new HashMap<>()
        try {
            def htmlParser = xmlSlurper.parseText(httpPage)
            htmlParser.'**'.findAll { it.name() == TAG_TR }.drop(1).each { NodeChild node ->
                def currencyRow = node.text()
                String date = currencyRow.substring(0, 10)
                String value = currencyRow.substring(11, currencyRow.length())
                result.putIfAbsent(date, value)
            }
        } catch (IOException | SAXException e) {
            log.error("Cannot parse currency records", e)
        }
        return result
    }

}
