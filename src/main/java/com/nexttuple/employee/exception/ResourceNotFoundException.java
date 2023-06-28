package com.nexttuple.employee.exception;

public class ResourceNotFoundException extends RuntimeException{
    private String reason;

    public ResourceNotFoundException(String message,String reason)
    {
        super(message);
        this.reason=reason;
    }
    public String getReason()
    {
        return reason;
    }
}
