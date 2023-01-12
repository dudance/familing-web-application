import { Component, OnInit } from "@angular/core";
import {
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from "@angular/forms";
import { Router } from "@angular/router";
import { IRegister } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { RegisterService } from "./register.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"],
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  submitted: boolean;
  constructor(
    private registerService: RegisterService,
    private router: Router,
    private utilFunctions: UtilFunctions
  ) {}

  ngOnInit() {
    this.registerForm = new FormGroup({
      firstName: new FormControl("", [
        Validators.required,
        Validators.maxLength(30),
      ]),
      lastName: new FormControl("", [
        Validators.required,
        Validators.maxLength(30),
      ]),
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30),
      ]),
      confirmPassword: new FormControl("", [Validators.required]),
    });
    this.registerForm.setValidators(
      this.passwordsMustMatch("password", "confirmPassword")
    );
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.registerForm);
    if (this.registerForm.invalid) {
      if (this.registerForm.controls["firstName"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid first name field value (first name is required and allows a max length of 30 characters)",
          "Close",
          4000
        );
      } else if (this.registerForm.controls["lastName"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid surname field value (surname is required and allows a max length of 30 characters)",
          "Close",
          4000
        );
      } else if (this.registerForm.controls["email"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid email field value (email is required and allows only correct email format)",
          "Close",
          4000
        );
      } else if (this.registerForm.controls["password"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid password field value (password is required and allows a length from 6 to 30 characters)",
          "Close",
          4000
        );
      } else if (this.registerForm.controls["confirmPassword"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid confirm password field value (confirm password is required and need to be same as password)",
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
      var requestBody: IRegister = {
        email: this.registerForm.controls["email"].value,
        name: this.registerForm.controls["firstName"].value,
        surname: this.registerForm.controls["lastName"].value,
        password: this.registerForm.controls["password"].value,
      };

      this.registerService.registerUser(requestBody).subscribe(
        () => {
          this.router.navigate(["login"]).then((navigated: boolean) => {
            if (navigated) {
              this.utilFunctions.displaySuccessSnackbar(
                "Successfully registered, you can sign in now",
                "Close",
                4000
              );
            }
          });
        },
        (error) => {
          this.utilFunctions.displayErrorSnackbar(error.error.message, "Close", 4000);
        }
      );
    }
  }

  public passwordsMustMatch(
    password: string,
    confirmPassword: string
  ): ValidatorFn {
    return (formGroup: FormGroup): ValidationErrors => {
      const passwordControl = formGroup.controls[password];
      const confirmPasswordControl = formGroup.controls[confirmPassword];

      if (
        confirmPasswordControl.errors &&
        !confirmPasswordControl.errors.passwordsMustMatch
      ) {
        return;
      }
      if (passwordControl.value !== confirmPasswordControl.value) {
        confirmPasswordControl.setErrors({ passwordsMustMatch: true });
      } else {
        confirmPasswordControl.setErrors(null);
      }
      return;
    };
  }
}
