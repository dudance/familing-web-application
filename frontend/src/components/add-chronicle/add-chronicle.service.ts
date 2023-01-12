import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IChronicle } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class AddChronicleService {
  constructor(private httpClient: HttpClient) {}

  createNewChronicle(chronicle: IChronicle) {
    return this.httpClient.post<IChronicle>(
      "http://localhost:8080/chronicle/create",
      chronicle
    );
  }
}
