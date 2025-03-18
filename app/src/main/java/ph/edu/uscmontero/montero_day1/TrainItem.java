package ph.edu.uscmontero.montero_day1;

public class TrainItem {
    private String from, to, travelDate, trainCompany, price;

    public TrainItem(String from, String to, String travelDate, String trainCompany, String price) {
        this.from = from;
        this.to = to;
        this.travelDate = travelDate;
        this.trainCompany = trainCompany;
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

    public String getTrainCompany() {
        return trainCompany;
    }

    public String getPrice() {
        return price;
    }
}
