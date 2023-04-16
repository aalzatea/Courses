// Andres => [A, n, d, r, e, s] => string => string[]
// [A, n, d, r, e, s] => Andres => string[] => string

export function parseStr(input: string): Array<string>;
export function parseStr(input: string[]): string;
export function parseStr(input: number): boolean;

export function parseStr(input: unknown): unknown {
  if(Array.isArray(input)) {
    return input.join('');
  } else if(typeof input === 'string') {
    return input.split('');
  } else if(typeof input === 'number') {
    return true;
  }
}

const responseArray = parseStr('Andres');
responseArray.reverse();
console.log('Array:', 'Andres =>', responseArray);

const responseString = parseStr(['A', 'n', 'd', 'r', 'e', 's']).toLowerCase();
console.log('String:', "['A', 'n', 'd', 'r', 'e', 's'] =>", responseString);

const responseBoolean = parseStr(38);
console.log('Boolean:', '38 =>', responseBoolean);
