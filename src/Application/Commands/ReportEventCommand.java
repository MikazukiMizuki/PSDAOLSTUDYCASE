package Application.Commands;

import Domain.Entity.Management;

public class ReportEventCommand {
    public final Management management;

    public ReportEventCommand(Management management) {
        this.management = management;
    }

    public Management getManagement() {
        return management;
    }

    public String commandReport(String report) throws Exception {
        if (report.equals("danger") || report.equals("red")) {
            throw new Exception();
        }
        return "command ok";
    }

}
