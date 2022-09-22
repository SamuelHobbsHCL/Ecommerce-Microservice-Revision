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

  constructor(private adminService : AdminService) { }

  ngOnInit(): void {
    this.adminService.getAllProducts()
    .subscribe(res => {
      this.productList = res;
      console.log(this.productList);
    });
  }

  public deleteProduct(productId : any) {
    console.log(productId);
    this.adminService.deleteProduct(parseInt(productId)).subscribe(data => {
      window.location.reload();
    });
  }
}
