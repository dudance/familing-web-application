import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IUserDetails } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class RequestsService {
  constructor(private httpClient: HttpClient) {}

  getUsersWhoSentRequest(familyId: number) {
    return this.httpClient.get<IUserDetails[]>(
      "http://localhost:8080/userDetails/usersOfRequests/" + familyId
    );
  }

  declineRequest(userId: number, familyId: number) {
    return this.httpClient.delete<string>(
      "http://localhost:8080/invitation/request/delete/" +
        familyId +
        "/" +
        userId
    );
  }

  acceptRequest(userId: number, familyId: number) {
    return this.httpClient.post<string>(
      "http://localhost:8080/invitation/request/create/" +
        familyId +
        "/" +
        userId,
      []
    );
  }
}
