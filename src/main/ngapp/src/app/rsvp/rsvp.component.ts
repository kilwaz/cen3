import {Component, OnInit} from '@angular/core';
import {ValidatedRSVP} from "../validatedRSVP";
import {SessionService} from "../session.service";
import {NestedTreeControl} from "@angular/cdk/tree";
import {PublicService} from "..";
import {RSVP} from "../RSVP";

export class FileNode {
  children: FileNode[];
  filename: string;
  type: any;
}

@Component({
  selector: 'app-rsvp',
  templateUrl: './rsvp.component.html',
  styleUrls: ['./rsvp.component.css'],
  providers: [PublicService]
})
export class RsvpComponent implements OnInit {

  rsvp = new RSVP();
  submitted = false;
  numbers: number[] = [0, 1, 2, 3, 4, 5, 6];
  rsvpYesNo: string[] = ["Yes, I can attend", "No, I am unable to attend", "Non Applicable"];

  nestedTreeControl = null;

  validatedRSVP: ValidatedRSVP = new ValidatedRSVP();
  RSVP_TYPE_ALL: number = ValidatedRSVP.RSVP_TYPE_ALL;

  data = null;

  TREE_DATA = JSON.stringify({
    Applications: {
      Calendar: 'app',
      Chrome: 'app',
      Webstorm: 'app'
    },
    Documents: {
      angular: {
        src: {
          compiler: 'ts',
          core: 'ts'
        }
      },
      material2: {
        src: {
          button: 'ts',
          checkbox: 'ts',
          input: 'ts'
        }
      }
    },
    Downloads: {
      October: 'pdf',
      November: 'pdf',
      Tutorial: 'html'
    },
    Pictures: {
      'Photo Booth Library': {
        Contents: 'dir',
        Pictures: 'dir'
      },
      Sun: 'png',
      Woods: 'jpg'
    }
  });

  private _getChildren = (node: FileNode) => node.children;

  constructor(sessionService: SessionService, private publicService: PublicService) {
    this.validatedRSVP = sessionService.validatedRSVP;
    this.nestedTreeControl = new NestedTreeControl<FileNode>(this._getChildren);

    const dataObject = JSON.parse(this.TREE_DATA);

    this.data = this.buildFileTree(dataObject, 0);
  }

  ngOnInit() {
  }

  selectedCount(count: number) {
    this.rsvp.createPeople(count);
  }

  buildFileTree(obj: { [key: string]: any }, level: number): FileNode[] {
    return Object.keys(obj).reduce<FileNode[]>((accumulator, key) => {
      const value = obj[key];
      const node = new FileNode();
      node.filename = key;

      if (value != null) {
        if (typeof value === 'object') {
          node.children = this.buildFileTree(value, level + 1);
        } else {
          node.type = value;
        }
      }

      return accumulator.concat(node);
    }, []);
  }

  submitForm() {
    this.publicService.postRSVP(JSON.stringify(this.rsvp)).subscribe(response => {
      this.submitted = true;
    });
  }

  // TODO: Remove this when we're done
  get diagnostic() {
    return JSON.stringify(this.rsvp);
  }
}
