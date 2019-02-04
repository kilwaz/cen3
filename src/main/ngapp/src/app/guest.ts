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

  vegan: boolean;
  jain: boolean;

  firstNameValidator = new FormControl('', [Validators.required]);
  lastNameValidator = new FormControl('', [Validators.required]);
  emailValidator = new FormControl('', [Validators.required, Validators.email]);

  mehndiAcceptedValidator = new FormControl('', [Validators.required]);
  civilAcceptedValidator = new FormControl('', [Validators.required]);
  receptionAcceptedValidator = new FormControl('', [Validators.required]);

  whiteWineValidator = new FormControl('', []);
  redWineValidator = new FormControl('', []);
  roseWineValidator = new FormControl('', []);

  beerValidator = new FormControl('', []);

  vodkaValidator = new FormControl('', []);
  ginValidator = new FormControl('', []);
  whiskeyValidator = new FormControl('', []);
  rumValidator = new FormControl('', []);
  disaronnoValidator = new FormControl('', []);

  nonAlcoholicOptionValidator = new FormControl('', []);

  veganValidator = new FormControl('', []);
  jainValidator = new FormControl('', []);

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

    this.whiteWineValidator.valueChanges.subscribe(form => {
      this.whiteWine = this.whiteWineValidator.value;
    });
    this.redWineValidator.valueChanges.subscribe(form => {
      this.redWine = this.redWineValidator.value;
    });
    this.roseWineValidator.valueChanges.subscribe(form => {
      this.roseWine = this.roseWineValidator.value;
    });

    this.beerValidator.valueChanges.subscribe(form => {
      this.beer = this.beerValidator.value;
    });

    this.vodkaValidator.valueChanges.subscribe(form => {
      this.vodka = this.vodkaValidator.value;
    });
    this.ginValidator.valueChanges.subscribe(form => {
      this.gin = this.ginValidator.value;
    });
    this.whiskeyValidator.valueChanges.subscribe(form => {
      this.whiskey = this.whiskeyValidator.value;
    });
    this.rumValidator.valueChanges.subscribe(form => {
      this.rum = this.rumValidator.value;
    });
    this.disaronnoValidator.valueChanges.subscribe(form => {
      this.disaronno = this.disaronnoValidator.value;
    });

    this.nonAlcoholicOptionValidator.valueChanges.subscribe(form => {
      this.nonAlcoholicOption = this.nonAlcoholicOptionValidator.value;
    });

    this.veganValidator.valueChanges.subscribe(form => {
      this.vegan = this.veganValidator.value;
    });
    this.jainValidator.valueChanges.subscribe(form => {
      this.jain = this.jainValidator.value;
    });

    if (this.rsvpType == ValidatedRSVP.RSVP_TYPE_ALL) {
      this.form = new FormGroup({
        firstName: this.firstNameValidator,
        lastName: this.lastNameValidator,
        email: this.emailValidator,
        mehndiAccepted: this.mehndiAcceptedValidator,
        civilAccepted: this.civilAcceptedValidator,
        receptionAccepted: this.receptionAcceptedValidator,
        whiteWine: this.whiteWineValidator,
        redWine: this.redWineValidator,
        roseWine: this.roseWineValidator,
        beer: this.beerValidator,
        vodka: this.vodkaValidator,
        gin: this.ginValidator,
        whiskey: this.whiskeyValidator,
        rum: this.rumValidator,
        disaronno: this.disaronnoValidator,
        nonAlcoholicOption: this.nonAlcoholicOptionValidator,
        vegan: this.veganValidator,
        jain: this.jainValidator
      });
    } else if (this.rsvpType == ValidatedRSVP.RSVP_TYPE_WEDDING_ONLY) {
      this.form = new FormGroup({
        firstName: this.firstNameValidator,
        lastName: this.lastNameValidator,
        email: this.emailValidator,
        civilAccepted: this.civilAcceptedValidator,
        receptionAccepted: this.receptionAcceptedValidator,
        whiteWine: this.whiteWineValidator,
        redWine: this.redWineValidator,
        roseWine: this.roseWineValidator,
        beer: this.beerValidator,
        vodka: this.vodkaValidator,
        gin: this.ginValidator,
        whiskey: this.whiskeyValidator,
        rum: this.rumValidator,
        disaronno: this.disaronnoValidator,
        nonAlcoholicOption: this.nonAlcoholicOptionValidator,
        vegan: this.veganValidator,
        jain: this.jainValidator
      });
    } else if (this.rsvpType == ValidatedRSVP.RSVP_TYPE_RECEPTION_ONLY) {
      this.form = new FormGroup({
        firstName: this.firstNameValidator,
        lastName: this.lastNameValidator,
        email: this.emailValidator,
        receptionAccepted: this.receptionAcceptedValidator,
        whiteWine: this.whiteWineValidator,
        redWine: this.redWineValidator,
        roseWine: this.roseWineValidator,
        beer: this.beerValidator,
        vodka: this.vodkaValidator,
        gin: this.ginValidator,
        whiskey: this.whiskeyValidator,
        rum: this.rumValidator,
        disaronno: this.disaronnoValidator,
        nonAlcoholicOption: this.nonAlcoholicOptionValidator,
        vegan: this.veganValidator,
        jain: this.jainValidator
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
      "rsvpType": this.returnRSVPType(this.rsvpType),
      "whiteWine": this.returnCheckBox(this.whiteWine),
      "redWine": this.returnCheckBox(this.redWine),
      "roseWine": this.returnCheckBox(this.roseWine),
      "beer": this.returnCheckBox(this.beer),
      "vodka": this.returnCheckBox(this.vodka),
      "gin": this.returnCheckBox(this.gin),
      "whiskey": this.returnCheckBox(this.whiskey),
      "rum": this.returnCheckBox(this.rum),
      "disaronno": this.returnCheckBox(this.disaronno),
      "nonAlcoholicOption": this.returnCheckBox(this.nonAlcoholicOption),
      "vegan": this.returnCheckBox(this.vegan),
      "jain": this.returnCheckBox(this.jain)
    };

    return jsonObject;
  }

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
