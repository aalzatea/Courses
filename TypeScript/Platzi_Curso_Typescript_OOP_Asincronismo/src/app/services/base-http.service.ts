import axios from 'axios';

import { Category } from '../models/category.model';
import { Product } from '../models/product.model';
import { UpdateProductDto } from '../dtos/product.dto';

export class BaseHttpService<T> {
  constructor(protected url: string) {}

  async getAll(): Promise<T[]> {
    const { data } = await axios.get<T[]>(this.url);
    return data;
  }

  async update<ID, DTO>(id: ID, changes: DTO): Promise<T> {
    const { data } = await axios.put<T>(`${this.url}/${id}`, changes);
    return data;
  }

}

(async () => {
  const urlProducts = 'https://api.escuelajs.co/api/v1/products';
  const productService = new BaseHttpService<Product>(urlProducts);
  const products = await productService.getAll();
  console.log(products.length);

  const product = await productService.update<Product['id'], UpdateProductDto>(products[0].id, {
    title: 'New Title from Generic'
  });
  console.log(product);

  /*console.log('---'.repeat(10));
  const urlCategories = 'https://api.escuelajs.co/api/v1/categories';
  const categoryService = new BaseHttpService<Category>(urlCategories);
  const categories = await categoryService.getAll();
  console.log(categories);*/
})();
