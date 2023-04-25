window.onload = function () {
getRecords();
getName();
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
                                      <input class="form-check-input" type="radio" name="problem${id}" id="problem${id}${index}" value="${_quizChoiceIsAnswer[index]}">
                                      <label class="form-check-label" for="problem${id}${index}">
                                          ${choice}
                                      </label>
                                  </div>
                              `).join('')}
                              <button class="btn btn-custom mt-3" onclick="submitAnswer(${id})">제출</button>
                              <div class="result" id="result${id}" style="display:none;"></div>
                          </div>
                      </td>
                  </tr>
              `;
          });

          problemList.innerHTML = html;
      })
      .catch(error => console.error(error));
}
  
// function filterProblems() {
 
// }

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



