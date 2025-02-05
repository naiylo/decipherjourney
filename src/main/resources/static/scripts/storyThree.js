import { switchClasses, waitForNSeconds, typeDialog } from './utils.js';

let currentDialogIndex = 0;  
let kipAnimationCounter = 0;
let sherlockHolmesclicked = 0;

const checkpoint = document.getElementById('checkpoint');
const textBubble = document.getElementById('textbubble');
const nextButton = document.getElementById('nextButton');
const animatedKip = document.getElementById('animatedKip');
const sherlockHolmes = document.getElementById('sherlockHolmes');
const testcipher = document.getElementById('testciphercontainter');
const texttodecipher = document.getElementById('texttodecipher');
const task = document.getElementById('task');
const sign = document.getElementById('sign');
const portal = document.getElementById('portal');

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
            switchClasses(animatedKip, 'hoverinthecenterdisappear', 'hoverinthecentreappear');
            await waitForNSeconds(2);
            nextButton.style.display = 'block';
            textBubble.style.display = 'block'; 
        }

        if (currentDialogIndex === 13) {
            switchClasses(html, "html2", "html3");
            nextButton.style.display = 'none';
            textBubble.style.display = 'none';
            await waitForNSeconds(3);
            nextButton.style.display = 'block';
            textBubble.style.display = 'block';
        }

        if (currentDialogIndex === 17) {
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
            texttodecipher.textContent = "M R W K N D";
            inputField.value = "";
            resultDiv.textContent = "";
            sign.textContent = "-";
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


//--------------------------    Control of the modulo button    ----------------------------------------

const modulobutton = document.getElementById('modulo-button');

modulobutton.addEventListener('click', () => {


    if (testnumber === 0) {
        let num1 = parseInt(document.getElementById("number1").value) || 0;
        let num2 = parseInt(document.getElementById("number2").value) || 0;
        let sum = num1 + num2;
        let result = sum % 26;
        document.getElementById("moduloResult").textContent = result;
        document.getElementById("calculationSteps").textContent = `${num1} + ${num2} = ${sum} mod 26 = ${result}`;
    } else {
        let num1 = parseInt(document.getElementById("number1").value) || 0;
        let num2 = parseInt(document.getElementById("number2").value) || 0;
        let sum = num1 - num2 + 26;
        let result = sum % 26;
        document.getElementById("moduloResult").textContent = result;
        document.getElementById("calculationSteps").textContent = `${num1} - ${num2} = ${sum} mod 26 = ${result}`;
    }

    
});

//--------------------------    Control the input of the player    ----------------------------------------

let testnumber = 0;
let guessnumber1 = 0;
let guessnumber2 = 0;

const button = document.getElementById('submitbutton');
const inputField = document.getElementById('input');
const resultDiv = document.getElementById('result');

const correctAnswer1 = "MQQEQQ";
const correctAnswer2 = "BIGBEN";  

button.addEventListener('click', () => {
    const userInput = inputField.value.trim().toUpperCase();
    
    if (testnumber === 0) {
        if (userInput === correctAnswer1) {
            testnumber = 1;
            console.log("correct");
            switchClasses(testcipher, 'testcipher', 'hidden');
            nextButton.style.display = 'block';
            textBubble.style.display = 'block';
            switchClasses(animatedKip, 'hoverinthecenterdisappear', 'hoverinthecentreappear');
        } else {
            if (guessnumber1 === 0) {
                resultDiv.textContent = "Falsch! Erinner dich, du musst das Wort mit dem Schlüssel ABC verschieben!";
                resultDiv.style.color = "red";
                guessnumber1 = 1;
            }
            else if (guessnumber1 === 1) {
                resultDiv.textContent = "Falsch! Erinner dich, du musst das Wort mit dem Schlüssel ABC verschieben, also Verschlüsseln!";
                resultDiv.style.color = "red";
                guessnumber1 = 2;
            }
            else if (guessnumber1 === 2) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Stell dir vor das ABC zweimal hintereinander unter LONDON steht.";
                resultDiv.style.color = "red";
                guessnumber1 = 3;
            }
            else if (guessnumber1 === 3) {
                resultDiv.textContent = "Falsch! Die ersten drei Zeichen LON werden um ABC verschoben also L um A O um B und N um C also L+A O+B und N+C.";
                resultDiv.style.color = "red";
                guessnumber1 = 4;
            }
            else if (guessnumber1 === 4) {
                resultDiv.textContent = "Falsch! L+A O+B und N+C und dann wieder D+A O+B und N+C.";
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
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Der Schlüssel könnte gerade mit der geredet haben.";
                resultDiv.style.color = "red";
                guessnumber2 = 2;
            }
            else if (guessnumber2 === 2) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Der Schlüssel besteht passt genau in das Wort. Vielleicht mit Wiederholung?";
                resultDiv.style.color = "red";
                guessnumber2 = 3;
            }
            else if (guessnumber2 === 3) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Der Schlüssel fliegt und ist dein Begleiter.";
                resultDiv.style.color = "red";
                guessnumber2 = 4;
            }
            else if (guessnumber2 === 4) {
                resultDiv.textContent = "Falsch! Versuche es noch einmal. Der Schlüssel fliegt und ist dein Begleiter.";
                resultDiv.style.color = "red";
            }
        }
    }
});