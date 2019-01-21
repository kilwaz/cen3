import {APIGuest} from "./model/aPIGuest";
import {FormControl, Validators} from "@angular/forms";

export class Guest implements APIGuest {
  firstName: string;
  lastName: string;
  email: string;

  civilAccepted: boolean;
  mehndiAccepted: boolean;
  receptionAccepted: boolean;

  firstNameValidator = new FormControl('', [Validators.required]);
  lastNameValidator = new FormControl('', [Validators.required]);
  emailValidator = new FormControl('', [Validators.required, Validators.email]);

  mehndiAcceptedValidator = new FormControl('', [Validators.required]);
  civilAcceptedValidator = new FormControl('', [Validators.required]);
  receptionAcceptedValidator = new FormControl('', [Validators.required]);

  constructor() {
    this.emailValidator.valueChanges.subscribe(form => {
      this.email = this.emailValidator.value;
    });
    this.lastNameValidator.valueChanges.subscribe(form => {
      this.lastName = this.lastNameValidator.value;
    });
    this.firstNameValidator.valueChanges.subscribe(form => {
      this.firstName = this.firstNameValidator.value;
    });

    this.mehndiAcceptedValidator.valueChanges.subscribe(form => {
      this.mehndiAccepted = this.mehndiAcceptedValidator.value;
    });
    this.civilAcceptedValidator.valueChanges.subscribe(form => {
      this.civilAccepted = this.civilAcceptedValidator.value;
    });
    this.receptionAcceptedValidator.valueChanges.subscribe(form => {
      this.receptionAccepted = this.receptionAcceptedValidator.value;
    });
  }

  getEmailErrorMessage(validator: FormControl) {
    return validator.hasError('required') ? 'You must enter a value' :
      validator.hasError('email') ? 'Not a valid email' : '';
  }

  getNameErrorMessage(validator: FormControl) {
    return validator.touched && validator.hasError('required') ? 'You must enter a value' : '';
  }
}
