import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data, uniqueSort } from 'jquery';
import { Observable } from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { UserReviewDto } from '../common/UserReviewDto';
import { Product }from '../common/product';
import { userReview } from '../common/userReview';
import { UpdateImageDTO } from '../UpdateImageDTO';
import { User } from '../user';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );

  PATH_OF_API = environment.apiUrl;

  product: Product | undefined;
  products: Product[] | undefined;
  reviews : userReview[] | undefined;
  constructor(private http : HttpClient) { }

  public getProduct(){
    return this.http.get<any>(this.PATH_OF_API + "/api/products")
    .pipe(map((res:any)=>{
      return res;
    }))
  }
  
  public getProductById(id: string): Observable<Product>{
    return this.http.get<Product>(this.PATH_OF_API + "/api/product/" + id).pipe(map((data: Product) => this.product = {
      productId: (data as any).productId,
      productName: (data as any).productName,
      unitPrice: (data as any).unitPrice,
      productStock: (data as any).productStock,
      stockThreshold: (data as any).stockThreshold,
      productImage: (data as any).productImage,
      productDescription: (data as any).productDescription,
      categories: (data as any).categories
      }),
      catchError(error => this.throwError(error))
    )
  }
  //retrieve reviews for product based on id
  public getProductReviews(id : string): Observable<any>{
    return this.http.get<any>(this.PATH_OF_API + "/api/review/" + id)
    .pipe(map((res:any)=>{
      return res;
    }));
  }

  public addUserReview(userReviewDto:UserReviewDto){
    return this.http.post<any>(this.PATH_OF_API + "/api/review", userReviewDto);
  }

  public getReviewAverage(id: string): Observable<number> {
    return this.http.get<number>(this.PATH_OF_API+"/api/review/average/"+id);
  }

  // Send page parameters to backend - allow it to handle pagination
  public getSearchResultPages(searchStr: string, index: string, count: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.PATH_OF_API + "/api/product/search/page", { params: {searchStr, index, count} });
  }

  // Only send search string - allow frontend to handle pagination
  public getSearchResult(searchStr: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.PATH_OF_API + "/api/product/search", { params: {searchStr} });
  }

  public updateProductImage(productId : string, updateImageDTO : UpdateImageDTO) : Observable<any>{
    console.log("updating product image");
    return this.http.put<any>(this.PATH_OF_API + "/api/product/update-image/" + productId, updateImageDTO);
  }

  public getCategories(): Observable<any> {
    return this.http.get<any>(this.PATH_OF_API + "/api/product/categories");
  }

  throwError(error: any) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }

}
