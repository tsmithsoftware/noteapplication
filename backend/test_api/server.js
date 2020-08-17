'use strict';

var url = require("url");
const express = require('express');
const { Client } = require('pg')
var connectionString = process.env.DATABASE_URL//'postgres://user:example@192.168.0.19:5432/db'  
console.log('database url from env is: ', connectionString)
// set timezone
process.env.TZ = 'Europe/London';

const client = new Client({
	connectionString
});

client.connect(err => {
  if (err) {
    console.error('connection error', err.stack)
  } else {
    console.log('connected')
  }
})

// Constants
const PORT = 4000;
const HOST = '0.0.0.0';
const selectQuery = "SELECT * FROM public.notes"
const deleteQuery = "DELETE FROM public.notes WHERE noteid="
const insertQuery = "INSERT INTO public.notes (notetitle, notedetails) VALUES ("
const updateQuery = "UPDATE public.notes SET notetitle = '"

function buildSelectQuery(noteId){
	return selectQuery + " WHERE noteid=" + noteId + ";"
}

function buildDeleteQuery(noteId) {
	return deleteQuery + noteId + ";"
}

function buildInsertQuery(noteTitle, noteDetails) {
	return insertQuery + "'" + noteTitle + "','" + noteDetails + "');"
}

function buildUpdateQuery(noteId, newTitle, newDetails) {
	return updateQuery + newTitle + "', notedetails = '" + newDetails + "' WHERE noteid = " + noteId + ";" 
}

function getDateTime() {

    var date = new Date();

    var hour = date.getHours();
    hour = (hour < 10 ? "0" : "") + hour;

    var min  = date.getMinutes();
    min = (min < 10 ? "0" : "") + min;

    var sec  = date.getSeconds();
    sec = (sec < 10 ? "0" : "") + sec;

    var year = date.getFullYear();

    var month = date.getMonth() + 1;
    month = (month < 10 ? "0" : "") + month;

    var day  = date.getDate();
    day = (day < 10 ? "0" : "") + day;

    return year + ":" + month + ":" + day + ":" + hour + ":" + min + ":" + sec;

}

// App
const app = express();
app.use(express.json()) // for parsing application/json
// Instantiating the express-jwt middleware
const accessTokenSecret = `-----BEGIN RSA PRIVATE KEY-----
MIIEpgIBAAKCAQEA9b1Ybrl3HkwqsUVGDLF30G3FItCWdLnFTX8zOSjtbF8IFW8N
SJ/OEsqRT3dg5sJmCfKuo1NBmRAd3aL7APkvwJjsvFJ3JigS8Q/lNPjx9BCrQwTY
j5yBFlRbn7OCJV3jRqY8rBRIBDThjbBRiLkbwyLFy5YALSAKiomvGrIMUbmWFtZD
LpBQ5lj39Ruuj/4Rj63MX4cf1vFEUJAuGe/ewhjdNCENwCnGRxiRGgyFq9FZuVqV
fuw5BrA9cmZr8RIPeTqJIGxM3RfD8hqrG1augFmELMyP19ddYU+l4ShgV/8efMd+
CcKPYA1s8nRTri+ajZ/tvan+u2zJaRSWSPaDQQIDAQABAoIBAQC+BSU9sXJ33iPh
IhURuaVRaaBX1H2TUxdLXsHIQGqfMzj4AgB+mPvrsfH9Re2LYuUuahc6uiVua3T8
rotzbDzNrDLbSNVTjwdempD9jkbiGIWl9DhVjIqRLrgO3OsVY+1b8y3VTnWFszJ6
ss3JcIIUOW5DiAEo467jA6aAeQ9nnRuXfjYB3aZjosFtPj/xVT8h10siHcIUp4hC
sHZYHLGxVMzddGmTj7ywmuO/q0hTcuscX26ijpFhfjyrVZLGtwTiyTPdtSj8Nye0
0XGeidVXlFQJjdtRxes7VnxCQXXX8SVNZI4vrmxJiWWcO4sqt16gkqkRpXJb4cxt
Bo13U6zxAoGBAPwykFLxdJgEyX8eL9K3g7eMdTFEDrDU5fyAjnq1xyCNbg0exMBO
/vt3Hg9luZiVGrckAeC+9HhKN124yK2WeERQFkN61j2+8LDpnrvgYP84e8pNz4Uy
7b+o8ub+iNxhVYnOnBt/GZN8du0KibSi0QuHiun/xlUq/zCQcW1kPr7lAoGBAPlx
2v0LS9J/Dvv2xfc8zUGSjuMqlJCjs8rR051kx8Ie30SFhRV8UIBMsIYv+MkOmCF1
OxF6JL4k+gCMJ9Vl2gltf9/fZtAfVswWrakF83KLEblgZ+yLJzr+1BjI7AbK/IBn
0lPLeV9tDHGU2ivWFmEyJ+Y5RFrjjSm3ihN4XtEtAoGBAOS6PybakjIldVHy4NCt
W6qmmssZvHEoRYXiWlZ0cqrVWlBP7YW0n5EVkY/JZLA72sTqxUE+Lp7CQMChjG/T
HjRmpiqBR7ksaUMqPqqKb65JXgw20dUuQW4oHlTd2OZ26ItZdT+C4IFgysNlYUsQ
t6NLRPPA4/HuUw+KkTTzS+z5AoGBANMc40QKTW+njVz+K+nlkaoPidME9Ju9kKr1
Du5EnzPRwRBTGKFohxEu1q+a5inzqhmXCBjwkSxESzovZH8dJn2YKQOmJXO7MlCk
WPGWUcX9oVCNISd3Vgp+Ja+cTPYq0a0zMTUuHlj/B+sRGdzD4kj8k/wrN4jc0Vof
NkLoOwc5AoGBAIKpjxjPCJhLmaUZCc/kAf1KW8UwT39vOwVXFMMsjxjJz+GlDOVr
4r8Zvk3xKMeDkuDCih3s9mx7UohEJhn6sscYV33ocqo1tMURQWFsLIOOoP1QCIBA
ANnNF+EHUFFGQw8aX6ixGxwyrFzFsbYtTUgEY8qGWbYLyzCWbHvMO5jL
-----END RSA PRIVATE KEY-----`
;

