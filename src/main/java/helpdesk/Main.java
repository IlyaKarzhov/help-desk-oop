package helpdesk;

import helpdesk.model.Administrator;
import helpdesk.model.Customer;
import helpdesk.model.SupportAgent;
import helpdesk.model.Ticket;
import helpdesk.model.User;
import helpdesk.repository.TicketRepository;
import helpdesk.service.ConsoleNotificationService;
import helpdesk.service.NotificationService;
import helpdesk.service.TicketService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HELP DESK ===");

        Customer customer = new Customer(1, "Анна Петрова", "anna@mail.ru");
        SupportAgent agent = new SupportAgent(2, "Сергей Иванов", "sergey@helpdesk.ru");
        System.out.println("Клиент: " + customer.getName());

        Ticket ticket = new Ticket(1, "Не работает Wi-Fi", "Ноутбук не подключается к сети");
        System.out.println("Заявка " + ticket);

        NotificationService notificationService = new ConsoleNotificationService();
        TicketService ticketService = new TicketService(notificationService);

        ticketService.startTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus());
        ticketService.resolveTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus());
        ticketService.closeTicket(ticket);
        System.out.println("Статус: " + ticket.getStatus());

        TicketRepository repository = new TicketRepository();
        repository.add(ticket);
        repository.add(new Ticket(2, "Ошибка приложения", "Программа закрывается при запуске"));
        repository.add(new Ticket(3, "Не печатает принтер", "Принтер не отвечает"));

        System.out.println();
        for (Ticket t : repository.findAll()) {
            System.out.println(t);
        }

        System.out.println();
        List<User> users = new ArrayList<>();
        users.add(customer);
        users.add(agent);
        users.add(new Administrator(3, "Олег", "admin@helpdesk.ru"));

        for (User user : users) {
            user.performAction();
        }

        System.out.println();
        Ticket mailTicket = new Ticket(4, "Нет доступа к почте", "Не открывается почта");
        System.out.println(mailTicket);
        System.out.println("Создана: " + mailTicket.getCreatedAt());
        mailTicket.cancel();
        System.out.println(mailTicket);
        ticket.cancel(); // первая уже закрыта, отменить нельзя

        try {
            new Ticket(5, "", "Заявка без названия");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
