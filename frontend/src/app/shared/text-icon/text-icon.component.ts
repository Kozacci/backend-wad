import {Component, Input} from '@angular/core';

@Component({
  selector: 'text-icon',
  templateUrl: './text-icon.component.html',
  styleUrls: ['./text-icon.component.css']
})
export class TextIconComponent {

  @Input()
  textVisible: boolean = true;
  @Input()
  text: string = "default";
  @Input()
  iconClass: string = "pi-prime";
  @Input()
  fontSize: string = "2em";
}
