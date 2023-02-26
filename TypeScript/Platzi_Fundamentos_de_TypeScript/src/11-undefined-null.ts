(() => {
  let myNumber: number | null = null;
  myNumber = 1234;

  let myString: string | undefined = undefined;
  myString = 'Test';

  let myNull: null = null;
  let myUndefined: undefined = undefined;

  function hi(name: string | null) {
    let hello = 'Hello ';

    if(name) {
      hello += name;
    } else {
      hello += 'nobody';
    }

    //hello += name?.toLowerCase() ?? 'nobody';
    //hello += name?.toLowerCase() || 'nobody';
    console.log(hello);
  }

  hi('Andres');
  hi(null);
})();
