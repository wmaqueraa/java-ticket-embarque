package com.ticket.electronico.domain.exception;

public class TicketDuplicateException extends DomainException{
    public  TicketDuplicateException(String message) {
        super(message);
    }
}
