import { Component, Input, OnInit } from "@angular/core";
import { IInvitation, IUser } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { FamilyService } from "../family/family.service";
import { FamilyMembersService } from "./family-members.service";

@Component({
  selector: "app-family-members",
  templateUrl: "./family-members.component.html",
  styleUrls: ["./family-members.component.scss"],
})
export class FamilyMembersComponent implements OnInit {
  @Input() familyId: number;
  @Input() currentUserId: number;
  @Input() familyOwnerId: number;

  familyMembers: IUser[] = [];
  allUsers: IUser[];
  filteredUsers: IUser[];
  selectedUser: IUser;

  constructor(
    private familyService: FamilyService,
    private utilFunctions: UtilFunctions,
    private familyMembersService: FamilyMembersService
  ) {}

  ngOnInit() {
    this.familyService
      .getMembersOfFamily(this.familyId)
      .subscribe((results) => {
        this.familyMembers = results;
        this.familyMembersService.getAllUsers().subscribe((usersList) => {
          this.allUsers = usersList;
          this.filteredUsers = usersList;
        });
      });
  }

  inviteUser() {
    if (this.selectedUser == null) {
      this.utilFunctions.displayErrorSnackbar(
        "Choose email from list",
        "Close",
        4000
      );
    } else {
      let invitationBody: IInvitation = {
        id: null,
        type: "INV_TO",
        familyId: this.familyId,
        userId: this.selectedUser.id,
      };

      this.familyService.inviteUser(invitationBody).subscribe(
        () => {
          this.utilFunctions.displaySuccessSnackbar(
            "Successfully invited user",
            "Close",
            4000
          );
        },
        (error) => {
          this.utilFunctions.displayErrorSnackbar(error.error, "Close", 4000);
        }
      );
    }
  }

  updateInput(value) {
    this.filteredUsers = this.filterUsers(value);
  }

  updateSelectedUser(value) {
    this.selectedUser = value;
  }

  filterUsers(value: string) {
    let filter = value.toLowerCase();
    return this.allUsers.filter((option) =>
      option.email.toLowerCase().includes(filter)
    );
  }
}
