package ph.edu.uscmontero.montero_day1;

public class FlightItem {
    private String from, to, departureDate, returnDate, airline, price;

    public FlightItem(String from, String to, String departureDate, String returnDate, String airline, String price) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.returnDate = returnDate.isEmpty() ? "One-way" : returnDate; // If empty, set default "One-way"
        this.airline = airline;
        this.price = price;
    }

    public String getFlightInfo() {
        return from + " → " + to + " | " + departureDate + " - " + returnDate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getAirline() {
        return airline;
    }

    public String getPrice() {
        return price;
    }
}
