const loginHost = "http://localhost:8081";
const nodeHost = "http://localhost:3000/api";
const frontHost = "http://localhost:3001";

async function getName() {
    await fetch(`${loginHost}/api/v1/email`, {
        method: "GET",
        headers: {
            authorization: sessionStorage.getItem("authorization"),
            refreshToken: sessionStorage.getItem("refreshToken"),
        },
    })
        .then((res) => res.json())
        .then((res) => {
            document.getElementById(
                "email-div",
            ).innerHTML += `${res.data.email}`;
        });
}

document.getElementById("record-page").addEventListener("click", function () {
    location.href = `${frontHost}/record`;
});

document.getElementById("main-page").addEventListener("click", function () {
    console.log('click');
    location.href = `${frontHost}/main`;
});
