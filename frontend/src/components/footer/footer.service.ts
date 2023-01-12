import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { INewsletter } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class FooterService {
  constructor(private httpClient: HttpClient) {}

  inviteUser(newsletter: INewsletter) {
    const httpOptions = {
      headers: new HttpHeaders({ "Content-Type": "application/json" }),
    };

    return this.httpClient.post<INewsletter>(
      "http://localhost:8080/newsletter/add",
      newsletter,
      httpOptions
    );
  }
}
