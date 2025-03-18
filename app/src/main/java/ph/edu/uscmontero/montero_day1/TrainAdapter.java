package ph.edu.uscmontero.montero_day1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class TrainAdapter extends BaseAdapter {
    private Context context;
    private List<TrainItem> trainList;

    public TrainAdapter(Context context, List<TrainItem> trainList) {
        this.context = context;
        this.trainList = trainList;
    }

    @Override
    public int getCount() {
        return trainList.size();
    }

    @Override
    public Object getItem(int position) {
        return trainList.get(position);
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

        TrainItem train = trainList.get(position);

        ((TextView) convertView.findViewById(R.id.busRoute)).setText(train.getFrom() + " → " + train.getTo());
        ((TextView) convertView.findViewById(R.id.travelDate)).setText("Travel Date: " + train.getTravelDate());
        ((TextView) convertView.findViewById(R.id.busCompany)).setText(train.getTrainCompany());
        ((TextView) convertView.findViewById(R.id.busPrice)).setText(train.getPrice());

        return convertView;
    }
}
