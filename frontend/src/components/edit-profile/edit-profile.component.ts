import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { IUserDetails } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { UserDetailsService } from "./user-details.service";

@Component({
  selector: "app-edit-profile",
  templateUrl: "./edit-profile.component.html",
  styleUrls: ["./edit-profile.component.scss"],
})
export class EditProfileComponent implements OnInit {
  currentUserEmail: string;
  currentUserDetails: IUserDetails;
  userDetailsForm: FormGroup = new FormGroup({
    name: new FormControl(),
    surname: new FormControl(),
    phone: new FormControl(),
  });

  imageVar: File = null;

  constructor(
    private userDetailsService: UserDetailsService,
    private utilFunctions: UtilFunctions
  ) {}

  ngOnInit() {
    this.currentUserEmail = sessionStorage.getItem("email");
    this.userDetailsService
      .getUserDetailsByUserEmail(this.currentUserEmail)
      .subscribe((result) => {
        this.currentUserDetails = result;

        this.userDetailsForm = new FormGroup({
          name: new FormControl(result.name, [
            Validators.required,
            Validators.maxLength(30),
          ]),
          surname: new FormControl(result.surname, [
            Validators.required,
            Validators.maxLength(30),
          ]),
          phone: new FormControl(result.phone, [
            Validators.pattern("^([0-9]{0,10})$"),
          ]),
        });
      });
  }

  onSubmit(formValues) {
    if (this.userDetailsForm.invalid) {
      if (this.userDetailsForm.controls["name"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid first name field value (first name is required and allows a max length of 30 characters)",
          "Close",
          4000
        );
      } else if (this.userDetailsForm.controls["surname"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid surname field value (surname is required and allows a max length of 30 characters)",
          "Close",
          4000
        );
      } else if (this.userDetailsForm.controls["phone"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid phone field value (phone require only numbers with length from 0 to 10)",
          "Close",
          4000
        );
      } else {
        this.utilFunctions.displayErrorSnackbar(
          "Incorrect form values",
          "Close",
          4000
        );
      }
    } else {
      this.currentUserDetails.name = formValues.name;
      this.currentUserDetails.surname = formValues.surname;
      this.currentUserDetails.phone = formValues.phone;

      this.userDetailsService
        .updateUserDetails(this.currentUserDetails)
        .subscribe(
          (secondResults) => {
            this.currentUserDetails = secondResults;
            if (this.imageVar != null) {
              this.userDetailsService.sendImage(this.imageVar).subscribe(
                (result) => {
                  console.log('udalo sie')
                  this.currentUserDetails.image = result;
                },
                (error) => {
                  // this.utilFunctions.displayErrorSnackbar(
                  //   JSON.stringify(error.error),
                  //   "Close",
                  //   4000
                  // );
                }
              );
            }
            this.utilFunctions.displaySuccessSnackbar(
              "Successfully saved profile",
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

  updateImage(event) {
    if (!this.validateImage(event.target.files[0].name)) {
      this.utilFunctions.displayErrorSnackbar(
        "Your image format is not supported",
        "Close",
        4000
      );
    } else {
      this.imageVar = event.target.files.item(0);
    }
  }

  validateImage(imageName: String) {
    var correctImageFormats: String[] = ["png", "jpg", "jpeg", "svg"];

    var format = imageName.substring(imageName.lastIndexOf(".") + 1);
    if (correctImageFormats.includes(format.toLowerCase())) {
      return true;
    } else {
      return false;
    }
  }
}
