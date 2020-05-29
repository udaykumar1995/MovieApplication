import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { MovieService } from '../movie.service';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
iserror: boolean;
  constructor(
    private router: Router,
  private movieService: MovieService,private AuthService:AuthService)
             {}
    ngOnInit() {
  }
  login(data:any) {
var registration = {
"userName":data.userName,
"Password":data.password
}
this.AuthService.login(data)
.subscribe(
(data) => {
if(data){
localStorage.setItem('access_token',data.token);
  console.log(data.token);
  this.router.navigate(['/movies']);
}
});
if(data.token==null)
{
  this.iserror=true;
}
}
}
