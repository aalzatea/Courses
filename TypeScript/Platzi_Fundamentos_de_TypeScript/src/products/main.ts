import { addProduct, calcStock, getProducts } from './product.service';

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

const stockTotal = calcStock();

console.log('Products: ', getProducts());
console.log('Stock Total: ', stockTotal);
