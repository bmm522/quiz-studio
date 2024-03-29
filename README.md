# QuizStudio


## Description

---
- 🔗링크 : https://www.quizstudio.site
- 🔊프로젝트 소개
  - 개인적인 학습을 위해 만든 서비스 입니다.
  - gpt한테 문제를 뽑아서 풀 수 있는 퀴즈 사이트
  - 프론트(Vanilla js) + 4개의 모듈(REST API)기반으로 구성된 프로젝트
  - 서비스의 A to Z 를 경험하고 싶어, 프론트 부터 백, CI/CD 모든 것을 구축했습니다.
- 🏗️개발 기간 : 2023.03 ~

- 🛠️사용기술
  - Java11, JavaScript, Typescript
  - Spring Boot, Spring Security, Spring Data JPA, Spring Batch, querydsl, Node 
  - MySQL, Redis, MongoDB
  - Junit, JEST, supertest
  - AWS EC2, AWS RDS, AWS Certificate Manager, AWS Route 53, AWS ELB, AWS CloudFront, AWS S3, Docker
- 💻구동화면

  
  <img width="800" alt="image" src="https://github.com/bmm522/quiz-studio/assets/102157839/b5984b61-782b-4816-a616-decade978e08">


---

<details><summary>📖 Version 업데이트 내역 보기</summary>



### _v1.3.1 Release [latest] [23.07.02]_
  - 서비스 안정화 작업
  - 약간의 코드 구조 수정 

---

### _v1.3.0 Release [23.06.25]_
  - Spring, Network, Interview 총 3가지의 카테고리가 추가 https://github.com/bmm522/quiz-studio/issues/43
  - batch-module 구조 개선 작업 https://github.com/bmm522/quiz-studio/issues/40 https://github.com/bmm522/quiz-studio/issues/41 https://github.com/bmm522/quiz-studio/issues/42

---

### _v1.2.0 Release  [23.06.18]_
  - git repository로 가는 메뉴 추가

---


