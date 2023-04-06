const host = "http://localhost:8081";

document
    .getElementById("google-connect")
    .addEventListener("click", function () {
        location.href = `${host}/api/v1/social/login/google`;
    });

document.getElementById("kakao-connect").addEventListener("click", function () {
    location.href = `${host}/api/v1/social/login/kakao`;
});
