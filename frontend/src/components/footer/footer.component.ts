import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { INewsletter } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { FooterService } from "./footer.service";

@Component({
  selector: "app-footer",
  templateUrl: "./footer.component.html",
  styleUrls: ["./footer.component.scss"],
})
export class FooterComponent implements OnInit {
  newsletterForm: FormGroup;

  constructor(
    private utilFunctions: UtilFunctions,
    private footerService: FooterService
  ) {}

  ngOnInit() {
    this.newsletterForm = new FormGroup({
      email: new FormControl("", [Validators.required, Validators.email]),
    });
  }

  onSubmit(formValues) {
    if (this.newsletterForm.invalid) {
      if (this.newsletterForm.controls["email"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid email field value (email is required and allows only correct email format)",
          "Close",
          4000
        );
      } else {
        this.utilFunctions.displayErrorSnackbar(
          "incorrect form values",
          "Close",
          4000
        );
      }
    } else {
      let newsletter: INewsletter = {
        id: null,
        email: formValues.email,
      };
      this.footerService.inviteUser(newsletter).subscribe(
        () => {
          this.newsletterForm.setValue({ email: "" });
          this.utilFunctions.displaySuccessSnackbar(
            "Added email to newsletter",
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
