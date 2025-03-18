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

public class BusDetails extends AppCompatActivity {

    private TextView fromLocation, toLocation, travelDate, busCompany, price, seatNumber, ticketNumber;
    private EditText passengerName;
    private Button confirmBusBookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        // Initialize UI Elements
        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        travelDate = findViewById(R.id.travelDate);
        busCompany = findViewById(R.id.busCompany);
        price = findViewById(R.id.price);
        seatNumber = findViewById(R.id.seatNumber);
        ticketNumber = findViewById(R.id.ticketNumber);
        passengerName = findViewById(R.id.passengerName);
        confirmBusBookingBtn = findViewById(R.id.confirmBusBookingBtn);

        // Get Data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            fromLocation.setText(intent.getStringExtra("from"));
            toLocation.setText(intent.getStringExtra("to"));
            travelDate.setText("Travel Date: " + intent.getStringExtra("travelDate"));
            busCompany.setText("Bus Company: " + intent.getStringExtra("busCompany"));
            price.setText(intent.getStringExtra("price"));

            // Generate random seat and ticket number
            seatNumber.setText(generateRandomSeat());
            ticketNumber.setText(generateRandomTicketNumber());
        }

        // Confirm Booking Action
        confirmBusBookingBtn.setOnClickListener(v -> {
            String name = passengerName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(BusDetails.this, "Please enter passenger name!", Toast.LENGTH_SHORT).show();
                return;
            }

            saveBookingToSharedPreferences(name);

            // Redirect to TicketHomePage
            Intent homeIntent = new Intent(BusDetails.this, TicketHomePage.class);
            startActivity(homeIntent);
            finish();
        });
    }

    private String generateRandomSeat() {
        return "Seat " + (new Random().nextInt(40) + 1);
    }

    private String generateRandomTicketNumber() {
        return "TKT" + (new Random().nextInt(900000) + 100000);
    }

    private void saveBookingToSharedPreferences(String passenger) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyTrips", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> bookings = sharedPreferences.getStringSet("busBookings", new HashSet<>());
        String booking = passenger + " - " + fromLocation.getText().toString() + " → " + toLocation.getText().toString() +
                " | " + travelDate.getText().toString() + " | " + busCompany.getText().toString() +
                " | Seat: " + seatNumber.getText().toString() + " | Ticket: " + ticketNumber.getText().toString() +
                " | Price: " + price.getText().toString();
        bookings.add(booking);
        editor.putStringSet("busBookings", bookings);
        editor.apply();
    }
}
