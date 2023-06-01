import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Category {
  @PrimaryGeneratedColumn()
  private readonly categoryId: number | undefined;

  @Column()
  private readonly categoryName: string;

  constructor(categoryName: string, categoryId?: number) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;
  }
}
