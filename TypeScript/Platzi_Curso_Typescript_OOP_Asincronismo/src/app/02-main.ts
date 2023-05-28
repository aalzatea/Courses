import { ProductMemoryService } from "./services/product-memory.service";

const productService = new ProductMemoryService();

productService.create({
  title: 'Product 1',
  price: 100,
  description: 'Description of the product 1',
  categoryId: 12,
  images: []
});

const products = productService.findAll();
const productId = products[0].id

productService.update(productId, {
  title: 'Change name',
});

const product = productService.findOne(productId);
console.log(product);
