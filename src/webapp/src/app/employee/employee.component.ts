import { Component, OnInit } from '@angular/core';
import { InputForEmployee } from '../Models/InputForEmployee';
import { FormBuilder ,Validators, FormGroup, FormControl} from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  constructor(private http: HttpClient){
  }

  postResponse : InputForEmployee;
  showResponse : boolean = false;

  SignupForm:FormGroup;
  ngOnInit(){
    this.SignupForm = new FormGroup({
      'userData': new FormGroup({
          'username':new FormControl(null, [ Validators.required, Validators.minLength(5)]),
          'divisionId':new FormControl(null, [ Validators.required, Validators.pattern('^[0-9]*$')]),
          'salary':new FormControl(null, [ Validators.required, Validators.pattern('^[0-9]*$')]),
          'managerID':new FormControl(null, [ Validators.required, Validators.pattern('^[0-9]*$')])
      }),
      
      
    });
    
  }

  // name: string;
  // divisionId: Number;
  // payroleData: Number;
  // managerID: Number;
  onSubmit(){
    console.log(this.SignupForm);
    //debugger;
    

    let httpHeaders = new HttpHeaders()
    .set('Content-Type', 'application/json')
    .set('Cache-Control', 'no-cache'); 
    let options = {
      headers: httpHeaders
 }; 
    this.http.post('http://localhost:8080/api/employee', 
      {
        employeeName: this.SignupForm.value.userData.username, 
        divisionId: this.SignupForm.value.userData.divisionId, 
        payroleData:  this.SignupForm.value.userData.salary, 
        managerName: this.SignupForm.value.userData.managerID
    }
    ,options).subscribe(
      (response:any) => {
        this.postResponse = response,
        console.log(this.postResponse),
        this.showResponse  = true

      },
      (error) => console.log(error)
    )
  }

}
