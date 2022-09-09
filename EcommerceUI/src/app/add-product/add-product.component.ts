import { Component, OnInit } from '@angular/core';
import { ProductDetailsComponent } from '../product-details/product-details.component';
import { Product } from '../common/product';
import Swal from 'sweetalert2';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product = new Product();

  constructor(private _service : AdminService) { }

  ngOnInit(): void {
  }

  addProduct(product : Product){
    this._service.addProduct(this.product).subscribe(
      data => {
        console.log(product);
        console.log("Response received!");
       
        Swal.fire(
          'Completed Addition',
          'Product Added!',
          'success'
        )
      },
      error => {
        console.log("Error!");
        Swal.fire(
          'Failure!',
          'Error! Please check input and try again',
          'error'
        ).then(() =>{
        
        })
      }
    )

  }
}
