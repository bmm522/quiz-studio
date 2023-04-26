const loginHost = "http://localhost:8081";
const nodeHost = "http://localhost:3000/api";
const frontHost = "http://localhost:3001";

async function getName() {
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

document.querySelector(".record-button").addEventListener("click", function(event) {
    event.stopPropagation(); // 이벤트 버블링 중지
      event.preventDefault(); // 기본 동작 중지
      window.location.href = `${frontHost}/record`;
});

// document.getElementById("record-page").addEventListener("click", async function (event) {
//      location.href = `${frontHost}/record`;
// });

// document.getElementById("main-page").addEventListener("click", async function (event) {
//     location.href = `${frontHost}/main`;
// });
