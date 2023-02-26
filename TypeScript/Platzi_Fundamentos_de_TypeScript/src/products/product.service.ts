import { Product } from './product.model';

const products: Product[] = [];

export const addProduct = (product: Product) => {
  products.push(product);
};

export const getProducts = (): Product[] => products;

export function calcStock(): number {
  return products.map(product => product.stock)
    .reduce((accum, value) => accum += value);
}
