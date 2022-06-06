package Application.EventHandlers;

import Application.Commands.ChangeAddressCommand;
import Application.Commands.ReportEventCommand;

public class ReportEventHandler {

    public String eventHandlerReport(String report) throws Exception {
        if (report.equals("danger") || report.equals("red")) {
            throw new Exception();
        }
        return "event handler ok";
    }

}
