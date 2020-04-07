package io.zipcoder.tc_spring_poll_application.dtos.error;

import java.util.List;
import java.util.Map;

public class ErrorDetail {

    String title;
    int status;
    String detail;
    long timeStamp;
    String developerMessage;
    Map<String, List<ValidationError>> errors;

    public Map<String, List<ValidationError>> getErrors(){
        return this.errors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
