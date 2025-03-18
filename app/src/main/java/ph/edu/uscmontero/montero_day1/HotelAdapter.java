package ph.edu.uscmontero.montero_day1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class HotelAdapter extends BaseAdapter {
    private Context context;
    private List<HotelItem> hotelList;

    public HotelAdapter(Context context, List<HotelItem> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
    }

    @Override
    public int getCount() { return hotelList.size(); }

    @Override
    public Object getItem(int position) { return hotelList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hotel_item, parent, false);
        }

        HotelItem hotel = hotelList.get(position);

        ((TextView) convertView.findViewById(R.id.hotelName)).setText(hotel.getHotelName());
        ((TextView) convertView.findViewById(R.id.hotelPrice)).setText(hotel.getPrice());

        return convertView;
    }
}
