#!/bin/sh

if [ "$1" = "query" ]; then
    curl -X POST http://localhost:5984/example/_temp_view -H "Content-Type: application/json" -d "$2"
elif [ "$1" = "getAll" ]; then
    curl -X GET http://localhost:5984/example/_all_docs
else
    echo "usage: ./script.sh [query|getAll]"
fi
