package com.mjm.workflowkami.add_classes;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestItemService;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPrequestDtlItem extends AppCompatActivity {

    private EditText preq_dtl_id, preq_dtl_desc, preq_dtl_qty, preq_dtl_unit, preq_dtl_job, preq_dtl_uni_price, preq_id_dtl;
    private TextView preq_dtl_line_tot;
    private Button btnCancel, btnSavePreqDtlItem;

    private PurchaseRequestClass pr = new PurchaseRequestClass();
    private PurchaseRequestItemClass pi = new PurchaseRequestItemClass();
    private PurchaseRequestItemService pService = API.getInstance().getPurchaseRequestItemService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prequest_dtl_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAddPreqDtlItem);
        toolbar.setTitle("Purchase Request Details");
        setSupportActionBar(toolbar);


        preq_id_dtl = (EditText) findViewById(R.id.preq_id_dtl);
        preq_dtl_id = (EditText) findViewById(R.id.preq_dtl_id);
        preq_dtl_desc = (EditText) findViewById(R.id.preq_dtl_desc);
        preq_dtl_qty = (EditText) findViewById(R.id.preq_dtl_qty);
        preq_dtl_unit = (EditText) findViewById(R.id.preq_dtl_unit);
        preq_dtl_job = (EditText) findViewById(R.id.preq_dtl_job);
        preq_dtl_uni_price = (EditText) findViewById(R.id.preq_dtl_uni_price);
        preq_dtl_line_tot = (TextView) findViewById(R.id.preq_dtl_line_tot);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSavePreqDtlItem = (Button) findViewById(R.id.btnSavePreqDtlItem);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(AddPrequestDtlItem.this, AddPRequest.class);
                startActivity(cancel);
            }
        });

        Intent piIntent = getIntent();
        pi = (PurchaseRequestItemClass) piIntent.getSerializableExtra("item");
        Intent prIntent = getIntent();
        pr = (PurchaseRequestClass) prIntent.getSerializableExtra("preqs");

//        Toast.makeText(AddPrequestDtlItem.this, preq.toString(), Toast.LENGTH_LONG).show();
//        Toast.makeText(AddPrequestDtlItem.this, pi.toString(), Toast.LENGTH_LONG).show();
        if(pr != null) {
            Toast.makeText(AddPrequestDtlItem.this, pr.toString(), Toast.LENGTH_LONG).show();
            preq_id_dtl.setText(String.valueOf(pr.getPreqID()));
        }

        if (pi != null) {
            Toast.makeText(AddPrequestDtlItem.this, String.valueOf(pi.getPrequestID().getPreqID()), Toast.LENGTH_LONG).show();
            preq_dtl_id.setText(String.valueOf(pi.getPreqItemID()));
            preq_dtl_desc.setText(pi.getPreqdesc());
            preq_dtl_qty.setText(String.valueOf(pi.getPreqqty()));
            preq_dtl_unit.setText(pi.getPrequnit());
            preq_dtl_job.setText(pi.getPreqjob());
            preq_dtl_uni_price.setText(pi.getPrequnitprice().toString());
            preq_dtl_line_tot.setText(pi.getPreqlinetotal().toString());
    }

        preq_dtl_uni_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int input = Integer.valueOf(preq_dtl_uni_price.getText().toString());
                int input2 = Integer.valueOf(preq_dtl_qty.getText().toString());
                int res = input * input2;
                preq_dtl_line_tot.setText(String.valueOf(res));

            }
        });


        final BigDecimal lineTot = new BigDecimal(preq_dtl_line_tot.getText().toString());
        btnSavePreqDtlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!preq_dtl_id.getText().toString().matches("")) {
                    //update
                    pi = new PurchaseRequestItemClass(Integer.valueOf(pi.getPreqItemID()),
                            pi.getPrequestID(),
                            Integer.valueOf(preq_dtl_qty.getText().toString()),
                            preq_dtl_unit.getText().toString().trim(),
                            preq_dtl_desc.getText().toString().trim(),
                            preq_dtl_job.getText().toString().trim(),
                            Double.valueOf(preq_dtl_uni_price.getText().toString()),
                            lineTot);
                    UpdateItem(pi.getPrequestID().getPreqID(), pi.getPreqItemID(), pi);
                    Toast.makeText(AddPrequestDtlItem.this, "Purchase item has been successfully edited!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    //add
                    pi = new PurchaseRequestItemClass(pi.getPrequestID(),
                            Integer.valueOf(preq_dtl_qty.getText().toString()),
                            preq_dtl_unit.getText().toString().trim(),
                            preq_dtl_desc.getText().toString().trim(),
                            preq_dtl_job.getText().toString().trim(),
                            Double.valueOf(preq_dtl_uni_price.getText().toString()),
                            lineTot);
                    AddItem(pi.getPrequestID().getPreqID(), pi);
                    Toast.makeText(AddPrequestDtlItem.this, "Saved!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_prequest_dtl_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_back_dtl_item:
                finish();
                return true;
//            case R.id.action_settings:
//                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void AddItem(int preqid, PurchaseRequestItemClass pi) {
        Call<PurchaseRequestItemClass> addItem = pService.addPReqItem(preqid, pi);

        addItem.enqueue(new Callback<PurchaseRequestItemClass>() {
            @Override
            public void onResponse(Call<PurchaseRequestItemClass> call, Response<PurchaseRequestItemClass> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(AddPrequestDtlItem.this, "Saved!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PurchaseRequestItemClass> call, Throwable t) {
                Toast.makeText(AddPrequestDtlItem.this, "An error has been encountered while adding purchase item", Toast.LENGTH_SHORT);
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddPrequestDtlItem.this);
//                alertDialogBuilder.setMessage(t.toString());
//                alertDialogBuilder.setCancelable(true);
//                alertDialogBuilder.show();
            }
        });
    }

    public void UpdateItem(int preqid, int item, PurchaseRequestItemClass pi) {
        Call<Void> edtItem = pService.editPReqItem(preqid, item, pi);

        edtItem.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddPrequestDtlItem.this, "Purchase item has been successfully edited!", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddPrequestDtlItem.this);
//                    alertDialogBuilder.setMessage("Purchase item has been successfully edited!");
//                    alertDialogBuilder.setCancelable(true);
//                    alertDialogBuilder.show();

                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddPrequestDtlItem.this, "An error has been encountered while editing purchase item", Toast.LENGTH_SHORT);
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddPrequestDtlItem.this);
//                alertDialogBuilder.setMessage(t.toString());
//                alertDialogBuilder.setCancelable(true);
//                alertDialogBuilder.show();
            }
        });
    }
}
