const loginHost = "http://localhost:8081";
const nodeHost = "http://localhost:3000";



const quizData = [
    {
      question: '다음 중 정답은 무엇인가요?',
      choices: ['선택지 1', '선택지 2', '선택지 3', '선택지 4'],
      answer: 1
    },
    {
      question: '다음 문장의 결과는 무엇인가요? ...',
      choices: ['결과 1', '결과 2', '결과 3', '결과 4'],
      answer: 2
    },
    // ... 이하 생략
  ];
const level = localStorage.getItem('level');
const category = localStorage.getItem('category');

  window.onload = function () {
    
    const quizContainer = document.getElementById('quiz-container');
    
    fetch(`${nodeHost}/api/v1/quiz?level=${level}&category=${category}`, {
        method:"GET",
    })
    .then((res) => res.json())
    .then(res=> {
        console.log(res.data);
    const quizData = res.data;  

    for (let i = 0; i < quizData.length; i++) {
        const quiz = quizData[i];
        
        const cardDiv = document.createElement('div');
        cardDiv.className = 'card shadow mb-4';
        
        const cardHeaderDiv = document.createElement('div');
        cardHeaderDiv.className = 'card-header py-3';
        
        const titleHeader = document.createElement('h6');
        titleHeader.className = 'm-0 font-weight-bold text-primary';
        titleHeader.textContent = `${i+1}번 문제`;
        cardHeaderDiv.appendChild(titleHeader);
        
        const cardBodyDiv = document.createElement('div');
        cardBodyDiv.className = 'card-body';
        
        const questionTitle = document.createElement('h5');
        questionTitle.className = 'card-title';
        questionTitle.textContent = quiz.quizTitle;
        cardBodyDiv.appendChild(questionTitle);
        
        for (let j = 0; j < quiz.quizChoice.length; j++) {
          const choice = quiz.quizChoice[j].content;
          const choiceDiv = document.createElement('div');
          choiceDiv.className = 'form-check';
          
          const choiceInput = document.createElement('input');
          choiceInput.className = 'form-check-input';
          choiceInput.type = 'radio';
          choiceInput.name = `quiz${i+1}`;
          choiceInput.id = `quiz${i+1}-choice${j+1}`;
          choiceInput.value = `option${j+1}`;
          choiceDiv.appendChild(choiceInput);
          
          const choiceLabel = document.createElement('label');
          choiceLabel.className = 'form-check-label';
          choiceLabel.setAttribute('for', `quiz${i+1}-choice${j+1}`);
          choiceLabel.textContent = choice;
          choiceDiv.appendChild(choiceLabel);

          const answerInput = document.createElement('input');
  answerInput.type = 'hidden';
  answerInput.id = 'isAnswer';
  answerInput.value = quiz.quizChoice[j].isAnswer ? true : false;
  choiceDiv.appendChild(answerInput);
          
          cardBodyDiv.appendChild(choiceDiv);
        }
        
        cardDiv.appendChild(cardHeaderDiv);
        cardDiv.appendChild(cardBodyDiv);
        quizContainer.appendChild(cardDiv);
      }
    })

  }
  