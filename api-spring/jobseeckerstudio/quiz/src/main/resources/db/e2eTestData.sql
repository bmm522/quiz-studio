insert into category(category_id, category_title, category_description, userKey)
values (3, 'testTitle', 'testDescription', 'testUserKey');
INSERT INTO quizSchema (quiz_title, category_id)
VALUES ('테스트 문제1', 3),
       ('테스트 문제2', 3),
       ('테스트 문제3', 3),
       ('테스트 문제4', 3);
INSERT INTO quiz_choice (choice_content, is_answer, quiz_id)
VALUES ('테스트 문제1의 예제 보기1', 1, 8)
     , ('테스트 문제1의 예제 보기2', 0, 8)
     , ('테스트 문제1의 예제 보기3', 0, 8)
     , ('테스트 문제1의 예제 보기4', 0, 8)
     , ('테스트 문제2의 예제 보기1', 0, 9)
     , ('테스트 문제2의 예제 보기2', 1, 9)
     , ('테스트 문제2의 예제 보기3', 0, 9)
     , ('테스트 문제2의 예제 보기4', 0, 9)
     , ('테스트 문제3의 예제 보기1', 0, 10)
     , ('테스트 문제3의 예제 보기2', 0, 10)
     , ('테스트 문제3의 예제 보기3', 1, 10)
     , ('테스트 문제3의 예제 보기4', 0, 10)
     , ('테스트 문제4의 예제 보기1', 0, 11)
     , ('테스트 문제4의 예제 보기2', 0, 11)
     , ('테스트 문제4의 예제 보기3', 0, 11)
     , ('테스트 문제4의 예제 보기4', 1, 11);
