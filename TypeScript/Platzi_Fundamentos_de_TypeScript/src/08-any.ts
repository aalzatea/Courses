(() => {
  let myDynamicVar: any;
  myDynamicVar = null;
  myDynamicVar = {};
  myDynamicVar = 100;
  myDynamicVar = 'Test'

  const result1 = (myDynamicVar as string).toLowerCase();
  console.log(result1);

  myDynamicVar = 1234;
  const result2 = (<number> myDynamicVar).toFixed();
  console.log(result2);
})();
