import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';
import { ApiService } from 'src/app/service/api.service';
import { Product } from '../../common/product'

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id: string;
  private sub: any;
  curProduct: any;

  product = new Product();


  constructor(private adminService : AdminService, private route: ActivatedRoute, private _router : Router, private apiService : ApiService) { }

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
    
    console.log(this.curProduct);
    return this.curProduct;
  }

  navigateToInventory() {
    this._router.navigate(['/inventory'])
  }

  navigateToUpdateProduct() {
    this._router.navigate(['/updateproduct'])
  }

  public updateProduct(productId:any) {
    console.log(productId);
    this.adminService.updateProduct(parseInt(productId)).subscribe(data => {
      window.location.reload();
    })
  }
}
