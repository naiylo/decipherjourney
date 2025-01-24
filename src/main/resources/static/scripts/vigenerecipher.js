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