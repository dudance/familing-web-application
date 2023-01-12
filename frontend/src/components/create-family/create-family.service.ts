import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IFamily } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class CreateFamilyService {
  constructor(private httpClient: HttpClient) {}

  sendImage(file: File, familyId: number) {
    const formData = new FormData();
    formData.append("image", file, file.name);
    return this.httpClient.post<string>(
      "http://localhost:8080/family/createFamily/updateImage/" + familyId,
      formData
    );
  }

  createNewFamily(family: IFamily) {
    return this.httpClient.post<IFamily>(
      "http://localhost:8080/family/createFamily/new",
      family
    );
  }
}
