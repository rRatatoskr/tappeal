const express = require('express')
const app = express()

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