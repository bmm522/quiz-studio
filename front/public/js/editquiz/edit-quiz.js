window.onload = async function () {
      await checkToken();
      await getCategories(1);
      
  }
  
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
  
      const tableBody = document.querySelector("table tbody");
      tableBody.innerHTML = "";
  
      categoryList.forEach((category) => {
            const { title, description, categoryId } = category;
          
            const row = document.createElement("tr");
          
            const categoryTitle = document.createElement("td");
            categoryTitle.textContent = title;
          
            const categoryDescription = document.createElement("td");
            categoryDescription.textContent = description;
          
            const editButtonCell = document.createElement("td");
            const editButton = document.createElement("button");
            editButton.className = "btn btn-primary btn-sm";
            editButton.textContent = "편집";
            editButton.setAttribute("id", categoryId);
            editButton.addEventListener("click", () => {
              console.log(`클릭한 카테고리: ${title}`);
              showModal(category);
            });

            const editQuizButtonCell = document.createElement("td");
            const editQuizButton = document.createElement("button");
            editQuizButton.className = "btn btn-primary btn-sm";
            editQuizButton.textContent = "문제 관리 하기";
            editQuizButton.setAttribute("id", categoryId);
            editQuizButton.addEventListener("click", () => {
                  localStorage.setItem("categoryId", categoryId);
                  window.location.href = `${frontHost}/edit-quiz-detail`;
            });
            
            editQuizButtonCell.appendChild(editQuizButton);
            editButtonCell.appendChild(editButton);
            row.appendChild(categoryTitle);
            row.appendChild(categoryDescription);
            row.appendChild(editButtonCell);
            row.appendChild(editQuizButtonCell);
            tableBody.appendChild(row);
          });
  
      const totalPages = data.data.totalPage;
      const paginationContainer = document.getElementById('pagination');
      paginationContainer.innerHTML = "";
  
      for (let i = 1; i <= totalPages; i++) {
          const button = document.createElement('button');
          button.className = 'btn btn-primary btn-sm mr-2';
          button.textContent = i;
          button.setAttribute('data-page', i);
          button.addEventListener('click', function() {
              const page = parseInt(this.getAttribute('data-page'));
              movePage(page);
          });
          paginationContainer.appendChild(button);
      }
  }
  function showModal(category) {
      console.log('편집창');
      const editDiv = document.getElementById('editModal');
      // 모달 요소 생성
      const modal = document.createElement("div");
      modal.className = "modal fade";
      modal.id = "exampleModal";
      modal.tabIndex = "-1";
      modal.setAttribute("aria-labelledby", "exampleModalLabel");
      modal.setAttribute("aria-hidden", "true");
      modal.innerHTML = `
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">카테고리 편집</h5>
            </div>
            <div class="modal-body">
              <label for="editTitle">제목:</label>
              <input type="text" id="editTitle" class="form-control" value="${category.title}">
              <label for="editDescription">내용:</label>
              <input type="text" id="editDescription" class="form-control" value="${category.description}">
            </div>
            <div class="modal-footer">
              <button id="editCloseButton" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button id="editButton" class="btn btn-primary">수정</button>
            </div>
          </div>
        </div>
      `;

      const editCloseButton = modal.querySelector('#editCloseButton');
      editCloseButton.addEventListener("click", () => {
            myModal.hide();
      })

    
      // 저장 버튼에 이벤트 추가
      const saveButton = modal.querySelector("#editButton");
      saveButton.addEventListener("click", () => {
        const editTitle = modal.querySelector("#editTitle").value;
        const editDescription = modal.querySelector("#editDescription").value;
        updateCategory(category.categoryId, editTitle, editDescription);
        var myModal = bootstrap.Modal.getInstance(document.getElementById('exampleModal'));
        myModal.hide();
      });
    
      // 모달을 body에 추가
      editDiv.appendChild(modal);
      var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
      myModal.show();
    }
  
  async function updateCategory(categoryId, editTitle, editDescription) {
      const response = await fetch(`${quizSpringHost}/api/v1/category`, {
          method: "PATCH",
          headers: {
              "Content-Type": "application/json",
              authorization: localStorage.getItem("authorization"),
              refreshToken: localStorage.getItem("refreshToken"),
          },
          body: JSON.stringify({
            categoryId: categoryId,
            updateTitle: editTitle,
             updateDescription: editDescription
          }),
      });
      const data = await response.json();
      console.log(data);
      alert('카테고리가 성공적으로 수정되었습니다.');
      window.location.reload();
  }
  
  async function movePage(page) {
      await checkToken();
      await getCategories(page);
  }
  
  async function saveEvent() {
      await checkToken();
      await saveCategory();
  }
  
  async function saveCategory() {
      const categoryTitle =  document.getElementById('categoryTitle').value;
      const categoryDescription =  document.getElementById('categoryContent').value;
      const response = await fetch(`${quizSpringHost}/api/v1/category`, {
          method: "POST",
          headers: {
              "Content-Type":"application/json",
              authorization: localStorage.getItem("authorization"),
              refreshToken: localStorage.getItem("refreshToken"),
          },
          body: JSON.stringify({
              title: categoryTitle,
              description: categoryDescription
          }),
      });
      const data = await response.json();
      console.log(data);
      alert('카테고리가 성공적으로 저장되었습니다.');
      window.location.reload();
  }