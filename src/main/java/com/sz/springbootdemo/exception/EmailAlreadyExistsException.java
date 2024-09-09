package com.sz.springbootdemo.exception;

/**
 * Custom exception class to handle cases where an email address already exists.
 *
 * <p>In the system, the {@code emailId} field for the {@code Customer} entity is unique and
 * cannot have duplicate values. When an attempt is made to insert a customer with an email
 * that already exists, this exception will be thrown.</p>
 *
 * <p>This exception is typically used in the service layer to validate data. If a duplicate
 * {@code emailId} is detected, it throws this exception to prevent the duplicate email from
 * being saved to the database.</p>
 *
 * <p>An exception handler can catch this exception and return a user-friendly error message,
 * usually with an HTTP 409 Conflict status code.</p>
 *
 * Example:
 * <pre>{@code
 *   if (existingCustomer.getEmailId().equals(customer.getEmailId())) {
 *       throw new EmailAlreadyExistsException("Email already exists: " + customer.getEmailId());
 *   }
 * }</pre>
 *
 * @author Shi Zeng
 * @version 1.0
 * @since 2024-09-09
 */
public class EmailAlreadyExistsException extends RuntimeException {

  public EmailAlreadyExistsException(String message) {
    super(message);
  }
}
