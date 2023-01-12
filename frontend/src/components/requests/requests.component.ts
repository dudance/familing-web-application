import { Component, Input, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { IUserDetails } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { RequestsService } from "./requests.service";

@Component({
  selector: "app-requests",
  templateUrl: "./requests.component.html",
  styleUrls: ["./requests.component.scss"],
})
export class RequestsComponent implements OnInit {
  @Input() familyId: number;
  listOfUsersOfRequests: IUserDetails[] = [];

  constructor(
    private requestsService: RequestsService,
    private utilFunctions: UtilFunctions,
    private router: Router
  ) {}

  ngOnInit() {
    this.updateListOfRequests();
  }

  updateListOfRequests() {
    this.requestsService
      .getUsersWhoSentRequest(this.familyId)
      .subscribe((response) => {
        this.listOfUsersOfRequests = response;
      });
  }

  acceptRequest(userId: number) {
    this.requestsService.acceptRequest(userId, this.familyId).subscribe(
      () => {
        this.router.navigate([
          "/my-families/id/" + this.familyId + "/requests",
        ]);
        this.utilFunctions.displaySuccessSnackbar(
          "Successfully added user",
          "Close",
          4000
        );
        this.updateListOfRequests();
      },
      (error) => {
        this.utilFunctions.displayErrorSnackbar(error.error.message, "Close", 4000);
      }
    );
  }

  declineRequest(userId: number) {
    this.requestsService.declineRequest(userId, this.familyId).subscribe(
      () => {
        this.router.navigate([
          "/my-families/id/" + this.familyId + "/requests",
        ]);
        this.utilFunctions.displaySuccessSnackbar(
          "Successfully removed request",
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
