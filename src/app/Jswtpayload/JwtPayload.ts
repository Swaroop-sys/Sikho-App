// jwt-helper.ts or wherever you're decoding token
import { JwtPayload } from 'jwt-decode';

export interface CustomJwtPayload extends JwtPayload {
  name: string;
  role: string;
  // add any other custom fields
}
