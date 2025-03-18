package ph.edu.uscmontero.montero_day1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class FlightAdapter extends BaseAdapter {
    private Context context;
    private List<FlightItem> flightList;

    public FlightAdapter(Context context, List<FlightItem> flightList) {
        this.context = context;
        this.flightList = flightList;
    }

    @Override
    public int getCount() {
        return flightList.size();
    }

    @Override
    public Object getItem(int position) {
        return flightList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.flight_item, parent, false);
        }

        FlightItem flight = flightList.get(position);

        ((TextView) convertView.findViewById(R.id.fromLocation)).setText(flight.getFrom());
        ((TextView) convertView.findViewById(R.id.toLocation)).setText(flight.getTo());
        ((TextView) convertView.findViewById(R.id.departureDate)).setText("Departure: " + flight.getDepartureDate());

        TextView returnDateTextView = convertView.findViewById(R.id.returnDate);
        if (flight.getReturnDate().equals("One-way")) {
            returnDateTextView.setVisibility(View.GONE);
        } else {
            returnDateTextView.setText("Return: " + flight.getReturnDate());
            returnDateTextView.setVisibility(View.VISIBLE);
        }

        ((TextView) convertView.findViewById(R.id.airline)).setText("Airline: " + flight.getAirline());
        ((TextView) convertView.findViewById(R.id.price)).setText(flight.getPrice());

        return convertView;
    }
}
