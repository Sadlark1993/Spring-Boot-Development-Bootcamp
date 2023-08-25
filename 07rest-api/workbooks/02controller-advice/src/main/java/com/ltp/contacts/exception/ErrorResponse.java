package com.ltp.contacts.exception;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
  private String message;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy hh:mm:ss")
  private LocalDateTime timeStamp;

  public ErrorResponse(String message) {
    this.message = message;
    this.timeStamp = LocalDateTime.now();
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTimeStamp() {
    return this.timeStamp;
  }

  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

}
