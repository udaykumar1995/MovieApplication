import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
registrationUrl='http://18.219.121.39:8080/auth/register';
loginUrl='http://18.219.121.39:8080/auth/login';
    constructor(private http: HttpClient) { }

addUser(data:any) {
console.log(data);
return this.http.post<any>(this.registrationUrl, data);
}
login(data:any) {
console.log(data);
return this.http.post<any>(this.loginUrl,data);
}
logout() {
        console.log("user log out");
        localStorage.removeItem('access_token');
  }

}
