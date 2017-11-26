package com.mjm.workflowkami.add_classes;


import android.content.Intent;
import android.os.AsyncTask;
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

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.UserClassAdapter;
import com.mjm.workflowkami.impl_classes.Users;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ddc on 11/14/17.
 */

public class AddPRequestHdr extends Fragment {

    private EditText preqProjID, projname, preq_daterequested, preqProjMan, projman_dateapproved, preqOfficeEng, officeengineer_dateapproved, preqPOfficer,
            dateauthorized_purchase, requestedby_preq, preqStatus;
    private RadioGroup rdogrppm, rdogrppo, rdogrpoe, rdogrpstat;
    private RadioButton isapprovedpm, isapprovedpo, isapprovedoe, isapprovedpmt, isapprovedpot, isapprovedoet, statt, stat;
    private Button btnSavePReq;
    private ServiceImpl serviceImpl = new ServiceImpl();
    private PurchaseRequestClass preqIntent = new PurchaseRequestClass();
    private PurchaseRequestClass addPreq = new PurchaseRequestClass();
    private ProjectClass projs = new ProjectClass();
    private PurchaseRequestService preqService = API.getInstance().getPurchaseRequestService();
    private SpinnerDialog reqDialog, pmDialog, poDialog, oeDialog;
    private String selectedReqBy;
    private UserClass req, pm, po, oe;

//    private class UserTask extends AsyncTask<String, Void, UserClass> {
//
//        List<UserClass> cached;
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected UserClass doInBackground(String... strings) {
//
//            do {
//                serviceImpl.FetchUserById(Integer.valueOf(selectedReqBy));
//                try  {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } while (serviceImpl.userClass ==null);
//
//            return serviceImpl.userClass;
//        }
//
//        @Override
//        protected void onPostExecute(UserClass userClassResponseEntity) {
//            req = userClassResponseEntity;
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest, container, false);
        serviceImpl.GetAllUserId();
        serviceImpl.GetAllUsers();
        req = new UserClass();
        pm = new UserClass();
        po = new UserClass();
        oe = new UserClass();
//        final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/user/users";
//        new UserDialogTask().execute(uri);
        rdogrpstat = (RadioGroup) rootView.findViewById(R.id.rdogrpstat);
        int selected0 = rdogrpstat.getCheckedRadioButtonId();
        statt = (RadioButton) rootView.findViewById(selected0);
        stat = (RadioButton) rootView.findViewById(R.id.statt);
//        isapprovedpmt = (RadioButton) rootView.findViewById(R.id.isapprovedpmt);

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

        //Spinner Dialog
        requestedby_preq = (EditText) rootView.findViewById(R.id.requestedby_preq);
        requestedby_preq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reqDialog.showSpinerDialog();
            }
        });
        preqProjID = (EditText) rootView.findViewById(R.id.preq_proj_id);
        projname = (EditText) rootView.findViewById(R.id.preqProjName);
        preq_daterequested = (EditText) rootView.findViewById(R.id.preq_daterequested);
//        preqStatus = (EditText) rootView.findViewById(R.id.preq_status);
        preqProjMan = (EditText) rootView.findViewById(R.id.preqProjMan);
        preqProjMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pmDialog.showSpinerDialog();
            }
        });
        projman_dateapproved = (EditText) rootView.findViewById(R.id.projman_dateapproved);
        preqOfficeEng = (EditText) rootView.findViewById(R.id.preqOfficeEng);
        preqOfficeEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poDialog.showSpinerDialog();
            }
        });
        officeengineer_dateapproved = (EditText) rootView.findViewById(R.id.officeengineer_dateapproved);
        preqPOfficer = (EditText) rootView.findViewById(R.id.preqPOfficer);
        preqOfficeEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oeDialog.showSpinerDialog();
            }
        });
        dateauthorized_purchase = (EditText) rootView.findViewById(R.id.dateauthorized_purchase);
        btnSavePReq = (Button) rootView.findViewById(R.id.btnSavePReq);


        Intent intent = getActivity().getIntent();
        preqIntent = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
        projs = (ProjectClass) intent.getSerializableExtra("projs");


