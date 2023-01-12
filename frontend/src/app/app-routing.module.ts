import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainComponent } from '../components/main/main.component';
import { LoginComponent } from '../components/login/login.component';
import { LogoutComponent } from '../components/logout/logout.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import { AllFamiliesComponent } from 'src/components/all-families/all-families.component';
import { InvitationsComponent } from 'src/components/invitations/invitations.component';
import { EditProfileComponent } from 'src/components/edit-profile/edit-profile.component';
import { RegisterComponent } from 'src/components/register/register.component';
import { MyFamiliesComponent } from 'src/components/my-families/my-families.component';
import { CreateFamilyComponent } from 'src/components/create-family/create-family.component';
import { FamilyComponent } from 'src/components/family/family.component';
import { AddChronicleComponent } from 'src/components/add-chronicle/add-chronicle.component';
import { SingleChronicleComponent } from 'src/components/single-chronicle/single-chronicle.component';
import { ChatComponent } from 'src/components/chat/chat.component';

const routes: Routes = [
  { path: '',redirectTo: 'main', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate:[AuthGaurdService] },
  { path: 'main', component: MainComponent },
  { path: 'all-families', component: AllFamiliesComponent, canActivate:[AuthGaurdService]  },
  { path: 'invitations', component: InvitationsComponent, canActivate:[AuthGaurdService] },
  { path: 'edit-profile', component: EditProfileComponent, canActivate:[AuthGaurdService] },
  { path: 'register', component: RegisterComponent},
  { path: 'my-families', component: MyFamiliesComponent, canActivate:[AuthGaurdService] },
  { path: 'my-families/id/:id', component: FamilyComponent, canActivate:[AuthGaurdService] },
  { path: 'my-families/id/:id/:navigation', component: FamilyComponent, canActivate:[AuthGaurdService] },
  { path: 'create-family', component: CreateFamilyComponent, canActivate:[AuthGaurdService] },
  { path: 'my-families/id/:id/chronicles/add', component: AddChronicleComponent, canActivate:[AuthGaurdService] },
  { path: 'my-families/id/:id/chronicles/:chronicleId', component: SingleChronicleComponent, canActivate:[AuthGaurdService] },
  { path: 'chat/:user', component: ChatComponent, canActivate:[AuthGaurdService] },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
