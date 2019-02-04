import {APIGuest} from "./model/aPIGuest";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ValidatedRSVP} from "./validatedRSVP";

export class Guest implements APIGuest {
  firstName: string;
  lastName: string;
  email: string;

  civilAccepted: number;
  mehndiAccepted: number;
  receptionAccepted: number;

  whiteWine: boolean;
  redWine: boolean;
  roseWine: boolean;

  beer: boolean;

  vodka: boolean;
  gin: boolean;
  whiskey: boolean;
  rum: boolean;
  disaronno: boolean;

  nonAlcoholicOption: boolean;

  firstNameValidator = new FormControl('', [Validators.required]);
  lastNameValidator = new FormControl('', [Validators.required]);
  emailValidator = new FormControl('', [Validators.required, Validators.email]);

  mehndiAcceptedValidator = new FormControl('', [Validators.required]);
  civilAcceptedValidator = new FormControl('', [Validators.required]);
  receptionAcceptedValidator = new FormControl('', [Validators.required]);

  rsvpType: number;
  form: FormGroup;

  constructor(rsvpType: number) {
    this.rsvpType = rsvpType;

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

    if (this.rsvpType == ValidatedRSVP.RSVP_TYPE_ALL) {
      this.form = new FormGroup({
        firstName: this.firstNameValidator,
        lastName: this.lastNameValidator,
        email: this.emailValidator,
        mehndiAccepted: this.mehndiAcceptedValidator,
        civilAccepted: this.civilAcceptedValidator,
        receptionAccepted: this.receptionAcceptedValidator,
      });
    } else if (this.rsvpType == ValidatedRSVP.RSVP_TYPE_WEDDING_ONLY) {
      this.form = new FormGroup({
        firstName: this.firstNameValidator,
        lastName: this.lastNameValidator,
        email: this.emailValidator,
        civilAccepted: this.civilAcceptedValidator,
        receptionAccepted: this.receptionAcceptedValidator,
      });
    } else if (this.rsvpType == ValidatedRSVP.RSVP_TYPE_RECEPTION_ONLY) {
      this.form = new FormGroup({
        firstName: this.firstNameValidator,
        lastName: this.lastNameValidator,
        email: this.emailValidator,
        receptionAccepted: this.receptionAcceptedValidator,
      });
    }
  }

  getEmailErrorMessage(validator: FormControl) {
    return validator.hasError('required') ? 'You must enter a value' :
      validator.hasError('email') ? 'Not a valid email' : '';
  }

  getNameErrorMessage(validator: FormControl) {
    return validator.touched && validator.hasError('required') ? 'You must enter a value' : '';
  }

  getIsValidData() {
    return this.form.valid;
  }

  createJSON() {
    let jsonObject = {
      "firstName": this.firstName,
      "lastName": this.lastName,
      "email": this.email,
      "civilAccepted": this.returnTextAccepted(this.civilAccepted),
      "mehndiAccepted": this.returnTextAccepted(this.mehndiAccepted),
      "receptionAccepted": this.returnTextAccepted(this.receptionAccepted),
      "rsvpType": this.returnRSVPType(this.rsvpType)
    };

    return jsonObject;
  }

  // whiteWine: boolean;
  // redWine: boolean;
  // roseWine: boolean;
  //
  // beer: boolean;
  //
  // vodka: boolean;
  // gin: boolean;
  // whiskey: boolean;
  // rum: boolean;
  // disaronno: boolean;
  //
  // nonAlcoholicOption: boolean;


  private returnTextAccepted(acceptedValue) {
    if (acceptedValue == 0) {
      return "Yes";
    } else if (acceptedValue == 1) {
      return "No";
    } else if (acceptedValue == 2) {
      return "N/A";
    }
  }

  private returnCheckBox(checkboxValue) {
    if (checkboxValue) {
      return "Yes";
    } else if (!checkboxValue) {
      return "No";
    }
  }

  private returnRSVPType(rsvpValue) {
    if (rsvpValue == ValidatedRSVP.RSVP_TYPE_ALL) {
      return "ALL";
    } else if (rsvpValue == ValidatedRSVP.RSVP_TYPE_RECEPTION_ONLY) {
      return "RECEPTION_ONLY";
    } else if (rsvpValue == ValidatedRSVP.RSVP_TYPE_WEDDING_ONLY) {
      return "WEDDING_ONLY";
    }
  }
}
