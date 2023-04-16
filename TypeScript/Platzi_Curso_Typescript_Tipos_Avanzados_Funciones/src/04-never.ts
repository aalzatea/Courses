const withoutEnd = (): never => {
    while(true) {
        console.log('Never stop of learning');
    }
};

const fail = (message: string): never => {
  throw new Error(message);
};

const example = (input: unknown): string => {
  if(typeof input === 'string') {
    return 'this is a string';
  } else if(Array.isArray(input)) {
    return 'this is an array';
  }

  return fail('anything match with the input');
};

console.log(example('Hello'));
console.log(example([1,1,1,1]));
console.log(example(10));
console.log(example('Hello again!'));
