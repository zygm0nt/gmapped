import jsonschema, simplejson

data = """{
  "name": "Ian Lewis",
  "email": "IanLewis@xyz.com",
  "address": "123 Main St.",
  "phone": "080-1942-9494"
}"""

schema = """{
  "type":"object",
  "properties":{
    "name": {"type":"string"},
    "age": {"type":"integer", "optional":true},
    "email": { "type":"string",
        "pattern":"^[A-Za-z0-9][A-Za-z0-9.]*@([A-Za-z0-9]+.)+[A-Za-z0-9]+$"
    },
    "address": {"type":"string"},
    "phone": {"type":"string"}
  }
}"""

x = simplejson.loads(data)
s = simplejson.loads(schema)
jsonschema.validate(x,s)
