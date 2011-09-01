package pl.ftang.example.feeder.dao

import org.jcouchdb.db.Database

/**
 * User: mcl
 * Date: 3/14/11 8:40 AM
 */
class CouchDbDriver {

    Database db

    def CouchDbDriver(host, dbname) {
        db = new Database(host, dbname);
    }

    def addDocument(note) {
        //def obj = db.getDocument(Note.class, note.id)
        db.createOrUpdateDocument(note)
    }
}
