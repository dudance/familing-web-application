import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { IFamily, IInvitation } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { AllFamiliesService } from "../all-families/all-families.service";
import { UserDetailsService } from "../edit-profile/user-details.service";
import { InvitationsService } from "./invitations.service";

@Component({
  selector: "app-invitations",
  templateUrl: "./invitations.component.html",
  styleUrls: ["./invitations.component.scss"],
})
export class InvitationsComponent implements OnInit {
  userEmail: string;
  invitationsList: IInvitation[];
  familiesList: IFamily[];
  searchedSurnames: string;
  searchedMember: string;

  constructor(
    private invitationsService: InvitationsService,
    private allFamiliesService: AllFamiliesService,
    private userDetailsService: UserDetailsService,
    private utilFunctions: UtilFunctions,
    private router: Router
  ) {}
  ngOnInit() {
    this.userEmail = sessionStorage.getItem("email");
    this.invitationsService
      .getInvitationsOfUserByEmail(this.userEmail)
      .subscribe((response) => {
        this.invitationsList = response;
        this.allFamiliesService
          .getFamiliesByUserInvitations(this.invitationsList)
          .subscribe((secondResponse) => {
            this.familiesList = secondResponse;
            this.updateDetails();
          });
      });
  }

  updateDetails() {
    this.familiesList.forEach((family) => {
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

  acceptInvitation(familyId: number) {
    let invitation: IInvitation = {
      id: null,
      type: "INV_FROM",
      familyId: familyId,
      userId: null,
    };

    this.invitationsService.sendFamilyRequest(invitation).subscribe(
      () => {
        this.router.navigate(["/my-families"]);
        this.utilFunctions.displaySuccessSnackbar(
          "Successfully asccepted invitation",
          "Close",
          4000
        );
      },
      (error) => {
        this.utilFunctions.displayErrorSnackbar(error.error, "Close", 4000);
      }
    );
  }

  declineInvitation(familyId: number) {
    let invitation: IInvitation = {
      id: null,
      type: "INV_TO",
      familyId: familyId,
      userId: null,
    };

    this.invitationsService.declineInvitation(invitation).subscribe(
      () => {
        this.utilFunctions.displaySuccessSnackbar(
          "Successfully declined invitation",
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
