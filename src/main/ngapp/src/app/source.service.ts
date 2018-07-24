import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Source} from "./source";
import {DataQuery} from "./data-query";

@Injectable({
  providedIn: 'root'
})
export class SourceService {

  private serviceURL = 'https://api.kilwaz.me/source';

  getSources(): Observable<Source[]> {
    let dataQuery = new DataQuery();
    dataQuery.type = DataQuery.REQUEST_ALL;

    let params = new HttpParams().set('json', JSON.stringify(dataQuery));
    return this.http.get<Source[]>(this.serviceURL, {params: params}).pipe(
      catchError(this.handleError('getSources', []))
    )
  }

  constructor(private http: HttpClient) {
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
