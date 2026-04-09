package com.ticket.electronico.application.port.out;

public interface SmsNotificationPort {
    void sendAppointmentReminder(String phoneNumber, String message);
}