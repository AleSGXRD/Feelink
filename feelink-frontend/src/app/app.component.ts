import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ClerkService } from 'ngx-clerk';
import { ButtonModule } from 'primeng/button';
import { environment } from './environment/environment';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ButtonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'feelink-frontend';
  constructor(private _clerk: ClerkService){
    this._clerk.__init({
      publishableKey: environment.CLERK_PUBLISHABLE_KEY,
    })
  }
}
