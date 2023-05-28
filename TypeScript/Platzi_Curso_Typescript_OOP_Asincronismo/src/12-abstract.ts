import { Animal, Dog } from './09-protected';

const animal = new Animal('Elite');
animal.greetings();

const dog = new Dog('Chase', 'Andres');
dog.greetings();
dog.woof(2);
