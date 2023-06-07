window.onload = async function () {
    if (getCookieValue("Authorization") !== "") {
        await setToken();
    }
    await getName();
    await getCategories(1);
};

async function getCategories(page) {
    const url = new URL(`${quizSpringHost}/api/v1/category`);
    url.searchParams.set("page", page);
    const response = await fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            authorization: localStorage.getItem("authorization"),
            refreshToken: localStorage.getItem("refreshToken"),
        },
    });

    const data = await response.json();
    const categoryList = data.data.categories;

    const categoryDiv = document.getElementById("custom-category-div");
    categoryDiv.innerHTML = "";

    await categoryList.forEach((category) => {
        categoryDiv.innerHTML += `     
         <div class="col-lg-3 mb-4" id="category-div-${category.categoryId}">      
        <div class="card bg-secondary text-white shadow">
        <div class="card-body" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            ${category.title}
            <div class="text-white-50 small">${category.description}</div>
        </div>
    </div>
</div>`;
    });
    const totalPages = data.data.totalPage;
    const paginationContainer = document.getElementById("pagination");
    paginationContainer.innerHTML = "";

    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement("button");
        button.className = "btn btn-primary btn-sm mr-2";
        button.textContent = i;
        button.setAttribute("data-page", i);
        button.addEventListener("click", async function () {
            const page = parseInt(this.getAttribute("data-page"));
            await checkToken();
            await getCategories(page);
        });
        paginationContainer.appendChild(button);
    }

    for (let i = 0; i < categoryList.length; i++) {
        const getCategoryDivCell = document.getElementById(
            "category-div-" + categoryList[i].categoryId,
        );
        getCategoryDivCell.addEventListener("click", async function () {
            await checkToken();
            await moveQuizPage(categoryList[i].categoryId);
        });
    }
}

async function moveQuizPage(categoryId) {
    // localStorage.setItem("level", "easy");
    location.href = `${frontHost}/custom-quiz?categoryId=${categoryId}`;
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
// async function getName() {
//     await fetch(`${loginHost}/api/v1/email`, {
//         method: "GET",
//         headers: {
//             authorization: sessionStorage.getItem("authorization"),
//             refreshToken: sessionStorage.getItem("refreshToken"),
//         },
//     })
//         .then((res) => res.json())
//         .then((res) => {
//             document.getElementById(
//                 "email-div",
//             ).innerHTML += `${res.data.email}`;
//         });
// }

const getCookieValue = (key) => {
    let cookieKey = key + "=";
    let result = "";
    const cookieArr = document.cookie.split(";");

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

document.getElementById("java-div").addEventListener("click", function () {
    // localStorage.setItem("level", "easy");
    localStorage.setItem("category", "java");
    location.href = `${frontHost}/quiz`;
});
document
    .getElementById("data-structure-div")
    .addEventListener("click", function () {
        // localStorage.setItem("level", "easy");
        localStorage.setItem("category", "datastructure");
        location.href = `${frontHost}/quiz`;
    });

document.getElementById("database-div").addEventListener("click", function () {
    // localStorage.setItem("level", "easy");
    localStorage.setItem("category", "database");
    location.href = `${frontHost}/quiz`;
});

function deleteCookie(name) {
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:01 GMT;";
}
