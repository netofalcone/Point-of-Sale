export class AppConstants {
  public static get baseURLServer(): string {return 'http://localhost:8080/pos/'}
  public static get baseLogin(): string{ return this.baseURLServer + 'login'}
  public static get baseUsers(): string{ return this.baseURLServer + 'users'}
}
