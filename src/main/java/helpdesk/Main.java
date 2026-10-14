package helpdesk;

import helpdesk.model.Ticket;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HELP DESK ===");

        Ticket ticket = new Ticket(1, "Не работает Wi-Fi", "Ноутбук не подключается к сети");
        System.out.println(ticket.getTitle() + " | " + ticket.getStatus());

        ticket.resolve(); // ещё не в работе, должна быть ошибка
        ticket.startProcessing();
        System.out.println("Статус: " + ticket.getStatus());
        ticket.resolve();
        System.out.println("Статус: " + ticket.getStatus());
        ticket.close();
        System.out.println("Статус: " + ticket.getStatus());
    }
}
