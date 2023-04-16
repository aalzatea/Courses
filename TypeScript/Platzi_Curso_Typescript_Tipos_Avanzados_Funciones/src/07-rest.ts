import { User, ROLES } from "./01-enum";

const currentUser: User = {
  username: 'user_test',
  role: ROLES.CUSTOMER
};

export const checkAdminRole = (): boolean => {
  if(currentUser.role === ROLES.ADMIN) {
    return true;
  }

  return false;
};

const response = checkAdminRole();
console.log('checkAdminRole', response);

export const checkRole = (role1: ROLES, role2: ROLES): boolean => {
  if(currentUser.role === role1) {
    return true;
  }

  if(currentUser.role === role2) {
    return true;
  }

  return false;
};

const response1 = checkRole(ROLES.ADMIN, ROLES.SELLER);
console.log('checkRole', response1);

export const checkRoleV2 = (...roles: ROLES[]): boolean => {
  if(roles.includes(currentUser.role)) {
    return true;
  }

  return false;
};

const response2 = checkRoleV2(ROLES.ADMIN, ROLES.SELLER);
console.log('checkRoleV2', response2);
