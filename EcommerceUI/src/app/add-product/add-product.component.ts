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

  categories : any[] = [];

  categoryList = [
    {
      id: 1,
      value: "TV"
    },
    {
      id: 2,
      value: "Laptop"
    },
    {
      id: 3,
      value: "Phone"
    },
    {
      id: 4,
      value: "Video Game"
    }
  ]

  constructor(private _service : AdminService, private cloudinary: CloudinaryService, private _router : Router) { }

  ngOnInit(): void {
    this.cloudinary.createUploadWidget(
      {
        cloudName: 'dwnb2nqcu',
        uploadPreset: 'ysvn2muf'
      },
      (error, result) => {
        if (result && result.event === "success") {          
          this.product.productImage = result.info.url;
        } else if (error) {
          console.error('Upload failed.');
        }
      }
    ).subscribe(widget => this.widget = widget);    
  }

  addProduct(product : Product){
    this.product.categories = this.categories;
    this.product.unitPrice = +product.unitPrice;
    console.log(this.product);
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
    this._router.navigate(['/admin/inventory-management'])
  }

  openWidget(){
    if(this.widget){
      console.log('open');
      this.widget.open();
    }
  }

  onCheckboxChange(option, event) {
    if(event.target.checked) {

      let category = {
        "categoryId" : option.id,
        "categoryName" : option.value,
      }

      this.categories.push(category);
    } else {
    for(var i=0 ; i < this.categoryList.length; i++) {
      if(this.categories[i] == option.id) {
        this.categories.splice(i,1);
     }
   }
 }
 console.log(this.categories);
}
}
