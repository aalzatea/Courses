(() => {
  let isEnable = true;

  let isNew: boolean = false;
  console.log('isNew = ', isNew);

  isNew = true;
  console.log('isNew = ', isNew);

  const random: number = Math.round(Math.random() * 10);
  console.log('Random = ', random);

  isNew = random >= 5;
  console.log('isNew = ', isNew);
})();
