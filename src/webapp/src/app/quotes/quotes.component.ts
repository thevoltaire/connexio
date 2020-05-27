import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-quotes',
  templateUrl: './quotes.component.html',
  styleUrls: ['./quotes.component.css']
})
export class QuotesComponent implements OnInit {

  quotes: any[];

  cols: any[];
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.cols = [
      { field: 'id', header: 'Quote ID' },
      { field: 'quote', header: 'Quote' }];

     let httpHeaders = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Cache-Control', 'no-cache'); 
      let options = {
        headers: httpHeaders
      }; 
 this.http.get('http://localhost:8080/api/pullQuotes'
                    ,options).subscribe(
  (response:any) => {
   this.quotes = response,
   console.log(this.quotes)
 },
 (error) => console.log(error)
)

  }

  customefilter(event, field, filterMatchMode) {
    console.log(event);
    console.log(field);
    console.log(filterMatchMode);
       
    }
}


