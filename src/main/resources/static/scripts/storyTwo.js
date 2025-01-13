import { switchClasses, waitForNSeconds, typeDialog } from './utils.js';

let currentDialogIndex = 0;  
let kipAnimationCounter = 0;
let salesmanclicked = 0;
let statueclicked = 0;

const checkpoint = document.getElementById('checkpoint');
const textBubble = document.getElementById('textbubble');
const nextButton = document.getElementById('nextButton');
const animatedKip = document.getElementById('animatedKip');
const statue = document.getElementById('statue');
const salesman = document.getElementById('romanSalesman');
const video = document.getElementById('video');

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
salesman.addEventListener('click', async () => {
    if (salesmanclicked === 0) {
        nextButton.style.display = 'block';
        textBubble.style.display = 'block'; 
        salesmanclicked = 1; 
    }
});

// Function to interact with statue
statue.addEventListener('click', async () => {
    if (statueclicked === 0) {
        nextButton.style.display = 'block';
        textBubble.style.display = 'block'; 
        statueclicked = 1;
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

        if (currentDialogIndex === 2) {
            switchClasses(animatedKip, 'hoverinthecentre', 'moveright');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'moveright', 'bouncerightmirrored');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'bouncerightmirrored', 'moveleft');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'moveleft', 'hoverinthecentre');
        }

        if (currentDialogIndex === 4) {
            switchClasses(animatedKip, 'hoverinthecentre', 'hoverinthecenterdisappear');
            switchClasses(html, 'html', 'html2');
            nextButton.style.display = 'none';
            textBubble.style.display = 'none'; 
            await waitForNSeconds(2.7);
            /* The statue and salesman will now appear */
            switchClasses(salesman, 'hidden', 'romanSalesman');
        }

        if (currentDialogIndex === 7) {
            nextButton.style.display = 'none';
            textBubble.style.display = 'none'; 
            switchClasses(statue, 'hidden', 'statue');
        }

        if (currentDialogIndex === 8) {
            switchClasses(animatedKip, 'hoverinthecenterdisappear', 'hoverinthecentreappear')
            switchClasses(salesman, 'romanSalesman', 'romanSalesman2');
            switchClasses(statue, 'statue', 'statue2');
        }

        if (currentDialogIndex === 9) {
            switchClasses(statue, 'statue2', 'hidden');
        }

        if (currentDialogIndex === 12) {
            switchClasses(html, "html2", "html3");
            nextButton.style.display = 'none';
            textBubble.style.display = 'none';
            await waitForNSeconds(3);
            nextButton.style.display = 'block';
            textBubble.style.display = 'block';
        }

        if (currentDialogIndex === 14) {
            switchClasses(animatedKip, 'hoverinthecentreappear', 'moveright3');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'moveright3', 'bouncerightmirrored4'); 
            switchClasses(video, 'hidden', 'video');
            nextButton.style.display = 'none';
            textBubble.style.display = 'none'; 
            await waitForNSeconds(5);
            nextButton.style.display = 'block';
            textBubble.style.display = 'block'; 
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
