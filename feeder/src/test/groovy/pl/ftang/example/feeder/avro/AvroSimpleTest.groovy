package pl.ftang.example.feeder.avro

import org.junit.Test
import org.apache.avro.Schema
import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.io.Encoder
import org.apache.avro.io.JsonEncoder
import org.apache.avro.generic.GenericRecord
import org.apache.avro.generic.GenericData

import org.apache.avro.generic.GenericDatumReader
import org.junit.Assert

import org.apache.avro.io.JsonDecoder
import org.apache.avro.io.Decoder

/**
 * User: mcl
 * Date: 3/9/11 8:29 AM
 */
class AvroSimpleTest {

    @Test
    void shouldSerializeMessage() throws Exception {
        def is = AvroSimpleTest.class.getResourceAsStream("/facebook.avro")
        Schema s = Schema.parse(is)
        ByteArrayOutputStream bao = new ByteArrayOutputStream()
        GenericDatumWriter w = new GenericDatumWriter(s)
        Encoder e = new JsonEncoder(s, bao)
        //Encoder e = new BinaryEncoder(bao);
        //e.init(new FileOutputStream(new File("/tmp/test_data.json")))

        GenericRecord r = new GenericData.Record(s)
        r.put("name", new org.apache.avro.util.Utf8("Doctor Who"))
        r.put("num_likes", 1)
        r.put("num_photos", 0)
        r.put("num_groups", 423)
        w.write(r, e)
        e.flush()

        println bao.toString()
    }

    @Test
    void shouldDeserializeMessage() throws Exception {
        def is = AvroSimpleTest.class.getResourceAsStream("/facebook.avro")
        Schema s = Schema.parse(is)

        def out = '''
            {"name":"Doctor Who","num_likes":1,"num_photos":0,"num_groups":423}
        '''

        GenericDatumReader<GenericRecord> reader = new GenericDatumReader(s)
        InputStream input = new ByteArrayInputStream(out.getBytes("UTF-8"))
        Decoder decoder = new JsonDecoder(s, input)
        GenericRecord deserialized = null
        deserialized = reader.read(deserialized, decoder);
        Assert.assertEquals("FacebookUser", deserialized.getSchema().getName());
        Assert.assertEquals("Doctor Who", deserialized.get("name").toString());
        Assert.assertEquals(1, deserialized.get("num_likes"));
        Assert.assertEquals(0, deserialized.get("num_photos"));
        Assert.assertEquals(423, deserialized.get("num_groups"));
    }

    @Test
    void shouldSerializeMnpNote() throws Exception {
        def is = AvroSimpleTest.class.getResourceAsStream("/note.avro")
        Schema s = Schema.parse(is)
        ByteArrayOutputStream bao = new ByteArrayOutputStream()
        GenericDatumWriter w = new GenericDatumWriter(s)
        Encoder e = new JsonEncoder(s, bao)

        GenericRecord r = new GenericData.Record(s)
        r.put("id", 123L)
        r.put("event_case_id", new org.apache.avro.util.Utf8("000-123123123"))
        r.put("event_msisdn", new org.apache.avro.util.Utf8("48764322212"))
        //r.put("num_groups", 423)
        w.write(r, e)
        e.flush()

        println bao.toString()
    }
}
