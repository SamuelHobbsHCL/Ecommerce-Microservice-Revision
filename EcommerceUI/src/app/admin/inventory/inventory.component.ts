import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  public productList: any;

  constructor(private adminService : AdminService, private _router: Router) { }

  navigateToUsers() {
    this._router.navigate(['admin']);
  }
  navigateToTest() {
    this._router.navigate(['test'])
  }
  navigateToUpdateProduct(product : any) {
    this._router.navigate(['/updateproduct', product.productId])
  }

  ngOnInit(): void {
    this.adminService.getAllProducts()
    .subscribe(res => {
      this.productList = res;
      console.log(this.productList);
    });
  }

  public ifDeleteProduct(productId: any) {
    var answer = window.confirm("Delete product?");
    if (answer) {
      this.adminService.deleteProduct(parseInt(productId)).subscribe(data => {
        window.location.reload();
      });
    }
  }
}
