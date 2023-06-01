insert into category(category_id, category_title, category_description, user_key)
values (10000, 'testTitle', 'testDescription', 'testUserKey');
INSERT INTO quiz (quiz_title, category_id)
VALUES ('테스트 문제1', 10000),
       ('테스트 문제2', 10000),
       ('테스트 문제3', 10000),
       ('테스트 문제4', 10000);
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
