export class TopLink {
  private readonly _link: string;
  private readonly _text: string;
  private readonly _picture: string;

  constructor(text: string, link: string, picture: string) {
    this._link = link;
    this._text = text;
    this._picture = picture;
  }

  get link(): string {
    return this._link;
  }

  get picture(): string {
    return this._picture;
  }

  get text(): string {
    return this._text;
  }
}
