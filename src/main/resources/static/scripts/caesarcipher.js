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

// Shift the disc according to shift value provided by user

// Apply the initial rotation
document.addEventListener('DOMContentLoaded', () => {
  const spinDegree = parseFloat(document.getElementById('spindegree').value) || 0;
  const innerDisk = document.getElementById('inner-disk');
  console.log(spinDegree);

  // Apply the initial rotation
  const currentAngle = spinDegree;
  innerDisk.style.transform = `rotate(${currentAngle}deg)`;
});

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
