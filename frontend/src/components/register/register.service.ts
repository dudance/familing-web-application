import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IRegister } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class RegisterService {
  constructor(private httpClient: HttpClient) {}

  registerUser(requestBody: IRegister) {
    const httpOptions = {
      headers: new HttpHeaders({ "Content-Type": "application/json" }),
    };
    return this.httpClient.post<IRegister>(
      "http://localhost:8080/register",
      requestBody,
      httpOptions
    );
  }
}
