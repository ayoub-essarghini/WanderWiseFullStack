import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {routes} from "../app.routes";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  formLogin! : FormGroup

  constructor(private fb: FormBuilder,private authServ: AuthService,private router:Router) {
  }

  ngOnInit(): void {
    this.formLogin =this.fb.group({
      username: this.fb.control("username"),
      password: this.fb.control("password")
    })
  }
  handleLogin()
  {
    let username = this.formLogin.value.username;
    let password = this.formLogin.value.password;
    this.authServ.login(username,password).subscribe({
      next: data => {
        this.authServ.loadProfile(data);
        this.router.navigateByUrl("/admin/dashboard");
      },
      error: err =>{
        console.log(err)
      }
    });
  }
}
