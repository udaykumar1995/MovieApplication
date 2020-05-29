import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(
    private router: Router,private AuthService:AuthService   ) { }

  ngOnInit() {
  }
  addUser(data:any) {
var registration = {
"firstName": data.firstName,
"lastName": data.lastName,
"userName":data.userName,
"Password":data.password
}
 this.AuthService.addUser(data)
.subscribe(
(res) => {
  console.log(res);
this.router.navigate(['/login']);

});
}
}
