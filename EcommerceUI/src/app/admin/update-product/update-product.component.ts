import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id: string;
  private sub: any;
  product: any = [];


  constructor(private adminService : AdminService, private route: ActivatedRoute, private _router : Router, private apiService : ApiService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id']; 
      this.product = this.apiService.getProductById(this.id);
    });
    this.apiService.getProductById(this.id).subscribe((data) => {
      this.product = data;

    }, (error: any) => {
      console.log("Unable to find product");
    }
    
    );
    //console.log(this.selectedProduct);
    return this.product;
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
