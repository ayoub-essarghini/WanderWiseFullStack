import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {Ripple} from "primeng/ripple";
import {MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    Ripple,
    ToastModule,
  ],

  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  formLogin! : FormGroup

  constructor(private fb: FormBuilder,private authServ: AuthService,private router:Router, private messageService:MessageService) {
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
    // this.showError()
    this.authServ.login(username,password).subscribe({
      next: data => {
        // console.log(data);
        this.showToast("success","Success","Login Successfully !!",3000)
        this.authServ.loadProfile(data);
        this.router.navigateByUrl("/home");
      },
      error: err =>{
        console.log(err)
        this.showToast("error","Error","Bad credential",3000)
        // console.error("error occured")
      }

    });
  }

  showToast(type:string,title:string,msg:string,time:number) {
    this.messageService.add({ severity: type, summary: title, detail: msg, life: time });
  }
}
