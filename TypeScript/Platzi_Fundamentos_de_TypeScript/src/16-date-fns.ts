import { subDays, format } from 'date-fns';

const date = new Date(1998, 1, 28); //0 = Enero, 1 = Febrero
const result = subDays(date, 10);
const dateFormated: string = format(result, 'yyyy-MM-dd');

console.log(`The result is ${dateFormated}`);
