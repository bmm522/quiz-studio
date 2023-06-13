const level = localStorage.getItem("level");
const category = localStorage.getItem("category");

window.onload = async function () {
    await getName();
    await checkToken();
    await loadQuiz();
};
async function getQuizData(category) {
    const url = new URL(`${nodeHost}/v1/quiz`);
    // url.searchParams.set("level", level);
    url.searchParams.set("category", category);
    
    const headers = new Headers();
    if(localStorage.getItem("authorization")!==null) {
        headers.append("authorization", localStorage.getItem("authorization"));
        headers.append("refreshToken", localStorage.getItem("refreshToken"));
    } else {
        headers.append("authorization", 'guest');
        headers.append("refreshToken", 'guest');
    }


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
