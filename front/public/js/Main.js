const loginHost = "http://localhost:8081";
const frontHost = "http://localhost:3001"


window.onload = async function() {

    console.log(getCookieValue('Authorization').replace("+"," "));
    console.log(getCookieValue('RefreshToken').replace("+"," "));
    await setToken();
    await getName();
}



async function setToken(){
  
    sessionStorage.setItem("authorization", getCookieValue('Authorization').replace("+"," "));
    sessionStorage.setItem("refreshToken", getCookieValue('RefreshToken').replace("+"," "));
}

async function getName() {

    await fetch(`${loginHost}/api/v1/email`, {
        method:"GET",
        headers:{
          authorization:sessionStorage.getItem('authorization'),
          refreshToken:sessionStorage.getItem('refreshToken')
        }
    })
    .then((res) => res.json())
    .then(res => {
        document.getElementById('email-div').innerHTML += `${res.data.email}`;
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


document.getElementById('java-easy').addEventListener('click', function() {
  localStorage.setItem('level', 'easy');
  localStorage.setItem('category', 'java');
  location.href = `${frontHost}/quiz`;
});

function deleteCookie(name) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
