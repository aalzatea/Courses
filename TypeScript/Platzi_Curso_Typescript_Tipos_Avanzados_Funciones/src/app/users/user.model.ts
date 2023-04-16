import { BaseModel } from "../base.model";

export enum Roles {
  ADMIN = 'admin',
  SELLER = 'seller',
  CUSTOMER = 'customer'
}

export interface User extends BaseModel {
  username: string;
  role: Roles;
}
