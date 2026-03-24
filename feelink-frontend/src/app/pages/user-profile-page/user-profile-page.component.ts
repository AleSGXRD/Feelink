import { Component } from '@angular/core';
import { ClerkSignInComponent } from "ngx-clerk";

@Component({
  selector: 'app-user-profile-page',
  imports: [ClerkSignInComponent],
  templateUrl: './user-profile-page.component.html',
  styleUrl: './user-profile-page.component.css'
})
export class UserProfilePageComponent {

}
