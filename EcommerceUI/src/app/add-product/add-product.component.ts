import { Component, OnInit } from '@angular/core';
import { Product } from '../common/product';
import Swal from 'sweetalert2';
import { AdminService } from '../service/admin.service';
import { CloudinaryService } from '../service/cloudinary.service';
import { Router } from '@angular/router';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  product = new Product();
  widget: any;

  categories : any[] = [];

  categoryList : any[];

  constructor(private apiService : ApiService, private _service : AdminService, private cloudinary: CloudinaryService, private _router : Router) { }

  ngOnInit(): void {
    this.apiService.getCategories().subscribe((data) => {
      this.categoryList = data;
      console.log("Connecting to category listings....");
    })

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
    console.log("Adding a new product.....");
    this._service.addProduct(this.product).subscribe(
      data => {
        console.log("Product found!");
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
        "categoryId" : option.categoryId,
        "categoryName" : option.categoryName,
      }

      this.categories.push(category);
    } else {
      this.categories.forEach((category) => {
        if(category.categoryId === option.categoryId) {
          let index = this.categories.indexOf(category);
          this.categories.splice(index,1)
        }
      })
    }
    console.log("Categories changed");
  }
}
