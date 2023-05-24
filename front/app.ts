import express from 'express';
import path from 'path';

const app = express();

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/html', 'Login.html'));
});

app.get('/main', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/html', 'Main.html'));
});

app.get('/quiz', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/html', 'Quiz.html'));
});

app.get('/edit-quiz', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/html', 'edit-quiz.html'));
});

app.get('/edit-quiz-detail', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/html', 'edit-quiz-detail.html'));
});

app.get('/record', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/html', 'record.html'));
});

app.get('/main', (req, res) => {
  res.sendFile(path.join(__dirname, 'public/html', 'main.html'));
});




app.listen(3001, () => {
  console.log('Server listening on port 3001');
});
