export const createProduct = (
  id: string | number,
  isNew: boolean = true,
  stock: number = 10,
) => ({id, stock, isNew});

const p1 = createProduct(5);
console.log(p1);
