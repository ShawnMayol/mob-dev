package ph.edu.uscmontero.montero_day1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyTrips extends AppCompatActivity {

    private ListView myTripsListView;
    private TextView noBookingsText;
    private List<String> tripsList;
    private ArrayAdapter<String> tripsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        myTripsListView = findViewById(R.id.myTripsListView);
        noBookingsText = findViewById(R.id.noBookingsText);

        tripsList = new ArrayList<>();
        tripsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tripsList);
        myTripsListView.setAdapter(tripsAdapter);

        // Load all types of bookings
        loadBookings();
    }

    private void loadBookings() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyTrips", Context.MODE_PRIVATE);

        // Load Flight Bookings
        Set<String> flightBookings = sharedPreferences.getStringSet("flightBookings", null);
        if (flightBookings != null && !flightBookings.isEmpty()) {
            tripsList.add("✈️ Flight Bookings:");
            tripsList.addAll(flightBookings);
        }

        // Load Bus Bookings
        Set<String> busBookings = sharedPreferences.getStringSet("busBookings", null);
        if (busBookings != null && !busBookings.isEmpty()) {
            tripsList.add("🚌 Bus Bookings:");
            tripsList.addAll(busBookings);
        }

        // Load Train Bookings
        Set<String> trainBookings = sharedPreferences.getStringSet("trainBookings", null);
        if (trainBookings != null && !trainBookings.isEmpty()) {
            tripsList.add("🚆 Train Bookings:");
            tripsList.addAll(trainBookings);
        }

        // Load Hotel Bookings
        Set<String> hotelBookings = sharedPreferences.getStringSet("hotelBookings", null);
        if (hotelBookings != null && !hotelBookings.isEmpty()) {
            tripsList.add("🏨 Hotel Bookings:");
            tripsList.addAll(hotelBookings);
        }

        if (tripsList.isEmpty()) {
            noBookingsText.setVisibility(View.VISIBLE);
        } else {
            tripsAdapter.notifyDataSetChanged();
            noBookingsText.setVisibility(View.GONE);
        }
    }
}
