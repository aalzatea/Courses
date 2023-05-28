export abstract class Animal {

  constructor(protected name: string) {
  }

  move() {
    console.log('Moving along!');
  }

  greetings() {
    return `Hello, I am ${this.name}.`;
  }

  protected doSomething() {
    console.log('Do something else!');
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
      console.log(`Woof! ${this.name}`);
    }

    this.doSomething();
  }

  move() {
    console.log('Moving as a Dog!');
    super.move();
  }
}

const dog = new Dog('Cheis', 'Andres');
//dog.name = 'another name';
dog.woof(1);
dog.move();
//dog.doSomething();
