import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'primary-button',
  templateUrl: './primary-button.component.html',
  styleUrls: ['./primary-button.component.css']
})
export class PrimaryButtonComponent {

  @Input()
  label: string = "Default";
  @Output()
  onClick: EventEmitter<any> = new EventEmitter<MouseEvent>();

  onClickButton(event: MouseEvent) {
    this.onClick.emit(event);
  }

}
