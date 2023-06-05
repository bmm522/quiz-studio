let questionCounter = 1;
// const quizData = [
//     {
//       quizId: 1,
//       question: "Quiz 1123",
//       answers: ["Question 1222", "Question 2", "Question 3", "Question 4"],
//       correctAnswer: [false, false, true, false], // 정답 번호 (0부터 시작)
//     },
//     {
//       quizId: 2,
//       question: "Quiz 2",
//       answers: ["Question 1", "Question 2", "Question 3", "Question 4"],
//       correctAnswer: [true, false, false, false], // 정답 번호 (0부터 시작)
//     },
//     // 추가적인 퀴즈 데이터를 여기에 추가할 수 있습니다.
//   ];

// 퀴즈 리스트 생성 함수
async function createQuizList(page) {
    const quizContainer = document.getElementById("quizContainer");
    const quizListElement = document.getElementById("quizList");
    const prevButton = document.getElementById("prevButton");
    const nextButton = document.getElementById("nextButton");

    // 퀴즈 리스트 초기화
    quizListElement.innerHTML = "";
    const url = new URL(
        `${quizSpringHost}/api/v1/category/${localStorage.getItem(
            "categoryId",
        )}/quiz`,
    );
    url.searchParams.set("page", page);
    const response = await fetch(url, {
        method: "GET",
        headers: {
            authorization: localStorage.getItem("authorization"),
            refreshToken: localStorage.getItem("refreshToken"),
        },
    });
    const res = await response.json();
    const quizData = res.data.quizzes;

    // const maxItemsPerPage = 10;
    // const startIndex = (page - 1) * maxItemsPerPage;
    // const endIndex = startIndex + maxItemsPerPage;

    // // 최대 10개의 항목을 보여줄 부분
    // const visibleQuizData = quizData.slice(startIndex, endIndex);
    quizData.forEach((quiz) => {
        quizListElement.innerHTML += `
        <li>
          <h5 class="mb-2 quiz-title" onclick="toggleQuizQuestions(${
              quiz.quizId
          })">${quiz.quizTitle}</h5>
          <ul class="choice-list" id="choice${
              quiz.quizId
          }" style="display:none;">
            ${quiz.choices
                .map(
                    (choice, index) =>
                        `<li class="form-check-label  ${
                            choice.answer ? "text-success" : ""
                        }">${choice.choiceContent} </li>`,
                )
                .join("")}
            <button class="btn btn-outline-dark" id="edit-quiz-btn${
                quiz.quizId
            }">편집</button>
          </ul>
        </li>`;
    });

    // 페이지네이션 버튼 표시
    const paginationContainer = document.getElementById("pagination");
    paginationContainer.innerHTML = "";
    for (let i = 1; i <= res.data.totalPage; i++) {
        const button = document.createElement("button");
        button.className = "btn btn-primary btn-sm mr-2";
        button.textContent = i;
        button.setAttribute("data-page", i);
        button.addEventListener("click", function () {
            const page = parseInt(this.getAttribute("data-page"));
            createQuizList(page);
        });
        paginationContainer.appendChild(button);
    }

    // 이전/다음 버튼 표시 및 이벤트 설정
    prevButton.disabled = page === 1;
    nextButton.disabled =
        page === res.data.totalPage || res.data.totalPage === 0;
    prevButton.addEventListener("click", function () {
        createQuizList(page - 1);
    });
    nextButton.addEventListener("click", function () {
        createQuizList(page + 1);
    });

    // 편집 버튼 이벤트 설정
    for (let i = 0; i < quizData.length; i++) {
        const editBtn = document.getElementById(
            "edit-quiz-btn" + quizData[i].quizId,
        );
        if (editBtn) {
            editBtn.addEventListener("click", () => {
                showModal(quizData[i]);
            });
        }
    }
}

function toggleQuizQuestions(quizId) {
    const questionsElement = document.getElementById("choice" + quizId);
    if (questionsElement.style.display === "none") {
        questionsElement.style.display = "block";
    } else {
        questionsElement.style.display = "none";
    }
}

function showModal(quiz) {
    const modalDiv = document.getElementById("quiz-edit-modal-div");
    modalDiv.innerHTML = `
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="quizModalLabel">문제 수정</h5>
            </div>
            <div class="modal-body">
              <form id="editQuizForm">
                <div id="editQuestionsContainer">
                  <div class="mb-3">
                    <label for="editTitle" class="form-label">퀴즈 제목</label>
                    <input type="text" class="form-control" id="editTitle" placeholder="퀴즈 제목" value="${
                        quiz.quizTitle
                    }">
                  </div>
                  <div class="mb-3">
                    <label class="form-label">문제 보기</label>
                    ${quiz.choices
                        .map(
                            (choice, index) =>
                                `<div class="form-check">
                             <input class="form-check-input" type="radio" name="correctAnswer" id="editOption${
                                 choice.choiceId
                             }" value="${choice.choiceId}" ${
                                    choice.answer ? "checked" : ""
                                }>
                             <input type="text" id="updateQuizChoice${
                                 choice.choiceId
                             }"class="form-control" value="${
                                    choice.choiceContent
                                }">
                           </div>`,
                        )
                        .join("")}
                  </div>
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" id="editCloseButton" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
              <button type="button" class="btn btn-primary" id="updateQuizBtn">수정</button>
            </div>
          </div>
        </div>
      </div>
    `;

    const editCloseButton = modalDiv.querySelector("#editCloseButton");
    editCloseButton.addEventListener("click", () => {
        //   const myModal = new bootstrap.Modal(document.getElementById("exampleModal"));
        myModal.hide();
    });

    const updateButton = modalDiv.querySelector("#updateQuizBtn");
    updateButton.addEventListener("click", () => {
        const editTitle = modalDiv.querySelector("#editTitle").value;
        const editOptions = modalDiv.querySelectorAll(
            'input[name="correctAnswer"]',
        );
        const correctAnswerIndex = Array.from(editOptions).findIndex(
            (option) => option.checked,
        );
        const updatedChoices = Array.from(quiz.choices).map((choice, index) => {
            return {
                choiceId: `${choice.choiceId}`,
                content: modalDiv.querySelector(
                    `#updateQuizChoice${choice.choiceId}`,
                ).value,
                isAnswer: index === correctAnswerIndex,
            };
        });
        const updatedQuizData = {
            ...quiz,
            title: editTitle,
            choices: updatedChoices,
        };
        // console.log(updatedQuizData);
        //   const myModal = new bootstrap.Modal(document.getElementById("exampleModal"));
        myModal.hide();
        // let updateQuizChoices = [];
        // updateQuizChoices.push(updatedQuizData);
        updateQuiz(updatedQuizData);
    });

    const myModal = new bootstrap.Modal(
        document.getElementById("exampleModal"),
    );
    myModal.show();
}

