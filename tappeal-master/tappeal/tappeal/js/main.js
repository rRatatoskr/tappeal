//expressの設定
const express=require('express');
const app=express();


app.use(express.json()) 
app.use(express.urlencoded({ extended: true }))

//データベース側の設定
const mysql = require('mysql2')
  const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'hakurei890',
    database: 'tes'
  })
//サーバー側の設定
  const http = require("http");
  const port = 3000;
  //サーバー起動
  const server = http.createServer(function(request,response) {
    response.writeHead(200, { "Content-Type": "application/json" });
    response.end();
  });
//クエリ
/*app.get('/login',function(req,res){
var re=req.query.q
  connection.query('SELECT testcol from test WHERE idtest=re;', function (err, results, fields) {
      res.send(JSON.stringify(results));
      console.log(re);
    })
  })
*/
  app.post('/login', (req, res) => {
    const { username, password } = req.body
    console.log(`Username: ${username} Password: ${password}`)
    if (username === 'admin' && password === 'password') {
      console.log('TRUE')
      res.json({ success: true, message: 'ログイン成功' })
    } else {
      console.log('FALSE')
      res.json({ success: false, message: 'ログイン失敗' })
    }
  })

//toilet_appeal
app.post('/send-name-time', (req, res) => {
  console.log('require:toilet')
  console.log(req.body)
  res.send('Success')
})

//que_appeal
app.post('/send-name-timeQ', (req, res) => {
  console.log('require:question')
  console.log(req.body.name)
  console.log(req.body.time)
  res.send('Success')
})

//comp
app.post('/submit-choice', (req, res) => {
  console.log(req.body)
  res.send('Success')
})

//message
app.post('/messages', (req, res) => {
  console.log(req.body)
  res.send('Success')
})

app.listen(3000, () => {
  console.log('Listening on port 3000')
})
  app.listen(port, '172.25.15.100');