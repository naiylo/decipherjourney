import { switchClasses, waitForNSeconds, typeDialog } from './utils.js';

let currentDialogIndex = 0;  
let kipAnimationCounter = 0;

const checkpoint = document.getElementById('checkpoint');
const textBubble = document.getElementById('textbubble');
const nextButton = document.getElementById('nextButton');
const animatedKip = document.getElementById('animatedKip');
const example = document.getElementById('example');
const html = document.getElementById('html');
const portal = document.getElementById('portal');

// Function to animate Kip
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
            nextButton.style.display = 'none';
            textBubble.style.display = 'none';
            await waitForNSeconds(2.5);
            nextButton.style.display = 'block';
            textBubble.style.display = 'block'; 
            switchClasses(animatedKip, 'moveright2', 'bouncerightmirrored2');
            switchClasses(portal, 'hidden', 'portal');
        }

        if (currentDialogIndex === 15) {
            switchClasses(animatedKip, 'bouncerightmirrored2', 'flyleftdisappear');
            switchClasses(portal, 'portal', 'portal2');
        }

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
