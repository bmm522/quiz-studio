export class NotFoundEntityError extends Error {
  constructor(message: string) {
    super(message);
    this.name = 'NotFoundEntityError';
  }
}