### _v1.1.1 Release [23.06.14]_
  - 문제창에서 뒤로가 눌렀을 때 다시 문제풀기 클릭 안되는 에러 수정 [#34](https://github.com/bmm522/quiz-studio/issues/34)
  - 로그아웃 제대로 안되던 에러 수정 [#24](https://github.com/bmm522/quiz-studio/issues/24)

---

### _v1.1.0 Release [23.06.13]_
  - 홈페이지 접속하면 로그인 안해도 게스트로 문제 풀 수 있도록 수정 [#25](https://github.com/bmm522/quiz-studio/issues/25)
  - 로그아웃 기능 추가 [#24](https://github.com/bmm522/quiz-studio/issues/24)

---
  
### _v1.0.4 Release [23.06.09]_
  - 퀴즈 데이터 안불러와지는 오류 수정 [#17](https://github.com/bmm522/quiz-studio/issues/17)

---
  
### _v1.0.3 Release [23.06.08]_
  - 문제 기록 제대로 안불러와지는 에러 수정 [#17](https://github.com/bmm522/quiz-studio/issues/17)

--- 
  
### _v1.0.2 Release [Pre-release] [23.06.07]_
  - 문제 기록 제대로 안불러와지는 에러 수정 [#10](https://github.com/bmm522/quiz-studio/issues/10)
  - quiz-batch module 재배포 시에 레디스 데이터 사라지는 에러 수정 [#10](https://github.com/bmm522/quiz-studio/issues/10)
  - 커스텀 문제 목록으로 넘어갈 시에 카테고리 아이디 null로 넘어가는 에러 수정 [#10](https://github.com/bmm522/quiz-studio/issues/10)

--- 
  
### _v1.0.1 Release [Pre-release] [23.06.07]_
  - css 경로 설정 잘못되어 있던 오류 해결 [#6](https://github.com/bmm522/quiz-studio/issues/6)
  - api 호출 제대로 안되던 오류 해결 [#6](https://github.com/bmm522/quiz-studio/issues/6)

--- 
  
### _v1.0.0 Release [Pre-release] [23.06.06]_
  - 첫 릴리즈
</details>

---

## Architecture

---

<img width="553" alt="image" src="https://github.com/bmm522/quiz-studio/assets/102157839/4b49a874-0270-4ffe-959c-1a9d29b75e81">


---

## 프로젝트 구조

- 목차

  - [OPENAI API 와 batch scheduler](#openai-api-와-batch-scheduler)
  - [AWS ELB 와 nginx](#aws-elb-와-nginx)
  - [한정적인 리소스 해결과 node module 도입 이유](#한정적인-리소스-해결과-node-module-도입-이유)
  - [개인정보 문제와 SSO(Single Sign-On)](#개인정보-문제와-ssosingle-sign-on)
  - [Test](#test)
  - [CI/CD](#cicd)
  - [Mapper](#mapper)
  - [CQRS 패턴](#cqrs-패턴)
  - [Facade 패턴](#facade-패턴)
  - [Validation 체크](#validation-체크)

---

## OPENAI API 와 batch scheduler

- Spring batch 도입 이유
  - OPENAI API 요청에서부터 AWS RDS에 저장작업, REDIS의 저장작업을 일괄적으로 처리해야 할 필요를 느껴 Spring Batch를 도입하였습니다.  

  - 이렇게 함으로써 API 요청에 따른 응답 속도 변동(30초에서 3분까지 다양)에 영향을 받지 않고 일관된 서비스를 제공할 수 있습니다. 또한, 한국 시간 기준으로 사용자들이 적은 새벽 시간대에 API 요청 작업을 수행하여 서비스의 안정성과 효율성을 높였습니다.  


  - 또한 1000토큰당 0.001$ 비용이 청구되는 만큼, 새로운 요청때마다 api 요청시에 과금의 위험에서도 벗어났습니다.  




<img src="https://github.com/bmm522/quiz-studio/assets/102157839/e7e451bf-306b-4542-a14a-a2d9a209d2b8" width="50%" height="40%"></img>
<img src="https://github.com/bmm522/quiz-studio/assets/102157839/6d039d96-8fbf-4e13-86e7-03e866b4875f" width="50%" height="40%"></img>

> 시간대가 다른 작업에서 데이터를 공유하는 문제에 대해 고민하였습니다. 
>
> 이를 해결하기 위해 내부적인 Queue를 활용하여 전역적으로 데이터를 공유할 수 있었습니다. 이렇게 함으로써 서로 다른 시간대에 작업하는 다른 요소들 사이에서 데이터를 원활하게 전달하고 공유할 수 있게 되었습니다.  



> <img src="https://github.com/bmm522/quiz-studio/assets/102157839/d1c77b68-e861-4c77-bb0c-84fc28d3d66b" width="70%" height="65%"></img>

---

## AWS ELB 와 nignx

### AWS ELB만 두지 않고 nignx까지 백서버에 구축한 이유는 다음과 같습니다.

   - 웹서버를 reverse proxy 구조로 설계하여, 앞단에 Nginx를 가짜 서버로 두고 뒷단의 웹서버를 보호  
   
   - AWS ELB에 추가적인 설정에 대한 과금 우려  
   
   
 해당 라우팅의 flow는 다음과 같습니다.

<img src="https://github.com/bmm522/quiz-studio/assets/102157839/90b11ac6-28a2-431d-ac58-53bd9892e09b" width="70%" height="65%"></img>

---

## 한정적인 리소스 해결과 node module 도입 이유

- 한정적인 리소스 해결

  - 학습용으로 제작한 프로젝트 이므로, 좋은 성능의 서버가 아닌 프리티어 서버(t2.micro)를 이용해야 했습니다.  

  - 계속되는 메모리 릭 현상을 해결하기 위해 swap file을 통해 메모리를 늘리고, 각 서버의 커넥션 수를 명시적으로 제한하여 해결하였습니다.  
  
   
 <img  width="60%" height="40%" alt="스왑" src="https://github.com/bmm522/quiz-studio/assets/102157839/16b931d3-29d8-43e4-af2b-f3e8229355cc">
  
  
- node module 도입 이유  

  - 주 서비스인 ai한테서 문제를 빠른 속도로 사용자에게 응답하기 위해 event non-blocking 기반인 node를 채택했습니다.   
  
  - 이를 통해 다수의 요청을 동시에 처리하고 병렬적으로 작업을 수행할 수 있어서 사용자에게 신속한 응답을 제공할 수 있습니다.  

  - 문제 요청 시에는 직접적인 AWS RDS 요청이 리소스를 크게 사용할 것으로 판단하여, AI 문제 데이터를 저장하는 장소로 인메모리 DB인 Redis를 선택하였습니다.   


 <img  width="60%" height="40%"  src="https://github.com/bmm522/quiz-studio/assets/102157839/15b01892-6b0a-4ee1-b19f-0fa57acf9bbc">

> 스프링 모듈에서 @Async 키워드와 같은 것으로 non-blocking을 구현할 수 있습니다. 
>
> 하지만 모듈에 선택한 기술 orm인 JPA 는 blocking query이기 때문에 사용하지 않았습니다.

---

## 개인정보 문제와 SSO(Single Sign-On)

- 개인정보 문제

  - 사용자에게 회원가입 프로세스는 접근성을 저하한다고 생각하여, 소셜로그인으로만 간편하게 로그인 할 수 있도록 하였습니다.  

  - 사용자에게 받는 개인정보는 email이 있습니다. 해당 email을 암호화 후 저장하여, 추후에 db 해킹이 되었을 때도 타격이 없도록 하였습니다.  
  

<img width="20%" height="10%" src="https://github.com/bmm522/quiz-studio/assets/102157839/71f0e3e0-6cc5-4634-84ff-01712e9e9edc">


- SSO(Single Sign-On)

  - 멀티 모듈 기반이므로 JWT토큰을 이용하여 SSO을 구현했습니다.  
  
  - Flow는 다음과 같습니다.  
  

<img width="60%" height="40%" src="https://github.com/bmm522/quiz-studio/assets/102157839/466e16aa-335e-415e-a510-1e281ae6a3c7">



---


## Test

- 코드를 검증하고, 개발자가 의도한 논리적 방향대로 잘 작동하는지를 확인할 수 있는 가장 좋은방법은 테스트라고 생각합니다.   

- 각각의 모듈에 총 155개의 e2e 테스트와 unit 테스트를 작성했습니다.
  - quiz-spring module : 51 Tests (Junit)
  - quiz-batch module : 36 Tests (Junit)
  - quiz-node module : 35 Tests (JEST, supertest)
  - user module : 33 Tests (Junit)

    
<img src="https://github.com/bmm522/quiz-studio/assets/102157839/169c6856-54fa-49cf-ab9e-a54eec49b1fa" width="50%" height="40%"></img>



---

## CI/CD

- github action을 통해 CI/CD를 구축했습니다.   

- 모듈들이 독립적으로 빌드되도록 각각의 CI/CD를 구축했습니다.  


<img src="https://github.com/bmm522/quiz-studio/assets/102157839/8bbf8a01-c5a2-4b0d-a167-23b30056cbd8" width="50%" height="40%"></img>

---

## Mapper

-   각 계층이 서로에게 영향이 없도록 분리하는 것은 프로젝트 설계 시 중요한 고민 사항 중 하나이었습니다. 이 고민에 대한 답으로 각 계층마다 확실한 분리를 위해 mapper 객체를 사용했습니다.

-   Mapper는 layer 이동 시에 데이터를 해당 레이어의 데이터 전용 객체(DTO)로 변환하는 역할을 수행합니다.

-   이를 통해 각 데이터는 서로 영향을 주지 않고, 해당 데이터에 대한 비즈니스 로직에만 집중할 수 있었습니다. 또한 데이터의 불변성을 보장할 수 있었습니다.

    <img src="https://user-images.githubusercontent.com/102157839/226163040-c8b416fb-3a89-44e6-b135-1f8e785c0a3a.png" width="60%" height="30%"></img>
    <img src="https://github.com/bmm522/quiz-studio/assets/102157839/25c2d98b-93ee-4f6b-8eba-80de4b4c2735" width="80%" height="60%"></img>

> 각각의 매퍼(mapper) 객체는 변환되는 시점에서의 역할을 수행하는 객체와 밀접한 관계를 가지므로, 메서드를 정적 메서드로 구성했습니다

---

## CQRS 패턴

- 단순 조회의 영역은 기능에 더 가까이 종속되고, 수정, 삭제, 삽입과 같은 작업은 도메인에 더 가까이 종속된다고 생각하여, 조회와 데이터조작 레포지토리를 분리하여, 복잡도와 책임 분리를 개선하고자 했습니다.

 <img src="https://github.com/bmm522/quiz-studio/assets/102157839/10794f76-5c5a-4ded-a569-1a9ca0457dfb" width="50%" height="20%"></img>

---

## Facade 패턴

- 하나의 도메인 서비스에서 다른 도메인의 레포지토리를 DI 하는 것은 추후에 서비스의 사이즈가 커질 수록 의존관계의 복잡도가 높아질 수 있기 때문에, 여러개의 도메인의 레포지토리를 DI해서 비즈니스 로직을 처리해야 하는 경우에는 Facade 패턴을 이용해 해결했습니다.

 <img src="https://github.com/bmm522/quiz-studio/assets/102157839/80e58bf4-4ed9-4d57-b8fd-d95dd3b06994" width="40%" height="15%"></img>

> 해당 구조 개선에 대한 issue 입니다. https://github.com/bmm522/quiz-studio/issues/23
---

## Validation 체크

-   Validation 체크의 경우를 두가지로 정했습니다.

    -   Client로 부터 오는 Validation 체크

        -   클라이언트로부터 오는 Validation에 대한 책임은 Request로부터 생성되는 DTO에 있으며, 이를 위해 DTO가 생성되는 시점인 Presentation 계층의 Mapper에서 체크하도록 설계했습니다.

        -   이를 통해, 컨트롤러는 검증된 데이터를 서비스레이어에 전달 할 수 있게 되었습니다.

        <img src="https://github.com/bmm522/quiz-studio/assets/102157839/e0e76ead-cb79-4847-9c7b-1aee6e35cd88" width="60%" height="50%"></img>


    -   Db의 데이터를 확인해야 하는 Validation 체크

        -   데이터베이스의 데이터를 확인해서 해야하는 Validation 체크는 서비스 레이어에서 이루어져야 한다고 판단했습니다.

        -   이에 대표적으로 권한 체크가 있었습니다. 앞서 설명한 대로, 해당 역할만 수행하는 Validator를 통해 이를 구현하였습니다.

        <img src="https://github.com/bmm522/quiz-studio/assets/102157839/35a7eb12-5dd9-42bf-b987-0a7b866fa71d" width="60%" height="50%"></img>

---











