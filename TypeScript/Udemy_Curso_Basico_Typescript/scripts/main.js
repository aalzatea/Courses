let firstName = "Andres";
let people = ["Andres", "Diana", "Pedro", "Jimena"];
let people_div = document.querySelector('#people');
const peopleItems = people.map(person => {
    return `<li>${person}</li>`;
}).join("");
people_div.innerHTML = `<ul>${peopleItems}</ul>`;
console.log(`El nombre es: ${firstName}`);
