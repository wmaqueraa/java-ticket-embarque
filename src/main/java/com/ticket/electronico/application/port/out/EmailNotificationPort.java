package com.ticket.electronico.application.port.out;

public interface EmailNotificationPort {
    void sendTicketGenerate(String email, String ownerName,
                                     String petName, String appointmentDate);

    void sendTicketReminder(String email, String ownerName,
                                 String petName, String appointmentDate);

    void sendVueloCancellation(String email, String ownerName,
                                     String petName, String appointmentDate);
}