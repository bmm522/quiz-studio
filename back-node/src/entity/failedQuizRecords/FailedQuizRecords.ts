import { Model, model, Document, Schema } from 'mongoose';

export interface FailedQuizRecords extends Document {
  userKey: string;
  quizTitle: string;
  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];
}

const failedQuizRecordsSchema = new Schema({
  userKey: {
    type: String,
    required: true,
  },

  quizTitle: {
    type: String,
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
}, { collection: 'failedQuizRecords' });

failedQuizRecordsSchema.statics.createFailedQuizRecords = async function(data: {
  userKey: string;
  quizTitle: string;
  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];
}): Promise<FailedQuizRecords> {
  return new this(data);
};

const FailedQuizRecords: Model<FailedQuizRecords> = model<FailedQuizRecords>(
    'failedQuizRecords',
    failedQuizRecordsSchema,
);

export { FailedQuizRecords };