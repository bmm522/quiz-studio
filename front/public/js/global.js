const loginHost = "http://localhost:8081";
const nodeHost = "http://localhost:3000/api";
const frontHost = "http://localhost:3001";
const quizSpringHost = "http://localhost:8082";
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
document.querySelector(".main-button").addEventListener("click", function(event) {
    event.stopPropagation(); // 이벤트 버블링 중지
      event.preventDefault(); // 기본 동작 중지
      window.location.href = `${frontHost}/main`;
});

document.querySelector(".edit-quiz-button").addEventListener("click", function(event) {

       event.stopPropagation(); 
      event.preventDefault(); 
      window.location.href = `${frontHost}/edit-quiz`;
});

document.querySelector(".record-button").addEventListener("click", function(event) {
    event.stopPropagation(); 
      event.preventDefault(); 
      window.location.href = `${frontHost}/record`;
});


async function checkToken() {

    await fetch(`${loginHost}/api/v1/check-expired-jwt`, {
        method:"GET",
        headers: {
                    authorization: localStorage.getItem("authorization"),
                    refreshToken: localStorage.getItem("refreshToken"),
                },
    })
    .then((res) => res.json())
    .then((res) => {
        console.log( res.data.jwtToken);
        console.log(res.data.refreshToken);
        localStorage.setItem("authorization", res.data.jwtToken);
        localStorage.setItem("refreshToken", res.data.refreshToken);
        
    })

}

// document.getElementById("record-page").addEventListener("click", async function (event) {
//      location.href = `${frontHost}/record`;
// });

// document.getElementById("main-page").addEventListener("click", async function (event) {
//     location.href = `${frontHost}/main`;
// });
