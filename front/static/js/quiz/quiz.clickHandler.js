function handleSubmitQuiz() {
    const result = confirm("퀴즈를 제출하시겠습니까?");
    const quizRecordArray = [];

    if (result) {
        const cardBodys = document.getElementsByClassName("card-body");
        let numCorrect = 0;
        for (let i = 0; i < cardBodys.length; i++) {
            const cardBody = cardBodys[i];
            const choiceInputs =
                cardBody.getElementsByClassName("form-check-input");
            const choicelabels =
                cardBody.getElementsByClassName("form-check-label");
            const quizTitle = cardBody.getElementsByClassName("card-title")[0];
            let record = {
                quizTitle: "",
                quizIsAnswer: false,
                quizChoiceContent: [],
                quizChoiceIsAnswer: [],
            };
            let isCorrect = false;
            for (let j = 0; j < choiceInputs.length; j++) {
                const choiceInput = choiceInputs[j];
                if (choiceInput.checked) {
                    choiceInput.disabled = true; // 선택지를 비활성화
                    if (choiceInput.value === "true") {
                        choiceInput.parentNode.classList.add("bg-success"); // 정답인 경우 배경색을 초록색으로 변경
                        isCorrect = true;
                        numCorrect++;
                    } else {
                        choiceInput.parentNode.classList.add("bg-danger"); // 오답인 경우 배경색을 빨간색으로 변경
                    }
                } else if (choiceInput.value === "true") {
                    choiceInput.parentNode.classList.add("bg-warning"); // 선택하지 않았지만 정답인 경우 텍스트 색상을 노란색으로 변경
                }
            }
            if (isCorrect) {
                record = {
                    quizTitle: quizTitle.outerText,
                    quizIsAnswer: true,
                    category: localStorage.getItem("category"),
                    level: localStorage.getItem("level"),
                    quizChoiceContent: Array.from(choicelabels).map(
                        (label) => label.outerText,
                    ),
                    quizChoiceIsAnswer: Array.from(choiceInputs).map(
                        (choice) => choice.value,
                    ),
                };
                quizRecordArray.push(record);
                quizTitle.classList.add("text-success"); // 정답인 경우 제목의 텍스트 색상을 초록색으로 변경
            } else {
                record = {
                    quizTitle: quizTitle.outerText,
                    quizIsAnswer: false,
                    category: localStorage.getItem("category"),
                    level: localStorage.getItem("level"),
                    quizChoiceContent: Array.from(choicelabels).map(
                        (label) => label.outerText,
                    ),
                    quizChoiceIsAnswer: Array.from(choiceInputs).map(
                        (choice) => choice.value,
                    ),
                };
                quizRecordArray.push(record);
                quizTitle.classList.add("text-danger"); // 오답인 경우 제목의 텍스트 색상을 빨간색으로 변경
            }
        }
        const submitBtn = document.getElementById("submit-btn");
        submitBtn.style.display = "none";
        createResultElement(numCorrect);

        submitRecordWithFailQuiz(quizRecordArray);
    }
}

function submitRecordWithFailQuiz(quizRecordArray) {
    if(localStorage.getItem("authorization")!==null) {
        fetch(`${nodeHost}/v1/records`, {
            method: "POST",
            headers: {
                authorization: localStorage.getItem("authorization"),
                refreshToken: localStorage.getItem("refreshToken"),
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                quizRecordArray: quizRecordArray,
            }),
        })
            .then((res) => res.json())
            .then((res) => {});
    }
  
}

function createResultElement(numCorrect) {
    const navbar = document.getElementById("after");

    const score = document.createElement("span");
    score.classList.add(
        "mr-2",
        "d-none",
        "d-lg-inline",
        "text-gray-600",
        "small",
    );
    score.innerText = `맞은 개수: ${numCorrect}/10`;

    const retryBtn = document.createElement("button");
    retryBtn.classList.add("btn", "btn-primary", "btn-sm", "mr-2");
    retryBtn.innerText = "다시 풀기";
    retryBtn.addEventListener("click", function () {
        window.location.reload();
    });

    const mainMenuBtn = document.createElement("button");
    mainMenuBtn.classList.add("btn", "btn-secondary", "btn-sm");
    mainMenuBtn.innerText = "메인 메뉴로";
    mainMenuBtn.addEventListener("click", function () {
        window.location.href = `${frontHost}/main`;
    });

    navbar.appendChild(score);
    navbar.appendChild(retryBtn);
    navbar.appendChild(mainMenuBtn);
    score.classList.remove("d-none");
    window.scrollTo(0, 0);
}
