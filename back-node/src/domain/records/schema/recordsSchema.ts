import { Model, model, Document, Schema } from 'mongoose';
export interface RecordsSchema extends Document {
  userKey: string;
  quizTitle: string;

  quizIsAnswer: boolean;
  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];
}

const failedQuizRecordsSchema = new Schema(
  {
    userKey: {
      type: String,
      required: true,
    },

    quizTitle: {
      type: String,
      required: true,
    },
    quizIsAnswer: {
      type: Boolean,
      required: true,
    },

    quizChoiceContent: {
      type: [String],
      required: true,
    },

    quizChoiceIsAnswer: {
      type: [Boolean],
      required: true,
    },

    createdAt: {
      type: Date,
      default: Date.now,
    },
  },
  { collection: 'Records' },
);

failedQuizRecordsSchema.statics.createFailedQuizRecords = async function(data: {
  userKey: string;
  quizTitle: string;
  quizIsAnswer: boolean;
  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];
}): Promise<RecordsSchema> {
  return new this(data);
};

const FailedQuizRecordsModel: Model<RecordsSchema> = model<RecordsSchema>(
  'Records',
  failedQuizRecordsSchema,
);

export { FailedQuizRecordsModel };
