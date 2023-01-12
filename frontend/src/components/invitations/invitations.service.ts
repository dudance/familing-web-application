import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IInvitation } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class InvitationsService {
  constructor(private httpClient: HttpClient) {}

  getInvitations() {
    return this.httpClient.get<IInvitation[]>(
      "http://localhost:8080/invitation"
    );
  }

  sendFamilyRequest(invitation: IInvitation) {
    return this.httpClient.post<string>(
      "http://localhost:8080/invitation/create/",
      invitation
    );
  }

  getInvitationsOfUserByEmail(email: String) {
    return this.httpClient.get<IInvitation[]>(
      "http://localhost:8080/invitation/invitations/" + email
    );
  }

  declineInvitation(invitation: IInvitation) {
    return this.httpClient.request<IInvitation[]>(
      "DELETE",
      "http://localhost:8080/invitation/delete",
      { body: invitation }
    );
  }
}
