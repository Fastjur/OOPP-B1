package server;

import org.codehaus.jackson.annotate.JsonCreator;

public class Address {
    private String street, housenumber, zipcode, city;

    /**
     * Address: constructor for class Address
     *
     * @param street      - String
     * @param housenumber - String
     * @param zipcode     - String
     * @param city        - String
     */
    public Address(String street, String housenumber, String zipcode, String city) {
        this.street = street;
        this.housenumber = housenumber;
        this.zipcode = zipcode;
        this.city = city;
    }

    public boolean contains(String s) {
        if(this.street.equals(s)) {
            return true;
        } else if (this.housenumber.equals(s)) {
            return true;
        } else if (this.zipcode.equals(s)) {
            return true;
        } else if (this.city.equals(s)) {
            return true;
        }
        return false;
    }

    @JsonCreator
    public Address() { }

    /**
     * toString: gives a textual representation of Address
     *
     * @return address - String
     */
    public String toString() {
        return this.street + " " + this.housenumber + ", " + this.zipcode + " " + this.city + "\n";
    }

    /**
     * getStreet: getter for private attribute street
     *
     * @return street - String
     */
    public String getStreet() {
        return street;
    }

    /**
     * getHousenumber: getter for private attribute housenumber
     *
     * @return housenumber - String
     */
    public String getHousenumber() {
        return housenumber;
    }

    /**
     * getZipcode: getter for private attribute zipcode
     *
     * @return zipcode - String
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * getCity: getter for private attribute city
     *
     * @return city - String
     */
    public String getCity() {
        return city;
    }

    /**
     * setStreet: setter for private attribute street
     *
     * @param street - String
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * setHousenumber: setter for private attribute housenumber
     *
     * @param housenumber - String
     */
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    /**
     * setZipcode: setter for private attribute zipcode
     *
     * @param zipcode - String
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * setCity: setter for private attribute city
     *
     * @param city - String
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * equals: checks if current object is the same as the other object
     *
     * @param other - Object
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other instanceof Address) {
            Address that = (Address) other;
            if (this.street.equals(that.getStreet()) &&
                    this.housenumber.equals(that.getHousenumber()) &&
                    this.zipcode.equals(that.getZipcode()) &&
                    this.city.equals(that.getCity())) {
                return true;
            }
        }
        return false;
    }
}
