import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import Swal from 'sweetalert2';
import { Address } from '../common/address';
import { OrderInfo } from '../common/orderInfo';
import { PaymentInfo } from '../common/payment-info';
import { AddressService } from '../service/address.service';
import { CartService } from '../service/cart.service';
import { CheckoutService } from '../service/checkout.service';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  user = new User();
  address : any;
  public grandTotal !: number;
  order : any = null;
  orderItems: any[] = [];

  orderInfo: OrderInfo = new OrderInfo();
  shippingAddress: Address = new Address();
  billingAddress: Address = new Address();

  //Initialize Stripe API
  stripe = Stripe(environment.stripePublishableKey);

  paymentInfo: PaymentInfo = new PaymentInfo();
  cardElement: any;
  displayError: any = "";

  constructor(private userService:UserService, private cartService:CartService, private addressService:AddressService, private router: Router, private checkoutService: CheckoutService) { }

  ngOnInit(): void {
      //setup Stripe payment form
      this.setupStripePaymentForm();

      this.userService.getCurrentUser()
      .subscribe((res: any)=>{
        this.user = res;
      });

      this.cartService.getOrderByUserId().subscribe(data => {
        this.order = data;
        this.orderItems = data.cartItems;
      });

      // If user has existing address, prepopulate fields
      this.addressService.getUserAddress().subscribe(data => {
        if (data !== null) {
          this.shippingAddress = data;
        }
      })
  }

  setupStripePaymentForm() {
    
    //get a handle to stripe elements
    var elements = this.stripe.elements();

    //create a card element ... and hide the zip-code field
    this.cardElement = elements.create('card', {hidePostalCode: true});

    // Add an instance of card UI component into the 'card-element' div
    this.cardElement.mount('#card-element');

    // Add event binding for the 'change' event on the card element
    this.cardElement.on('change', (event) => {
      
      //get a handle to card-errors element
      this.displayError = document.getElementById('card-errors');

      if(event.complete){
        this.displayError.textContent ="";
      } else if(event.error){
        //show validation error to customer
        this.displayError.textContent = event.error.massage;
      }
       
    })

  }

  checkout() {
    //compute payment info
    this.paymentInfo.amount = this.order.orderTotal * 100;
    this.paymentInfo.currency = "USD";

    //if valid form then
    // -create payment intent
    // - confirm card payment
    // - palce order

    if(this.displayError.textContent === ""){
      this.checkoutService.createPaymentIntent(this.paymentInfo).subscribe(
        (paymentIntentResponse) => {
          this.stripe.confirmCardPayment(paymentIntentResponse.client_secret, {
            payment_method: {
              card: this.cardElement
            }
          }, { handleActions: false})
          .then((result) =>{
            if(result.error){
              //inform the customer there was am error
              alert(`There was an error: ${result.error.message}`);
            } else {
              this.orderInfo.shippingAddress = this.shippingAddress;
              if (true) { /* TODO replace boolean w/check */      
                this.orderInfo.billingAddress = this.shippingAddress;
              } else {
                this.orderInfo.billingAddress = this.billingAddress;
              }
              console.log(this.orderInfo);
              //call REST API via the CheckoutService
              this.cartService.checkOut(this.orderInfo).subscribe(data => {
                console.log(data);
              })

              Swal.fire(
                'Success!',
                'You have successfully checked out!',
                'success'
              ).then(function(){
                window.location.reload();
              })
          
              this.router.navigate(['/']);
            }
          })
        }
      )
    }
  }
}
