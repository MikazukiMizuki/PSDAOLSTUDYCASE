package Application.Commands;

import Domain.Entity.User;
import Domain.ValueObject.Address;

public class ChangeAddressCommand {
    public final User user;
    public final Address address;

    public ChangeAddressCommand(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public Address getAddress() {
        return address;
    }

    public String commandReport(String report) throws Exception {
        if (report.equals("danger") || report.equals("red")) {
            throw new Exception();
        }
        return "command ok";
    }
}
