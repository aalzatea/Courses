export const createProduct = (
  id: string | number,
  isNew?: boolean,
  stock?: number,
) => {
  return {
    id,
    stock: stock || 10,
    isNew: isNew || true
  };
};

const p1 = createProduct(1, true, 3);
console.log(p1);

const p2 = createProduct(2, false, 0);
console.log(p2);

const p3 = createProduct(3);
console.log(p3);

const createProduct2 = (
  id: string | number,
  isNew?: boolean,
  stock?: number,
) => {
  return {
    id,
    stock: stock ?? 10,
    isNew: isNew ?? true
  };
};

const p4 = createProduct2(4, false, 0);
console.log(p4);
