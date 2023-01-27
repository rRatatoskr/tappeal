const mysql = require('mysql2')
const connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'hakurei890',
  database: 'tes'
})

connection.connect()

connection.query('SELECT * from test;', function(err, results, fields) {
  console.log(results);
});

connection.query('UPDATE test SET testcol=10 WHERE idtest=5;')

connection.query('SELECT * from test;', function(err, results, fields) {
    console.log(results);
});
connection.end();