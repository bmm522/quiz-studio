const loginHost = "http://localhost:8081";
const nodeHost = "http://localhost:3000/api";
const frontHost = "http://localhost:3001";

const level = localStorage.getItem("level");
const category = localStorage.getItem("category");

function getQuizData(level, category) {
   
    const url = new URL(`${nodeHost}/v1/quiz`);
    url.searchParams.set("level", level);
    url.searchParams.set("category", category);
    console.log(sessionStorage.getItem("refreshToken"));
    console.log("퀴즈 : " + sessionStorage.getItem("authorization"));
    const headers = new Headers();
    headers.append("authorization", sessionStorage.getItem("authorization"));
    headers.append("refreshToken", sessionStorage.getItem("refreshToken"));

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
