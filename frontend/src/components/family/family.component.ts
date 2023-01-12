import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { IFamily } from "src/app/interfaces";
import { FamilyService } from "./family.service";

enum navigationOptions {
  members = "members",
  chronicles = "chronicles",
  discussion = "discussion",
  requests = "requests",
}

@Component({
  selector: "app-family",
  templateUrl: "./family.component.html",
  styleUrls: ["./family.component.scss"],
})
export class FamilyComponent implements OnInit {
  familyId: number;
  currentFamily: IFamily;
  currentUserId: number;
  familyOwnerId: number;
  navigationOption: navigationOptions;

  constructor(
    private route: ActivatedRoute,
    private familyService: FamilyService,
    private router: Router
  ) {}

  ngOnInit() {
    this.familyId = this.route.snapshot.params["id"];
    this.navigationOption = this.route.snapshot.params["navigation"];
    this.checkNavigationRedirect();

    this.familyService
      .getCurrentUserIdByEmail(sessionStorage.getItem("email"))
      .subscribe((result) => {
        this.currentUserId = result;
      });

    this.familyService.getFamilyById(this.familyId).subscribe((result) => {
      this.currentFamily = result;
      this.familyOwnerId = result.ownerId;
    });
  }

  checkNavigationRedirect() {
    if (this.navigationOption == null) {
      this.navigationOption = navigationOptions.members;
    } else if (
      !Object.values(navigationOptions).includes(this.navigationOption)
    ) {
      console.log("wrong url");
    }
  }
}
