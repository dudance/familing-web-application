import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { IFamily } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { CreateFamilyService } from "./create-family.service";

@Component({
  selector: "app-create-family",
  templateUrl: "./create-family.component.html",
  styleUrls: ["./create-family.component.scss"],
})
export class CreateFamilyComponent implements OnInit {
  newFamilyForm: FormGroup;
  imageVar: File = null;
  newFamily: IFamily = {
    id: null,
    description: null,
    image: null,
    name: null,
    ownerData: null,
    ownerId: null,
    surnames: null,
  };

  constructor(
    private createFamilyService: CreateFamilyService,
    private utilFunctions: UtilFunctions,
    private router: Router
  ) {}

  ngOnInit() {
    this.newFamilyForm = new FormGroup({
      name: new FormControl("", [
        Validators.required,
        Validators.maxLength(30),
      ]),
      description: new FormControl("", [
        Validators.required,
        Validators.maxLength(255),
      ]),
    });
  }

  onSubmit(formValues) {
    if (this.newFamilyForm.invalid) {
      if (this.newFamilyForm.controls["name"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid name field value (name is required and allows a max length of 30 characters)",
          "Close",
          4000
        );
      } else if (this.newFamilyForm.controls["description"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid description field value (description is required and allows a max length of 255 characters)",
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
      this.newFamily.name = formValues.name;
      this.newFamily.description = formValues.description;

      this.createFamilyService.createNewFamily(this.newFamily).subscribe(
        (result) => {
          if (this.imageVar != null) {
            this.createFamilyService
              .sendImage(this.imageVar, result.id)
              .subscribe(
                () => {},
                (error) => {
                  // this.utilFunctions.displayErrorSnackbar(
                  //   error.error,
                  //   "Close",
                  //   4000
                  // );
                }
              );
          }
          this.router.navigate(["/my-families"]);
          this.utilFunctions.displaySuccessSnackbar(
            "Successfully created family",
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
