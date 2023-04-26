const level = localStorage.getItem("level");
const category = localStorage.getItem("category");

getName();

function getQuizData(level, category) {
    console.log("실행됨");
    const url = new URL(`${nodeHost}/v1/quiz`);
    url.searchParams.set("level", level);
    url.searchParams.set("category", category);
    const headers = new Headers();
    headers.append("authorization", localStorage.getItem("authorization"));
    headers.append("refreshToken", localStorage.getItem("refreshToken"));

    return fetch(url, { headers })
        .then((response) => response.json())
        .then((data) => data.data)
        .catch((error) => console.error(error));
}

function loadQuiz() {
    getQuizData(level, category).then((quizData) => {
        const quizElements = createQuizElements(quizData);
        quizContainer.append(...quizElements);
    });
}

loadQuiz();

document
    .getElementById("submit-btn")
    .addEventListener("click", handleSubmitQuiz);
