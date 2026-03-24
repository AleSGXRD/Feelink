import { Injectable } from '@angular/core';
import { ClerkService } from 'ngx-clerk';
import { firstValueFrom } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private clerkService: ClerkService) {}

  async getToken(): Promise<string | null> {
    const session = await firstValueFrom(this.clerkService.session$)

    if (!session) {
      return null;
    }

    return session?.getToken();
  }
}
