package com.ticket.electronico.infrastructure.adapter.out.notification;


import com.ticket.electronico.application.port.out.SmsNotificationPort;
import com.ticket.electronico.shared.annotation.Adapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Adapter
public class SmsNotificationAdapter implements SmsNotificationPort {

    private static final Logger logger = LoggerFactory.getLogger(SmsNotificationAdapter.class);

    // En un entorno real, inyectaríamos un cliente SMS como Twilio, AWS SNS, etc.
    // private final TwilioClient twilioClient;

    @Override
    public void sendAppointmentReminder(String phoneNumber, String message) {
        try {
            // Simular envío de SMS
            logger.info("Enviando SMS a {}: {}", phoneNumber, message);

            // En un entorno real:
            // twilioClient.sendSms(phoneNumber, message);

        } catch (Exception e) {
            logger.error("Error enviando SMS a: {}", phoneNumber, e);
        }
    }
}