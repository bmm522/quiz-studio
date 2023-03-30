export const loginHost = "http://localhost:8081";
export const headers = {
    Authorization:localStorage.getItem('authorization'),
    RefreshToken:localStorage.getItem('refreshToken'),
}