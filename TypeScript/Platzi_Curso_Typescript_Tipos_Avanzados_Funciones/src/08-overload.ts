// Andres => [A, n, d, r, e, s] => string => string[]
// [A, n, d, r, e, s] => Andres => string[] => string

function parseStr(input: string | string[]): (string | string[]) {
  if(Array.isArray(input)) {
    return input.join('');
  }

  return input.split('');
}

const responseArray = parseStr('Andres') as Array<string>;
console.log('Array:', 'Andres =>', responseArray);

const responseString = parseStr(['A', 'n', 'd', 'r', 'e', 's']) as string;
console.log('String:', "['A', 'n', 'd', 'r', 'e', 's'] =>", responseString);
