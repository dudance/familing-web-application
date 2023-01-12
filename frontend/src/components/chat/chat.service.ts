import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IUser } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class ChatService {
  constructor(private httpClient: HttpClient) {}

  getUserByEmail(email: string) {
    return this.httpClient.get<IUser>("http://localhost:8080/user/" + email);
  }
}
