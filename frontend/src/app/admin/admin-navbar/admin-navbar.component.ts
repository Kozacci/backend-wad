import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent {

  isExtended: boolean = true;

  changeIsExtended() {
    this.isExtended = !this.isExtended;
  }


}
