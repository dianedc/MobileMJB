package com.mjm.workflowkami.add_classes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mjm.workflowkami.R;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestHdr extends Fragment {

    private EditText projname, preq_daterequested, preqProjMan, projman_dateapproved, preqOfficeEng, officeengineer_dateapproved, preqPOfficer,
            dateauthorized_purchase, requestedby_preq;
    private RadioGroup rdogrppm, rdogrppo, rdogrpoe;
    private RadioButton isapprovedpm, isapprovedpo, isapprovedoe, isapprovedpmt, isapprovedpot, isapprovedoet;
    private Button btnSavePReq;
    PurchaseRequestClass preq = new PurchaseRequestClass();
    ProjectClass projs = new ProjectClass();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest, container, false);


        rdogrppm = (RadioGroup) rootView.findViewById(R.id.rdogrppm);
        int selected1 = rdogrppm.getCheckedRadioButtonId();
        isapprovedpm = (RadioButton) rootView.findViewById(selected1);
        isapprovedpmt = (RadioButton) rootView.findViewById(R.id.isapprovedpmt);

        rdogrpoe = (RadioGroup)  rootView.findViewById(R.id.rdogrpoe);
        int selected2 = rdogrpoe.getCheckedRadioButtonId();
        isapprovedoe = (RadioButton) rootView.findViewById(selected2);
        isapprovedpot = (RadioButton) rootView.findViewById(R.id.isapprovedpot);

        rdogrppo = (RadioGroup)  rootView.findViewById(R.id.rdogrppo);
        int selected3 = rdogrppo.getCheckedRadioButtonId();
        isapprovedpo = (RadioButton) rootView.findViewById(selected3);
        isapprovedoet = (RadioButton) rootView.findViewById(R.id.isapprovedoet);

        requestedby_preq = (EditText) rootView.findViewById(R.id.requestedby_preq);
        projname = (EditText) rootView.findViewById(R.id.preqProjName);
        preq_daterequested = (EditText) rootView.findViewById(R.id.preq_daterequested);
        preqProjMan = (EditText) rootView.findViewById(R.id.preqProjMan);
        projman_dateapproved = (EditText) rootView.findViewById(R.id.projman_dateapproved);
        preqOfficeEng = (EditText) rootView.findViewById(R.id.preqOfficeEng);
        officeengineer_dateapproved = (EditText) rootView.findViewById(R.id.officeengineer_dateapproved);
        preqPOfficer = (EditText) rootView.findViewById(R.id.preqPOfficer);
        dateauthorized_purchase = (EditText) rootView.findViewById(R.id.dateauthorized_purchase);
        btnSavePReq = (Button) rootView.findViewById(R.id.btnSavePReq);



        Intent intent = getActivity().getIntent();
        preq = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
        projs = (ProjectClass) intent.getSerializableExtra("projs");


//        Toast.makeText(getActivity(), preq.toString(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
        if (preq != null) {
            projname.setText(projs.getProjname());
            requestedby_preq.setText(preq.getPreqdate());
            if (preq.getPreqprojman().getLastname() != null) {
                preqProjMan.setText(preq.getPreqprojman().getLastname() + ", " + preq.getPreqprojman().getFirstname());
            }
            if (preq.getIsapprovedpm() == true) {
//                int pm = rdogrppm.getCheckedRadioButtonId();
                isapprovedpmt.setChecked(true);
            }
            projman_dateapproved.setText(preq.getPreqpmdate());
            if (preq.getPreqofficeengr() != null) {
                preqOfficeEng.setText(preq.getPreqofficeengr().getLastname() + ", " + preq.getPreqofficeengr().getFirstname());
            }
            if (preq.getIsapprovedoe() == true) {
//                int pm = rdogrppm.getCheckedRadioButtonId();
                isapprovedoet.setChecked(true);
            }
            officeengineer_dateapproved.setText(preq.getPreqoedate());
            if (preq.getPreqpurchofficer().getLastname() != null) {
                preqPOfficer.setText(preq.getPreqpurchofficer().getLastname() + ", " + preq.getPreqpurchofficer().getFirstname());
            }
            if (preq.getIsapprovedpo() == true) {
//                int pm = rdogrppm.getCheckedRadioButtonId();
                isapprovedpot.setChecked(true);
            }
            dateauthorized_purchase.setText(preq.getPreqpodate());

            if (preq.getPreqstatus() == true) {
                projname.setEnabled(false);
                preq_daterequested.setEnabled(false);
                preqProjMan.setEnabled(false);
                projman_dateapproved.setEnabled(false);
                preqOfficeEng.setEnabled(false);
                officeengineer_dateapproved.setEnabled(false);
                preqPOfficer.setEnabled(false);
                dateauthorized_purchase.setEnabled(false);
                for (int i = 0; i < rdogrppm.getChildCount(); i++) {
                    rdogrppm.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rdogrppo.getChildCount(); i++) {
                    rdogrppo.getChildAt(i).setEnabled(false);
                }
                for (int i = 0; i < rdogrpoe.getChildCount(); i++) {
                    rdogrpoe.getChildAt(i).setEnabled(false);
                }
            }
        }

        btnSavePReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rootView;
    }

//    public void onClickListenerButton(){
//        rdogrppm = (RadioGroup) rootView.findViewById(R.id.rdogrppm);
//        int selected1 = rdogrppm.getCheckedRadioButtonId();
//        isapprovedpm = (RadioButton) rootView.findViewById(selected1);
//
//        rdogrpoe = (RadioGroup)  rootView.findViewById(R.id.rdogrpoe);
//        int selected2 = rdogrpoe.getCheckedRadioButtonId();
//        isapprovedoe = (RadioButton) rootView.findViewById(selected2);
//
//        rdogrppo = (RadioGroup)  rootView.findViewById(R.id.rdogrppo);
//        int selected3 = rdogrppo.getCheckedRadioButtonId();
//        isapprovedpo = (RadioButton) rootView.findViewById(selected3);
//    }
    public void onClickCancel(View v) {
        Intent cancel = new Intent(getActivity(), AddPRequest.class);
        startActivity(cancel);

    }
}
