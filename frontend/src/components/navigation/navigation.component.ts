import { Component, Input, OnInit } from "@angular/core";

@Component({
  selector: "app-navigation",
  templateUrl: "./navigation.component.html",
  styleUrls: ["./navigation.component.scss"],
})
export class NavigationComponent implements OnInit {
  @Input() familyId: number;
  @Input() currentUserId: number;
  @Input() familyOwnerId: number;
  @Input() option: string;

  ngOnInit() {}
}
