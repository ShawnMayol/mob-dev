package ph.edu.uscmontero.montero_day1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class TicketHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_home_page);

        // Initialize ImageViews
        ImageView airplane = findViewById(R.id.airplane);
        ImageView bus = findViewById(R.id.bus);
        ImageView train = findViewById(R.id.train);
        ImageView hotel = findViewById(R.id.hotel);
        ImageView myTrips = findViewById(R.id.mytrips);
        ImageView settings = findViewById(R.id.settings);

        // Set Click Listeners for each ImageView
        airplane.setOnClickListener(view -> openPage(SearchFlight.class));
        bus.setOnClickListener(view -> openPage(SearchBus.class));
        train.setOnClickListener(view -> openPage(SearchTrain.class));
        hotel.setOnClickListener(view -> openPage(SearchHotel.class));
        myTrips.setOnClickListener(view -> openPage(MyTrips.class));

    }

    // Generic method to open an activity
    private void openPage(Class<?> activityClass) {
        Intent intent = new Intent(TicketHomePage.this, activityClass);
        startActivity(intent);
    }
}
