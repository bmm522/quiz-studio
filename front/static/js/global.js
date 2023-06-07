const loginHost = "https://api.quizstudio.site/user";
const nodeHost = "https://api.quizstudio.site/quiz-node/api";
const frontHost = "https://www.quizstudio.site";
const quizSpringHost = "https://api.quizstudio.site/quiz-spring";

async function getName() {
    await checkToken();
    await getEmail();
}

async function getEmail() {
    await fetch(`${loginHost}/api/v1/email`, {
        method: "GET",
        headers: {
            authorization: localStorage.getItem("authorization"),
            refreshToken: localStorage.getItem("refreshToken"),
        },
    })
        .then((res) => res.json())
        .then((res) => {
            document.getElementById(
                "email-div",
            ).innerHTML += `${res.data.email}`;
        });
}
document
    .querySelector(".main-button")
    .addEventListener("click", function (event) {
        event.stopPropagation(); // 이벤트 버블링 중지
        event.preventDefault(); // 기본 동작 중지
        window.location.href = `${frontHost}/main`;
    });

document
    .querySelector(".edit-quiz-button")
    .addEventListener("click", function (event) {
        event.stopPropagation();
        event.preventDefault();
        window.location.href = `${frontHost}/edit-quiz`;
    });

document
    .querySelector(".record-button")
    .addEventListener("click", function (event) {
        event.stopPropagation();
        event.preventDefault();
        window.location.href = `${frontHost}/record`;
    });

document
    .querySelector("#title-div")
    .addEventListener("click", function (event) {
        event.stopPropagation();
        event.preventDefault();
        window.location.href = `${frontHost}/main`;
    });

async function checkToken() {
    await fetch(`${loginHost}/api/v1/check-expired-jwt`, {
        method: "GET",
        headers: {
            authorization: localStorage.getItem("authorization"),
            refreshToken: localStorage.getItem("refreshToken"),
        },
    })
        .then((res) => res.json())
        .then((res) => {
            localStorage.setItem("authorization", res.data.jwtToken);
            localStorage.setItem("refreshToken", res.data.refreshToken);
        });
}

// document.getElementById("record-page").addEventListener("click", async function (event) {
//      location.href = `${frontHost}/record`;
// });

// document.getElementById("main-page").addEventListener("click", async function (event) {
//     location.href = `${frontHost}/main`;
// });
