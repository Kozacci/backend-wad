import {Component} from '@angular/core';
import {PathService} from "../../shared/services/path.service";

@Component({
  selector: 'app-client-footer',
  templateUrl: './client-footer.component.html',
  styleUrls: ['./client-footer.component.css']
})
export class ClientFooterComponent {

  currentPath: string = '';

  constructor(
    private readonly pathService: PathService
  ) {}

  isHomepage(): boolean {
    this.pathService.getCurrentPath()
      .subscribe(
        currentPath => this.currentPath = currentPath
      );
    return this.currentPath === '/';
  }

}
