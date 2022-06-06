package Domain.Event;

import Domain.Entity.Management;

public class ReportEvent {
    public Management management;

    public ReportEvent(Management management) {
        this.management = management;
    }

    public Management getManagement() {
        return management;
    }

}
