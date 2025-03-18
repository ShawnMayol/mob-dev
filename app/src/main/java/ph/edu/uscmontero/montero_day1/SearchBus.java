package ph.edu.uscmontero.montero_day1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class SearchBus extends AppCompatActivity {

    private EditText fromEditText, toEditText, travelDateEditText;
    private Button searchBusBtn, bookBusBtn;
    private ListView busListView;
    private BusAdapter busAdapter;
    private List<BusItem> busList;
    private BusItem selectedBus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bus);

        fromEditText = findViewById(R.id.fromEditText);
        toEditText = findViewById(R.id.toEditText);
        travelDateEditText = findViewById(R.id.travelDateEditText);
        searchBusBtn = findViewById(R.id.searchBusBtn);
        bookBusBtn = findViewById(R.id.bookBusBtn);
        busListView = findViewById(R.id.busListView);

        busList = new ArrayList<>();
        busAdapter = new BusAdapter(this, busList);
        busListView.setAdapter(busAdapter);

        searchBusBtn.setOnClickListener(v -> searchBuses());
        travelDateEditText.setOnClickListener(v -> pickDate(travelDateEditText));

        busListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedBus = busList.get(position);
            bookBusBtn.setVisibility(View.VISIBLE);
        });

        bookBusBtn.setOnClickListener(v -> {
            if (selectedBus != null) {
                Intent intent = new Intent(SearchBus.this, BusDetails.class);
                intent.putExtra("from", selectedBus.getFrom());
                intent.putExtra("to", selectedBus.getTo());
                intent.putExtra("travelDate", selectedBus.getTravelDate());
                intent.putExtra("busCompany", selectedBus.getBusCompany());
                intent.putExtra("price", selectedBus.getPrice());
                startActivity(intent);
            }
        });
    }

    private void pickDate(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) ->
                editText.setText(day + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void searchBuses() {
        String from = fromEditText.getText().toString().trim();
        String to = toEditText.getText().toString().trim();
        String travelDate = travelDateEditText.getText().toString().trim();

        busList.clear();

        if (from.isEmpty() || to.isEmpty() || travelDate.isEmpty()) {
            Toast.makeText(this, "Please enter Departure City, Arrival City, and Travel Date.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sample Data
        busList.add(new BusItem(from, to, travelDate, "FastBus Express", "$20"));
        busList.add(new BusItem(from, to, travelDate, "Comfort Travels", "$25"));

        busAdapter.notifyDataSetChanged();
    }
}
