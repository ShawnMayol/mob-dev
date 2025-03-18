package ph.edu.uscmontero.montero_day1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class BusAdapter extends BaseAdapter {
    private Context context;
    private List<BusItem> busList;

    public BusAdapter(Context context, List<BusItem> busList) {
        this.context = context;
        this.busList = busList;
    }

    @Override
    public int getCount() {
        return busList.size();
    }

    @Override
    public Object getItem(int position) {
        return busList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bus_item, parent, false);
        }

        BusItem bus = busList.get(position);

        ((TextView) convertView.findViewById(R.id.busRoute)).setText(bus.getFrom() + " → " + bus.getTo());
        ((TextView) convertView.findViewById(R.id.travelDate)).setText("Travel Date: " + bus.getTravelDate());
        ((TextView) convertView.findViewById(R.id.busCompany)).setText(bus.getBusCompany());
        ((TextView) convertView.findViewById(R.id.busPrice)).setText(bus.getPrice());

        return convertView;
    }
}
