type Sizes = 'S' | 'M' | 'L' | 'XL';
type ProductId = string | number;
/*type Product = {
  id: number | string;
  title: string;
  createdAt: Date;
  stock: number;
  size?: Sizes;
};*/

interface Product {
  id: ProductId;
  title: string;
  createdAt: Date;
  stock: number;
  size?: Sizes;
};

const products: Product[] = [];

products.push({
  id: '1',
  title: 'Product 1',
  createdAt: new Date(),
  stock: 30,
  size: 'M'
});

const addProduct = (data: Product) => products.push(data);
