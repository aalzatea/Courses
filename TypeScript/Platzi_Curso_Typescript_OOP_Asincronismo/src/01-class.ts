const date = new Date();
console.log('Date 1: ', date.getHours(), date.getTime(), date.toISOString());

const date2 = new Date(1993, 1, 12);
console.log('Date 2: ', date2.getHours(), date2.getTime(), date2.toISOString());

class MyDate {
  year: number;
  month: number;
  day: number;

  constructor(year: number, month: number, day: number) {
    this.year = year;
    this.month = month;
    this.day = day;
  }
}

const myDate = new MyDate(2021, 3, 13);
console.log(myDate);
