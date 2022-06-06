package Factory;

import Domain.ValueObject.Address;

public class AddressFactory {
    public Address createAddress(String street, String city, String zipCode) {
        return new Address(street, city, zipCode);
    }
}