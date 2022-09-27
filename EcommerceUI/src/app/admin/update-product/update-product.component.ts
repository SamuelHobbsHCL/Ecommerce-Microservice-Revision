import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/service/api.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import Swal from 'sweetalert2';
import { AdminService } from '../../service/admin.service';
import { CloudinaryService } from 'src/app/service/cloudinary.service';
import { UpdateImageDTO } from 'src/app/UpdateImageDTO';
import { trueColor } from '@cloudinary/url-gen/qualifiers/colorSpace';
@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id: string;
  private sub: any;
  widget: any;

  product = new Product();
  updateImageDTO = new UpdateImageDTO();

  categories: any[] = [];

  categoryList: any[];

  constructor(private adminService: AdminService, private cloudinary: CloudinaryService, private route: ActivatedRoute, private _router: Router, private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getCategories().subscribe((data) => {
      this.categoryList = data;
      console.log(this.categoryList);
    })

    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.apiService.getProductById(this.id).subscribe((data) => {
      this.product = data;

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
          this.apiService.updateProductImage(this.product.productId, this.updateImageDTO).subscribe(
            (data) => {

            }, (error) => {
              if (error == "OK") {
                Swal.fire(
                  'Success!',
                  'Your profile image has been updated!',
                  'success'
                ).then(function () {
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

  }

  public updateProduct(product: any) {
    console.log(product);

    if(this.categories !== null && this.categories !== undefined && this.categories.length !== 0) {
      product.categories = this.categories;
    } 
    this.adminService.updateProduct(product).subscribe(data => {
      Swal.fire(
        'Success',
        'Product has been updated',
        'success'
      )
      this._router.navigate(['/admin/inventory-management']);
    })
  }

  openWidget() {
    if (this.widget) {
      console.log('open');
      this.widget.open();
    }
  }

  onCheckboxChange(option, event) {
    if (event.target.checked) {

      let category = {
        "categoryId": option.categoryId,
        "categoryName": option.categoryName,
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
    console.log(this.categories);
  }
}
