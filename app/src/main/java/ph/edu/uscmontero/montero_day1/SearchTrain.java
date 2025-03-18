package ph.edu.uscmontero.montero_day1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class SearchTrain extends AppCompatActivity {

    private EditText fromEditText, toEditText, travelDateEditText;
    private Button searchTrainBtn, bookTrainBtn;
    private ListView trainListView;
    private TrainAdapter trainAdapter;
    private List<TrainItem> trainList;
    private TrainItem selectedTrain = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);

        fromEditText = findViewById(R.id.fromEditText);
        toEditText = findViewById(R.id.toEditText);
        travelDateEditText = findViewById(R.id.travelDateEditText);
        searchTrainBtn = findViewById(R.id.searchTrainBtn);
        bookTrainBtn = findViewById(R.id.bookTrainBtn);
        trainListView = findViewById(R.id.trainListView);

        trainList = new ArrayList<>();
        trainAdapter = new TrainAdapter(this, trainList);
        trainListView.setAdapter(trainAdapter);

        searchTrainBtn.setOnClickListener(v -> searchTrains());
        travelDateEditText.setOnClickListener(v -> pickDate(travelDateEditText));

        trainListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedTrain = trainList.get(position);
            bookTrainBtn.setVisibility(View.VISIBLE);
        });

        bookTrainBtn.setOnClickListener(v -> {
            if (selectedTrain != null) {
                Intent intent = new Intent(SearchTrain.this, TrainDetails.class);
                intent.putExtra("from", selectedTrain.getFrom());
                intent.putExtra("to", selectedTrain.getTo());
                intent.putExtra("travelDate", selectedTrain.getTravelDate());
                intent.putExtra("trainCompany", selectedTrain.getTrainCompany());
                intent.putExtra("price", selectedTrain.getPrice());
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

    private void searchTrains() {
        String from = fromEditText.getText().toString().trim();
        String to = toEditText.getText().toString().trim();
        String travelDate = travelDateEditText.getText().toString().trim();

        trainList.clear();

        if (from.isEmpty() || to.isEmpty() || travelDate.isEmpty()) {
            Toast.makeText(this, "Please enter Departure Station, Arrival Station, and Travel Date.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sample Data
        trainList.add(new TrainItem(from, to, travelDate, "Rapid Rail", "$30"));
        trainList.add(new TrainItem(from, to, travelDate, "Metro Express", "$40"));

        trainAdapter.notifyDataSetChanged();
    }
}
