import {Injectable} from "@angular/core";
import {BehaviorSubject, filter, Observable} from "rxjs";
import {NavigationEnd, Router} from "@angular/router";

@Injectable({providedIn: 'root'})
export class PathService {
  private currentPath = new BehaviorSubject<string>('');

  constructor(private router: Router) {
    this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.currentPath.next(event.urlAfterRedirects);
    });
  }

  getCurrentPath(): Observable<string> {
    return this.currentPath.asObservable();
  }
}
