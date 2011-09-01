import couchdb

couch = couchdb.Server() # Assuming localhost:5984

# select database
db = couch['example']

#create a document and insert it into the db:
doc = {'foo': 'bar', '_id' : 'document_id'}
db.save(doc)
