import { faker } from '@faker-js/faker';
import { CreateProductDto, UpdateProductDto } from '../dtos/product.dto';
import { Product } from '../models/product.model';
import { Category } from '../models/category.model';
import { ProductService } from '../models/product-service.model';

export class ProductMemoryService implements ProductService {

  private products: Product[] = [];

  create(data: CreateProductDto): Product {
    const category: Category = {
      id: data.categoryId,
      name: faker.commerce.department(),
      image: faker.image.url()
    };

    const newProduct = {
      ...data,
      id: faker.number.int(),
      category
    };

    return this.add(newProduct);
  }

  update(id: Product['id'], newProduct: UpdateProductDto): Product {//Index typing. It's great when a property type is changed to other type and mantain consistency
    const index = this.products.findIndex(product => product.id === id);
    const prevProduct = this.products[index];

    if(index === -1) {
      throw new Error(`Product with id ${id} was not found.`);
    }

    this.products[index] = {
      ...prevProduct,
      ...newProduct
    };

    return this.products[index];
  }

  findOne(id: Product['id']): Product | undefined {
    return this.products.find(item => item.id === id);
  }

  findAll(): Product[] {
    return this.products;
  }

  private add(product: Product): Product {
    this.products.push(product);

    return product;
  }
}
