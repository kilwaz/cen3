import {EncodedProgress} from "./encoded-progress";

export class Source {
  uuid: string;
  fileName: string;
  encodedFileName: string;
  name: string;
  url: string;
  encodedUrl: string;
  encodedProgress: EncodedProgress;
  sourceInfo: JSON;
}
