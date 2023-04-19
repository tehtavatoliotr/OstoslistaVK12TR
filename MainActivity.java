package com.example.ostoslistaoliootuomasr;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DemoAdapter demoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> items = new LinkedList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        demoAdapter = new DemoAdapter(items);
        recyclerView.setAdapter(demoAdapter);

        EditText editText = findViewById(R.id.editText);
        findViewById(R.id.add).setOnClickListener(view -> {
            String newItem = editText.getText().toString();
            items.add(newItem);
            demoAdapter.notifyItemInserted(items.size()-1);
            editText.setText("");
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort) {
            demoAdapter.sortAlphabetically();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
