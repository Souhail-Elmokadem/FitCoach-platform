import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './core/auth/login/login.component';
import { RegisterComponent } from './core/auth/register/register.component';
import { NavbarComponent } from './core/navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ExploreComponent } from './Shared/components/explore/explore.component';
import { AppHttpInterceptor } from './core/interceptors/http.interceptor';
import { DashboardComponent } from './Shared/components/dashboard/dashboard.component';
import { DashboardHomeComponent } from './Shared/components/dashboard/dashboard-home/dashboard-home.component';
import { CustomDatePipe } from './Shared/pipes/pipe/custom-date.pipe';
import { ProfileComponent } from './Shared/components/dashboard/profile/profile.component';
import { ProgrammeComponent } from './Shared/components/dashboard/programme/programme.component';
import { ProgramCreateComponent } from './Shared/components/dashboard/program-create/program-create.component';
import { CoachsComponent } from './Shared/components/dashboard/coachs/coachs.component';
import { ChatComponent } from './Shared/components/dashboard/chat/chat.component';
import { DashboardClientComponent } from './Shared/components/dashboard-client/dashboard-client.component';
import { DashboardClientHomeComponent } from './Shared/components/dashboard-client/dashboard-client-home/dashboard-client-home.component';
import { BillingComponent } from './Shared/components/dashboard-client/billing/billing.component';
import { ChatClientComponent } from './Shared/components/dashboard-client/chat-client/chat-client.component';
import { UserMenuComponent } from './core/navbar/user-menu/user-menu.component';
import { AuthentificationGuard } from './core/gards/authentification.guard';
import { CoachPageComponent } from './Shared/components/coach-page/coach-page.component';
import { UpdateComponent } from './Shared/components/dashboard/programme/update/update.component';
import { ProgramComponent } from './Shared/components/dashboard-client/program/program.component';
import { ProfileClientComponent } from './Shared/components/dashboard-client/profile-client/profile-client.component';
import { EditProfileClientComponent } from './Shared/components/dashboard-client/profile-client/edit-profile-client/edit-profile-client.component';
import { EditProfileCoachComponent } from './Shared/components/dashboard/profile/edit-profile-coach/edit-profile-coach.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    ExploreComponent,
    DashboardComponent,
    CoachsComponent,
    DashboardHomeComponent,
    CustomDatePipe,
    ProfileComponent,
    ProgrammeComponent,
    ProgramCreateComponent,
    ProgramComponent,
    ChatComponent,
    DashboardClientComponent,
    DashboardClientHomeComponent,
    BillingComponent,
    ChatClientComponent,
    UserMenuComponent,
    CoachPageComponent,
    UpdateComponent,
    ProfileClientComponent,
    EditProfileClientComponent,
    EditProfileCoachComponent,
    
    
    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [CustomDatePipe],
  providers: [AuthentificationGuard,{
    provide: HTTP_INTERCEPTORS, // Use the HTTP_INTERCEPTORS token
    useClass: AppHttpInterceptor, // Use the appHttpInterceptor class
    multi : true // Specify that the interceptor is multi-provider

  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
