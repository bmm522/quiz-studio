import { App } from './app';

// App 클래스의 인스턴스를 생성하고, 서버를 시작하는 역할을 합니다.
try {
  const app = new App().app;
  const port = 3000;

  app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
  });

} catch (error) {
  console.log(error);
}
