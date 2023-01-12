import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { of } from "rxjs";
import { IFamily, IInvitation } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class AllFamiliesService {
  constructor(private httpClient: HttpClient) {}

  getAllFamilies() {
    return this.httpClient.get<IFamily[]>("http://localhost:8080/family");
  }

  getFamiliesBySearchedSurnames(criteria: string) {
    return this.httpClient.get<IFamily[]>(
      "http://localhost:8080/family/surnames/" + criteria
    );
  }

  getFamiliesBySearchedMember(criteria: string) {
    return this.httpClient.get<IFamily[]>(
      "http://localhost:8080/family/member/" + criteria
    );
  }

  getFamiliesByUserInvitations(invitations: IInvitation[]) {
    var body = JSON.stringify(invitations);

    const httpOptions = {
      headers: new HttpHeaders({ "Content-Type": "application/json" }),
    };

    if (invitations.length > 0) {
      return this.httpClient.post<IInvitation[]>(
        "http://localhost:8080/family/invitations/",
        body,
        httpOptions
      );
    } else {
      return of([]);
    }
  }

  getFamiliesOfCurrentUser() {
    return this.httpClient.get<IFamily[]>(
      "http://localhost:8080/family/currentUser"
    );
  }
}
