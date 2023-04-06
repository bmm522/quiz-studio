const loginHost = "http://localhost:8081";
const nodeHost = "http://localhost:3000";


const level = localStorage.getItem('level');
const category = localStorage.getItem('category');

window.onload = function () {

  const quizContainer = document.getElementById('quiz-container');

  fetch(`${nodeHost}/api/v1/quiz?level=${level}&category=${category}`, {
      method:"GET",
  })
  .then((res) => res.json())
  .then(res=> {
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
        choiceInput.value = quiz.quizChoice[j].isAnswer ? true : false;
        choiceDiv.appendChild(choiceInput);

        const choiceLabel = document.createElement('label');
        choiceLabel.className = 'form-check-label';
        choiceLabel.setAttribute('for', `quiz${i+1}-choice${j+1}`);
        choiceLabel.textContent = choice;
        choiceDiv.appendChild(choiceLabel);

        cardBodyDiv.appendChild(choiceDiv);
      }

      cardDiv.appendChild(cardHeaderDiv);
      cardDiv.appendChild(cardBodyDiv);
      quizContainer.appendChild(cardDiv);
    }
  });

}


document.getElementById('submit-btn').addEventListener('click', function() {
  const result = confirm('퀴즈를 제출하시겠습니까?');
  if (result) {
    const cardBodys = document.getElementsByClassName('card-body');
    for (let i = 0; i < cardBodys.length; i++) {
      const cardBody = cardBodys[i];
      const choiceInputs = cardBody.getElementsByClassName('form-check-input');
      const quizTitle = cardBody.getElementsByClassName('card-title')[0];
      let isCorrect = false;
      for (let j = 0; j < choiceInputs.length; j++) {
        const choiceInput = choiceInputs[j];
        if (choiceInput.checked) {
          choiceInput.disabled = true; // 선택지를 비활성화
          if (choiceInput.value === 'true') {
            choiceInput.parentNode.classList.add('bg-success'); // 정답인 경우 배경색을 초록색으로 변경
            isCorrect = true;
          } else {
            choiceInput.parentNode.classList.add('bg-danger'); // 오답인 경우 배경색을 빨간색으로 변경
          }
        } else if (choiceInput.value === 'true') {
          console.log('여기');
          choiceInput.parentNode.classList.add('bg-success'); // 선택하지 않았지만 정답인 경우 텍스트 색상을 초록색으로 변경
        }
      }
      if (isCorrect) {
        quizTitle.classList.add('text-success'); // 정답인 경우 제목의 텍스트 색상을 초록색으로 변경
      } else {
        quizTitle.classList.add('text-danger'); // 오답인 경우 제목의 텍스트 색상을 빨간색으로 변경
      }
    }
  }
});
