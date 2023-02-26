(() => {
  type UserID = string | number;
  let userId: UserID;

  //Literal types
  type Sizes = 'S' | 'M' | 'L' | 'XL';
  let shirtSize: Sizes;
  shirtSize = 'S';
  shirtSize = 'M';
  shirtSize = 'L';
  shirtSize = 'XL';

  function greeting(userId: UserID, size: Sizes) {
    if(typeof userId === 'string') {
      console.log(`string ${userId.toLowerCase()}`);
    }
  }

  greeting(1122, 'M');
  greeting('1111', 'S');
})();
