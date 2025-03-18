package ph.edu.uscmontero.montero_day1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class SearchHotel extends AppCompatActivity {

    private EditText destinationEditText, checkInDateEditText, checkOutDateEditText, guestsEditText;
    private Button searchHotelBtn, bookHotelBtn;
    private ListView hotelListView;
    private HotelAdapter hotelAdapter;
    private List<HotelItem> hotelList;
    private HotelItem selectedHotel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        destinationEditText = findViewById(R.id.destinationEditText);
        checkInDateEditText = findViewById(R.id.checkInDateEditText);
        checkOutDateEditText = findViewById(R.id.checkOutDateEditText);
        guestsEditText = findViewById(R.id.guestsEditText);
        searchHotelBtn = findViewById(R.id.searchHotelBtn);
        bookHotelBtn = findViewById(R.id.bookHotelBtn);
        hotelListView = findViewById(R.id.hotelListView);

        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(this, hotelList);
        hotelListView.setAdapter(hotelAdapter);

        searchHotelBtn.setOnClickListener(v -> searchHotels());
        checkInDateEditText.setOnClickListener(v -> pickDate(checkInDateEditText));
        checkOutDateEditText.setOnClickListener(v -> pickDate(checkOutDateEditText));

        hotelListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedHotel = hotelList.get(position);
            bookHotelBtn.setVisibility(View.VISIBLE);
        });

        bookHotelBtn.setOnClickListener(v -> {
            if (selectedHotel != null) {
                Intent intent = new Intent(SearchHotel.this, HotelDetails.class);
                intent.putExtra("destination", selectedHotel.getDestination());
                intent.putExtra("checkInDate", selectedHotel.getCheckInDate());
                intent.putExtra("checkOutDate", selectedHotel.getCheckOutDate());
                intent.putExtra("hotelName", selectedHotel.getHotelName());
                intent.putExtra("price", selectedHotel.getPrice());
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

    private void searchHotels() {
        String destination = destinationEditText.getText().toString().trim();
        String checkInDate = checkInDateEditText.getText().toString().trim();
        String checkOutDate = checkOutDateEditText.getText().toString().trim();

        hotelList.clear();

        if (destination.isEmpty() || checkInDate.isEmpty() || checkOutDate.isEmpty()) {
            Toast.makeText(this, "Please enter all details.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sample Data
        hotelList.add(new HotelItem(destination, checkInDate, checkOutDate, "Grand Plaza Hotel", "$120"));
        hotelList.add(new HotelItem(destination, checkInDate, checkOutDate, "Luxury Suites", "$200"));

        hotelAdapter.notifyDataSetChanged();
    }
}
