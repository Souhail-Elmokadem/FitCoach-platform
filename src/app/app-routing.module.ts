import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './core/auth/login/login.component';
import { AuthentificationGuard } from './core/gards/authentification.guard';
import { RegisterComponent } from './core/auth/register/register.component';
import { ExploreComponent } from './Shared/components/explore/explore.component';
import { DashboardComponent } from './Shared/components/dashboard/dashboard.component';
import { DashboardHomeComponent } from './Shared/components/dashboard/dashboard-home/dashboard-home.component';
import { ProfileComponent } from './Shared/components/dashboard/profile/profile.component';
import { ProgrammeComponent } from './Shared/components/dashboard/programme/programme.component';
import { ProgramCreateComponent } from './Shared/components/dashboard/program-create/program-create.component';
import { CoachsComponent } from './Shared/components/dashboard/coachs/coachs.component';
import { ChatComponent } from './Shared/components/dashboard/chat/chat.component';
import { DashboardClientComponent } from './Shared/components/dashboard-client/dashboard-client.component';
import { ProgramComponent } from './Shared/components/dashboard-client/program/program.component';
import { BillingComponent } from './Shared/components/dashboard-client/billing/billing.component';
import { ChatClientComponent } from './Shared/components/dashboard-client/chat-client/chat-client.component';
import { authcoachGuard } from './core/gards/authorization-coach/authcoach.guard';
import { authclientguard } from './core/gards/authorization-client/authclient.guard';
import { CoachPageComponent } from './Shared/components/coach-page/coach-page.component';

const routes: Routes = [
  { path : "" , component : HomeComponent},
  { path: "auth/login" ,component : LoginComponent,canActivate : [AuthentificationGuard]},
  { path: "auth/register" ,component : RegisterComponent,canActivate : [AuthentificationGuard]},
  { path: "explorer" ,component : ExploreComponent ,canActivate:[authclientguard]},
  { path: "explorer/:id/:name",component:CoachPageComponent ,canActivate:[authclientguard]},
  {
    path: "coach/service",
    redirectTo: "coach/service/home",
    pathMatch: "full"
  },
  {
    path: "coach/service",component : DashboardComponent,canActivate:[authcoachGuard],
    children: [
      { path: "home", component: DashboardHomeComponent },
      { path: "members", component: CoachsComponent },
      { path: "profile" , component: ProfileComponent},
      { path: "program" , component: ProgrammeComponent},
      { path: "program/new" , component: ProgramCreateComponent},
      { path: "chat" , component: ChatComponent}
    ]
  },

  {
    path: "client/service",
    redirectTo: "client/service/home",
    pathMatch: "full"
  },
  {
    path: "client/service",component : DashboardClientComponent,canActivate:[authclientguard],
    children: [
      { path: "home", component: DashboardHomeComponent },
      { path: "program", component: ProgramComponent },
      { path: "billing" , component:BillingComponent},
      {path: "chat",component:ChatClientComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }



