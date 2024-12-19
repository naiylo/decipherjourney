let currentDialogIndex = 0;  

let kipAnimationCounter = 0;

const checkpoint = document.getElementById('checkpoint');
const textBubble = document.getElementById('textbubble');
const dialogList = document.getElementById('dialogList');
const nextButton = document.getElementById('nextButton');
const animatedKip = document.getElementById('animatedKip');

// Funktion to animate Kip
animatedKip.addEventListener('click', async () => {
    if (kipAnimationCounter === 0) {
        
        animatedKip.classList.remove('robotkip');
        animatedKip.classList.add('robotkip2');
        kipAnimationCounter = 1;
        
        
        await waitForNSeconds(3);  

        animatedKip.classList.remove('robotkip2');
        animatedKip.classList.add('robotkip3');
        textBubble.classList.remove('hidden');
        textBubble.classList.add('textbubble');
        checkpoint.classList.remove('hidden');
    }
});

// Function to wait for a certain amount of time
function waitForNSeconds(n) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve();
        }, n * 1000); 
    });
}

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
    }, 20); 
}

// Function to move to the next dialog
async function showNextDialog() {
    if (currentDialogIndex < dialogs.length) {
        typeDialog(dialogs[currentDialogIndex], () => {
            nextButton.style.display = 'block';
        });
        currentDialogIndex++;
        console.log(currentDialogIndex);

        if (currentDialogIndex === 8) {
            animatedKip.classList.remove('robotkip3');
            animatedKip.classList.add('robotkip4');
            await waitForNSeconds(1.5);
            animatedKip.classList.remove('robotkip4');
            animatedKip.classList.add('robotkip5'); 
        }
    } else {
        nextButton.style.display = 'none'; 
    }
}

nextButton.addEventListener('click', () => {
    nextButton.style.display = 'none';  
    showNextDialog();
});

showNextDialog();
