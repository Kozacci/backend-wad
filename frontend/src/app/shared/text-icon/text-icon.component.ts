import {Component, EventEmitter, Input, Output} from '@angular/core';

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
  iconClass: string = "pi pi-prime";
  @Input()
  fontSize: string = "2em";
  @Output()
  onClick: EventEmitter<any> = new EventEmitter<MouseEvent>();
  @Input()
  isClickable: boolean = false;

  onClickButton(event: MouseEvent) {
    this.onClick.emit(event);
  }

}
