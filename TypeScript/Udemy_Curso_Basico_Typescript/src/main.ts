let firstName:string = "Andres";
let people:Array<string> = ["Andres", "Diana", "Pedro", "Jimena"];

let people_div:HTMLElement | null = document.querySelector('#people');
const peopleItems = people.map(person => {
    return `<li>${person}</li>`;
}).join("");

people_div.innerHTML = `<ul>${peopleItems}</ul>`;

console.log(`El nombre es: ${firstName}`);