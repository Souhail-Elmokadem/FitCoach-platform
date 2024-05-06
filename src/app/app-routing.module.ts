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

const routes: Routes = [
  { path : "" , component : HomeComponent},
  { path: "auth/login" ,component : LoginComponent,canActivate : [AuthentificationGuard]},
  { path: "auth/register" ,component : RegisterComponent,canActivate : [AuthentificationGuard]},
  { path: "explorer" ,component : ExploreComponent},
  {
    path: "service",
    redirectTo: "service/home",
    pathMatch: "full"
  },
  {
    path: "service",component : DashboardComponent,
    children: [
      { path: "home", component: DashboardHomeComponent },
      { path: "members", component:CoachsComponent },
      { path: "profile" , component: ProfileComponent},
      { path: "program" , component: ProgrammeComponent},
      { path: "program/new" , component: ProgramCreateComponent},

    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
