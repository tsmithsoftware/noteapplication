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
const query = "SELECT * FROM public.notes;"

// App
const app = express();
app.get('/notes', (req, res) => {
	//res.status(200).send("{result:true}");
	client.query(query,
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

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);