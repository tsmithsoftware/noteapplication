'use strict';

const express = require('express');
const { Client } = require('pg')
var connectionString = process.env.DATABASE_URL
console.log('database url from env is: ', connectionString)

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
const deleteQuery = "DELETE FROM public.notes WHERE noteId="
const insertQuery = "INSERT INTO public.notes (notetitle, notedetails) VALUES ("

// App
const app = express();
app.use(express.json()) // for parsing application/json

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
	var fullQuery = selectQuery + " WHERE noteId=" + req.params["noteId"] + ";"
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
	var fullQuery = deleteQuery + req.params["noteId"]
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
	var fullQuery = insertQuery + "'" + noteObject.noteTitle + "','" + noteObject.noteDetails + "');"
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