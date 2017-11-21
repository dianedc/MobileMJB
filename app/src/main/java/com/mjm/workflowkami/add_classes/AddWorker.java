package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mjm.workflowkami.Fragments.Worker;
import com.mjm.workflowkami.R;

public class AddWorker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddWorker);
        toolbar.setTitle("Worker");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_worker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back_worker:
                startActivity(new Intent(this, Worker.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
