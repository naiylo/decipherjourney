let currentDialogIndex = 0;  
const textBubble = document.getElementById('textbubble');
const dialogList = document.getElementById('dialogList');
const nextButton = document.getElementById('nextButton');

// Function to simulate typing effect
function typeDialog(dialog, callback) {
    let charIndex = 0;
    dialogList.innerHTML = "";  

    const typeInterval = setInterval(() => {
        dialogList.innerHTML += dialog.charAt(charIndex);  
        charIndex++;

        if (charIndex === dialog.length) {
            clearInterval(typeInterval); 
            callback(); 
        }
    }, 30); 
}

// Function to move to the next dialog
function showNextDialog() {
    if (currentDialogIndex < dialogs.length) {
        typeDialog(dialogs[currentDialogIndex], () => {
            nextButton.style.display = 'block';  
        });
        currentDialogIndex++;
    } else {
        nextButton.style.display = 'none';  
    }
}

nextButton.addEventListener('click', () => {
    nextButton.style.display = 'none';  
    showNextDialog();
});

showNextDialog();
