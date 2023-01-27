const express = require('express')
const app = express()
const bodyParser = require('body-parser');

app.use(bodyParser.json());
app.set('view engine', 'ejs');
app.set("views", "views");
app.use(express.json()) 
app.use(express.urlencoded({ extended: true })) 

//login
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

app.post('/login-T', (req, res) => {
  const { username, password } = req.body
  console.log(`Username: ${username} Password: ${password}`)
  if (username === 'admin2' && password === 'password2') {
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
  console.log(req.body)
  res.send('Success')
})

let data = {1: 0, 2: 0, 3: 0, 4: 0};

//comp
app.post('/submit-choice', (req, res) => {
    let choice = req.body.choice;
    data[choice]++;
    console.log(data);
    res.send('Data received');
});

app.post('/send-name-timeT', (req, res) => {
    data = {1: 0, 2: 0, 3: 0, 4: 0};
    console.log('Data reset');
    res.send('Data reset');
});

//message
app.post('/messages', (req, res) => {
 console.log(req.body)
  res.send('Success')
})

app.listen(3000, () => {
  console.log('Listening on port 3000')
})