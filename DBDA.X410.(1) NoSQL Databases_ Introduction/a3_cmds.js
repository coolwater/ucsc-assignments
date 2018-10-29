use geodb

var cal = db.states.findOne( { code:"CA" } );

var caap = db.airports.find(
 {
 loc : { $geoWithin : { $geometry : cal.loc } }
 },
 { name : 1 , type : 1, code : 1, _id: 0 }
 );

caap.count()

var intl = db.airports.find( 
  { 
    loc : { $geoWithin : { $geometry : cal.loc } },
    type : "International" 
  },
  { name : 1 , type : 1, code : 1, _id: 0 } 
).sort({ name : 1 });

intl.count()

db.airports.find( 
  { 
    loc : { $geoWithin : { $geometry : cal.loc } },
    type : "International" 
  },
  { name : 1 , type : 1, code : 1, _id: 0 } 
).sort({ name : 1 }).explain("executionStats");

db.airports.getIndexes();

db.airports.createIndex( { "loc" : "2dsphere"});

db.airports.getIndexes();

db.airports.find( 
  { 
    loc : { $geoWithin : { $geometry : cal.loc } },
    type : "International" 
  },
  { name : 1 , type : 1, code : 1, _id: 0 } 
).sort({ name : 1 }).explain("executionStats");

var cal = db.states.findOne({code:"CA"});

db.airports.find(
 {
 loc : { $geoIntersects : { $geometry : cal.loc } },
 code: {$ne: "CA" }
 },
 { name : 1 , code : 1, _id: 0 }
 );
 
var cal = db.states.findOne( {code : "CA"} );
db.states.find(
{
loc : { $geoIntersects : { $geometry : cal.loc } } ,
code : { $ne : "CA" }
},
{ name : 1, code : 1 , _id : 0 }
).explain("executionStats");

db.states.createIndex( {"loc":"2dsphere"});
db.airports.getIndexes(); 

var cal = db.states.findOne( {code : "CA"} );
db.states.find(
{
loc : { $geoIntersects : { $geometry : cal.loc } } ,
code : { $ne : "CA" }
},
{ name : 1, code : 1 , _id : 0 }
).explain("executionStats");

db.airports.find().limit(1).pretty()
db.airports.dropIndex("loc_2dsphere");
db.airports.find(
	{
		loc: {
			$near : {
				$geometry : {
					type: "Point",
					coordinates : [ -73.965355, 40.782865 ]
				},
				$maxDistance : 20000
			}
		},
		type: "International"
	},
	{
		name : 1,
		code : 1,
		_id : 0
	}
)
db.airports.createIndex( {loc: "2dsphere" } )
db.airports.getIndexes()

db.airports.find(
	{
		loc: {
			$near : {
				$geometry : {
					type: "Point",
					coordinates : [ -73.965355, 40.782865 ]
				},
				$minDistance : 21000,
				$maxDistance : 100000
			}
		},
		type: "International"
	},
	{
		name : 1,
		code : 1,
		_id : 0
	}
)