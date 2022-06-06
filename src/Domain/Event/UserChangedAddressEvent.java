package Domain.Event;

import Domain.Entity.User;
import Domain.ValueObject.Address;

public class UserChangedAddressEvent {
    public User user;
    public Address address;

    public UserChangedAddressEvent(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }
}
