package com.ltp.contacts.exception;

/* Controller Advice (runtime exception)
 * because its an unchecked runtime exception, there is no need to catch it. It will be thrown as the application runs.
 */
public class ContactNotFoundException extends RuntimeException {
  public ContactNotFoundException(String id) {
    super("The id " + id + " does not exist in our records.");
  }

}
