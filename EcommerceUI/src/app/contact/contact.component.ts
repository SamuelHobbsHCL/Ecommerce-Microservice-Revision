import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  sendEmail(){
    Swal.fire(
      'Success!',
      'Sucessfully sent email!',
      'success'
    ).then(function(){
      window.location.reload();
    })
  }

}
