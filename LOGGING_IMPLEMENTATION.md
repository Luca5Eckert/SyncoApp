# Logging Implementation Summary

## Overview
This document describes the logging implementation added to the Blog API to monitor important actions and errors.

## Implementation Details

### Logging Framework
- **Framework**: SLF4J (Simple Logging Facade for Java) via Lombok's `@Slf4j` annotation
- **Implementation**: Logback (default with Spring Boot)
- **Annotation**: `@Slf4j` from Lombok provides automatic logger instance creation

### Use Cases with Logging

#### 1. Authentication Module

##### UserLoginUseCase
- **INFO**: Login attempt with user email
- **WARN**: Login failure when authentication is not validated
- **INFO**: Successful login with user email and role
- **Location**: `com.api.blog.module.authentication.domain.use_case.UserLoginUseCase`

##### UserRegisterUseCase
- **INFO**: User registration attempt with email
- **WARN**: Registration failure due to invalid password
- **WARN**: Registration failure due to duplicate email
- **INFO**: Successful registration with email and role
- **Location**: `com.api.blog.module.authentication.domain.use_case.UserRegisterUseCase`

#### 2. User Module

##### UserCreateUseCase
- **INFO**: User creation attempt with email and role
- **WARN**: User creation failure due to invalid password
- **WARN**: User creation failure due to duplicate email
- **INFO**: Successful user creation with email and role
- **Location**: `com.api.blog.module.user.domain.use_case.UserCreateUseCase`

##### UserEditUseCase
- **INFO**: User edit attempt with user ID being edited and authenticated user ID
- **WARN**: Edit failure due to insufficient permissions
- **INFO**: Successful user edit with user ID, email, and authenticated user ID
- **Location**: `com.api.blog.module.user.domain.use_case.UserEditUseCase`

##### UserDeleteUseCase
- **INFO**: User deletion attempt with user ID being deleted and authenticated user ID
- **WARN**: Deletion failure when user not found
- **WARN**: Deletion failure due to insufficient permissions
- **INFO**: Successful user deletion with user ID and authenticated user ID
- **Location**: `com.api.blog.module.user.domain.use_case.UserDeleteUseCase`

#### 3. Exception Handling

##### GlobalExceptionHandler
- **ERROR**: All exceptions are logged with request path and exception message
- **Categories**:
  - UserException: Logged at ERROR level
  - AuthenticationException: Logged at ERROR level
  - RuntimeException: Logged at ERROR level with full stack trace
  - Generic Exception: Logged at ERROR level with full stack trace
- **Location**: `com.api.blog.infrastructure.exception.GlobalExceptionHandler`

### Log Levels Used

#### INFO
Used for normal, successful operations that provide audit trail:
- User registration
- User login
- User creation
- User edit
- User deletion

#### WARN
Used for expected business rule violations or validation failures:
- Invalid passwords
- Duplicate emails
- Permission denied scenarios
- User not found during deletion

#### ERROR
Used for all exceptions that need immediate attention:
- User exceptions
- Authentication exceptions
- Runtime exceptions
- Generic exceptions

### Logging Configuration

Configuration added to `application.properties`:

```properties
# Logging Configuration
logging.level.root=INFO
logging.level.com.api.blog=INFO
logging.level.com.api.blog.module.authentication.domain.use_case=INFO
logging.level.com.api.blog.module.user.domain.use_case=INFO
logging.level.com.api.blog.infrastructure.exception=ERROR
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```

### Example Log Output

#### Successful User Registration
```
2025-10-24 22:00:00 - User registration attempt for email: user@example.com
2025-10-24 22:00:01 - User registered successfully: user@example.com with role: USER
```

#### Failed Login Attempt
```
2025-10-24 22:00:00 - Login attempt for user: user@example.com
2025-10-24 22:00:01 - Login failed - authentication not validated for user: user@example.com
```

#### Permission Denied
```
2025-10-24 22:00:00 - User deletion attempt - User ID: 5 being deleted by authenticated user ID: 3
2025-10-24 22:00:01 - User deletion failed - User ID: 3 does not have permission to delete users
```

#### Exception Handling
```
2025-10-24 22:00:00 - User exception at path /api/blog/users: Email already exists
```

## Best Practices Followed

1. **No Sensitive Data**: Passwords are never logged
2. **Structured Logging**: Consistent format with relevant context (IDs, emails, roles)
3. **Appropriate Levels**: 
   - INFO for successful operations
   - WARN for business rule violations
   - ERROR for exceptions
4. **Audit Trail**: All important actions (CRUD operations) are logged
5. **Troubleshooting**: Errors include context (path, user IDs) for debugging
6. **Performance**: Logging uses parameterized messages to avoid string concatenation overhead

## Benefits

1. **Auditability**: Track all user actions (registration, login, CRUD operations)
2. **Security Monitoring**: Detect failed login attempts and permission violations
3. **Troubleshooting**: Detailed error logs with context for debugging
4. **Compliance**: Audit trail for regulatory requirements
5. **Operations**: Monitor application health and usage patterns

## Future Enhancements

Potential improvements for the logging system:

1. Add correlation IDs for request tracing
2. Implement structured JSON logging for better parsing
3. Add metrics for login attempts, failures, etc.
4. Integrate with centralized logging systems (ELK, Splunk, etc.)
5. Add log rotation configuration
6. Implement log sanitization for PII data
