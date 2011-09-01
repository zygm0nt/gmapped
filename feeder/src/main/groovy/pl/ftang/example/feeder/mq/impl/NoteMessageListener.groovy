package pl.ftang.example.feeder.mq.impl

import pl.ftang.example.feeder.mq.MessageListener
import pl.ftang.example.feeder.mq.Event
import org.apache.log4j.Logger
import pl.ftang.example.feeder.dao.CouchDbDriver
import org.svenson.JSONParser
import pl.ftang.example.feeder.model.Note
import org.jcouchdb.exception.UpdateConflictException

/**
 * User: mcl
 * Date: 3/14/11 8:22 AM
 */
class NoteMessageListener implements MessageListener {

    Logger log = Logger.getLogger(NoteMessageListener.class);

    CouchDbDriver couch

    void onEvent(Event e) {
        log.info("Received: " + e.content)
        def note = JSONParser.defaultJSONParser().parse(Note.class, e.content)
        try {
            couch.addDocument(note)
        } catch (UpdateConflictException ex) {
            log.info("Seen this before: " + note.id, ex)
        }
    }
}
