import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../services/auth.service";

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

  constructor(private fb: FormBuilder,private authServ: AuthService) {
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
    this.authServ.login(username,password);
  }
}
