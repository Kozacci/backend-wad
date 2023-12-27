import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'danger-button',
  templateUrl: './danger-button.component.html',
  styleUrls: ['./danger-button.component.css']
})
export class DangerButtonComponent {

  @Input()
  label: string = "Default";
  @Output()
  onClick: EventEmitter<any> = new EventEmitter<MouseEvent>();
  @Input()
  bordered: boolean = false;

  onClickButton(event: MouseEvent) {
    this.onClick.emit(event);
  }

}
