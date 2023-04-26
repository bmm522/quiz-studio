window.onload = function () {
getRecords();
getName();
}



function getRecords() {
  const problemList = document.querySelector('#problemList');
  const url = new URL(`${nodeHost}/v1/records`);
  const headers = new Headers();
  headers.append("authorization", localStorage.getItem("authorization"));
  headers.append("refreshToken", localStorage.getItem("refreshToken"));

  fetch(url, { headers })
      .then(response => response.json())
      .then(data => {
          let html = '';

          data.data.forEach((problem, index) => {
              const { _quizTitle, _category, _level, _quizChoiceContent, _quizIsAnswer, _quizChoiceIsAnswer } = problem;
              const id = index;
              html += `
                  <tr class= "title-tr" data-status="${_quizIsAnswer !== false ? 'resolved' : 'unresolved'}">
                      <td><div onclick="toggleProblemDescription(${id})">${_quizTitle}</div></td>
                      <td>${_category}</td>
                      <td>${_level}</td>
                      <td><span class="badge badge-${_quizIsAnswer !== false ? 'success' : 'warning'}">${_quizIsAnswer !== false ? '해결됨' : '해결 못함'}</span></td>
                  </tr>
                  <tr class="problem-description" id="problemDescription${id}" style="display:none;">
                      <td colspan="3">
                          <div class="card-body">
                          ${_quizChoiceContent.map((choice, index) => `
                          <div class="form-check">
                            <label class="form-check-label ${_quizChoiceIsAnswer[index] === true ? 'text-success' : ''}" for="problem${id}${index}">
                              ${choice}
                            </label>
                          </div>
                        `).join('')}
                      
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
  const radios = document.querySelectorAll(`#problemDescription${id} input[type="radio"]`);
  let userChoice = null;

  for (let i = 0; i < radios.length; i++) {
    if (radios[i].checked) {
      userChoice = radios[i].value == true || radios[i].value == 'true';
      if (userChoice) {
        radios[i].parentNode.classList.add('text-success');
        radios[i].parentNode.classList.remove('text-danger');
      } else {
        radios[i].parentNode.classList.add('text-danger');
        radios[i].parentNode.classList.remove('text-success');
      }
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
// function filterProblems() {
 
// }

function toggleProblemDescription(index) {
    const descriptionRow = document.getElementById(`problemDescription${index}`);
    
    if (descriptionRow.style.display === 'none') {
      descriptionRow.style.display = 'table-row';
    } else {
      descriptionRow.style.display = 'none';
    }
  }
  
  function filterProblems() {
    const showUnresolved = document.getElementById('chkUnresolved').checked;
    const categoryFilter = document.getElementById('categorySelect').value;
    const levelFilter = document.getElementById('levelSelect').value;
    const tableRows = document.getElementsByClassName('title-tr');
    
    for (let i = 0; i < tableRows.length; i++) {
      const row = tableRows[i];
      const category = row.children[1].textContent;
      const level = row.children[2].textContent;
      const status = row.getAttribute('data-status');
  
      // 필터링 조건에 따라 row의 display 설정
      if (showUnresolved && status === 'unresolved') {
        row.style.display = 'table-row';
      } else if (!showUnresolved) {
        row.style.display = 'table-row';
      } else {
        row.style.display = 'none';
      }
      
      // 카테고리와 레벨이 선택된 경우, 일치하지 않으면 row의 display 설정
      if (categoryFilter && categoryFilter !== category && row.style.display !== 'none') {
        row.style.display = 'none';
      }
      if (levelFilter && levelFilter !== level && row.style.display !== 'none') {
        row.style.display = 'none';
      }
      
      // 각 문제의 선택지 영역은 일단 숨기기
      const choiceDiv = row.nextElementSibling;
      choiceDiv.style.display = 'none';
    }
  }

  document.getElementById('confirmDeleteBtn').addEventListener('click', async function() {
    const deleteOption = document.querySelector('input[name="deleteOption"]:checked').value;
    const url = new URL(`${nodeHost}/v1/records`);
    url.searchParams.set("deleteOption", deleteOption);
    const headers = new Headers();
    headers.append("authorization", localStorage.getItem("authorization"));
    headers.append("refreshToken", localStorage.getItem("refreshToken"));
    
    fetch(url, {
      method: "DELETE",
      headers: headers
    }).then(() => {
      alert('삭제되었습니다.');
      window.location.reload();
    });
  });
// const status = row.getAttribute('data-status');
// if (showUnresolved && status === 'unresolved') {
// row.style.display = 'table-row';

// } else if (!showUnresolved) {
// row.style.display = 'table-row';

// } else {
// row.style.display = 'none';
// }
// });

// for(let i = 0 ; i < choiceDiv.length ; i ++) {
// choiceDiv[i].style.display = 'none';
// }



