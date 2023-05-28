export class MyDate {

  constructor(
    private year: number = 1993,
    private month: number = 6,
    private day: number = 15
  ) {}

  public printFormat(): string {
    const day = this.addPadding(this.day);
    const month = this.addPadding(this.month);

    return `${day}/${month}/${this.year}`;
  }

  public add(amount: number, type: 'days' | 'months' | 'years') {
    switch(type) {
      case "days":
        this.day += amount;
        break;
      case "months":
        this.month += amount;
        break;
      case "years":
        this.year += amount;
        break;
    }
  }

  getDay() {
    return this.day;
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
console.log(myDate.getDay());

const myDate2 = new MyDate();
console.log('() =>', myDate2.printFormat());

const myDate3 = new MyDate(2020);
console.log('(2020) =>', myDate3.printFormat());

const myDate4 = new MyDate(2020, 12);
console.log('(2020, 12) =>', myDate4.printFormat());
