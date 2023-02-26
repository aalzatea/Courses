(() => {
  let userId: string | number;
  userId = 'Test';
  userId = 1234;

  function greeting(myText: string | number) {
    if(typeof myText === 'string') {
      console.log(`string ${myText.toLowerCase()}`);
    } else {
      console.log(`number ${myText.toFixed()}`);
    }
  }

  greeting('Hi');
  greeting(12345);
})();
