import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from '../components/header/header.component';
import { FooterComponent } from '../components/footer/footer.component';
import { LoginComponent } from '../components/login/login.component';
import { LogoutComponent } from '../components/logout/logout.component';
import { AngularMaterialModule } from './angular-material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BasicAuthHtppInterceptorService } from './service/basic-auth-interceptor.service';
import { MainComponent } from '../components/main/main.component';
import { AllFamiliesComponent } from '../components/all-families/all-families.component';
import { MyFamiliesComponent } from '../components/my-families/my-families.component';
import { InvitationsComponent } from '../components/invitations/invitations.component';
import { FamilyComponent } from '../components/family/family.component';
import { CreateFamilyComponent } from '../components/create-family/create-family.component';
import { RequestsComponent } from '../components/requests/requests.component';
import { DiscussionComponent } from '../components/discussion/discussion.component';
import { ChroniclesComponent } from '../components/chronicles/chronicles.component';
import { AddChronicleComponent } from '../components/add-chronicle/add-chronicle.component';
import { EditProfileComponent } from '../components/edit-profile/edit-profile.component';
import { MatSnackBarModule } from '@angular/material';
import { ActivatedRouteSnapshot } from '@angular/router';
import { RegisterComponent } from '../components/register/register.component';
import { NavigationComponent } from '../components/navigation/navigation.component';
import { FamilyMembersComponent } from '../components/family-members/family-members.component';
import { SingleChronicleComponent } from '../components/single-chronicle/single-chronicle.component';
import { ChatComponent } from '../components/chat/chat.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    MainComponent,
    AllFamiliesComponent,
    MyFamiliesComponent,
    InvitationsComponent,
    FamilyComponent,
    CreateFamilyComponent,
    RequestsComponent,
    DiscussionComponent,
    ChroniclesComponent,
    AddChronicleComponent,
    EditProfileComponent,
    RegisterComponent,
    NavigationComponent,
    FamilyMembersComponent,
    SingleChronicleComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    AngularMaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule
  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
