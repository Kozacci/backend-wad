import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../shared/services/auth/auth.service";

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent {

  isExtended: boolean = false;
  extendHideIcon: string = 'pi pi-arrow-left';

  constructor(private router: Router,
              protected authService: AuthService) {
  }

  changeIsExtended() {
    this.isExtended = !this.isExtended;
    this.extendHideIcon = this.isExtended ? "pi pi-arrow-right" : "pi pi-arrow-left";
  }

  getNavBarWidth(): string {
    return this.isExtended ? "230px" : "75px";
  }

  navigateToQuestions(): void {
    this.router.navigate(['/admin/questions']);
  }

  navigateToCourses(): void {
    this.router.navigate(['/admin/courses']);
  }

  navigateToEvents(): void {
    this.router.navigate(['/admin/events']);
  }

  navigateToPassing(): void {
    this.router.navigate(['/admin/passing'])
  }

  navigateToAccesses(): void {
    this.router.navigate(['/admin/accesses'])
  }

  navigateToHomePage(): void {
    this.router.navigate(['']);
  }
  // navigateToCalendar(): void {
  //   this.router.navigate(['/admin/calendar'])
  // }

}
