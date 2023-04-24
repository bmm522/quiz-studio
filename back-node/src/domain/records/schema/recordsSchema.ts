import { Model, model, Document, Schema } from 'mongoose';
export interface RecordsSchema extends Document {
  userKey: string;
  quizTitle: string;

  quizIsAnswer: boolean;

  category: string;

  level: string;
  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];
}

const RecordsSchema = new Schema(
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

      category: {
        type:String,
          required: true,
      },

      level: {
        type:String,
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

RecordsSchema.statics.createFailedQuizRecords = async function(data: {
  userKey: string;
  quizTitle: string;
  quizIsAnswer: boolean;
  category: string;
  level: string;
  quizChoiceContent: string[];
  quizChoiceIsAnswer: boolean[];
}): Promise<RecordsSchema> {
  return new this(data);
};

const RecordsModel: Model<RecordsSchema> = model<RecordsSchema>(
  'Records',
  RecordsSchema,
);

export { RecordsModel };
