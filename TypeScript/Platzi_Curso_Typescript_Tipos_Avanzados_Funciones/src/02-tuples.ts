(() => {
  const prices: (number | string)[] = [1, 2, 3, 56, 7, 92, 1, 'a'];
  prices.push(12);
  prices.push('12');

  //Tuple
  const user: [string, number, boolean] = ['username', 12, true];

  //Destructuring Tuple
  const [username, age] = user;

  console.log(username);
  console.log(age);
})();
