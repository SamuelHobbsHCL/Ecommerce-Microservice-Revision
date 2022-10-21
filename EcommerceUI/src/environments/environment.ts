// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  stripePublishableKey: "pk_test_51LfG2CDyv2YM2ORIc44V4mFeS98IC25SbMO4x3Mxjo1fzSda1hQhitfKIXBN3yhyHTAQfrHBiVaHhtwOOZuLl91Z00goFJBbcb",
  apiUrl: "http://localhost:8080",
  ecommGatewayUrl: "http://localhost:8080/ecomm",  // For accessing the ecommerce api (port 8082) via the gateway
  productGatewayUrl: "http://localhost:8080/product",   // For accessing the product api (port 8081) via the gateway
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
