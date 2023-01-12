import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IUserDetails } from "src/app/interfaces";

@Injectable({
  providedIn: "root",
})
export class UserDetailsService {
  constructor(private httpClient: HttpClient) {}

  updateUserDetails(userDetails: IUserDetails) {
    return this.httpClient.post<IUserDetails>(
      "http://localhost:8080/userDetails/updateUser/",
      userDetails
    );
  }

  getSurnamesOfFamily(familyId: number) {
    return this.httpClient.get<string[]>(
      "http://localhost:8080/userDetails/getSurnames/" + familyId
    );
  }

  getUserDetailsByUserId(id: number) {
    return this.httpClient.get<IUserDetails>(
      "http://localhost:8080/userDetails/user/" + id
    );
  }

  getUserDetailsByUserEmail(email: string) {
    return this.httpClient.get<IUserDetails>(
      "http://localhost:8080/userDetails/email/" + email
    );
  }

  sendImage(file: File) {
    const formData = new FormData();
    formData.append("image", file, file.name);
    console.log(formData)
    return this.httpClient.post<string>(
      "http://localhost:8080/userDetails/updateUser/updateImage/",
      formData
    );
  }
}
