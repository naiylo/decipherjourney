// Function to change classes
export function switchClasses(element, oldClass, newClass) {
    if (element.classList.contains(oldClass)) {
        element.classList.remove(oldClass);
    }
    element.classList.add(newClass);
}

// Function to wait for a certain amount of time
export function waitForNSeconds(n) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve();
        }, n * 1000); 
    });
}

// Function to simulate typing effect
export function typeDialog(dialog, callback) {
    let charIndex = 0;
    dialogList.innerHTML = "";  

    const typeInterval = setInterval(() => {
        dialogList.innerHTML += dialog.charAt(charIndex);  
        charIndex++;

        if (charIndex === dialog.length) {
            clearInterval(typeInterval); 
            callback(); 
        }
    }, 15); 
}