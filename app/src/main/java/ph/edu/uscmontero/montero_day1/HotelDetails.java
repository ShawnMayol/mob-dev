package ph.edu.uscmontero.montero_day1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Set;

public class HotelDetails extends AppCompatActivity {

    private TextView hotelName, hotelDestination, checkInDate, checkOutDate, guestsCount, hotelPrice;
    private Spinner roomTypeSpinner;
    private Button confirmHotelBookingBtn;
    private String selectedRoomType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        // Initialize UI Elements
        hotelName = findViewById(R.id.hotelName);
        hotelDestination = findViewById(R.id.hotelDestination);
        checkInDate = findViewById(R.id.checkInDate);
        checkOutDate = findViewById(R.id.checkOutDate);
        guestsCount = findViewById(R.id.guestsCount);
        hotelPrice = findViewById(R.id.hotelPrice);
        roomTypeSpinner = findViewById(R.id.roomTypeSpinner);
        confirmHotelBookingBtn = findViewById(R.id.confirmHotelBookingBtn);

        // Get Data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            hotelName.setText(intent.getStringExtra("hotelName"));
            hotelDestination.setText("Location: " + intent.getStringExtra("destination"));
            checkInDate.setText("Check-in: " + intent.getStringExtra("checkInDate"));
            checkOutDate.setText("Check-out: " + intent.getStringExtra("checkOutDate"));
            hotelPrice.setText(intent.getStringExtra("price"));
        }

        // Room Type Options
        String[] roomTypes = {"Standard Room", "Deluxe Room", "Suite", "Penthouse"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, roomTypes);
        roomTypeSpinner.setAdapter(adapter);

        // Capture selected room type
        roomTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedRoomType = roomTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Confirm Booking Action
        confirmHotelBookingBtn.setOnClickListener(v -> {
            if (selectedRoomType == null || selectedRoomType.isEmpty()) {
                Toast.makeText(HotelDetails.this, "Please select a room type!", Toast.LENGTH_SHORT).show();
                return;
            }

            saveBookingToSharedPreferences();

            // Redirect to TicketHomePage
            Intent homeIntent = new Intent(HotelDetails.this, TicketHomePage.class);
            startActivity(homeIntent);
            finish();
        });
    }

    private void saveBookingToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyTrips", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> bookings = sharedPreferences.getStringSet("hotelBookings", new HashSet<>());
        String booking = hotelName.getText().toString() + " - " + hotelDestination.getText().toString() +
                " | " + checkInDate.getText().toString() + " | " + checkOutDate.getText().toString() +
                " | Room Type: " + selectedRoomType + " | Price: " + hotelPrice.getText().toString();
        bookings.add(booking);
        editor.putStringSet("hotelBookings", bookings);
        editor.apply();
    }
}
