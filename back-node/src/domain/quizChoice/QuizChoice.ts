import { Column, Entity, JoinColumn, ManyToOne, PrimaryGeneratedColumn } from 'typeorm';
import { Quiz } from '../quiz/Quiz';

@Entity()
export class QuizChoice {
  @PrimaryGeneratedColumn()
  choiceId: number | undefined;

  @Column()
  choiceContent: string;

  @Column()
  isAnswer: boolean;

  @ManyToOne(() => Quiz, { eager: false })
  @JoinColumn({ name: 'quiz_id' })
  quiz: Quiz;

  constructor(choiceContent: string, isAnswer: boolean, quiz: Quiz, choiceId?: number) {
    this.choiceId = choiceId;
    this.choiceContent = choiceContent;
    this.isAnswer = isAnswer;
    this.quiz = quiz;
  }
}
