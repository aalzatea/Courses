import { faker } from '@faker-js/faker';
import { addProduct, findProducts, getProducts, updateProduct } from './products/product.service';

for (let index = 0; index < 50; index++) {
  /*const category: Category = {
    id: faker.datatype.uuid(),
    createdAt: faker.date.recent(),
    name: faker.commerce.department()
  };

  addProduct({
    id: faker.datatype.uuid(),
    title: faker.commerce.productName(),
    description: faker.commerce.productDescription(),
    image: faker.image.imageUrl(),
    createdAt: faker.date.recent(),
    stock: faker.datatype.number({min: 10, max: 100}),
    size: faker.helpers.arrayElement(['S', 'M', 'L', 'XL']),
    color: faker.color.human(),
    category,
    price: parseFloat(faker.commerce.price(2, 250, 2)),
    isNew: faker.datatype.boolean(),
    tags: faker.helpers.arrayElements<string>()
  });*/

  addProduct({
    title: faker.commerce.productName(),
    description: faker.commerce.productDescription(),
    image: faker.image.imageUrl(),
    stock: faker.datatype.number({min: 10, max: 100}),
    size: faker.helpers.arrayElement(['S', 'M', 'L', 'XL']),
    color: faker.color.human(),
    categoryId: faker.datatype.uuid(),
    price: parseFloat(faker.commerce.price(2, 250, 2)),
    isNew: faker.datatype.boolean(),
    tags: faker.helpers.arrayElements<string>()
  });
}

console.log(getProducts());

const product = getProducts()[0];
updateProduct(product.id, {
  title: 'New title',
  stock: 80
});

findProducts({
  stock: 10,
  color: 'red',
  tags: ['a', 'b', 'c']
});
