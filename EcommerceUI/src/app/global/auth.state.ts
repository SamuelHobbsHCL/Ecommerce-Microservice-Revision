import { OktaAuthStateService } from "@okta/okta-angular";

export const authStateSpy = jasmine.createSpyObj<OktaAuthStateService>([],['authState$']);