import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ValidationMessage } from './validation-messages';

@Component({
  selector: 'app-error-message',
  templateUrl: './error-message.component.html',
  styleUrls: ['./error-message.component.css']
})
export class ErrorMessageComponent implements OnInit {
  @Input() control: FormControl;

  constructor() { }

  ngOnInit(): void { }

  get errorMessages() {
    for (const error in this.control.errors) {
      if (this.control.errors.hasOwnProperty(error) && this.control.touched) {
        console.log(this.control.errors[error]);
        return ValidationMessage.getErrorMessage(error, this.control.errors[error]);
      }
    }
    return null;
  }

}
