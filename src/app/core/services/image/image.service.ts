import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http:HttpClient) { }
  
  getImage(imageUrl: string): Observable<string> {
    return this.http.get(imageUrl, { responseType: 'blob' }).pipe(
      map(blob => {
        return URL.createObjectURL(blob);
      })
    );
  }
}
