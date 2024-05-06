export  class SessionData {

    IsAuthenticated: boolean = false;
    roles: any;
    username: any;
    jwtToken!: string;
    jwtRefreshToken!:string;

    public constructor(isAuth: boolean, roles: any, username: any, jwtToken: string, JwtRefreshToken: string) {
        this.IsAuthenticated = isAuth;
        this.roles = roles;
        this.username = username;
        this.jwtToken = jwtToken;
        this.jwtRefreshToken = JwtRefreshToken;
    }

}