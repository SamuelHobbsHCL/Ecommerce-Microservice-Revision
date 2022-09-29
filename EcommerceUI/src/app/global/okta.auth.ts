import { OktaAuth } from "@okta/okta-auth-js";

export const oktaAuth = new OktaAuth({
    issuer: 'https://dev-06861319.okta.com/oauth2/default',
    clientId: '0oa6b7ee0wwOnJzuz5d7',
    redirectUri: window.location.origin + '/login/callback'
  });