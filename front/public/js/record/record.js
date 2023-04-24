window.onload = function () {
getRecords();
}


function getRecords() {
    const problemList = document.querySelector('#problemList');
    const url = new URL(`${nodeHost}/v1/records`);
    const headers = new Headers();
    headers.append("authorization", sessionStorage.getItem("authorization"));
    headers.append("refreshToken", sessionStorage.getItem("refreshToken"));
  
    fetch(url, { headers })
      .then(response => response.json())
      .then(data => {
        console.log(data.data);
        let html = '';
  
        data.data.forEach((problem, index) => {
          const { _quizTitle, _quizChoiceContent, _quizIsAnswer } = problem;
          const id = index;
  
          html += `
            <tr data-status="${_quizIsAnswer !== null ? 'resolved' : 'unresolved'}">
              <td><a href="#" onclick="toggleProblemDescription(${id})">${_quizTitle}</a></td>
              <td>${problem.createdAt.slice(0, 10)}</td>
              <td><span class="badge badge-${_quizIsAnswer !== null ? 'success' : 'warning'}">${_quizIsAnswer !== null ? '해결됨' : '해결 중'}</span></td>
            </tr>
            <tr class="problem-description" id="problemDescription${id}" style="display:none;">
              <td colspan="3">
                <div class="card-body">
                  <form>
                    ${_quizChoiceContent.map((choice, index) => `
                      <div class="form-check">
                        <input class="form-check-input" type="radio" name="quiz${id}" id="quiz${id}-choice${index}" value="${index === _quizIsAnswer ? 'true' : 'false'}">
                        <label class="form-check-label" for="quiz${id}-choice${index}">${choice}</label>
                      </div>
                    `).join('')}
                    <button class="btn btn-custom mt-3" onclick="submitAnswer(${id})">제출</button>
                    <div class="result" id="result${id}" style="display:none;"></div>
                  </form>
                </div>
              </td>
            </tr>
          `;
        });
  
        problemList.innerHTML = html;
      })
      .catch(error => console.error(error));
  }
  
  function submitAnswer(id) {
    const form = document.querySelector(`#problemDescription${id} form`);
    const radios = form.elements[`quiz${id}`];
    let userChoice = null;
  
    for (let i = 0; i < radios.length; i++) {
      if (radios[i].checked) {
        userChoice = radios[i].value === 'true';
        break;
      }
    }
  
    if (userChoice === null) {
      return;
    }
  
    const resultElement = document.querySelector(`#result${id}`);
    const resultMessage = userChoice ? '정답입니다!' : '오답입니다.';
  
    resultElement.innerHTML = resultMessage;
    resultElement.style.display = 'block';
  }

function toggleProblemDescription(index) {
    const descriptionRow = document.getElementById(`problemDescription${index}`);
    console.log(descriptionRow.style.display);
    
    if (descriptionRow.style.display === 'none') {
      descriptionRow.style.display = 'table-row';
    } else {
      descriptionRow.style.display = 'none';
    }
  }
  
  function filterProblems() {
    const showUnresolved = document.getElementById('chkUnresolved').checked;
    const choiceDiv = document.getElementsByClassName("problem-description");
    console.log(choiceDiv[0].style.display);
  
    const tableRows = document.querySelectorAll('tbody > tr');

tableRows.forEach(row => {

const status = row.getAttribute('data-status');
if (showUnresolved && status === 'unresolved') {
row.style.display = 'table-row';

} else if (!showUnresolved) {
row.style.display = 'table-row';

} else {
row.style.display = 'none';
}
});

for(let i = 0 ; i < choiceDiv.length ; i ++) {
choiceDiv[i].style.display = 'none';
}

}  

