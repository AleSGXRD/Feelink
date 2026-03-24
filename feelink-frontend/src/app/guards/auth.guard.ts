import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { ClerkService } from 'ngx-clerk';
import { firstValueFrom } from 'rxjs';

export const authGuard: CanActivateFn = async (route, state) => {
  const clerk = inject(ClerkService);
  const session = await firstValueFrom(clerk.session$)
  if (!session) {
    // Redirect to login page or show an error message
    window.location.href = '/login';
    return false;
  }
  return true;
};
