import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IUser } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class FamilyMembersService {
  constructor(private httpClient: HttpClient) {}

  getAllUsers() {
    return this.httpClient.get<IUser[]>("http://localhost:8080/allUsers");
  }
}
