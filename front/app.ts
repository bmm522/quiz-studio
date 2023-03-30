import express from 'express';
import path from 'path';

const app = express();

app.use(express.static(path.join(__dirname, 'public')));


app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public',  'LoginPage.html'));
  });

app.get('/menu', (req, res) => {
    res.sendFile(path.join(__dirname, 'public',  'MenuPage.html'));
  });

app.listen(3001, () => {
    console.log('Server listening on port 3001');
});