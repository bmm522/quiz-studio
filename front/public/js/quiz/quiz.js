const level = localStorage.getItem("level");
const category = localStorage.getItem("category");


window.onload = async function () {
    await checkToken();
    await getEmail();
    await loadQuiz();
}
async function getQuizData(category) {
    console.log("실행됨");
    const url = new URL(`${nodeHost}/v1/quiz`);
    // url.searchParams.set("level", level);
    url.searchParams.set("category", category);
    const headers = new Headers();
    headers.append("authorization", localStorage.getItem("authorization"));
    headers.append("refreshToken", localStorage.getItem("refreshToken"));

    return await fetch(url, { headers })
        .then((response) => response.json())
        .then((data) => data.data)
        .catch((error) => console.error(error));
}

async function loadQuiz() {
    getQuizData(category).then((quizData) => {
        const quizElements = createQuizElements(quizData);
        quizContainer.append(...quizElements);
    });
}



document
    .getElementById("submit-btn")
    .addEventListener("click", handleSubmitQuiz);
