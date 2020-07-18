import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Movie } from './movie';
import {Http } from '@angular/http';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class MovieService {
  apiUrl = 'http://18.218.110.140:8080/movie';
constructor(private http: HttpClient) { }
  getMovies() {
    let token = localStorage.getItem('access_token');
   let headers = new HttpHeaders ({
        'Content-Type': 'application/json',
        'X-AUTH-TOKEN' : localStorage.getItem('access_token')
      });
    return this.http.get<Movie[]>(this.apiUrl,{ headers :headers });
  }

  addMovie(data:Movie) {
    let token = localStorage.getItem('access_token');
   let headers = new HttpHeaders ({
        'Content-Type': 'application/json',
        'X-AUTH-TOKEN' : localStorage.getItem('access_token')
      });
      console.log(token);
    console.log(data);
    return this.http.post<Movie[]>(this.apiUrl,data,{ headers :headers});
  }
  deleteMovie(id:number) {
    let token = localStorage.getItem('access_token');
   let headers = new HttpHeaders ({
        'Content-Type': 'application/json',
        'X-AUTH-TOKEN' : localStorage.getItem('access_token')
      });
    console.log(id);
    return this.http.delete(this.apiUrl + '/' + id,{ headers :headers});
  }
  updateMovie(data:Movie) {
    let token = localStorage.getItem('access_token');
    let headers = new HttpHeaders ({
        'Content-Type': 'application/json',
        'X-AUTH-TOKEN' : localStorage.getItem('access_token')
      });
    console.log(data);
    return this.http.patch(this.apiUrl + '/' + data.id, data,{ headers :headers});
  }
}
