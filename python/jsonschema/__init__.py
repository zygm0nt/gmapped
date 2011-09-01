#!/usr/bin/env python
#:coding=utf-8:
#:tabSize=2:indentSize=2:noTabs=true:
#:folding=explicit:collapseFolds=1:

'''
A complete, full-featured validator for JSON Schema

JSON Schema validation is based on the specifications of the the 
JSON Schema Proposal Second Draft
(http://groups.google.com/group/json-schema/web/json-schema-proposal---second-draft).

jsonschema provides an API similar to simplejson in that validators can be
overridden to support special property support or extended functionality.

Parsing a simple JSON document

>>> import jsonschema
>>> jsonschema.validate("simplejson", {"type":"string"})

Parsing a more complex JSON document.

>>> import simplejson
>>> import jsonschema
>>> 
>>> data = simplejson.loads('["foo", {"bar":["baz", null, 1.0, 2]}]')
>>> schema = {
...   "type":"array", 
...   "items":[
...     {"type":"string"},
...     {"type":"object",
...      "properties":{
...        "bar":{
...          "items":[
...            {"type":"string"},
...            {"type":"any"},
...            {"type":"number"},
...            {"type":"integer"}
...          ]
...        }
...      }
...    }
...   ]
... }
>>> jsonschema.validate(data,schema)

Handling validation errors
ValueErrors are thrown when validation errors occur.

>>> import jsonschema
>>> try:
...     jsonschema.validate("simplejson", {"type":"string","minLength":15})
... except ValueError, e:
...     print e.message
... 
Length of 'simplejson' must be more than 15.000000

'''

#TODO: Line numbers for error messages
#TODO: Add checks to make sure the schema itself is valid
#TODO: Support command line validation kind of like how simplejson allows 
#      encoding using the "python -m<modulename>" format.
#TODO: Support encodings other than utf-8

from jsonschema.validator import JSONSchemaValidator

__all__ = [ 'validate', 'JSONSchemaValidator' ]
__version__ = '0.1a'

def validate(data, schema, validator_cls=None):
  '''Validates a parsed json document against the provided schema'''
  if validator_cls == None:
    validator_cls = JSONSchemaValidator
  v = validator_cls()
  return v.validate(data,schema)

if __name__ == '__main__':
  import sys, simplejson
  if len(sys.argv) == 1:
    raise SystemExit("%s [schemafile [infile]]" % (sys.argv[0],))
  elif len(sys.argv) == 2:
    if sys.argv[1] == "--help":
      raise SystemExit("%s [schemafile [infile]]" % (sys.argv[0],))
    schemafile = open(sys.argv[1], 'rb')
    infile = sys.stdin
  elif len(sys.argv) == 3:
    schemafile = open(sys.argv[1], 'rb')
    infile = open(sys.argv[2], 'rb')
  else:
    raise SystemExit("%s [schemafile [infile]]" % (sys.argv[0],))
  try:
    obj = simplejson.load(infile)
    schema = simplejson.load(schemafile)
    validate(obj, schema)
  except ValueError, e:
    raise SystemExit(e)