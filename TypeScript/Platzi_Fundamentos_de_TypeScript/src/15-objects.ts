(() => {
  type Sizes = 'S' | 'M' | 'L' | 'XL';
  type Product = {
    title: string,
    createdAt: Date,
    stock: number,
    size?: Sizes
  };

  const products: Product[] = [];

  const addProduct = (product: Product) => {
    products.push(product);
  };

  addProduct({
    title: 'Product Title 1',
    createdAt: new Date(),
    stock: 15,
    size: "M"
  });

  addProduct({
    title: 'Product Title 2',
    createdAt: new Date(),
    stock: 10,
  });

  addProduct({
    title: 'Product Title 3',
    createdAt: new Date(2022, 1, 28),
    stock: 20,
    size: "S"
  });

  console.log('Products: ', products);

})();
