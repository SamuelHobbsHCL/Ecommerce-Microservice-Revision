import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/service/api.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import Swal from 'sweetalert2';
import { AdminService } from '../../service/admin.service';
import { CloudinaryService } from 'src/app/service/cloudinary.service';
import { UpdateImageDTO } from 'src/app/UpdateImageDTO';
@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id: string;
  private sub: any;
  curProduct: any;
  widget : any;

  product = new Product();
  updateImageDTO = new UpdateImageDTO();

  constructor(private adminService : AdminService, private cloudinary: CloudinaryService, private route: ActivatedRoute, private _router : Router, private apiService : ApiService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id']; 
      this.curProduct = this.apiService.getProductById(this.id);
    });
    this.apiService.getProductById(this.id).subscribe((data) => {
    this.curProduct = data;
    this.product.productId = this.curProduct.productId;
    this.product.productName = this.curProduct.productName;
    this.product.unitPrice = this.curProduct.unitPrice;
    this.product.productDescription = this.curProduct.productDescription;
    this.product.productImage = this.curProduct.productImage;
    this.product.productStock = this.curProduct.productStock;

    }, (error: any) => {
      console.log("Unable to find product");
    }
    
    );
    this.cloudinary.createUploadWidget(
      {
        cloudName: 'dwnb2nqcu',
        uploadPreset: 'ysvn2muf'
      },
      (error, result) => {
        if (!error && result && result.event === "success") {
          console.log('Done! Here is the image info: ', result.info);
          this.updateImageDTO.imageUrl = result.info.url;
          this.apiService.updateProductImage(this.product.productId ,this.updateImageDTO).subscribe(
            (data) => {
              
          }, (error) => {
            if(error == "OK") {
              Swal.fire(
                'Success!',
                'Your profile image has been updated!',
                'success'
              ).then(function(){
                window.location.reload();
              })
            } else {
              Swal.fire(
                'Error!',
                'Image upload error!',
                'error'
              )

            }
          }
          )
           
        }
      }
    ).subscribe(widget => this.widget = widget);
    
    console.log(this.curProduct);
    return this.curProduct;
  }

  public updateProduct(product:any) {
    console.log(product);
    this.adminService.updateProduct(product).subscribe(data => {
      Swal.fire(
        'Success',
        'Product has been updated',
        'success'
      )
      window.location.reload();
    })
  }

  openWidget(){
    if(this.widget){
      console.log('open');
      this.widget.open();
    }
  }
}
