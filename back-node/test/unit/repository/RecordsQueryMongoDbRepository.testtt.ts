// import { CategoryEnum } from "../../../src/global/enum/CategoryEnum";
// import { RepositoryGetRecordRequest } from "../../../src/repository/records/dto/RepositoryGetRecordRequest";
// import { Level } from "../../../src/global/enum/Level";
// import { RecordsQueryMongoDbRepository } from "../../../src/repository/records/RecordsQueryMongoDbRepository";
// import { ServiceGetQuizResponse } from "../../../src/service/quiz/dto/ServiceGetQuizResponse";
// import { RepositoryGetRecordResponse } from "../../../src/repository/records/dto/RepositoryGetRecordResponse";
//
// describe('RecordsQueryMongoDbRepository Test', () => {
//     let recordsQueryRepository: RecordsQueryMongoDbRepository;
//
//     beforeEach(() => {
//         recordsQueryRepository = new RecordsQueryMongoDbRepository();
//     });
//
//     it('toGetResponse Test', async () => {
//         const repositoryGetRecordRequest = RepositoryGetRecordRequest.create(
//             'test',
//             1,
//             false,
//             CategoryEnum.JAVA,
//             Level.EASY,
//         );
//
//         const result = await recordsQueryRepository.findByUserKey(repositoryGetRecordRequest);
//
//         console.log(result);
//
//         expect(result).toBeInstanceOf(RepositoryGetRecordResponse);
//     }, 10000);
// });