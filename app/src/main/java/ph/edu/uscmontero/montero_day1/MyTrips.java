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

        // Load bookings from SharedPreferences
        loadBookings();
    }

    private void loadBookings() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyTrips", Context.MODE_PRIVATE);
        Set<String> savedTrips = sharedPreferences.getStringSet("flightBookings", null);

        if (savedTrips != null && !savedTrips.isEmpty()) {
            tripsList.addAll(savedTrips);
            tripsAdapter.notifyDataSetChanged();
            noBookingsText.setVisibility(View.GONE); // Hide "No trips booked" message
        } else {
            noBookingsText.setVisibility(View.VISIBLE); // Show "No trips booked" message
        }
    }
}