function updateQuiz(updatedQuizData) {
    const url = new URL(
        `${quizSpringHost}/api/v1/category/${localStorage.getItem(
            "categoryId",
        )}/quiz`,
    );

    fetch(url, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
            authorization: localStorage.getItem("authorization"),
            refreshToken: localStorage.getItem("refreshToken"),
        },
        body: JSON.stringify({
            quizzes: [updatedQuizData],
        }),
    })
        .then((res) => res.json())
        .then((res) => {
            alert("퀴즈가 성공적으로 수정 되었습니다.");
            window.location.reload();
        });
}

function addQuestion() {
    // Check if the maximum number of questions has been reached (10)
    if (questionCounter <= 10) {
        // Create question container
        let questionContainer = document.createElement("div");
        questionContainer.classList.add("question-container");

        // Question input
        let questionInput = document.createElement("input");
        questionInput.classList.add("form-control", "mb-2");
        questionInput.id = "quizTitle";
        questionInput.required = true;
        questionInput.setAttribute("name", "question");
        questionInput.setAttribute("placeholder", "문제 제목");
        questionContainer.appendChild(questionInput);

        // Options inputs
        for (let i = 1; i <= 4; i++) {
            let optionInput = document.createElement("input");
            optionInput.classList.add("form-control", "mb-2");
            optionInput.id = `quizChoice${i}`;
            optionInput.required = true;
            optionInput.setAttribute("name", `option${i}`);
            optionInput.setAttribute("placeholder", `문제 보기 ${i}`);
            questionContainer.appendChild(optionInput);
        }

        // Correct Answer select
        let correctAnswerSelect = document.createElement("select");
        correctAnswerSelect.id = "correctAnswer";
        correctAnswerSelect.classList.add("form-control", "mb-2");
        correctAnswerSelect.setAttribute("name", "correctAnswer");
        for (let i = 1; i <= 4; i++) {
            let option = document.createElement("option");
            option.setAttribute("value", i);
            option.textContent = `정답 : 문제 보기 ${i}`;
            correctAnswerSelect.appendChild(option);
        }
        questionContainer.appendChild(correctAnswerSelect);

        // Cancel question button
        let cancelQuestionBtn = document.createElement("span");
        cancelQuestionBtn.classList.add("cancel-question");
        cancelQuestionBtn.innerHTML = `<button  class="btn btn-danger">X</button>`;
        cancelQuestionBtn.addEventListener("click", function () {
            questionContainer.remove();
            questionCounter--;
            checkSubmitButtonVisibility();
        });
        questionContainer.appendChild(cancelQuestionBtn);

        // Add question container to questions container
        document
            .getElementById("questionsContainer")
            .appendChild(questionContainer);

        // Increment question counter
        questionCounter++;

        checkSubmitButtonVisibility();
    } else {
        alert("퀴즈 생성은 최대 10개까지만 가능합니다.");
    }
}

