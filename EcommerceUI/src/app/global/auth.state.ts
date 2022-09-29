import { OktaAuthStateService } from "@okta/okta-angular";
import { AuthState, IDToken, UserClaims } from "@okta/okta-auth-js";

export const authStateSpy = jasmine.createSpyObj<OktaAuthStateService>([],['authState$']);

export const authState: AuthState = {
    isAuthenticated: true,
    idToken: {
        idToken: 'mocktoken',
        authorizeUrl: 'authorize',
        expiresAt: 99999,
        scopes: [],
        claims: {
            sub: 'mocksub',
            name: 'Test Name'
        },
        issuer: 'mockissuer',
        clientId: 'mockclient'
    }
};