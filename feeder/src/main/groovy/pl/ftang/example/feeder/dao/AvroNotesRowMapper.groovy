package pl.ftang.example.feeder.dao

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.io.Encoder
import org.apache.avro.io.JsonEncoder
import org.apache.avro.generic.GenericRecord
import org.apache.avro.generic.GenericData

/**
 * User: mcl
 * Date: 3/15/11 9:10 AM
 */
class AvroNotesRowMapper implements RowMapper {

    def schema

    Object mapRow(ResultSet rs, int rowNum) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream()
         GenericDatumWriter w = new GenericDatumWriter(schema)
         Encoder e = new JsonEncoder(schema, bao)
         //w.write(generateTestRecord(), e)
         w.write(generateRecord(rs), e)
         e.flush()
        bao.toString()
    }

    private def generateTestRecord() {
        GenericRecord r = new GenericData.Record(schema)
        r.put("name", new org.apache.avro.util.Utf8("Doctor Who"))
        r.put("num_likes", 1)
        r.put("num_photos", 0)
        r.put("num_groups", 423)
        r
    }

    private def generateRecord(ResultSet rs) {
        GenericRecord r = new GenericData.Record(schema)
        r.put("id", rs.getLong("id"))
        r.put("event_case_id", new org.apache.avro.util.Utf8("000-123123123"))
        r.put("event_msisdn", new org.apache.avro.util.Utf8("48764322212"))
        r.put("note", new org.apache.avro.util.Utf8(rs.getString("note")))
        r
    }
}
