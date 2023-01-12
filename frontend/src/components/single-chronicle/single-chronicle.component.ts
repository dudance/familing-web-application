import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { IChronicle, IUserDetails } from "src/app/interfaces";
import { SingleChronicleService } from "./single-chronicle.service";

@Component({
  selector: "app-single-chronicle",
  templateUrl: "./single-chronicle.component.html",
  styleUrls: ["./single-chronicle.component.scss"],
})
export class SingleChronicleComponent implements OnInit {
  familyId: number;
  chronicleId: number;
  currentChronicle: IChronicle;
  ownerDetails: IUserDetails;

  constructor(
    private route: ActivatedRoute,
    private singleChronicleService: SingleChronicleService
  ) {}

  ngOnInit() {
    this.familyId = this.route.snapshot.params["id"];
    this.chronicleId = this.route.snapshot.params["chronicleId"];

    this.singleChronicleService
      .getChronicleById(this.chronicleId)
      .subscribe((result) => {
        this.currentChronicle = result;
        this.singleChronicleService
          .getUserDetailsOfChronicleOwner(this.chronicleId)
          .subscribe((secondResult) => {
            this.ownerDetails = secondResult;
          });
      });
  }
}
