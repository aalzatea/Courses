export class MyDate {

  constructor(
    private year: number = 1993,
    private month: number = 6,
    private _day: number = 15
  ) {}

  public printFormat(): string {
    const day = this.addPadding(this._day);
    const month = this.addPadding(this.month);

    return `${day}/${month}/${this.year}`;
  }

  public add(amount: number, type: 'days' | 'months' | 'years') {
    switch(type) {
      case "days":
        this._day += amount;
        break;
      case "months":
        this.month += amount;
        break;
      case "years":
        this.year += amount;
        break;
    }
  }

  get day() {
    return this._day;
  }

  get isLeapYear(): boolean {
    if(this.year % 400 === 0) return true;
    if(this.year % 100 === 0) return false;

    return this.year % 4 === 0;
  }

  private addPadding(value: number) {
    if(value < 10) {
      return `0${value}`;
    }

    return `${value}`;
  }
}

const myDate = new MyDate(1993, 7, 9);
console.log(myDate.printFormat());
console.log(myDate.day);
console.log('1993', myDate.isLeapYear);

const myDat2 = new MyDate(2000, 7, 9);
console.log(myDat2.printFormat());
console.log(myDat2.day);
console.log('2000', myDat2.isLeapYear);
