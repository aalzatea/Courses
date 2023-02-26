(() => {
  const calcTotal = (prices: number[]): number => {
    let total = 0;
    prices.forEach(it => total += it);
    //return prices.reduce((result, value) => result += value);
    return total;
  };

  const printCalcTotal = (prices: number[]): void => {
    const result = calcTotal(prices);
    console.log(result);
  };

  const prices: Array<number> = [1, 4, 56, 7, 98];
  console.log(calcTotal(prices));
  printCalcTotal(prices);
})();
