export type BaseModelId = string | number;

export interface BaseModel {
  readonly id: BaseModelId;
  readonly createdAt: Date;
  updatedAt?: Date;
}
