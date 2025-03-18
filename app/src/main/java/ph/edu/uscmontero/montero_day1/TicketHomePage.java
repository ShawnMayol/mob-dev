package ph.edu.uscmontero.montero_day1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class TicketHomePage extends AppCompatActivity {

    private SearchView searchView;
    private ListView searchResultsListView;
    private ArrayAdapter<String> searchAdapter;
    private List<String> searchItems, filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_home_page);

        // Initialize SearchView and ListView for search results
        searchView = findViewById(R.id.searchView);
        searchResultsListView = findViewById(R.id.searchResultsListView);

        // Define available search options
        searchItems = new ArrayList<>();
        searchItems.add("Flights");
        searchItems.add("Bus");
        searchItems.add("Trains");
        searchItems.add("Hotels");
        searchItems.add("My Trips");

        filteredItems = new ArrayList<>();
        searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filteredItems);
        searchResultsListView.setAdapter(searchAdapter);

        // Handle Search Query Changes
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterResults(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterResults(newText);
                return false;
            }
        });

        // Handle Click on Search Results
        searchResultsListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = filteredItems.get(position);
            navigateToPage(selectedItem);
        });

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

    // Filter search results
    private void filterResults(String query) {
        filteredItems.clear();
        if (!TextUtils.isEmpty(query)) {
            for (String item : searchItems) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.add(item);
                }
            }
            searchResultsListView.setVisibility(View.VISIBLE);
        } else {
            searchResultsListView.setVisibility(View.GONE);
        }
        searchAdapter.notifyDataSetChanged();
    }

    // Navigate to corresponding pages
    private void navigateToPage(String page) {
        Intent intent = null;
        switch (page) {
            case "Flights":
                intent = new Intent(TicketHomePage.this, SearchFlight.class);
                break;
            case "Bus":
                intent = new Intent(TicketHomePage.this, SearchBus.class);
                break;
            case "Trains":
                intent = new Intent(TicketHomePage.this, SearchTrain.class);
                break;
            case "Hotels":
                intent = new Intent(TicketHomePage.this, SearchHotel.class);
                break;
            case "My Trips":
                intent = new Intent(TicketHomePage.this, MyTrips.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
