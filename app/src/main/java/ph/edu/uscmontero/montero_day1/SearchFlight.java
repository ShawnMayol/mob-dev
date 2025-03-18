package ph.edu.uscmontero.montero_day1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class SearchFlight extends AppCompatActivity {

    private EditText fromEditText, toEditText, departureDateEditText, returnDateEditText;
    private Button searchBtn, bookFlightBtn;
    private ListView flightListView;
    private FlightAdapter flightAdapter;
    private List<FlightItem> flightList;
    private FlightItem selectedFlight = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);

        fromEditText = findViewById(R.id.fromEditText);
        toEditText = findViewById(R.id.toEditText);
        departureDateEditText = findViewById(R.id.departureDateEditText);
        returnDateEditText = findViewById(R.id.returnDateEditText);
        searchBtn = findViewById(R.id.searchBtn);
        bookFlightBtn = findViewById(R.id.bookFlightBtn);
        flightListView = findViewById(R.id.flightListView);

        flightList = new ArrayList<>();
        flightAdapter = new FlightAdapter(this, flightList);
        flightListView.setAdapter(flightAdapter);

        searchBtn.setOnClickListener(v -> searchFlights());
        departureDateEditText.setOnClickListener(v -> pickDate(departureDateEditText));
        returnDateEditText.setOnClickListener(v -> pickDate(returnDateEditText));

        flightListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedFlight = flightList.get(position);
            bookFlightBtn.setVisibility(View.VISIBLE);
        });

        bookFlightBtn.setOnClickListener(v -> {
            if (selectedFlight != null) {
                Intent intent = new Intent(SearchFlight.this, FlightDetails.class);
                intent.putExtra("from", selectedFlight.getFrom());
                intent.putExtra("to", selectedFlight.getTo());
                intent.putExtra("departureDate", selectedFlight.getDepartureDate());
                intent.putExtra("returnDate", selectedFlight.getReturnDate());
                intent.putExtra("airline", selectedFlight.getAirline());
                intent.putExtra("price", selectedFlight.getPrice());
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

    private void searchFlights() {
        String from = fromEditText.getText().toString().trim();
        String to = toEditText.getText().toString().trim();
        String departureDate = departureDateEditText.getText().toString().trim();
        String returnDate = returnDateEditText.getText().toString().trim();

        flightList.clear();

        if (from.isEmpty() || to.isEmpty() || departureDate.isEmpty()) {
            Toast.makeText(this, "Please enter FROM, TO, and Departure Date.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Data
        flightList.add(new FlightItem(from, to, departureDate, returnDate, "Montero Airways", "$250"));
        flightList.add(new FlightItem(from, to, departureDate, returnDate, "Alvarizz Airlines", "$320"));
        flightList.add(new FlightItem(from, to, departureDate, returnDate, "Veloso Flyways", "$690"));

        flightAdapter.notifyDataSetChanged();
    }
}
