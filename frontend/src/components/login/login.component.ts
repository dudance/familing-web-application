import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { UtilFunctions } from "src/assets/utils/functions";
import { AuthenticationService } from "../../app/service/authentication.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  email = "";
  password = "";

  constructor(
    private router: Router,
    private loginservice: AuthenticationService,
    private utilFunctions: UtilFunctions
  ) {}

  ngOnInit() {
    if (sessionStorage.getItem("email")) {
      this.router.navigate([""]);
    }
    this.loginForm = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30),
      ]),
    });
  }

  checkLogin() {
    if (this.loginForm.invalid) {
      if (this.loginForm.controls["email"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid email field value (email is required and allows only correct email format)",
          "Close",
          4000
        );
      } else if (this.loginForm.controls["password"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid password field value (password is required and allows a length from 6 to 30 characters)",
          "Close",
          4000
        );
      } else {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid form values",
          "Close",
          4000
        );
      }
    } else {
      this.loginservice.authenticate(this.email, this.password).subscribe(
        () => {
          this.router.navigate([""]);
          this.utilFunctions.displaySuccessSnackbar(
            "Successfully logged in",
            "Close",
            4000
          );
        },
        (error) => {
          this.utilFunctions.displayErrorSnackbar(error.error.message, "Close", 4000);
        }
      );
    }
  }
}
