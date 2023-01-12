import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IFamily, IInvitation, IUser } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class FamilyService {
  constructor(private httpClient: HttpClient) {}

  getFamilyById(familyId: number) {
    return this.httpClient.get<IFamily>(
      "http://localhost:8080/family/id/" + familyId
    );
  }

  getMembersOfFamily(familyId: number) {
    return this.httpClient.get<IUser[]>(
      "http://localhost:8080/getMembers/" + familyId
    );
  }

  getCurrentUserIdByEmail(email: string) {
    return this.httpClient.get<number>(
      "http://localhost:8080/currentUserId/" + email
    );
  }

  inviteUser(invitationBody: IInvitation) {
    const httpOptions = {
      headers: new HttpHeaders({ "Content-Type": "application/json" }),
    };

    return this.httpClient.post<string>(
      "http://localhost:8080/invitation/create",
      invitationBody,
      httpOptions
    );
  }
}
