import { Component, OnInit } from '@angular/core';
import { InputForEmployee } from '../Models/InputForEmployee';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  searchByDivison: InputForEmployee[];
  searchByManager: InputForEmployee[];
  empStatsByManager : {name : string, value: number};
  empStatsByDivison : {name : string, value: number};

  constructor(private http: HttpClient,private route: ActivatedRoute) { }

  ngOnInit() {
  }

  findEmployeesbyDivision(intPut: string){
    console.log("Searching for the divison - "+intPut);

    let httpHeaders = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Cache-Control', 'no-cache'); 

      let options = {
        headers: httpHeaders
      }; 

    this.http.get('http://localhost:8080/api/employee/?divisionID='.concat(intPut), 
    options,
    //{params: params}
   
    ).subscribe(
      (response:any) => {
        this.searchByDivison = response,
        console.log(this.searchByDivison)
      },
      (error) => console.log(error)
    )
  }

  findEmployeesbyManager(intPut: string){
    console.log("Searching for the divison - "+intPut);

    let httpHeaders = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Cache-Control', 'no-cache'); 
      let options = {
        headers: httpHeaders
      }; 

    this.http.get('http://localhost:8080/api/employee/'.concat(intPut), 
    options,
    //{params: params}
   
    ).subscribe(
      (response:any) => {
        this.searchByManager = response,
        console.log(this.searchByManager)
      },
      (error) => console.log(error)
    )
  }

  findEmpStatsbyManager(){
    let httpHeaders = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Cache-Control', 'no-cache'); 
      let options = {
        headers: httpHeaders
      }; 

    this.http.get('http://localhost:8080/api/employee/employeeStatsByManager', 
    options
   
    ).subscribe(
      (response:any) => {
        this.empStatsByManager = response,
        console.log(this.empStatsByManager)
      },
      (error) => console.log(error)
    )
  }

  findEmpStatsbyDivison(){
    let httpHeaders = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Cache-Control', 'no-cache'); 
      let options = {
        headers: httpHeaders
      }; 

    this.http.get('http://localhost:8080/api/employee/employeeStatsByDivison', 
    options
   
    ).subscribe(
      (response:any) => {
        this.empStatsByDivison = response,
        console.log(this.empStatsByDivison)
      },
      (error) => console.log(error)
    )
  }

}
