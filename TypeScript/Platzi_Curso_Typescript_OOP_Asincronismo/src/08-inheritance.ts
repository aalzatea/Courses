export class Animal {

  constructor(public name: string) {
  }

  move() {
    console.log('Moving along!');
  }

  greetings() {
    return `Hello, I am ${this.name}.`;
  }
}

export class Dog extends Animal {

  constructor(
    name: string,
    public owner: string
  ) {
    super(name);
  }

  woof(times: number): void {
    for (let index = 0; index < times; index++) {
      console.log('Woof!');
    }
  }
}

const animal = new Animal('Fifi');
animal.move();
console.log(animal.greetings());

const dog = new Dog('Cheis', 'Andres');
dog.move();
console.log(dog.greetings());
dog.woof(5);
console.log(dog.owner);
