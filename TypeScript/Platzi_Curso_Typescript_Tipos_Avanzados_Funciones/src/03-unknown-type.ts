let anyVar: any;
anyVar = true;
anyVar = undefined;
anyVar = null;
anyVar = [];
anyVar = {};

let isNew: boolean = anyVar;

anyVar.doSomething();
anyVar.toUpperCase();

let unknownVar: unknown;
unknownVar = true;
unknownVar = undefined;
unknownVar = null;
unknownVar = [];
unknownVar = {};

if(typeof unknownVar === 'string') {
  unknownVar.toUpperCase();
}

const parse = (value: string): unknown => JSON.parse(value);
