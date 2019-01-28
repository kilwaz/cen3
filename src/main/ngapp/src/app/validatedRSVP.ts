export class ValidatedRSVP {
  private _validated: boolean = true;
  private _rsvpType: number = 1;

  public static RSVP_TYPE_ALL = 1;
  public static RSVP_TYPE_WEDDING_ONLY = 2;
  public static RSVP_TYPE_RECEPTION_ONLY = 3;

  constructor() {
  }

  get validated(): boolean {
    return this._validated;
  }

  set validated(value: boolean) {
    this._validated = value;
  }

  get rsvpType(): number {
    return this._rsvpType;
  }

  set rsvpType(value: number) {
    this._rsvpType = value;
  }
}
