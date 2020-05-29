import { Component, OnInit } from '@angular/core';
import { Movie } from '../movie';
import { MovieService } from '../movie.service';
import { AuthService } from '../auth.service';
import {Router, NavigationExtras} from '@angular/router';
import { ToasterService } from '../toaster.service';
@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {

  movies: Movie[];
  updateFlag:boolean;
  addRecordFlag:boolean;
  movie:Movie;
  constructor(private movieService: MovieService,
    private authService:AuthService,private router: Router,
              private toasterService:ToasterService) {}
  ngOnInit(){
    if(localStorage.getItem('access_token')!=null){
        this.getMovies();
    }else{
      this.router.navigate(['/login']);}

  }
  getMovies() {
    return this.movieService.getMovies()
      .subscribe(data => this.movies = data);
  }
    addMovie(data:Movie) {
    var movie = {
      "name": data.name,
      "director": data.director
    }
    return this.movieService.addMovie(data)
      .subscribe(
        (data) => {
          this.addRecordFlag = false
          this.toasterService.successMessage("Movie record added successfully");
          this.getMovies();
        });
 }
 onLogout() {
    this.authService.logout();
        this.router.navigate(['/login']);
      }

 onDelete(data:Movie) {
  return this.movieService.deleteMovie(data.id)
  .subscribe(
    (data) => {
      this.toasterService.successMessage("Movie record deleted successfully");
      this.getMovies();
    });
 }

 onEdit(data:Movie) {
   console.log(data);
  this.updateFlag = true;
  this.addRecordFlag = false;
  this.movie = data;
 }

 updateMovie(data:Movie) {
  return this.movieService.updateMovie(data)
  .subscribe(
    (data) => {
      this.updateFlag = false
      this.toasterService.successMessage("Movie record updated successfully");
      this.getMovies();
    });
 }
 addRecord() {
  this.addRecordFlag = true;
  this.updateFlag = false;
 }

}
