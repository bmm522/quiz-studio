import {Category} from "../category/Category";
import {Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn} from "typeorm";
import {QuizChoice} from "../quizChoice/QuizChoice";

@Entity()
export class Quiz {

    @PrimaryGeneratedColumn()
    quizId: number | undefined;

    @Column()
    quizTitle: string;

    @Column()
    difficulty: string;

    @ManyToOne(() => Category, { eager: false })
    @JoinColumn({ name: "category_id" })
    category: Category;

    @OneToMany(() => QuizChoice, quizChoice => quizChoice.quiz, { eager: false })
    quizChoices: QuizChoice[] | undefined;

    constructor(
        quizTitle: string,
        difficulty: string,
        category: Category,
        quizId?: number,
        quizChoices?: QuizChoice[]
    ) {
        this.quizId = quizId;
        this.quizTitle = quizTitle;
        this.difficulty = difficulty;
        this.category = category;
        this.quizChoices = quizChoices;
    }
}