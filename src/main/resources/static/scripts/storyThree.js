import { switchClasses, waitForNSeconds, typeDialog } from './utils.js';

let currentDialogIndex = 0;  
let kipAnimationCounter = 0;
let sherlockHolmesclicked = 0;

const checkpoint = document.getElementById('checkpoint');
const textBubble = document.getElementById('textbubble');
const nextButton = document.getElementById('nextButton');
const animatedKip = document.getElementById('animatedKip');
const sherlockHolmes = document.getElementById('sherlockHolmes');

// Function to animate Kip
animatedKip.addEventListener('click', async () => {
    if (kipAnimationCounter === 0) {
        // Use the imported function
        switchClasses(animatedKip, 'hoverinthecentreclickableappear', 'hoverinthecentre');
        kipAnimationCounter = 1;
        switchClasses(textBubble, 'hidden', 'textbubble');
        switchClasses(checkpoint, 'hidden', 'checkpoint'); 
    }
});

// Function to interact with salesman
sherlockHolmes.addEventListener('click', async () => {
    if (sherlockHolmesclicked === 0) {
        nextButton.style.display = 'block';
        textBubble.style.display = 'block'; 
        sherlockHolmesclicked = 1; 
    }
});

// Function to move to the next dialog
async function showNextDialog() {
    if (currentDialogIndex < dialogs.length - 1) {
        typeDialog(dialogs[currentDialogIndex], () => {
            nextButton.style.display = 'block';
        });

        if (currentDialogIndex === 3) {
            switchClasses(animatedKip, 'hoverinthecentre', 'hoverinthecenterdisappear');
            nextButton.style.display = 'none';
            textBubble.style.display = 'none'; 
            await waitForNSeconds(1);
            switchClasses(html, 'html', 'html2');
            await waitForNSeconds(2.7);
            switchClasses(sherlockHolmes, 'hidden', 'sherlockHolmes');
        }

        if (currentDialogIndex === 7) {
            switchClasses(sherlockHolmes, 'sherlockHolmes', 'hidden');
            nextButton.style.display = 'none';
            textBubble.style.display = 'none'; 
            await waitForNSeconds(1);
            switchClasses(animatedKip, 'hoverinthecenterdisappear', 'hoverinthecentreappear');
            await waitForNSeconds(2);
            nextButton.style.display = 'block';
            textBubble.style.display = 'block'; 
        }

        currentDialogIndex++;
        console.log(currentDialogIndex);
        console.log(dialogs[currentDialogIndex]);


    } else {
        nextButton.style.display = 'none';
        textBubble.style.display = 'none'; 
    }
}

nextButton.addEventListener('click', () => {
    nextButton.style.display = 'none';  
    showNextDialog();
});

showNextDialog();