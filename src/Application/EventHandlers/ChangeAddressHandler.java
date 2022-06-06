package Application.EventHandlers;

public class ChangeAddressHandler {
    public String eventHandlerReport(String report) throws Exception {
        if (report.equals("danger") || report.equals("red")) {
            throw new Exception();
        }
        return "event handler ok";
    }
}
