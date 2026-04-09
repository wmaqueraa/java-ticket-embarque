package com.ticket.electronico.infrastructure.adapter.out.notification;

import com.ticket.electronico.application.port.out.EmailNotificationPort;
import com.ticket.electronico.shared.annotation.Adapter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Adapter
public class EmailNotificationAdapter implements EmailNotificationPort {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationAdapter.class);

    private final JavaMailSender mailSender;

    public EmailNotificationAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendTicketGenerate(String email, String ownerName, String petName, String appointmentDate) {

    }

    @Override
    public void sendTicketReminder(String email, String ownerName, String petName, String appointmentDate) {

    }

    @Override
    public void sendVueloCancellation(String email, String ownerName, String petName, String appointmentDate) {

    }
}
