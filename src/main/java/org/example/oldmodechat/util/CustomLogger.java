package org.example.oldmodechat.util;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.*;

public class CustomLogger {

    private final Logger logger;
    private final String base_path;

    public CustomLogger(String name, String base_path) throws FileNotFoundException {
        this.logger = Logger.getLogger(name);
        this.base_path=base_path;

    }

    public static StreamHandler StreamHandler(Level level,String initialPath) throws FileNotFoundException {
        return new StreamHandler(new FileOutputStream(String.format("%s-%s.txt",initialPath,level.getName())),
                new Formatter() {
            @Override
            public String format(LogRecord record) {
                if(record.getLevel().equals(level)){
                    return String.format("%s--%s -> %s",LocalDateTime.now(),record.getLevel(),record.getMessage());
                }else{
                    return null;
                }
            }
        });


    }

    /**
     * One Method to use log with validation
     *
     * @param logger object logger
     * @param level level to log
     * @param msg message to log
     * @return True if u write log and False if not write
     */
    public static boolean Use_Log(Logger logger,Level level,String msg){
        if(logger==null){
            return false;
        }else {
            logger.log(level,msg);
            return true;
        }
    }


    public String getBase_path() {
        return base_path;
    }

    public Logger getLogger() {
        return logger;
    }
}
