package Application.CommandHandlers;

import java.sql.Date;

public class ChangeAddressCommandHandler {
    private final Date eventDate;

    public ChangeAddressCommandHandler(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String handlerReport(String report) throws Exception {
        if (report.equals("danger") || report.equals("red")) {
            throw new Exception();
        }
        return "handler ok";
    }

}