//        Toast.makeText(getActivity(), preq.toString(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
        try {
            if (preqIntent != null) {
                preqProjID.setText(String.valueOf(preqIntent.getProjectID().getProjID()));
                projname.setText(projs.getProjname());
                requestedby_preq.setText(preqIntent.getPreqdate());
                if (preqIntent.getPreqstatus() == true) {
                    int st = rdogrpstat.getCheckedRadioButtonId();
                    statt.setChecked(true);
                    stat = (RadioButton) rootView.findViewById(st);
                }
                if (preqIntent.getPreqprojman().getLastname() != null) {
                    preqProjMan.setText(preqIntent.getPreqprojman().getLastname() + ", " + preqIntent.getPreqprojman().getFirstname());
                }
                if (preqIntent.getIsapprovedpm() == true) {
                    int pm = rdogrppm.getCheckedRadioButtonId();
                    isapprovedpmt.setChecked(true);
                    isapprovedpm = (RadioButton) rootView.findViewById(pm);
                }
                projman_dateapproved.setText(preqIntent.getPreqpmdate());
                if (preqIntent.getPreqofficeengr() != null) {
                    preqOfficeEng.setText(preqIntent.getPreqofficeengr().getLastname() + ", " + preqIntent.getPreqofficeengr().getFirstname());
                }
                if (preqIntent.getIsapprovedoe() == true) {
                    int oe = rdogrpoe.getCheckedRadioButtonId();
                    isapprovedoet.setChecked(true);
                    isapprovedoe = (RadioButton) rootView.findViewById(oe);
                }
                officeengineer_dateapproved.setText(preqIntent.getPreqoedate());
                if (preqIntent.getPreqpurchofficer().getLastname() != null) {
                    preqPOfficer.setText(preqIntent.getPreqpurchofficer().getLastname() + ", " + preqIntent.getPreqpurchofficer().getFirstname());
                }
                if (preqIntent.getIsapprovedpo() == true) {
                    int po = rdogrppo.getCheckedRadioButtonId();
                    isapprovedpot.setChecked(true);
                    isapprovedpo = (RadioButton) rootView.findViewById(po);
                }
                dateauthorized_purchase.setText(preqIntent.getPreqpodate());


//                if (preqIntent.getPreqstatus() == true) {
//                    projname.setEnabled(false);
//                    preq_daterequested.setEnabled(false);
//                    preqProjMan.setEnabled(false);
//                    projman_dateapproved.setEnabled(false);
//                    preqOfficeEng.setEnabled(false);
//                    officeengineer_dateapproved.setEnabled(false);
//                    preqPOfficer.setEnabled(false);
//                    dateauthorized_purchase.setEnabled(false);
//                    for (int i = 0; i < rdogrppm.getChildCount(); i++) {
//                        rdogrppm.getChildAt(i).setEnabled(false);
//                    }
//                    for (int i = 0; i < rdogrppo.getChildCount(); i++) {
//                        rdogrppo.getChildAt(i).setEnabled(false);
//                    }
//                    for (int i = 0; i < rdogrpoe.getChildCount(); i++) {
//                        rdogrpoe.getChildAt(i).setEnabled(false);
//                    }
//                }
            }
        } catch (Exception eo) {
            Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show();
        }


        reqDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        reqDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                selectedReqBy = s;


//                requestedby_preq.setText(s);

            }
        });

//        req = serviceImpl.FetchUserById(Integer.parseInt(selectedReqBy));
//        final String uri = "http://servicemjm-env.ap-southeast-1.elasticbeanstalk.com/user/"+Integer.valueOf(selectedReqBy)+"";
//        new UserTask().execute(uri);

//        Toast.makeText(getActivity(), selectedReqBy.toString(), Toast.LENGTH_LONG).show();

        pmDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        pmDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
//                selectedReqBy = s;
                preqProjMan.setText(s);
//                Toast.makeText(getActivity(), String.valueOf(selectedReqBy), Toast.LENGTH_SHORT).show();
            }
        });


        poDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        poDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
//                selectedReqBy = s;
                preqPOfficer.setText(s);
//                Toast.makeText(getActivity(), String.valueOf(selectedReqBy), Toast.LENGTH_SHORT).show();
            }
        });


        oeDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
        oeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
//                selectedReqBy = s;
                preqOfficeEng.setText(s);
            }
        });

        btnSavePReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPreq = new PurchaseRequestClass(preqIntent.getProjectID(),
                        preq_daterequested.getText().toString().trim(),
                        req,
                        stat.isChecked(),
                        pm,
                        projman_dateapproved.getText().toString().trim(),
                        isapprovedpm.isChecked(),
                        po,
                        dateauthorized_purchase.getText().toString().trim(),
                        isapprovedpo.isChecked(),
                        oe,
                        officeengineer_dateapproved.getText().toString().trim(),
                        isapprovedoet.isChecked());

//                Toast.makeText(getActivity(), addPreq.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), req.getLastname(), Toast.LENGTH_SHORT).show();

            }
        });


        return rootView;
    }

    public void SavePReq(PurchaseRequestClass pr) {

        Call<PurchaseRequestClass> addPreq = preqService.addPreq(pr);

        addPreq.enqueue(new Callback<PurchaseRequestClass>() {
            @Override
            public void onResponse(Call<PurchaseRequestClass> call, Response<PurchaseRequestClass> response) {
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Request has been successfully added!", Toast.LENGTH_SHORT).show();

                    Intent u = new Intent(getActivity(), AddPRequest.class);
                    startActivity(u);
                }
            }

            @Override
            public void onFailure(Call<PurchaseRequestClass> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has been encountered while adding request", Toast.LENGTH_SHORT);
            }
        });
    }

//
//    public void UpdatePReq(int pid, int tid, TaskClass t) {
//        Call<Void> addTask = taskService.editTask(pid, tid, t);
//
//        addTask.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                Toast.makeText(AddTask.this, response.toString(), Toast.LENGTH_LONG).show();
//                if (response.isSuccessful()) {
//                    Toast.makeText(AddTask.this, "Task has been successfully edited!", Toast.LENGTH_SHORT).show();
//
//                    Intent u = new Intent(AddTask.this, Users.class);
//                    startActivity(u);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//                Toast.makeText(AddTask.this, "An error has been encountered while editing task", Toast.LENGTH_SHORT);
//            }
//        });
//    }
    public void onClickCancel(View v) {
        Intent cancel = new Intent(getActivity(), AddPRequest.class);
        startActivity(cancel);

    }
}
