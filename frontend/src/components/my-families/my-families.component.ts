import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { IFamily } from "src/app/interfaces";
import { UserDetailsService } from "../edit-profile/user-details.service";
import { MyFamiliesService } from "./my-families.service";

@Component({
  selector: "app-my-families",
  templateUrl: "./my-families.component.html",
  styleUrls: ["./my-families.component.scss"],
})
export class MyFamiliesComponent implements OnInit {
  allFamilies: IFamily[];
  filteredFamilies: IFamily[];

  constructor(
    private myFamiliesService: MyFamiliesService,
    private userDetailsService: UserDetailsService,
    private router: Router
  ) {}

  ngOnInit() {
    this.myFamiliesService.getFamilies().subscribe((response) => {
      this.allFamilies = response;
      this.filteredFamilies = response;
      this.updateDetails();
    });
  }

  searchFamilies(searchedValue: string) {
    this.filteredFamilies = this.allFamilies.filter((family: IFamily) => {
      return family.name.toLowerCase().includes(searchedValue.toLowerCase());
    });
  }

  updateDetails() {
    this.allFamilies.forEach((family) => {
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

  visitFamilyPage(familyId: number) {
    this.router.navigate(["my-families/id/" + familyId]);
  }
}
