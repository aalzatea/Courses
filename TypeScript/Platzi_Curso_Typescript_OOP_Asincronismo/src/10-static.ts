console.log('Math.PI', Math.PI);
console.log('Math.max', Math.max(1, 3, 4, 56, 7, 89, 11, 94, 9, 11));

class MyMath {
  static readonly PI = 3.14;

  static max(...numbers: number[]) {
    //return numbers.reduce((maxValue, value) => value >= maxValue ? value : maxValue, 0);
    return numbers.reduce((maxValue, value) => value >= maxValue ? value : maxValue);
  }
}

//const math = new MyMath();
//math.PI;

console.log('MyMath.PI', MyMath.PI);
console.log('MyMath.max', MyMath.max(1, 3, 4, 56, 7, 89, 11, 94, 9, 11));

const numbers = [1, 3, 4, 56, 7, 89, 11, 94, 9, 11];
console.log('MyMath.max(#Array)', MyMath.max(...numbers));
