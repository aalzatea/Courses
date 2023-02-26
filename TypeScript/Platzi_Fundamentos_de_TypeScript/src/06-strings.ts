(() => {
  let productTitle = 'My amazing product';
  productTitle = 'My amazing product changed';

  console.log('Product Title = ', productTitle);

  const productDescription: string = 'This is the product description';
  console.log('Product Description = ', productDescription);

  let productPrice = 100.0;
  let newProduct: boolean = false;

  const summary: string = `
    Title: ${productTitle}
    Description: ${productDescription}
    Price: ${productPrice}
    Is New Product: ${newProduct}
  `;

  console.log(summary);
})();