function editQuiz(quizIndex) {
    const quiz = quizData[quizIndex];
    const modal = document.getElementById("editQuizModal");
    const modalLabel = document.getElementById("editQuizModalLabel");
    const editQuizForm = document.getElementById("editQuizForm");
    const editQuestionsContainer = document.getElementById(
        "editQuestionsContainer",
    );

    // Set quiz title in the modal
    modalLabel.textContent = quiz.question;

    // Clear previous questions from edit form
    editQuestionsContainer.innerHTML = "";

    // Iterate through questions and add to edit form
    for (let i = 0; i < quiz.answers.length; i++) {
        const questionText = quiz.answers[i];
        const questionContainer = document.createElement("div");
        questionContainer.classList.add("question-container");

        // Question input
        const questionInput = document.createElement("input");
        questionInput.classList.add("form-control", "mb-2");
        questionInput.setAttribute("name", `question${i}`);
        questionInput.setAttribute("placeholder", "Enter the question");
        questionInput.value = questionText;
        questionContainer.appendChild(questionInput);

        // Options inputs
        for (let j = 1; j <= 4; j++) {
            const optionInput = document.createElement("input");
            optionInput.classList.add("form-control", "mb-2");
            optionInput.setAttribute("name", `option${i}-${j}`);
            optionInput.setAttribute("placeholder", `Enter option ${j}`);
            questionContainer.appendChild(optionInput);
        }

        // Correct Answer select
        const correctAnswerSelect = document.createElement("select");
        // correctAnswerSelect.id = `correctAnswer${i}`;
        correctAnswerSelect.classList.add("form-control", "mb-2");
        correctAnswerSelect.setAttribute("name", `correctAnswer${i}`);
        for (let j = 1; j <= 4; j++) {
            const option = document.createElement("option");
            option.setAttribute("value", j);
            option.textContent = `Option ${j}`;
            correctAnswerSelect.appendChild(option);
        }
        questionContainer.appendChild(correctAnswerSelect);

        // Add question container to edit questions container
        editQuestionsContainer.appendChild(questionContainer);
    }
    modal.style.display = "block";
    editQuizForm.addEventListener("submit", function (e) {
        e.preventDefault();
        // saveQuiz(quizIndex);
    });
}

