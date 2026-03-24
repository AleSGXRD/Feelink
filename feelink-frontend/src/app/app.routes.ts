import { Routes } from '@angular/router';
import { catchAllRoute, ClerkAuthGuardService } from 'ngx-clerk';
import { UserProfilePageComponent } from './pages/user-profile-page/user-profile-page.component';
import { HomeComponent } from './pages/home/home.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  {
    matcher: catchAllRoute('login'),
    component: UserProfilePageComponent,
    canActivate: [ClerkAuthGuardService],
  },
  {
    path:'',
    component: HomeComponent,
    canActivate: [authGuard]
  },
];
