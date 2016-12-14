#ITV REST API technical test

Build & Run with tests:

./gradlew build && java -jar build/libs/itv-0.0.1-SNAPSHOT.jar


Usage:

/startTransaction (Generated ID of this transaction must be included in header of following endpoints.)


Body example:
[{
"item": "A",
"price": 50,
"specialPrice": { "count": 3, "price": 130} 
}]


/addItem

Body example:
item: A

/endTransaction