import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IChronicle, IUserDetails } from "src/app/interfaces";


@Injectable({
  providedIn: "root",
})
export class ChroniclesService {
  constructor(private httpClient: HttpClient) {}

  getChroniclesOfFamily(familyId: number) {
    return this.httpClient.get<IChronicle[]>(
      "http://localhost:8080/chronicle/family/" + familyId
    );
  }

  getUserDetailsOfAllFamilyChronicles(familyId: number) {
    return this.httpClient.get<IUserDetails[]>(
      "http://localhost:8080/userDetails/chronicles/" + familyId
    );
  }
}
