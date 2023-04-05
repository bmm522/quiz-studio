import {Column, Entity, PrimaryGeneratedColumn} from "typeorm";

@Entity()
export class Category {
    @PrimaryGeneratedColumn()
    categoryId: number | undefined;

    @Column()
    categoryName: string;

    constructor(categoryName: string, categoryId?: number ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}