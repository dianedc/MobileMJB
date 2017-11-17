package com.example.jasper.capstone.add_classes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasper.capstone.R;
import com.example.jasper.capstone.impl_classes.PurchaseRequest;
import com.example.jasper.capstone.model_classes.PurchaseRequestClass;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestHdr extends Fragment {

    private EditText projname, preq_daterequested, preqProjMan, projman_dateapproved, preqOfficeEng, officeengineer_dateapproved, preqPOfficer,
            dateauthorized_purchase;
    PurchaseRequestClass preq = new PurchaseRequestClass();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest, container, false);

        projname = (EditText) rootView.findViewById(R.id.projname);
        preq_daterequested = (EditText) rootView.findViewById(R.id.preq_daterequested);
        preqProjMan = (EditText) rootView.findViewById(R.id.preqProjMan);
        projman_dateapproved = (EditText) rootView.findViewById(R.id.projman_dateapproved);
        preqOfficeEng = (EditText) rootView.findViewById(R.id.preqOfficeEng);
        officeengineer_dateapproved = (EditText) rootView.findViewById(R.id.officeengineer_dateapproved);
        preqPOfficer = (EditText) rootView.findViewById(R.id.preqPOfficer);
        dateauthorized_purchase = (EditText) rootView.findViewById(R.id.dateauthorized_purchase);

        Intent intent = getActivity().getIntent();
        preq = (PurchaseRequestClass) intent.getSerializableExtra("preqs");

        Toast.makeText(getActivity(), preq.toString(), Toast.LENGTH_SHORT).show();
        if (preq != null) {
//            projname.setText(preq);
            preq_daterequested.setText(preq.getPreqdate());
//            preqProjMan.setText(preq.getPreqprojman().getLastname());
            projman_dateapproved.setText(preq.getPreqpmdate());
//            preqOfficeEng.setText(preq.getPreqofficeengr().getLastname());
            officeengineer_dateapproved.setText(preq.getPreqoedate());
//            preqPOfficer.setText(preq.getPreqpurchofficer().getLastname());
            dateauthorized_purchase.setText(preq.getPreqpodate());
        }
        return rootView;
    }
}
