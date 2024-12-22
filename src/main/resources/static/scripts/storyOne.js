import { switchClasses } from './utils.js';

let currentDialogIndex = 0;  
let kipAnimationCounter = 0;

const checkpoint = document.getElementById('checkpoint');
const textBubble = document.getElementById('textbubble');
const dialogList = document.getElementById('dialogList');
const nextButton = document.getElementById('nextButton');
const animatedKip = document.getElementById('animatedKip');
const example = document.getElementById('example');
const html = document.getElementById('html');
const portal = document.getElementById('portal');

// Funktion to animate Kip
animatedKip.addEventListener('click', async () => {
    if (kipAnimationCounter === 0) {
        // Use the imported function
        switchClasses(animatedKip, 'hoverindoorclickable', 'flyfromdoortomiddlepart');
        kipAnimationCounter = 1;

        await waitForNSeconds(3);

        switchClasses(animatedKip, 'flyfromdoortomiddlepart', 'hoverinthecentre');
        switchClasses(textBubble, 'hidden', 'textbubble');
        switchClasses(checkpoint, 'hidden', 'checkpoint'); 
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
    }, 5); 
}

// Function to move to the next dialog
async function showNextDialog() {
    if (currentDialogIndex < dialogs.length - 1) {
        typeDialog(dialogs[currentDialogIndex], () => {
            nextButton.style.display = 'block';
        });
        currentDialogIndex++;
        console.log(currentDialogIndex);

        if (currentDialogIndex === 8) {
            switchClasses(animatedKip, 'hoverinthecentre', 'moveright');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'moveright', 'bouncerightmirrored');
            await waitForNSeconds(1.5);
            switchClasses(example, 'hidden', 'exampleCipher');
        }

        if (currentDialogIndex === 8) {
            switchClasses(animatedKip, 'hoverinthecentre', 'moveright');
            switchClasses(example, 'hidden', 'exampleCipher');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'moveright', 'bouncerightmirrored');
        }

        if (currentDialogIndex === 10) {
            switchClasses(animatedKip, 'bouncerightmirrored', 'moveleft');
            switchClasses(example, 'exampleCipher', 'hidden');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'moveleft', 'hoverinthecentre');
        }

        if (currentDialogIndex === 14) {
            switchClasses(animatedKip, 'hoverinthecentre', 'moveright2');
            switchClasses(html, 'html', 'html2');
            await waitForNSeconds(2.5);
            switchClasses(animatedKip, 'moveright2', 'bouncerightmirrored2');
            switchClasses(portal, 'hidden', 'portal');
        }

        if (currentDialogIndex === 15) {
            switchClasses(animatedKip, 'bouncerightmirrored2', 'flyleftdisappear');
            switchClasses(portal, 'portal', 'portal2');
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