// verification middleware

app.use(function(req,res, next) {
	const jwt = require('njwt');
	
	console.log("in middleware")
	console.log("Current date time: " + getDateTime())
	// check for basic auth header
    if (!req.headers.authorization) {
		console.log("missing auth header")
        return res.status(401).json({ message: 'Missing Authorization Header' });
    }
	
	if (req.headers.authorization.indexOf('Bearer ') === -1) {
		console.log("missing bearer token")
		return res.status(401).json({ message: 'Missing Bearer token' });
	}
	
	const authToken = req.headers.authorization.split(" ")[1]
	console.log("auth token: " + authToken)
	jwt.verify(authToken, accessTokenSecret, 'RS256', (err, verifiedJwt) => {
		if(err){
			console.log(err.message)
			res.send(err.message)
			}else{
				console.log("Request verified")
				next()
				}
			})
			}
);


app.get('/check', (req,res) => {
	res.status(200).send("Response!");
});

app.get('/notes', (req, res) => {
	client.query(selectQuery + ";",
		function (err, result) {
			if (err) {
				console.log (err);
				res.status(400).send(err);
				} else {
					res.status(200).send(result.rows);
				}
		} 
		
	)
}
);

app.get('/notes/:noteId', (req, res) => {
	var fullQuery = buildSelectQuery(req.params["noteId"])
	client.query(fullQuery,
		function (err, result) {
			if (err) {
				console.log (err);
				res.status(400).send(err);
				} else {
					res.status(200).send(result.rows[0]);
				}
		} 
		
	)
}
);

app.delete('/notes/:noteId', (req, res) => {
	var fullQuery = buildDeleteQuery(req.params["noteId"])
	console.log(fullQuery)
	client.query(fullQuery,
		function (err, result) {
			if (err) {
				console.log (err);
				res.status(400).send(err);
				} else {
					res.status(200).send(result.rows);
				}
		} 
		
	)
});

app.post('/notes', (req,res) => {
	var noteObject = req.body
	var fullQuery = buildInsertQuery(noteObject.noteTitle, noteObject.noteDetails)
	console.log(fullQuery)
	
	client.query(fullQuery,
		function (err, result) {
			if (err) {
				console.log (err);
				res.status(400).send(err);
				} else {
					res.status(200).send();
				}
		} 
	)
});

app.put('/notes/:id', (req,res) => {
	var noteObject = req.body
	const id = req.params.id;
	var fullQuery = buildUpdateQuery(id, noteObject.noteTitle, noteObject.noteDetails)
	console.log(fullQuery)
	
	client.query(fullQuery, 
		function (err, result) {
			if (err) {
				console.log (err);
				res.status(400).send(err);
				} else {
					res.status(200).send();
				}
		} 
	)
});


app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);