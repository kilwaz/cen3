import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpParams, HttpRequest} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable()
export class UploadService {

  constructor(private http: HttpClient) {
  }

  uploadFile(url: string, file: File): Observable<HttpEvent<any>> {
    let formData = new FormData();
    formData.append('upload', file);

    let params = new HttpParams();

    const options = {
      params: params,
      reportProgress: true,
    };

    const req = new HttpRequest('POST', url, formData, options);
    return this.http.request(req);
  }
}
