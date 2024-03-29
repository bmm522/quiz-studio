const level = localStorage.getItem("level");
const category = localStorage.getItem("category");

window.onload = async function () {
    await getName();
    if(localStorage.getItem("authorization")!==null) {
        await loadQuiz();
    } else {
        alert('잘못된 접근입니다.');
        window.location.href = `${frontHost}/main`;
    }
    
};
async function getQuizData() {
    const urlParams = new URL(location.href).searchParams;
    const categoryId = urlParams.get('categoryId');
    const url = new URL(
        `${quizSpringHost}/api/v1/category/${categoryId}/take-quiz`,
    );
    const headers = new Headers();
    headers.append("authorization", localStorage.getItem("authorization"));
    headers.append("refreshToken", localStorage.getItem("refreshToken"));

    return await fetch(url, { headers })
        .then((response) => response.json())
        .then((data) => data.data)
        .catch((error) => console.error(error));
}

async function loadQuiz() {
    getQuizData().then((quizData) => {
        const quizElements = createQuizElements(quizData.quizzes);
        quizContainer.append(...quizElements);
    });
}

document
    .getElementById("submit-btn")
    .addEventListener("click", handleSubmitQuiz);
