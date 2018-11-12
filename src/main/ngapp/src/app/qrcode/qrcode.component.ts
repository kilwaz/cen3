import {Component, OnInit} from '@angular/core';
import {PrivateService, QRCode} from "..";

@Component({
  selector: 'app-qrcode',
  templateUrl: './qrcode.component.html',
  styleUrls: ['./qrcode.component.css'],
  providers: [PrivateService]
})
export class QrcodeComponent implements OnInit {
  qrCode: QRCode;
  qrText: string = "HELLO";

  constructor(private privateService: PrivateService) {
  }

  ngOnInit() {
    this.getQRCode();
  }

  getQRCode(): void {
    this.privateService.getQRCode(this.qrText).subscribe(qrCode => this.qrCode = qrCode);
  }

  createQRCode(): void {
    this.getQRCode();
  }
}
