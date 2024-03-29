import {Component} from '@angular/core';
import {PathService} from "./shared/services/path.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'water adventure';

  isAdminPath: boolean = false;
  isAuthPath: boolean = false;

  constructor(private pathService: PathService) {
    this.pathService.getCurrentPath().subscribe(path => {
      this.isAdminPath = path.startsWith('/admin');
      this.isAuthPath = path.startsWith('/autoryzacja')
    });
  }

}
