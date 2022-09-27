import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { throwToolbarMixedModesError } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/service/api.service';
import { CartService } from 'src/app/service/cart.service';
import Swal from 'sweetalert2';
import { Product } from '../common/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;

  public productList : Product[];
  public searchedProducts : Product[];
  isLoaded: boolean=false;
  searchStr: string = "";
  searchKey:string ="";

  public pageSize = 10;
  public currentPage = 0;
  public filteredList : Product[] = [];
  public allProductList : Product[];

  toggle: boolean = false;

  categories : any[] = [];

  categoryList: any[];

  constructor(protected api : ApiService, private cartService : CartService, private _router : Router) { }
  
  ngOnInit(): void {
    this.api.getCategories().subscribe((data) => {
      this.categoryList = data;
      console.log(this.categoryList);

      //Add all category for side bar
      const allCategory = {
        categoryId: 0,
        categoryName: "All"
      }

      this.categoryList.push(allCategory);
      this.categoryList.sort(this.sortById());
    })
    this.getProducts();
  }

  //sort by ID to make the "All" category on top
  sortById() {
    return function(a,b) {
      if(a['categoryId'] > b['categoryId'])
        return 1;
      else if(a['categoryId'] < b['categoryId'])
        return -1;

      return 0;
    }
  }

  public handlePage(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.pageIterator();
  }

  private getProducts(): void {
    console.log(this.searchStr);
    // If searchStr is not present, search all products; else, search matching products
    if (this.searchStr == undefined || this.searchStr == "") {
      this.api.getProduct()
        .subscribe(res => {
          this.searchedProducts = res;
          this.allProductList = res;
          this.pageIterator();
      });
    } else {
      // TODO add pagination
      this.api.getSearchResult(this.searchStr)
        .subscribe(res => {
          this.searchedProducts = res;
          this.pageIterator();
        });
    }    
  }

  private pageIterator() {
    const startIndex = this.currentPage * this.pageSize;
    const endIndex = (this.currentPage + 1) * this.pageSize;
    this.productList = this.searchedProducts.slice(startIndex,endIndex);   
  }

  selectedProduct: any;

  goToProductDetails(product: any) {
    this._router.navigate(['/product-details', product.productId]);
  }

  onSelect(product: any): Promise<Product>{
    this.api.getProductById(product.productId).subscribe((data) => {
      this.selectedProduct = data;
      this.isLoaded = true;
      }, (error: any) => {
      console.log("Unable to find product");
    });
    return this.selectedProduct;
  }

  addtocart(item: any){
    this.cartService.addtoCart(item, 1).subscribe(data => {
      Swal.fire(
        'Success!',
        'Product added to cart!',
        'success'
      ).then(function(){
        window.location.reload();
      })
    });   
  }

  search(searchStr: string){
    this.searchStr = searchStr;
    this.getProducts();
  }

  filter(option) {
    //set link active on click
    this.categoryList.forEach((item) => {
      if(item.categoryName === option.categoryName) {
        item.active = true;
      } else {
        item.active = false;
      }
    })

    //create new filter list if category matches option
    this.allProductList.forEach((product) => {
      product.categories.forEach((category) => {
        if(category.categoryName === option.categoryName) {
          this.filteredList.push(product);
        }
      })
    })

    this.searchedProducts = this.filteredList;
    this.productList = this.filteredList;
 
    //reset filter list
    this.filteredList = [];

    //reset product list if category is "All"
    if(option.categoryName === "All") {
      this.searchedProducts = this.allProductList;
      this.productList = this.allProductList;
    }

  }
}