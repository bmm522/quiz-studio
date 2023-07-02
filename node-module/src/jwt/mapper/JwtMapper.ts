import { Request } from 'express';
import { JwtToken } from '../dto/JwtToken';

export class JwtMapper {

  static async toJwtToken(req: Request): Promise<JwtToken> {
    return new JwtToken(
        typeof req.headers.authorization === 'string' ? req.headers.authorization : undefined,
        typeof req.headers.refreshtoken === 'string' ? req.headers.refreshtoken : undefined,
    );
  }
//   static async toJwtToken(req: Request): Promise<JwtToken> {
//     const jwtToken: string =  await this.getCookieValue(req, "Authorization");
//     const refreshToken: string = await  this.getCookieValue(req, "RefreshToken");
//     jwtToken.replace("+", " ");
//     refreshToken.replace("+", " ");
//     return new JwtToken(
//      jwtToken,refreshToken
//     );
//   }
//
//   static async getCookieValue(req: Request, cookieName: string) : Promise<string>{
//     const cookies = req.cookies;
//     let cookieValue = '';
//
//     if (cookieName && cookies && typeof cookies === 'object') {
//       for (const name in cookies) {
//         if (Object.prototype.hasOwnProperty.call(cookies, name) && name === cookieName) {
//           cookieValue = cookies[name];
//           break;
//         }
//       }
//     }
//
//     if (!cookieValue) {
//       cookieValue = 'guest';
//     }
//
//     return cookieValue;
// }
}
