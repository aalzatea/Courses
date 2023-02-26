import _ from 'lodash';

const data = [
  {
    username: 'andres',
    role: 'admin'
  },
  {
    username: 'kike',
    role: 'seller'
  },
  {
    username: 'tulia',
    role: 'seller'
  },
  {
    username: 'valentina',
    role: 'customer'
  }
];

const response = _.groupBy(data, item => item.role);
console.log(response);
