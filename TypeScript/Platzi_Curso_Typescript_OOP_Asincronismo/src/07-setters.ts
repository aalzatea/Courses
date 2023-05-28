export class MyDate {

  constructor(
    private year: number = 1993,
    private _month: number = 6,
    private _day: number = 15
  ) {}

  public printFormat(): string {
    const day = this.addPadding(this._day);
    const month = this.addPadding(this._month);

    return `${day}/${month}/${this.year}`;
  }

  public add(amount: number, type: 'days' | 'months' | 'years') {
    switch(type) {
      case "days":
        this._day += amount;
        break;
      case "months":
        this._month += amount;
        break;
      case "years":
        this.year += amount;
        break;
    }
  }

  get day() {
    return this._day;
  }

  get month() {
    return this._month;
  }

  get isLeapYear(): boolean {
    if(this.year % 400 === 0) return true;
    if(this.year % 100 === 0) return false;

    return this.year % 4 === 0;
  }

  set month(month: number) {
    if(month >= 1 && month <= 12) {
      this._month = month;
    } else {
      throw new Error('month out of range.');
    }
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
myDate.month = 11;
console.log(myDate.month);
myDate.month = 13;
