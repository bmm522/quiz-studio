const loginHost = "https://api.quizstudio.site/user";
const nodeHost = "https://api.quizstudio.site/quiz-node/api";
const frontHost = "https://www.quizstudio.site";
const quizSpringHost = "https://api.quizstudio.site/quiz-spring";

async function getName() {
    if (getCookieValue("Authorization") !== "") {
        console.log('들어오니? 쿠키?');
        await setToken();
    }
    if(localStorage.getItem("authorization")!==null) {
        await checkToken();
        await getEmail();
    }else {
        document.getElementById(
            "email-div",
        ).innerHTML = `GUEST 로그인`;

        document.getElementById('userDropdown-div').innerHTML = ` <a style="height: 40px;" class="dropdown-item" href="https://api.quizstudio.site/user/api/v1/social/login/google">
        <i >    <img width="15px" src="https://www.quizstudio.site/static/image/google.png" alt="google"></i>
        구글 로그인
    </a>
    <a class="dropdown-item" href="https://api.quizstudio.site/user/api/v1/social/login/kakao">
        <i >    <img width="15px" src="https://www.quizstudio.site/static/image/kakao.png" alt="kakao"></i>
        카카오 로그인
    </a>`
    }


}

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
                ).innerHTML = `${res.data.email}`;

                document.getElementById('userDropdown-div').innerHTML =`<div  class="dropdown-item" style="cursor: pointer;" id="logout-div" onclick="logoutEvent();">
                <i >    <img width="15px" src="https://www.quizstudio.site/static/image/logout.png" alt="logout"></i>
                   로그아웃
          </div>`;

                    
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
        if(localStorage.getItem("authorization")!==null) {
            event.stopPropagation();
            event.preventDefault();
            window.location.href = `${frontHost}/edit-quiz`;
        } else {
            alert('로그인이 필요한 서비스입니다.');
            event.stopPropagation(); // 이벤트 버블링 중지
            event.preventDefault(); // 기본 동작 중지
            window.location.href = `${frontHost}/main`;
        }

    });

document
    .querySelector(".record-button")
    .addEventListener("click", function (event) {
        if(localStorage.getItem("authorization")!==null) {
            event.stopPropagation();
            event.preventDefault();
            window.location.href = `${frontHost}/record`;
        } else {
            alert('로그인이 필요한 서비스입니다.');
            event.stopPropagation(); // 이벤트 버블링 중지
            event.preventDefault(); // 기본 동작 중지
            window.location.href = `${frontHost}/main`;
        }
      
    });

document
    .querySelector("#title-div")
    .addEventListener("click", function (event) {
        event.stopPropagation();
        event.preventDefault();
        window.location.href = `${frontHost}/main`;
    });
 function deleteCookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;domain=quizstudio.site; path=/;';
}

function logoutEvent() {
    const result = confirm("로그아웃을 하시겠습니까?");
    if(result) {

        fetch(`${loginHost}/api/v1/logout`, {
            method: "GET",
            headers: {
                authorization: localStorage.getItem("authorization"),
                refreshToken: localStorage.getItem("refreshToken"),
            },
        }).then((res) => {
            console.log('여기들어옴');
            localStorage.removeItem("authorization");
            localStorage.removeItem("refreshToken");
            alert('로그아웃 되었습니다.')
            window.location.href = `${frontHost}/main`;
        })

    }

}
async function setToken() {
    localStorage.setItem(
        "authorization",
        getCookieValue("Authorization").replace("+", " "),
    );
    localStorage.setItem(
        "refreshToken",
        getCookieValue("RefreshToken").replace("+", " "),
    );
}

// document.getElementById("record-page").addEventListener("click", async function (event) {
//      location.href = `${frontHost}/record`;
// });

// document.getElementById("main-page").addEventListener("click", async function (event) {
//     location.href = `${frontHost}/main`;
// });
const getCookieValue = (key) => {
    let cookieKey = key + "=";
    let result = "";
    const cookieArr = document.cookie.split(";");
    console.log(key);
    for (let i = 0; i < cookieArr.length; i++) {
       
        if (cookieArr[i][0] === " ") {
            cookieArr[i] = cookieArr[i].substring(1);
        }

        if (cookieArr[i].indexOf(cookieKey) === 0) {
            result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
            return result;
        }
    }
    return result;
};