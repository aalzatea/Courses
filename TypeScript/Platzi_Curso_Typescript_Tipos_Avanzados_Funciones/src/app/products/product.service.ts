import { faker } from '@faker-js/faker';
import { BaseModelId } from '../base.model';
import { CreateProductDto, FindProductDto, UpdateProductDto } from './product.dto';
import { Product } from './product.model';
import { Category } from '../categories/category.model';

const products: Product[] = [];

export const getProducts = () => products;

export const getProduct = (id: BaseModelId) => products.filter(product => product.id === id);

export const findProducts = (dto: FindProductDto): Product[] => {
  //code
  dto.tags = [];
  dto.tags?.push();
  dto.tags?.pop();

  return products;
}

export const addProduct = (data: CreateProductDto): Product => {
  const category: Category = {
    id: data.categoryId,
    createdAt: faker.date.recent(),
    name: faker.commerce.department()
  };

  const newProduct = {
    ...data,
    id: faker.datatype.uuid(),
    createdAt: faker.date.recent(),
    category
  };

  products.push(newProduct);

  return newProduct;
}

//export const updateProduct = (id: BaseModelId, newProduct: UpdateProductDto): Product => {
export const updateProduct = (id: Product['id'], newProduct: UpdateProductDto): Product => {//Index typing. It's great when a property type is changed to other type and mantain consistency
  const index = products.findIndex(product => product.id === id);
  const prevProduct = products[index];

  if(index === -1) {
    throw new Error(`Product with id ${id} was not found.`);
  }

  products[index] = {
    ...prevProduct,
    ...newProduct
  };

  return products[index];
}

export const deleteProduct = (id: BaseModelId): void => {
  const index = products.findIndex(product => product.id === id);

  if(index !== -1) {
    products.splice(index, 1);
  }
};
