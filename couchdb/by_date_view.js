function(doc) {
      if (doc.created_date != null) {
            emit(doc.created_date, doc);
      }
}
