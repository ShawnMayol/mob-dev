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

public class TrainDetails extends AppCompatActivity {

    private TextView fromLocation, toLocation, travelDate, trainCompany, price, seatNumber, ticketNumber;
    private EditText passengerName;
    private Button confirmTrainBookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_details);

        // Initialize UI Elements
        fromLocation = findViewById(R.id.fromLocation);
        toLocation = findViewById(R.id.toLocation);
        travelDate = findViewById(R.id.travelDate);
        trainCompany = findViewById(R.id.trainCompany);
        price = findViewById(R.id.price);
        seatNumber = findViewById(R.id.seatNumber);
        ticketNumber = findViewById(R.id.ticketNumber);
        passengerName = findViewById(R.id.passengerName);
        confirmTrainBookingBtn = findViewById(R.id.confirmTrainBookingBtn);

        // Get Data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            fromLocation.setText(intent.getStringExtra("from"));
            toLocation.setText(intent.getStringExtra("to"));
            travelDate.setText("Travel Date: " + intent.getStringExtra("travelDate"));
            trainCompany.setText("Train Company: " + intent.getStringExtra("trainCompany"));
            price.setText(intent.getStringExtra("price"));

            // Generate random seat and ticket number
            seatNumber.setText(generateRandomSeat());
            ticketNumber.setText(generateRandomTicketNumber());
        }

        // Confirm Booking Action
        confirmTrainBookingBtn.setOnClickListener(v -> {
            String name = passengerName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(TrainDetails.this, "Please enter passenger name!", Toast.LENGTH_SHORT).show();
                return;
            }

            saveBookingToSharedPreferences(name);

            // Redirect to TicketHomePage
            Intent homeIntent = new Intent(TrainDetails.this, TicketHomePage.class);
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
        Set<String> bookings = sharedPreferences.getStringSet("trainBookings", new HashSet<>());
        String booking = passenger + " - " + fromLocation.getText().toString() + " → " + toLocation.getText().toString() +
                " | " + travelDate.getText().toString() + " | " + trainCompany.getText().toString() +
                " | Seat: " + seatNumber.getText().toString() + " | Ticket: " + ticketNumber.getText().toString() +
                " | Price: " + price.getText().toString();
        bookings.add(booking);
        editor.putStringSet("trainBookings", bookings);
        editor.apply();
    }
}
