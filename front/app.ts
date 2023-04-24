import express from 'express';
import path from 'path';

const app = express();

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'Login.html'));
});

app.get('/main', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'Main.html'));
});

app.get('/quiz', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'Quiz.html'));
});

app.get('/record', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'record.html'));
});

app.get('/main', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'main.html'));
});

app.listen(3001, () => {
  console.log('Server listening on port 3001');
});
