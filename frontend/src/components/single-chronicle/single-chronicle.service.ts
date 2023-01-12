import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IChronicle, IUserDetails } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class SingleChronicleService {
  constructor(private httpClient: HttpClient) {}

  getChronicleById(chronicleId: number) {
    return this.httpClient.get<IChronicle>(
      "http://localhost:8080/chronicle/id/" + chronicleId
    );
  }

  getUserDetailsOfChronicleOwner(chronicleId: number) {
    return this.httpClient.get<IUserDetails>(
      "http://localhost:8080/userDetails/chronicle/" + chronicleId
    );
  }
}
