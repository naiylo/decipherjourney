// Function to change classes
export function switchClasses(element, oldClass, newClass) {
    if (element.classList.contains(oldClass)) {
        element.classList.remove(oldClass);
    }
    element.classList.add(newClass);
}