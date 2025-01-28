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
const testcipher = document.getElementById('testciphercontainter');
const portal = document.getElementById('portal');
const texttodecipher = document.getElementById('texttodecipher');
const task = document.getElementById('task');

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
        switchClasses(animatedKip, 'hoverinthecenterdisappear', 'hoverinthecentreappear')
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
        console.log(dialogs[currentDialogIndex]);

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
            nextButton.style.display = 'none';
            textBubble.style.display = 'none'; 
            await waitForNSeconds(1);
            switchClasses(html, 'html', 'html2');
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
            switchClasses(salesman, 'romanSalesman', 'romanSalesman2');
            switchClasses(statue, 'statue', 'statue2');
        }

        if (currentDialogIndex === 12) {
            switchClasses(statue, 'statue2', 'hidden')
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
        }

        if (currentDialogIndex === 19) {
            switchClasses(video, 'video', 'video2');
            await waitForNSeconds(1.5);
            video.style.display = 'none';
            switchClasses(animatedKip, 'bouncerightmirrored4', 'moveleft');
            await waitForNSeconds(1.5);
            switchClasses(animatedKip, 'moveleft', 'hoverinthecentre');
        }

        if (currentDialogIndex === 21) {
            switchClasses(animatedKip, 'hoverinthecentre', 'hoverinthecenterdisappear');
            nextButton.style.display = 'none';
            textBubble.style.display = 'none'; 
            await waitForNSeconds(1.5);
            switchClasses(testcipher, 'hidden', 'testcipher');
        }

        if (currentDialogIndex === 24) {
            switchClasses(animatedKip, 'hoverinthecentreappear', 'hoverinthecenterdisappear');
            nextButton.style.display = 'none';
            textBubble.style.display = 'none';
            await waitForNSeconds(1.5);
            texttodecipher.textContent = "W A X A E E Q G Y";
            inputField.value = "";
            resultDiv.textContent = "";
            shift.value= "";
            task.textContent = "Aufgabe: Versuche das Wort zu Entschlüsseln. Bei falschen Versuchen erhälst du Hinweise.";
            switchClasses(testcipher, 'hidden', 'testcipher'); 
        }

        if (currentDialogIndex === 26) {
            switchClasses(portal, 'hidden', 'portal');
        }

        if (currentDialogIndex === 27) {
            switchClasses(portal, 'hidden', 'portal');
            switchClasses(animatedKip, 'hoverinthecentreappear', 'moverightdisappear');
            await waitForNSeconds(1.5);
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

//-------------------------- Animation of the discs in the example ----------------------------------------

const diskContainer = document.getElementById('disk-container');
const innerDisk = document.getElementById('inner-disk');

let isDragging = false; // Tracks whether the user is dragging
let startAngle = 0; // Initial angle when dragging starts
let currentAngle = 0; // Cumulative angle of rotation

// Calculate angle between mouse and center
function calculateAngle(event, centerX, centerY) {
  const dx = event.clientX - centerX;
  const dy = event.clientY - centerY;
  return Math.atan2(dy, dx) * (180 / Math.PI);
}

// Mouse down: start dragging
diskContainer.addEventListener('mousedown', (event) => {
  const rect = diskContainer.getBoundingClientRect();
  const centerX = rect.left + rect.width / 2;
  const centerY = rect.top + rect.height / 2;

  // Calculate the initial angle
  startAngle = calculateAngle(event, centerX, centerY) - currentAngle;
  isDragging = true; // Enable dragging
});

// Mouse move: rotate if dragging
document.addEventListener('mousemove', (event) => {
  if (!isDragging) return;

  const rect = diskContainer.getBoundingClientRect();
  const centerX = rect.left + rect.width / 2;
  const centerY = rect.top + rect.height / 2;

  // Calculate the current angle and apply rotation
  const angle = calculateAngle(event, centerX, centerY);
  currentAngle = angle - startAngle;
  innerDisk.style.transform = `rotate(${currentAngle}deg)`;
});

// Mouse up: stop dragging
document.addEventListener('mouseup', () => {
  isDragging = false;
});

//-------------------------- Animation of the discs in the example ----------------------------------------


//--------------------------    Control the input of the player    ----------------------------------------

let testnumber = 0;
let guessnumber1 = 0;
let guessnumber2 = 0;

const button = document.querySelector('.submitbutton');
const inputField = document.getElementById('input');
const resultDiv = document.getElementById('result');

const correctAnswer1 = "NRORVVHXP";
const correctAnswer2 = "KOLOSSEUM";  

button.addEventListener('click', () => {
    const userInput = inputField.value.trim().toUpperCase();
    
    if (testnumber === 0) {
        if (userInput === correctAnswer1) {
            testnumber = 1;
            switchClasses(testcipher, 'testcipher', 'hidden');
            nextButton.style.display = 'block';
            textBubble.style.display = 'block';
            switchClasses(animatedKip, 'hoverinthecenterdisappear', 'hoverinthecentreappear');
        } else {
            if (guessnumber1 === 0) {
                resultDiv.textContent = "Falsch! Erinner dich, du musst das Wort um 3 Stellen Verschieben!";
                resultDiv.style.color = "red";
                guessnumber1 = 1;
            }
            else if (guessnumber1 === 1) {
                resultDiv.textContent = "Falsch! Erinner dich, du musst das Wort um 3 Stellen Verschieben, also Verschlüsseln!";
                resultDiv.style.color = "red";
                guessnumber1 = 2;
            }
            else if (guessnumber1 === 2) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Bei einer Verschlüsselung wäre das -3.";
                resultDiv.style.color = "red";
                guessnumber1 = 3;
            }
            else if (guessnumber1 === 3) {
                resultDiv.textContent = "Falsch! Probiere die Scheibe um -3 gegen Uhrzeigersinn zu drehen.";
                resultDiv.style.color = "red";
                guessnumber1 = 4;
            }
            else if (guessnumber1 === 4) {
                resultDiv.textContent = "Falsch! Probiere die Scheibe um -3 gegen den Uhrzeigersinn zu drehen. Das W würde für Z stehen.";
                resultDiv.style.color = "red";
            }
        }
    }

    else if (testnumber === 1) {
        if (userInput === correctAnswer2) {
            testnumber = 2;
            switchClasses(testcipher, 'testcipher', 'hidden');
            nextButton.style.display = 'block';
            textBubble.style.display = 'block';
            switchClasses(animatedKip, 'hoverinthecenterdisappear', 'hoverinthecentreappear');
        } else {
            if (guessnumber2 === 0) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal.";
                resultDiv.style.color = "red";
                guessnumber2 = 1;
            }
            else if (guessnumber2 === 1) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Denke daran häufige Buchstaben zu vergleichen und mit häufig auftretenden zu ersetzten.";
                resultDiv.style.color = "red";
                guessnumber2 = 2;
            }
            else if (guessnumber2 === 2) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Denke zurück an die Anzahl der Götter und der Ziegen?";
                resultDiv.style.color = "red";
                guessnumber2 = 3;
            }
            else if (guessnumber2 === 3) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Probiere das W als K zu ersetzen.";
                resultDiv.style.color = "red";
            }
        }
    }
});

// Shift the disc according to shift value provided by user

const shiftButton = document.getElementById('shift-button');
const shift = document.getElementById('shift');

// Apply the initial rotation
shiftButton.addEventListener('click', () => {
    const spinDegree = parseFloat(shift.value * 13.8461538462) || 0;
    const innerDisk = document.getElementById('inner-disk');
    console.log(spinDegree);
  
    // Apply the initial rotation
    const currentAngle = spinDegree;
    innerDisk.style.transform = `rotate(${currentAngle}deg)`;
  });