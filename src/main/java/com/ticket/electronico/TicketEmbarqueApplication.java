package com.ticket.electronico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ✅ Así debe quedar
@SpringBootApplication
public class TicketEmbarqueApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketEmbarqueApplication.class, args);
    }
}