export class MyService {

  private static instance: MyService | null = null;

  private constructor(private name: string) {
  }

  static getInstance(name: string): MyService {
    if(MyService.instance === null) {
      MyService.instance = new MyService(name);
    }

    return MyService.instance;
  }

  getName() {
    return this.name;
  }
}

const myService1 = MyService.getInstance('Service 1');
console.log(myService1);

const myService2 = MyService.getInstance('Service 2');
console.log(myService2);

const myService3 = MyService.getInstance('Service 3');
console.log(myService3);

console.log(myService1 === myService2);
console.log(myService2 === myService3);
console.log(myService1 === myService3);
