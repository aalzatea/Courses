(() => {
  type Sizes = 'S' | 'M' | 'L' | 'XL';

  const login = (data: {email: string, password: string}) => {
    console.log(`Email: ${data.email} - Password: ${data.password}`);
  };

  login({
    email: 'test@gmail.com',
    password: '121212121212'
  });

  const products: any[] = [

  ];

  const addProduct = (data: {
    title: string,
    createdAt: Date,
    stock: number,
    size?: Sizes
  }) => {
    products.push(data);
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

  console.log('Products: ', products);
})();
