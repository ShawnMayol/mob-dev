package ph.edu.uscmontero.montero_day1;

public class HotelItem {
    private String destination, checkInDate, checkOutDate, hotelName, price;

    public HotelItem(String destination, String checkInDate, String checkOutDate, String hotelName, String price) {
        this.destination = destination;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotelName = hotelName;
        this.price = price;
    }

    public String getDestination() { return destination; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public String getHotelName() { return hotelName; }
    public String getPrice() { return price; }
}
