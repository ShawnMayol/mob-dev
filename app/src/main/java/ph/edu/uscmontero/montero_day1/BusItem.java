package ph.edu.uscmontero.montero_day1;

public class BusItem {
    private String from, to, travelDate, busCompany, price;

    public BusItem(String from, String to, String travelDate, String busCompany, String price) {
        this.from = from;
        this.to = to;
        this.travelDate = travelDate;
        this.busCompany = busCompany;
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public String getBusCompany() {
        return busCompany;
    }

    public String getPrice() {
        return price;
    }
}
