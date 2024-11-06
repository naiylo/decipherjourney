document.addEventListener("DOMContentLoaded", function() {
    const welcomeTextElement = document.getElementById("welcome-text");
    const text = welcomeTextElement.textContent;

    // Define a set of colors to cycle through
    const colors = ['#ff0000', '#00ff00', '#0000ff', '#ffeb3b', '#ff00ff', '#00ffff'];

    // Split text into words instead of characters
    const words = text.split(' ');

    // Wrap each word in a <span> with color cycling
    const coloredWords = words.map((word, wordIndex) => {
        const coloredChars = word.split('').map((char, charIndex) => {
            const color = colors[(wordIndex * word.length + charIndex) % colors.length]; // Cycle through colors
            return `<span style="color: ${color}; animation-delay: ${0.1 * (wordIndex * word.length + charIndex)}s;">${char}</span>`;
        }).join('');
        return `${coloredChars}`; // Don't add a space between letters of the same word
    }).join(' '); // Add a space between words

    // Set the innerHTML of the welcomeTextElement
    welcomeTextElement.innerHTML = coloredWords;
});