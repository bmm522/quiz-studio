const loginHost = "http://localhost:8081";
const headers = {
    Authorization:localStorage.getItem('authorization'),
    RefreshToken:localStorage.getItem('refreshToken'),
}

window.onload = async function() {
  
    console.log(getCookieValue('Authorization').replace("+"," "));
    console.log(getCookieValue('RefreshToken').replace("+"," "));
    await setToken();
    await getName();
}

async function setToken(){
    localStorage.setItem("authorization", getCookieValue('Authorization').replace("+"," "));
    localStorage.setItem("refreshToken", getCookieValue('RefreshToken').replace("+"," "));
}

async function getName() {
    await fetch(`${loginHost}/api/v1/email`, {
        method:"GET",
        headers:headers
    })
    .then((res) => res.json())
    .then(res => {
      console.log(res.data.email);
        document.getElementById('email-div').innerHTML += `${res.data.email} 접속`;
    })
}

const getCookieValue = (key) => {
    let cookieKey = key + "="; 
    let result = "";
    const cookieArr = document.cookie.split(";");
    
    for(let i = 0; i < cookieArr.length; i++) {
      if(cookieArr[i][0] === " ") {
        cookieArr[i] = cookieArr[i].substring(1);
      }
      
      if(cookieArr[i].indexOf(cookieKey) === 0) {
        result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
        return result;
      }
    }
    return result;
  }