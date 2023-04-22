import { Request } from 'express';

export interface UserKeyRequest extends Request {
  userKey?: string;
}
