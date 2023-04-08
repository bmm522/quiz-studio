import { Request, Response, NextFunction } from 'express';
import { UnauthorizedError } from '../UnauthorizedError';
import { ExpiredTokenError } from '../ExpiredTokenError';
import { NotFoundEntityError } from '../NotFoundEntityError';

export const ErrorHandler = (err: Error, req: Request, res: Response, next: NextFunction) => {
  if (res.headersSent) {
    return next(err);
  }

  if (err instanceof UnauthorizedError) {
    res.status(401).json({ message: err.message });
  } else if (err instanceof ExpiredTokenError) {
    res.status(401).json({ message: err.message });
  } else if (err instanceof NotFoundEntityError) {
    res.status(500).json({ message: err.message });
  } else {
    res.status(500).json({ message: 'Internal Server Error' });
  }
};
