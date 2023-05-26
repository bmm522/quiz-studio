const quizContainer = document.getElementById("quiz-container");

function createQuizElements(quizData) {
    localStorage.setItem("category",quizData[0].categoryTitle );
    localStorage.setItem("level", "custom");
    return quizData.map((quiz, i) => {
        console.log(quiz.title);
        console.log(quiz);
        const cardDiv = document.createElement("div");
        cardDiv.className = "card shadow mb-4";

        const cardHeaderDiv = document.createElement("div");
        cardHeaderDiv.className = "card-header py-3";

        const titleHeader = document.createElement("h6");
        titleHeader.className = "m-0 font-weight-bold text-primary";
        titleHeader.textContent = `${i + 1}번 문제`;
        cardHeaderDiv.appendChild(titleHeader);

        const cardBodyDiv = document.createElement("div");
        cardBodyDiv.className = "card-body";

        const questionTitle = document.createElement("h5");
        questionTitle.className = "card-title";
        questionTitle.textContent = quiz.quizTitle;
        cardBodyDiv.appendChild(questionTitle);

        quiz.choices.forEach((choice, j) => {
            const choiceDiv = document.createElement("div");
            choiceDiv.className = "form-check";

            const choiceInput = document.createElement("input");
            choiceInput.className = "form-check-input";
            choiceInput.type = "radio";
            choiceInput.name = `quiz${i + 1}`;
            choiceInput.id = `quiz${i + 1}-choice${j + 1}`;
            choiceInput.value = choice.answer;
            choiceDiv.appendChild(choiceInput);

            const choiceLabel = document.createElement("label");
            choiceLabel.className = "form-check-label";
            choiceLabel.setAttribute("for", `quiz${i + 1}-choice${j + 1}`);
            choiceLabel.textContent = choice.choiceContent;
            choiceDiv.appendChild(choiceLabel);

            cardBodyDiv.appendChild(choiceDiv);
        });

        cardDiv.appendChild(cardHeaderDiv);
        cardDiv.appendChild(cardBodyDiv);

        return cardDiv;
    });
}
