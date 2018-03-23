package com.mjm.workflowkami.add_classes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.mjm.workflowkami.impl_classes.PurchaseOrder;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.PurchaseOrderClass;

public class AddPurchaseOrder extends AppCompatActivity{

    private EditText preq_pord_id, pord_id, pord_dateapproved, preqpm, preqoe, preqpo;
    private TextView reqid, pmid, oeid, poid, subtotal, salestax, total;

    private PurchaseOrderClass pordIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preqpm = (EditText) findViewById(R.id.preqProjMan);
        preqoe = (EditText) findViewById(R.id.preqOfficeEng);
        preqpo = (EditText) findViewById(R.id.preqPOfficer);
        pord_id = (EditText) findViewById(R.id.pord_id);
        preq_pord_id = (EditText) findViewById(R.id.preq_pord_id);
        pord_dateapproved = (EditText) findViewById(R.id.pord_dateapproved);
        subtotal = (TextView) findViewById(R.id.preq_sub_total);
        salestax = (TextView) findViewById(R.id.preq_sales_tax);
        total = (TextView) findViewById(R.id.preq_total);

        reqid = (TextView) findViewById(R.id.requestedby_preq_id);
        pmid = (TextView) findViewById(R.id.preqProjMan_id);
        oeid = (TextView) findViewById(R.id.preqOfficeEng_id);
        poid = (TextView) findViewById(R.id.preqPOfficer_id);

        Intent intent = getIntent();
        pordIntent = (PurchaseOrderClass) intent.getSerializableExtra("pords");

        if (pordIntent != null) {
            preq_pord_id.setText(String.valueOf(pordIntent.getPrequestID().getPreqID()));
            pord_id.setText(String.valueOf(pordIntent.getPordID()));
            pord_dateapproved.setText(pordIntent.getPordapproveddate());
            preqpm.setText(pordIntent.getPordprojman().getFirstname() +" "+ pordIntent.getPordprojman().getLastname());
            preqoe.setText(pordIntent.getPordofficeengr().getFirstname()+" "+pordIntent.getPordofficeengr().getLastname());
            preqpo.setText(pordIntent.getPordpurchofficer().getFirstname()+" "+pordIntent.getPordpurchofficer().getLastname());

            subtotal.setText(String.valueOf(pordIntent.getPordsubtotal()));
            salestax.setText(String.valueOf(pordIntent.getPordsalestax()));
            total.setText(String.valueOf(pordIntent.getPordtotal()));

            pord_dateapproved.setEnabled(false);
            preqpm.setEnabled(false);
            preqoe.setEnabled(false);
            preqpo.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_purchase_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickCancel(View view) {
        AddPurchaseOrder.this.finish();
    }
}
