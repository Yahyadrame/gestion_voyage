import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CandidatureService {
  private apiUrl = 'http://localhost:8080/api'; // Remplacez par l'URL de votre backend

  constructor(private http: HttpClient) {}

  // Méthode pour soumettre une candidature d'ancien candidat
  soumettreAncienCandidature(formData: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/candidature/ancien`, formData);
  }

  // Méthode pour soumettre une candidature de nouveau candidat
  soumettreNouveauCandidature(formData: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/candidature/nouveau`, formData);
  }
}