import { Component, OnInit } from '@angular/core';
import { Product } from '../common/product';
import Swal from 'sweetalert2';
import { AdminService } from '../service/admin.service';
import { CloudinaryService } from '../service/cloudinary.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product = new Product();
  widget: any;

  constructor(private _service : AdminService, private cloudinary: CloudinaryService, private _router : Router) { }

  ngOnInit(): void {
    this.cloudinary.createUploadWidget(
      {
        cloudName: 'dwnb2nqcu',
        uploadPreset: 'ysvn2muf'
      },
      (error, result) => {
        if (!error && result && result.event === "success") {          
          this.product.productImage = result.info.url;
        } else {
          console.error('Upload failed.');
        }
      }
    ).subscribe(widget => this.widget = widget);    
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
        this.navigateToInventory();
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
  
  navigateToInventory() {
    this._router.navigate(['/inventory'])
  }

  navigateToUpdateProduct() {
    this._router.navigate(['/updateproduct'])
  }
  
  openWidget(){
    if(this.widget){
      console.log('open');
      this.widget.open();
    }
  }
}
