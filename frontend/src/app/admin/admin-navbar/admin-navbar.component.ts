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

  getIconClass(): string {
    return this.isExtended ? "pi pi-arrow-right" : "pi pi-arrow-left";
  }

  getNavBarWidth(): string {
    return this.isExtended ? "230px" : "75px";
  }

  doNothing(): void {
    console.log("clicked");
  }


}
