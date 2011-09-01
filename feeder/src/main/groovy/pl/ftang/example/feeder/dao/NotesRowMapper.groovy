package pl.ftang.example.feeder.dao

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import org.svenson.JSON
import pl.ftang.example.feeder.model.Note

/**
 * User: mcl
 * Date: 3/29/11 8:25 AM
 */
class NotesRowMapper implements RowMapper {

    Object mapRow(ResultSet rs, int rowNum) {
        Note n = new Note()
        n.id = rs.getLong("id") + "_" + rs.getString("event_case_id") + "-" + rs.getString("event_msisdn")
        n.objectId = rs.getLong("id") 
        n.eventCaseId = rs.getString("event_case_id")
        n.eventMsisdn = rs.getString("event_msisdn")
        n.firstName = rs.getString("first_name")
        n.lastName = rs.getString("last_name")
        n.note = rs.getString("note")
        n.address = rs.getString("street") + " " + rs.getString("house_number")
        n.city = rs.getString("city")
        n.eventCategory = rs.getString("action")
        n.createdDate = rs.getString("created_date")

        JSON.defaultJSON().forValue( n );
    }

}
