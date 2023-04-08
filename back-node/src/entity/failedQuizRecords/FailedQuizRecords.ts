import * as mongoose from "mongoose";

const FailedQuizRecords = new mongoose.Schema({

    quizTitle: {
        type: String,
        required: true
    },

    quizChoiceContent: {
        type: String,
        required: true
    },

    quizChoiceIsAnswer: {
        type: Boolean,
        required: true
    }

});