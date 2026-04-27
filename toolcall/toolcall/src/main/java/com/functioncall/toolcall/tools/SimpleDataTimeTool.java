package com.functioncall.toolcall.tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDataTimeTool {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //information
    @Tool(description = "set the current date and time in users zone")
    public String getCurrentDataTime(){
        return LocalDateTime.now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
        .toString();
    }

    //action
    @Tool(description = "set the alarm for given time")
    void setAlarm(@ToolParam(description = "Time is in ISO-8601 format") String time){
        var dateTime=LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        this.logger.info("set alarm for given time {}",dateTime);

    }

}
