(() => {
  type Sizes = 'S' | 'M' | 'L' | 'XL';

  function createProductToJson(
    title: string,
    createdAt: Date,
    stock: number,
    size: Sizes) {
      return {
        title,
        createdAt,
        stock,
        size
      };
  }

  const product1 = createProductToJson('Product Title', new Date(), 12, 'XL');
  console.log(product1);
  console.log(product1.title);
  console.log(product1.createdAt);
  console.log(product1.stock);
  console.log(product1.size);

  const createProductToJson2 = (
    title: string,
    createdAt: Date,
    stock: number,
    size?: Sizes
  ) => (
    {
      title,
      createdAt,
      stock,
      size}
  );
  const product2 = createProductToJson2('Product Title 1', new Date(), 15);
  console.log(product2);
})();
