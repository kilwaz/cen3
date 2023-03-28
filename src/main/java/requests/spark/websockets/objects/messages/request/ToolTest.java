package requests.spark.websockets.objects.messages.request;

import log.AppLogger;
import org.apache.logging.log4j.Logger;
import org.joda.time.*;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import requests.spark.websockets.objects.Message;
import requests.spark.websockets.objects.MessageType;
import requests.spark.websockets.objects.messages.dataobjects.ToolTestData;
import requests.spark.websockets.objects.messages.mapping.WebSocketDataClass;

@MessageType("ToolTest")
@WebSocketDataClass(ToolTestData.class)
public class ToolTest extends Message {
    private static Logger log = AppLogger.logger();

    public void process() {
        ToolTestData toolTestData = (ToolTestData) this.getWebSocketData();

        DateTimeZone LONDON = DateTimeZone.forID("Europe/London");
        DateTime start = new DateTime(toolTestData.getStartYear(), toolTestData.getStartMonth(), toolTestData.getStartDay(), 0, 0, 0, LONDON);
        DateTime end = new DateTime(toolTestData.getAgeOnYear(), toolTestData.getAgeOnMonth(), toolTestData.getAgeOnDay(), 0, 0, 0, LONDON);

        Period period = new Period(start.toLocalDate(), end.toLocalDate(), PeriodType.yearMonthDay());
        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .printZeroAlways().minimumPrintedDigits(1)
                .appendYears().appendSuffix(" Years ").appendMonths().appendSuffix(" Months ").appendDays().appendSuffix(" Days")
                .toFormatter();
        String periodStr = formatter.print(period);

        toolTestData.setAgeDays(period.getDays());
        toolTestData.setAgeMonths(period.getMonths());
        toolTestData.setAgeYears(period.getYears());
    }
}

