import { RateCard } from './../domain/rateCard';
import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable()
export class DataService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private API_URL = environment.baseUrl
  private ADD_RATE_CARD = this.API_URL + '/ratecard/add';
  private DELETE_RATE_CARD = this.API_URL + '/ratecard/delete';
  private DELETE_ALL_RATE_CARDS = this.API_URL + '/ratecards/clear';
  private IMPORT_FROM_TEXT = this.API_URL + '/ratecards/import';
  private EXPORT_AS_TEXT = this.API_URL + '/ratecards/csv';
  private IMPORT_AS_FILE = this.API_URL + '/ratecards/importFile';

  constructor(
    private http: HttpClient) {
  }

  getRateCards(): Observable<RateCard[]> {
    const url = `${this.API_URL}/ratecards`;
    return this.http.get<RateCard[]>(url);
  }

  getRateCardsAsCSV(): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return this.http.get(this.EXPORT_AS_TEXT, { headers, responseType: 'text'});
  }

  importFromText(text : string) : Observable<any> {
    return this.http.post<string>(this.IMPORT_FROM_TEXT, text, this.httpOptions).pipe(
      catchError(this.handleError<any>('importFromText'))
    );
  }

  addRateCard(data): Observable<RateCard> {
    return this.http.post<RateCard>(this.ADD_RATE_CARD, data, this.httpOptions).pipe(
      catchError(this.handleError<RateCard>('addRateCard'))
    );
  }

  deleteRateCard(id) {
    const url = `${this.DELETE_RATE_CARD}/${id}`;
    return this.http.delete(url, this.httpOptions).pipe(
      catchError(this.handleError<RateCard>('deleteRateCard'))
    );
  }

  deleteAllRateCards() {
    return this.http.post(this.DELETE_ALL_RATE_CARDS, this.httpOptions).pipe(
      catchError(this.handleError<any>('deleteAllRateCard'))
    );
  }

  public upload(formData) {
    return this.http.post<any>(this.IMPORT_AS_FILE, formData, {
      reportProgress: true,
      observe: 'events'
    });
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
