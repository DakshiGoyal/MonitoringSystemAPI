/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

public class MyException extends Exception{

  private static final long serialVersionUID = 1L;
  private final String exceptionMessage;
  public String getExceptionMessage() {
    return exceptionMessage;
  }
  public MyException(String message)
  {
    this.exceptionMessage=message;
  }
}
