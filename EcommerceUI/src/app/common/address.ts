export class Address {
    street: string;
    unit?: string;
    city: string;
    state: string;
    country: string;
    zipcode: string;

    constructor(){
        this.street = "";
        this.city = "";
        this.state = "";
        this.country = "";
        this.zipcode = "";
    }
}
