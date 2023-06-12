# quiz-studio


## Description

---

- 🔊프로젝트 소개
  - gpt한테 문제를 뽑아서 풀 수 있는 퀴즈 사이트
  - 프론트(Vanilla js) + 4개의 모듈(REST API)기반으로 구성된 프로젝트
  - 서비스의 A to Z 를 경험하고 싶어, 프론트 부터 백, CI/CD 모든 것을 구축했습니다.
- 🏗️개발 기간 : 2023.03 ~

- 🛠️사용기술
  - Java11, JavaScript, Typescript
  - Spring Boot, Spring Security, Spring Data JPA, Spring Batch, Node 
  - MySQL, Redis, MongoDB
  - Junit, JEST, supertest
  - AWS EC2, AWS RDS, AWS Certificate Manager, AWS Route 53, AWS ELB, AWS CloudFront, AWS S3, Docker
---

## Architecture

---

<img width="553" alt="image" src="https://github.com/bmm522/quiz-studio/assets/102157839/4b49a874-0270-4ffe-959c-1a9d29b75e81">


---

## 프로젝트 구조

- 목차
  - Mapper
  - CQRS 패턴
  - Facade 패턴
  - Validation 체크
  - Test
  - CI/CD
  - OPENAI API 와 batch scheduler
---

### Mapper

-   각 계층이 서로에게 영향이 없도록 분리하는 것은 프로젝트 설계 시 중요한 고민 사항 중 하나이었습니다. 이 고민에 대한 답으로 각 계층마다 확실한 분리를 위해 mapper 객체를 사용했습니다.

-   Mapper는 layer 이동 시에 데이터를 해당 레이어의 데이터 전용 객체(DTO)로 변환하는 역할을 수행합니다.

-   이를 통해 각 데이터는 서로 영향을 주지 않고, 해당 데이터에 대한 비즈니스 로직에만 집중할 수 있었습니다. 또한 데이터의 불변성을 보장할 수 있었습니다.

    <img src="https://user-images.githubusercontent.com/102157839/226163040-c8b416fb-3a89-44e6-b135-1f8e785c0a3a.png" width="60%" height="30%"></img>
    <img src="https://github.com/bmm522/quiz-studio/assets/102157839/25c2d98b-93ee-4f6b-8eba-80de4b4c2735" width="80%" height="60%"></img>

> 각각의 매퍼(mapper) 객체는 변환되는 시점에서의 역할을 수행하는 객체와 밀접한 관계를 가지므로, 메서드를 정적 메서드로 구성했습니다

---

### CQRS 패턴

- 단순 조회의 영역은 기능에 더 가까이 종속되고, 수정, 삭제, 삽입과 같은 작업은 도메인에 더 가까이 종속된다고 생각하여, 조회와 데이터조작 레포지토리를 분리하여, 복잡도와 책임 분리를 개선하고자 했습니다.

 <img src="https://github.com/bmm522/quiz-studio/assets/102157839/10794f76-5c5a-4ded-a569-1a9ca0457dfb" width="50%" height="20%"></img>

---

### Facade 패턴

- 하나의 도메인 서비스에서 다른 도메인의 레포지토리를 DI 하는 것은 추후에 서비스의 사이즈가 커질 수록 의존관계의 복잡도가 높아질 수 있기 때문에, 여러개의 도메인의 레포지토리를 DI해서 비즈니스 로직을 처리해야 하는 경우에는 Facade 패턴을 이용해 해결했습니다.

 <img src="https://github.com/bmm522/quiz-studio/assets/102157839/80e58bf4-4ed9-4d57-b8fd-d95dd3b06994" width="40%" height="15%"></img>

> 해당 구조 개선에 대한 issue 입니다. https://github.com/bmm522/quiz-studio/issues/23
---

### Validation

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

### Test

- 코드를 검증하고, 개발자가 의도한 논리적 방향대로 잘 작동하는지를 확인할 수 있는 가장 좋은방법은 테스트라고 생각합니다. 
- 각각의 모듈에 e2e 테스트와 unit 테스트를 작성했습니다. (스프링 + junit , 노드 + JEST(unit) + supertest(e2e))
    
<img src="https://github.com/bmm522/quiz-studio/assets/102157839/e01daedb-d3f5-4839-be4a-a2a6c72bb056" width="50%" height="40%"></img>

---

### CI/CD

- github action을 통해 CI/CD를 구축했습니다. 
- 모듈들이 독립적으로 빌드되도록 각각의 CI/CD를 구축했습니다.

<img src="https://github.com/bmm522/quiz-studio/assets/102157839/8bbf8a01-c5a2-4b0d-a167-23b30056cbd8" width="50%" height="40%"></img>

