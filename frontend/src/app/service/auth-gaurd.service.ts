import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, ActivatedRoute } from '@angular/router';
import { UtilFunctions } from 'src/assets/utils/functions';
import { MyFamiliesService } from 'src/components/my-families/my-families.service';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService implements CanActivate {

  familyId: number;
  hasPermission: boolean = true;

  constructor(private router: Router,
    private authService: AuthenticationService, private utilFunctions: UtilFunctions, private familiesService: MyFamiliesService ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isUserLoggedIn()) {
      this.familyId = route.params['id'];
      if (this.familyId) {
        this.familiesService.getFamilies().subscribe((familiesOfCurrentUser) => {
          if (familiesOfCurrentUser.filter(family => family.id == this.familyId).length > 0) {
            return true;
          } else {
            this.router.navigate(['']);
            this.utilFunctions.displayErrorSnackbar('You havent got permission to visit that page', 'Close', 4000);
            return false;
          }
        })
      }
        return true;
    }
     
    this.router.navigate(['login']);
    this.utilFunctions.displayErrorSnackbar('You need to log in first to visit that page', 'Close', 4000);
    return false;

  }

}