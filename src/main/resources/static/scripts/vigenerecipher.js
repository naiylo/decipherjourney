// Controlling the modal

const openModalBtn = document.getElementById('open-modal-btn');
const tutorialModal = document.getElementById('tutorial-modal');
const closeModalBtn = document.getElementById('close-modal-btn');
const video = document.getElementById('video');

openModalBtn.addEventListener('click', () => {
    tutorialModal.style.display = 'block'; 
    video.play(); 
});

// Close the modal
closeModalBtn.addEventListener('click', () => {
  tutorialModal.style.display = 'none';
  video.pause(); 
  video.currentTime = 0; 
});

//--------------------------    Control of the modulo button    ----------------------------------------

const modulobutton = document.getElementById('modulo-button');

modulobutton.addEventListener('click', () => {
    let testnumber = parseInt(document.getElementById('testnumber').value);
    console.log(testnumber);

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