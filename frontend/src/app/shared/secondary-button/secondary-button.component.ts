import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'secondary-button',
  templateUrl: './secondary-button.component.html',
  styleUrls: ['./secondary-button.component.css']
})
export class SecondaryButtonComponent {

  @Input()
  label: string = "Default";
  @Output()
  onClick: EventEmitter<any> = new EventEmitter<MouseEvent>();

  onClickButton(event: MouseEvent) {
    this.onClick.emit(event);
  }

}