async function saveQuizzes() {
    const containerList = document.getElementsByClassName("question-container");
    let customQuizList = [];

    for (let index = 0; index < containerList.length; index++) {
        const quizTitle =
            containerList[index].querySelector("#quizTitle").value;
        const quizChoice = [
            containerList[index].querySelector("#quizChoice1").value,
            containerList[index].querySelector("#quizChoice2").value,
            containerList[index].querySelector("#quizChoice3").value,
            containerList[index].querySelector("#quizChoice4").value,
        ];
        const checkAnswer =
            containerList[index].querySelector("#correctAnswer").value;
        const isAnswer = [
            checkAnswer == 1,
            checkAnswer == 2,
            checkAnswer == 3,
            checkAnswer == 4,
        ];

        let customQuizChoices = [];
        for (let i = 0; i < quizChoice.length; i++) {
            let choice = {
                content: quizChoice[i],
                isAnswer: isAnswer[i],
            };
            customQuizChoices.push(choice);
        }

        let customQuiz = {
            title: quizTitle,
            choices: customQuizChoices,
        };

        customQuizList.push(customQuiz);
    }

    const url = new URL(
        `${quizSpringHost}/api/v1/category/${localStorage.getItem(
            "categoryId",
        )}/quiz`,
    );

    const response = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            authorization: localStorage.getItem("authorization"),
            refreshToken: localStorage.getItem("refreshToken"),
        },
        body: JSON.stringify({
            quizzes: customQuizList,
        }),
    });

    const data = await response.json();
    alert("퀴즈가 성공적으로 저장되었습니다.");
    window.location.reload();
}
// function saveQuiz(quizIndex) {
//   const editQuizForm = document.getElementById("editQuizForm");
//   const formData = new FormData(editQuizForm);
//   const questionsContainer = formData.getAll("question");
//   const optionsContainer = Array.from(formData.entries())
//     .filter(([name]) => name.includes("option"))
//     .reduce((result, [name, value]) => {
//       const [questionIndex, optionIndex] = name.split("-").slice(1);
//       if (!result[questionIndex]) {
//         result[questionIndex] = [];
//       }
//       result[questionIndex][optionIndex] = value;
//       return result;
//     }, []);
//   const correctAnswers = formData.getAll("correctAnswer");

//   quizData[quizIndex].question = questionsContainer[0];
//   quizData[quizIndex].answers = optionsContainer[0];
//   quizData[quizIndex].correctAnswer = correctAnswers[0];
//   console.log(quizData[quizIndex].question);
//   // createQuizList();

//   const modal = document.getElementById("editQuizModal");
//   modal.style.display = "none";
// }

function processQuiz() {
    const quizForm = document.getElementById("quizForm");
    const formData = new FormData(quizForm);
}

function checkSubmitButtonVisibility() {
    const questionsContainer = document.getElementById("questionsContainer");
    const submitButton = document.getElementById("submitQuizBtn");
    if (questionsContainer.childElementCount === 0) {
        submitButton.style.display = "none";
    } else {
        submitButton.style.display = "block";
    }
}

window.onload = async function () {
    await getName();
    await createQuizList(1);

    // Add question button click event
    document
        .getElementById("addQuestionBtn")
        .addEventListener("click", async function () {
            await checkToken();
            await addQuestion();
        });

    // Quiz form submit event
    document
        .getElementById("quizForm")
        .addEventListener("submit", async function (e) {
            e.preventDefault();
            await checkToken();
            await saveQuizzes();
        });
};
