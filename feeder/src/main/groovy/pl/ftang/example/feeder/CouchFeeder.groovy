package pl.ftang.example.feeder

import org.apache.log4j.Logger
import pl.ftang.example.feeder.util.Config
import pl.ftang.example.feeder.dao.CouchDbDriver
import pl.ftang.example.feeder.mq.ReceiverImpl
import pl.ftang.example.feeder.mq.Receiver
import pl.ftang.example.feeder.mq.impl.NoteMessageListener

/**
 * User: mcl
 * Date: 3/10/11 9:07 AM
 */
class CouchFeeder {

    Logger log = Logger.getLogger(CouchFeeder.class);

    static void main(args) {
        def config = new Config(args)
        def couch = new CouchDbDriver(config.get("couchdb.host"), config.get("couchdb.dbname"))
        Receiver recv = new ReceiverImpl("localhost", "hello")
        recv.addListeners(new NoteMessageListener(couch:couch))
        recv.receive()
    }
}
