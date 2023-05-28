import { ProductHttpService } from "./services/product-http.service";

(async () => {
  const productService = new ProductHttpService();

  /*try {
    console.log('---'.repeat(10));
    console.log('Find All');
    const products = await productService.findAll();
    console.log(products.length);

    console.log('---'.repeat(10));
    console.log('Update');
    const productId = products[0].id;
    await productService.update(productId, {
      price: 10000,
      title: 'New Title',
      description: 'New Description'
    });

    console.log('---'.repeat(10));
    console.log('Find One');
    const product = await productService.findOne(productId);
    console.log(product);
  } catch(error) {
    console.log(error);
  }*/

  console.log('---'.repeat(10));
  const product = await productService.findAll()
    .then(data => {
      const productId = data[0].id;

      return productService.update(productId, {
        price: 500,
        title: 'New Title 1',
        description: 'New Description 1'
      });
    })
    .then(data => productService.findOne(data.id))
    .catch(error => console.error('---------> Chaining Promises Error:', error));

  console.log(product);
})();
