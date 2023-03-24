export class SplAuthModel {
  authToken: string;
  refreshToken: string;
  expiresIn: Date;

  setAuth(auth: SplAuthModel) {
    this.authToken = auth.authToken;
    this.refreshToken = auth.refreshToken;
    this.expiresIn = auth.expiresIn;
  }
}
