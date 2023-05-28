import axios from 'axios';

import { CreateProductDto, UpdateProductDto } from '../dtos/product.dto';
import { Product } from '../models/product.model';
import { Category } from '../models/category.model';
import { ProductService } from '../models/product-service.model';

export class ProductHttpService implements ProductService {

  private url = 'https://api.escuelajs.co/api/v1/products';

  async create(product: CreateProductDto): Promise<Product> {
    const { data } = await axios.post(this.url, product);
    return data;
  }

  async update(id: Product['id'], changes: UpdateProductDto): Promise<Product> {//Index typing. It's great when a property type is changed to other type and mantain consistency
    const { data } = await axios.put(`${this.url}/${id}`, changes);
    return data;
  }

  async findOne(id: Product['id']): Promise<Product> {
    const { data } = await axios.get(`${this.url}/${id}`);
    return data;
  }

  async findAll(): Promise<Product[]> {
    const { data } = await axios.get<Product[]>(this.url);
    return data;
  }
}
