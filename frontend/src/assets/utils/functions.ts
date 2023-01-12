import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material";
@Injectable({
  providedIn: "root",
})
export class UtilFunctions {
  constructor(private snackBar: MatSnackBar) {}

  displayErrorSnackbar(content, action, duration) {
    let sb = this.snackBar.open(content, action, {
      duration: duration,
      panelClass: ["red-snackbar"],
    });
    sb.onAction().subscribe(() => {
      sb.dismiss();
    });
  }

  displaySuccessSnackbar(content, action, duration) {
    let sb = this.snackBar.open(content, action, {
      duration: duration,
      panelClass: ["green-snackbar"],
    });
    sb.onAction().subscribe(() => {
      sb.dismiss();
    });
  }
}
