package ph.edu.uscmontero.montero_day1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class FlightDetails extends AppCompatActivity {

    private TextView fromLocation, toLocation, departureDate, returnDate, airline, price, seatNumber, flightNumber;
    private EditText passengerName;
    private Button confirmBookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        // Initialize UI Elements
        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        departureDate = findViewById(R.id.departureDate);
        returnDate = findViewById(R.id.returnDate);
        airline = findViewById(R.id.airline);
        price = findViewById(R.id.price);
        seatNumber = findViewById(R.id.seatNumber);
        flightNumber = findViewById(R.id.flightNumber);
        passengerName = findViewById(R.id.passengerName);
        confirmBookingBtn = findViewById(R.id.confirmBookingBtn);

        // Get Data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            fromLocation.setText(intent.getStringExtra("from"));
            toLocation.setText(intent.getStringExtra("to"));
            departureDate.setText("Departure: " + intent.getStringExtra("departureDate"));

            String returnDateValue = intent.getStringExtra("returnDate");
            if (returnDateValue.isEmpty() || returnDateValue.equals("One-way")) {
                returnDate.setVisibility(View.GONE);  // Hide return date if one-way flight
            } else {
                returnDate.setText("Return: " + returnDateValue);
            }

            airline.setText("Airline: " + intent.getStringExtra("airline"));
            price.setText(intent.getStringExtra("price"));

            // Generate random seat number and flight number
            seatNumber.setText(generateRandomSeat());
            flightNumber.setText(generateRandomFlightNumber());
        }

        // Confirm Booking Action
        confirmBookingBtn.setOnClickListener(v -> {
            String name = passengerName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(FlightDetails.this, "Please enter passenger name!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save Booking to SharedPreferences
            saveBookingToSharedPreferences(name);

            // Redirect to TicketHomePage
            Intent homeIntent = new Intent(FlightDetails.this, TicketHomePage.class);
            startActivity(homeIntent);
            finish();
        });
    }

    // Generate Random Seat Number (Example: A12, B5, C8)
    private String generateRandomSeat() {
        String[] rows = {"A", "B", "C", "D", "E", "F"};
        int seatNum = new Random().nextInt(30) + 1;  // Random seat between 1-30
        return rows[new Random().nextInt(rows.length)] + seatNum;
    }

    // Generate Random Flight Number (Example: XY123, AB456)
    private String generateRandomFlightNumber() {
        String[] prefixes = {"XY", "AB", "CD", "EF", "GH", "IJ"};
        int number = new Random().nextInt(900) + 100;  // Random 3-digit number
        return prefixes[new Random().nextInt(prefixes.length)] + number;
    }

    // Save Flight Booking to SharedPreferences
    private void saveBookingToSharedPreferences(String passenger) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyTrips", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Retrieve existing bookings
        Set<String> bookings = sharedPreferences.getStringSet("flightBookings", new HashSet<>());

        // Create a new booking entry
        String booking = passenger + " - " + fromLocation.getText().toString() + " → " + toLocation.getText().toString() +
                " | " + departureDate.getText().toString() + " | " + airline.getText().toString() +
                " | Seat: " + seatNumber.getText().toString() + " | Flight: " + flightNumber.getText().toString() +
                " | Price: " + price.getText().toString();

        bookings.add(booking);

        // Save updated booking set
        editor.putStringSet("flightBookings", bookings);
        editor.apply();
    }
}
