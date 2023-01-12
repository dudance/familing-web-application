import { Component, OnInit } from "@angular/core";
import { UserDetailsService } from "../edit-profile/user-details.service";
import { AllFamiliesService } from "./all-families.service";
import { InvitationsService } from "../invitations/invitations.service";
import { Router } from "@angular/router";
import { UtilFunctions } from "src/assets/utils/functions";
import { IFamily, IInvitation } from "src/app/interfaces";

@Component({
  selector: "app-all-families",
  templateUrl: "./all-families.component.html",
  styleUrls: ["./all-families.component.scss"],
})
export class AllFamiliesComponent implements OnInit {
  currentUserFamilies: IFamily[];
  allFamilies: IFamily[];
  searchedSurnames: string = "";
  searchedMember: string = "";
  filteredFamilies: IFamily[];

  constructor(
    private allFamiliesService: AllFamiliesService,
    private userDetailsService: UserDetailsService,
    private invitationService: InvitationsService,
    private router: Router,
    private utilFunctions: UtilFunctions
  ) {}

  ngOnInit() {
    this.allFamiliesService.getAllFamilies().subscribe((response) => {
      this.allFamilies = response;
      this.filteredFamilies = response;
      this.updateDetails();
      this.allFamiliesService.getFamiliesOfCurrentUser().subscribe((result) => {
        this.currentUserFamilies = result;
      });
    });
  }

  isUserPartOFFamily(familyId: number): boolean {
    return (
      this.currentUserFamilies.filter((family) => {
        return family.id == familyId;
      }).length > 0
    );
  }

  searchBySurnames() {
    let surnamesString = this.searchedSurnames.replace(/ /gi, "*");
    if (surnamesString.length > 0) {
      this.allFamiliesService
        .getFamiliesBySearchedSurnames(surnamesString)
        .subscribe((response) => {
          this.filteredFamilies = response;
          this.updateDetails();
        });
    } else {
      this.utilFunctions.displayErrorSnackbar(
        "incorrect format of data",
        "Close",
        4000
      );
    }
  }

  searchByMember() {
    let userDataArray = this.searchedMember.split(" ");
    if (userDataArray.length != 2) {
      this.utilFunctions.displayErrorSnackbar(
        "incorrect format of data",
        "Close",
        4000
      );
    } else {
      this.allFamiliesService
        .getFamiliesBySearchedMember(userDataArray[0] + "*" + userDataArray[1])
        .subscribe((response) => {
          this.filteredFamilies = response;
          this.updateDetails();
        });
    }
  }

  searchByName(searchedValue: string) {
    this.filteredFamilies = this.allFamilies.filter((family: IFamily) => {
      return family.name.toLowerCase().includes(searchedValue.toLowerCase());
    });
  }

  sendRequest(familyId: number) {
    let invitation: IInvitation = {
      id: null,
      type: "INV_FROM",
      familyId: familyId,
      userId: null,
    };

    this.invitationService.sendFamilyRequest(invitation).subscribe(
      () => {
        this.utilFunctions.displaySuccessSnackbar(
          "Successfully sent request",
          "Close",
          4000
        );
      },
      (error) => {
        this.utilFunctions.displayErrorSnackbar(error.error, "Close", 4000);
      }
    );
  }

  updateDetails() {
    this.filteredFamilies.forEach((family) => {
      this.userDetailsService
        .getSurnamesOfFamily(family.id)
        .subscribe((result) => {
          family.surnames = result;
        });
      this.userDetailsService
        .getUserDetailsByUserId(family.ownerId)
        .subscribe((result) => {
          family.ownerData = result.name + " " + result.surname;
        });
    });
  }

  displayInvitationsOfUser() {
    this.router.navigate(["invitations"]);
  }

  resetFilters() {
    this.filteredFamilies = this.allFamilies;
  }
}
