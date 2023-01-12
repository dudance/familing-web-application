import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IFamily } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class MyFamiliesService {
  constructor(private httpClient: HttpClient) {}

  getFamilies() {
    return this.httpClient.get<IFamily[]>(
      "http://localhost:8080/family/currentUser"
    );
  }
}
