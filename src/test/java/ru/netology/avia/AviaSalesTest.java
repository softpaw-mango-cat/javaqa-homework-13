package ru.netology.avia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.utility.TicketTimeComparator;

public class AviaSalesTest {
    Ticket ticket1;
    Ticket ticket2;
    Ticket ticket3;
    Ticket ticket4;

    @BeforeEach
    public void testData() {
        ticket1 = new Ticket(
                "Moscow",
                "Vladivostok",
                15000,
                1764313200,
                1764351000);

        ticket2 = new Ticket(
                "Moscow",
                "Vladivostok",
                17800,
                1764361800,
                1764405900);

        ticket3 = new Ticket(
                "Moscow",
                "Vladivostok",
                13100,
                1764219600,
                1764237300);

        ticket4 = new Ticket(
                "Moscow",
                "Vladivostok",
                20000,
                1763978400,
                1764023400);
    }

    // проверка метода compareTo
    @Test
    public void shouldCompareByPriceWhenFirstIsCheaper() {
        // ticket1 = 15000, ticket2 = 17800

        int actual = ticket1.compareTo(ticket2);
        Assertions.assertEquals(-1, actual);
    }

    @Test
    public void shouldCompareByPriceWhenSecondIsCheaper() {
        // ticket3 = 13100, ticket4 = 20000

        int actual = ticket4.compareTo(ticket3);
        Assertions.assertEquals(1, actual);
    }

    @Test
    public void shouldCompareByPriceWhenSamePrice() {
        // ticket 1 = 15000

        Ticket ticket5 = new Ticket(
                "Moscow",
                "Vladivostok",
                15000,
                1764219600,
                1764237300);

        int actual = ticket1.compareTo(ticket5);
        Assertions.assertEquals(0, actual);
    }

    // проверка метода search
    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSales sales = new AviaSales();
        sales.add(ticket1);
        sales.add(ticket2);
        sales.add(ticket3);
        sales.add(ticket4);

        Ticket[] actual = sales.search("Moscow", "Vladivostok");

        /*  ticket1 = 15000, ticket2 = 17800,
        ticket3 = 13100, ticket4 = 20000 */
        Ticket[] expected = {ticket3, ticket1, ticket2, ticket4};
        Assertions.assertArrayEquals(expected, actual);
    }

    // проверка TicketTimeComparator
    @Test
    public void shouldCompareByTimeWhenFirstIsShorter() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        // вывод для наглядности
        System.out.println(ticket1.getDuration()); //10
        System.out.println(ticket2.getDuration()); //12

        int actual = comparator.compare(ticket1, ticket2);
        Assertions.assertEquals(-1, actual);
    }

    @Test
    public void shouldCompareByTimeWhenSecondIsShorter() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        // вывод для наглядности
        System.out.println(ticket2.getDuration()); //12
        System.out.println(ticket3.getDuration()); //4

        int actual = comparator.compare(ticket2, ticket3);
        Assertions.assertEquals(1, actual);
    }

    @Test
    public void shouldCompareByTimeWhenEquals() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        // вывод для наглядности
        System.out.println(ticket2.getDuration()); //12
        System.out.println(ticket4.getDuration()); //12

        int actual = comparator.compare(ticket4, ticket2);
        Assertions.assertEquals(0, actual);
    }

    // проверка searchAndSortBy
    @Test
    public void shouldSearchAndSortByDuration() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        AviaSales sales = new AviaSales();
        sales.add(ticket4);
        sales.add(ticket3);
        sales.add(ticket1);

        System.out.println(ticket4.getDuration()); //12
        System.out.println(ticket3.getDuration()); //4
        System.out.println(ticket1.getDuration()); //10

        Ticket[] actual = sales.searchAndSortBy("Moscow", "Vladivostok", comparator);
        Ticket[] expected = {ticket3, ticket1, ticket4}; // 4, 10, 12

        Assertions.assertArrayEquals(expected, actual);
    }
}
