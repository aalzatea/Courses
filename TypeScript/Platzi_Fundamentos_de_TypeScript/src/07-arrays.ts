(() => {
  let prices: number[] = [1,2,3,4,5,6,7];
  prices.push(8);
  console.log('Prices = ', prices);

  let products: (string | boolean | number)[] = [1, 2, 5, 8, 0, true, 'Object'];
  products.push('Object added');
  console.log('Objects = ', products);

  prices = prices.map(it => it * 2);
  console.log('Prices = ', prices);
})();
