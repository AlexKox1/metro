package ru.metro.service;

import org.springframework.stereotype.Service;
import ru.metro.model.Ticket;
import ru.metro.model.Turnstile;

import java.util.List;
import java.util.Scanner;

@Service
public class ControlService {

    private MetroService metroService;

    public ControlService(MetroService metroService) {
        this.metroService = metroService;
    }

    public void start() {
        List<Turnstile> turnstiles = metroService.getTurnstiles();
        for (Turnstile turnstile : turnstiles) {
            System.out.println("Турникет № " + turnstile.getId());
        }
        System.out.println();
        System.out.println("Если хотите довабить еще один турникет введите addTurnstile");
        System.out.println();
        List<Ticket> tickets = metroService.getTickets();
        for (Ticket ticket : tickets) {
            System.out.println("Билет № " + ticket.getId() + " кол-во едениц " + ticket.getQuantity() + " Срок окончания " + ticket.getClosed());
        }
        System.out.println();
        System.out.println("Если хотите довабить билет введите addTicket_кол-во едениц\nНапример addTicket_20");
        System.out.println();
        System.out.println("Если хотите узнать статус турникета введите statusTurnstile_#турникета\nНапример statusTurnstile_1");
        System.out.println();
        System.out.println("Если хотите приложить билет к турникету введите putTiсket_#билета_#турникета\nНапример putTiсket_1_1");
        System.out.println();
        System.out.println("Чтобы пройти через турникет введите go_#номер датчика_#турникета\nНапример go_1_1");
        Scanner in = new Scanner(System.in);
        for (;;){
            String s = in.nextLine();
            if (s.equals("addTurnstile")) {
                Turnstile turnstile = metroService.createTurnstile();
                System.out.println("Турникет № " + turnstile.getId() + " готов к работе");
            }
            if (s.contains("addTicket")) {
                String[] splits = s.split("_");
                Integer quantity = Integer.parseInt(splits[1]);
                Ticket ticket = metroService.createTicket(quantity);
                System.out.println("Билет № " + ticket.getId() + " кол-во едениц " + ticket.getQuantity() + " Срок окончания " + ticket.getClosed());
            }
            if (s.contains("statusTurnstile")) {
                String[] splits = s.split("_");
                Long id = Long.parseLong(splits[1]);
                Turnstile turnstile = metroService.getTurnstile(id);
                System.out.println("Турникет № " + turnstile.getId() + " " + (turnstile.isEnabled() ? "открыт" : "закрыт"));
            }
            if (s.contains("putTiсket")) {
                String[] splits = s.split("_");
                Long ticketId = Long.parseLong(splits[1]);
                Long turnstileId = Long.parseLong(splits[2]);
                boolean pass = metroService.pass(ticketId, turnstileId);
                if (pass) {
                    System.out.println("Вы можете пройти");
                } else {
                    System.out.println("Билет не действителен");
                }
            }
            if (s.contains("go")) {
                String[] splits = s.split("_");
                Long numberSensor = Long.parseLong(splits[1]);
                Long turnstileId = Long.parseLong(splits[2]);
                boolean go = metroService.go(turnstileId);
                System.out.println(go ? "вы прошли" : "проход закрыт");
            }

        }
    }
}
