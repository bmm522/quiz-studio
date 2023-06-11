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
  - AWS EC2, AWS RDS, AWS Certificate Manager, AWS Route 53, AWS ELB, AWS CloudFront, AWS S3, Docker
---

## Architecture

---

<img width="553" alt="image" src="https://github.com/bmm522/quiz-studio/assets/102157839/4b49a874-0270-4ffe-959c-1a9d29b75e81">


---

## 프로젝트 구조

---

### 1. 각 계층과 객체의 확실한 책임 분리

> 각 계층과 객체의 확실한 책임 분리는 코드의 유지보수성, 확장성, 테스트 용이성을 향상시키기 위한 중요한 설계 원칙 중 하나라고 생각합니다.
>
> 따라서 이번 프로젝트에서도 각 계층과 객체의 책임을 분리하여 객체지향적인 설계를 추구했습니다.
>
> 다음 항목들은 각 계층과 객체의 확실한 책임 분리를 위해 고려했던 것들입니다.

---

### Mapper

-   각 계층이 서로에게 영향이 없도록 분리하는 것은 프로젝트 설계 시 중요한 고민 사항 중 하나이었습니다. 이 고민에 대한 답으로 각 계층마다 확실한 분리를 위해 mapper 객체를 사용했습니다.

-   Mapper는 layer 이동 시에 데이터를 해당 레이어의 데이터 전용 객체(DTO)로 변환하는 역할을 수행합니다.

-   이를 통해 각 데이터는 서로 영향을 주지 않고, 해당 데이터에 대한 비즈니스 로직에만 집중할 수 있었습니다. 또한 데이터의 불변성을 보장할 수 있었습니다.

    <img src="https://user-images.githubusercontent.com/102157839/226163040-c8b416fb-3a89-44e6-b135-1f8e785c0a3a.png" width="60%" height="30%"></img>
    <img src="https://github.com/bmm522/quiz-studio/assets/102157839/25c2d98b-93ee-4f6b-8eba-80de4b4c2735" width="80%" height="60%"></img>

> 각각의 매퍼(mapper) 객체는 변환되는 시점에서의 역할을 수행하는 객체와 밀접한 관계를 가지므로, 메서드를 정적 메서드로 구성했습니다







