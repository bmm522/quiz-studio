function toggleProblemDescription(index) {
    const descriptionRow = document.getElementById(`problemDescription${index}`);
    console.log(descriptionRow.style.display);
    
    if (descriptionRow.style.display === 'none') {
      descriptionRow.style.display = 'table-row';
    } else {
      descriptionRow.style.display = 'none';
    }
  }
  
  function filterProblems() {
    const showUnresolved = document.getElementById('chkUnresolved').checked;
    const choiceDiv = document.getElementsByClassName("problem-description");
    console.log(choiceDiv[0].style.display);
  
    const tableRows = document.querySelectorAll('tbody > tr');

tableRows.forEach(row => {

const status = row.getAttribute('data-status');
if (showUnresolved && status === 'unresolved') {
row.style.display = 'table-row';

} else if (!showUnresolved) {
row.style.display = 'table-row';

} else {
row.style.display = 'none';
}
});

for(let i = 0 ; i < choiceDiv.length ; i ++) {
choiceDiv[i].style.display = 'none';
}

}  
