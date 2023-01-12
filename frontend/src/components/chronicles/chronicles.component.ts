import { Component, Input, OnInit } from "@angular/core";
import { IChronicle } from "src/app/interfaces";
import { ChroniclesService } from "./chronicles.service";

@Component({
  selector: "app-chronicles",
  templateUrl: "./chronicles.component.html",
  styleUrls: ["./chronicles.component.scss"],
})
export class ChroniclesComponent implements OnInit {
  @Input() familyId: number;
  listOfChronicles: IChronicle[] = [];
  constructor(private chroniclesService: ChroniclesService) {}

  ngOnInit() {
    this.chroniclesService
      .getChroniclesOfFamily(this.familyId)
      .subscribe((response) => {
        this.listOfChronicles = response;
      });
  }
}
