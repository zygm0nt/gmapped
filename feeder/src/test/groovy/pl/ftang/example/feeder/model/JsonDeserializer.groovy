package pl.ftang.example.feeder.model

import org.junit.Test
import org.svenson.JSONParser
import org.junit.Assert
import org.svenson.matcher.RegExPathMatcher

/**
 * User: mcl
 * Date: 3/21/11 8:32 AM
 */
class JsonDeserializer {

    @Test
    void shouldDeserializeJsonToClass() throws Exception {
        def inputJson2 = '''
        {"id":40,"event_case_id":"000-123123123","event_msisdn":"48764322212","note":"Planowana data portacji: 2011/01/27 11:42:49","created_date":null,"event_category":null,"city":null,"address":null}
        '''
        Note bean = JSONParser.defaultJSONParser().parse(Note.class, inputJson2)
        Assert.assertEquals(40, bean.id)
        Assert.assertEquals("000-123123123", bean.eventCaseId)
    }

    //@Test
    void shouldDeserializeJsonToClass2() throws Exception {
        def inputJson = '''
        {"id":40,"event_case_id":"000-123123123","event_msisdn":"48764322212","note":{"string":"Planowana data portacji: 2011/01/27 11:42:49"},"created_date":null,"event_category":null,"city":null,"address":null}
        '''
        JSONParser parser = JSONParser.defaultJSONParser()
        parser.addTypeHint(new RegExPathMatcher(".note.string*"), String.class)
        Note bean = parser.parse(Note.class, inputJson)

        Assert.assertEquals(40, bean.id)
        Assert.assertEquals("000-123123123", bean.eventCaseId)
    }
}
