package Application.Commands;

import Domain.Entity.User;

public class ChangeAddressCommand {
    public final User user;

    public ChangeAddressCommand(User user) {
        this.user = user;
    }

    public String changedAddressNotif() {
        return "You have successfully changed your Address" + user;
    }
}
