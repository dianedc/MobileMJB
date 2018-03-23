package com.mjm.workflowkami.add_classes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mjm.workflowkami.API;
import com.mjm.workflowkami.R;
import com.mjm.workflowkami.ServiceImpl;
import com.mjm.workflowkami.adapter_classes.OfficeEngSpinnerAdapter;
import com.mjm.workflowkami.adapter_classes.ProjManSpinnerAdapter;
import com.mjm.workflowkami.adapter_classes.ProjectSpinnerAdapter;
import com.mjm.workflowkami.adapter_classes.PurchaseOfficerSpinnerAdapter;
import com.mjm.workflowkami.adapter_classes.ReqBYSpinnerAdapter;
import com.mjm.workflowkami.adapter_classes.UserClassAdapter;
//import com.mjm.workflowkami.impl_classes.PurchaseRequest;
import com.mjm.workflowkami.impl_classes.Users;
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestClass;
import com.mjm.workflowkami.model_classes.RoleClass;
import com.mjm.workflowkami.model_classes.UserClass;
import com.mjm.workflowkami.service_classes.PurchaseRequestService;
import com.mjm.workflowkami.service_classes.RoleService;
import com.mjm.workflowkami.service_classes.UserService;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
//      preqProjMan  preqOfficeEng preqPOfficer  requestedby_preq
//    private EditText  preqID,  projectID,  preqapproveddate,  preqrequesteddate,  preqrequestedby,  preqstatus,  preqprojman,  preqpmdate,  isapprovedpm,  preqpurchofficer,  preqpodate, isapprovedpo, preqofficeengr,  preqoedate,  isapprovedoe, preqsubtotal, preqsalestax, preqtotal;
    private EditText preqID, preqProjID, preq_daterequested, preq_dateapproved,  projman_dateapproved, officeengineer_dateapproved, dateauthorized_purchase, preqStatus;
    private TextView requestedby_preq_id, preqProjMan_id, preqOfficeEng_id, preqPOfficer_id,  preq_sub_total, preq_sales_tax, preq_total;
    private TextView isApproved, isWaiting, isDeclined;
    private RadioGroup rdogrppm, rdogrppo, rdogrpoe, rdogrpstat;
    private RadioButton rdopm, rdooe, rdopo, spm, soe, spo, fpm, foe, fpo;
    private Button btnSavePReq;
    private SpinnerDialog reqDialog, pmDialog, poDialog, oeDialog;
    private Spinner preq_reqby, preq_proj_man, preq_office_eng, preq_po, preq_proj_name;

    private ServiceImpl serviceImpl = new ServiceImpl();
    private PurchaseRequestClass preqIntent = new PurchaseRequestClass();
    private PurchaseRequestClass addPreq;
    private ProjectClass projs = new ProjectClass();
    private PurchaseRequestService preqService = API.getInstance().getPurchaseRequestService();

    private ProgressDialog progressDialog;

    private UserClass req, pm, po, oe;
    private ProjectClass projects;
    private Boolean isstat, ispm, ispo, isoe;
    private DateFormat dateFormat;

    private UserClass roleUsers;
    private List<UserClass> pmrole,reqrole, porole, oerole;
    private UserService userService = API.getInstance().getUserService();
    private List<ProjectClass> proj;

    private class ReqByRole extends AsyncTask<String, Void, List<UserClass>> {

        @Override
        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage("Loading. Please wait... ");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
        }

        @Override
        protected List<UserClass> doInBackground(String... strings) {

            do {
                serviceImpl.GetAllUsers();

                try  {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (serviceImpl.usersList == null);
            return serviceImpl.usersList;
        }
        @Override
        protected void onPostExecute(List<UserClass> workerClassResponseEntity) {
//            progressDialog.dismiss();
            ReqBYSpinnerAdapter adapter = new ReqBYSpinnerAdapter(getActivity(), workerClassResponseEntity);
            preq_reqby.setAdapter(adapter);
        }
    }

    private class ProjManRole extends AsyncTask<String, Void, List<UserClass>> {

        @Override
        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage("Loading. Please wait... ");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
        }

        @Override
        protected List<UserClass> doInBackground(String... strings) {

            do {
                Call<List<UserClass>> getRolesList = userService.getByRoles(3);
                getRolesList.enqueue(new Callback<List<UserClass>>() {
                    @Override
                    public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                        List<UserClass> roles = response.body();

                        try {
                            for (int i = 0; i < roles.size(); i++) {
                                pmrole.add(new UserClass(roles.get(i).getUserID(),
                                        roles.get(i).getFirstname(),
                                        roles.get(i).getLastname(),
                                        roles.get(i).getMiddlename(),
                                        roles.get(i).getEmail(),
                                        roles.get(i).getPassword(),
                                        roles.get(i).getUserstatus()));
                            }
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserClass>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                try  {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (pmrole == null);
            return pmrole;
        }
        @Override
        protected void onPostExecute(List<UserClass> workerClassResponseEntity) {
//            progressDialog.dismiss();
            ProjManSpinnerAdapter adapter = new ProjManSpinnerAdapter(getActivity(), workerClassResponseEntity);
            preq_proj_man.setAdapter(adapter);
        }
    }

    private class OfficeEngRole extends AsyncTask<String, Void, List<UserClass>> {

        @Override
        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage("Loading. Please wait... ");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
        }

        @Override
        protected List<UserClass> doInBackground(String... strings) {

            do {
                Call<List<UserClass>> getRolesList = userService.getByRoles(6);
                getRolesList.enqueue(new Callback<List<UserClass>>() {
                    @Override
                    public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                        List<UserClass> roles = response.body();

                        try {
                            for (int i = 0; i < roles.size(); i++) {
                                oerole.add(new UserClass(roles.get(i).getUserID(),
                                        roles.get(i).getFirstname(),
                                        roles.get(i).getLastname(),
                                        roles.get(i).getMiddlename(),
                                        roles.get(i).getEmail(),
                                        roles.get(i).getPassword(),
                                        roles.get(i).getUserstatus()));
                            }
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserClass>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                try  {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (oerole == null);
            return oerole;
        }
        @Override
        protected void onPostExecute(List<UserClass> workerClassResponseEntity) {
//            progressDialog.dismiss();
            OfficeEngSpinnerAdapter adapter = new OfficeEngSpinnerAdapter(getActivity(), workerClassResponseEntity);
            preq_office_eng.setAdapter(adapter);
        }
    }

    private class PORole extends AsyncTask<String, Void, List<UserClass>> {

        @Override
        protected void onPreExecute() {
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage("Loading. Please wait... ");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
        }

        @Override
        protected List<UserClass> doInBackground(String... strings) {

            do {
                Call<List<UserClass>> getRolesList = userService.getByRoles(5);
                getRolesList.enqueue(new Callback<List<UserClass>>() {
                    @Override
                    public void onResponse(Call<List<UserClass>> call, Response<List<UserClass>> response) {
                        List<UserClass> roles = response.body();

                        try {
                            for (int i = 0; i < roles.size(); i++) {
                                porole.add(new UserClass(roles.get(i).getUserID(),
                                        roles.get(i).getFirstname(),
                                        roles.get(i).getLastname(),
                                        roles.get(i).getMiddlename(),
                                        roles.get(i).getEmail(),
                                        roles.get(i).getPassword(),
                                        roles.get(i).getUserstatus()));
                            }
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserClass>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                try  {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (porole == null);
            return porole;
        }
        @Override
        protected void onPostExecute(List<UserClass> workerClassResponseEntity) {
//            progressDialog.dismiss();
            PurchaseOfficerSpinnerAdapter adapter = new PurchaseOfficerSpinnerAdapter(getActivity(), workerClassResponseEntity);
            preq_po.setAdapter(adapter);
        }
    }

    public class ShowProjects extends AsyncTask<String, Void, List<ProjectClass>> {

//        List<ProjectClass> cached;
        @Override
        protected void onPreExecute() {
//            if (cached == null) {
//                showLoadingDialog();
//            } else {
//                super.onPostExecute(cached);
//            }
        }

        @Override
        protected List<ProjectClass> doInBackground(String... strings) {

            do {
                serviceImpl.GetAllActiveProjects();
                try  {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (serviceImpl.projectsList ==null);

            return serviceImpl.projectsList;
        }

        @Override
        protected void onPostExecute(List<ProjectClass> projectClassResponseEntity) {
//            dismissProgressDialog();
            preq_proj_name.setAdapter(new ProjectSpinnerAdapter(getActivity(), projectClassResponseEntity));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_prequest, container, false);

        pmrole = new ArrayList<UserClass>();
        reqrole = new ArrayList<UserClass>();
        porole = new ArrayList<UserClass>();
        oerole = new ArrayList<UserClass>();
        proj = new ArrayList<ProjectClass>();

        new ProjManRole().execute();
        new PORole().execute();
        new ReqByRole().execute();
        new OfficeEngRole().execute();
        new ShowProjects().execute();

        serviceImpl.GetAllUserId();
        serviceImpl.GetAllUsers();
        addPreq = new PurchaseRequestClass();
        req = new UserClass();
        pm = new UserClass();
        po = new UserClass();
        oe = new UserClass();
        projects = new ProjectClass();

        dateFormat = new SimpleDateFormat("yyyy-dd-MM");

//        rdogrpstat = (RadioGroup) rootView.findViewById(R.id.rdogrpstat);//
//        int selected0 = rdogrpstat.getCheckedRadioButtonId();
//        rdostat = (RadioButton) rootView.findViewById(selected0);
//        sstat = (RadioButton) rootView.findViewById(R.id.statt);
//
//        sstat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                preq_dateapproved.setText(dateFormat.format(new Date()));
//            }
//        });

        rdogrppm = (RadioGroup) rootView.findViewById(R.id.rdogrppm);
        int selected1 = rdogrppm.getCheckedRadioButtonId();
        rdopm = (RadioButton) rootView.findViewById(selected1);
        spm = (RadioButton) rootView.findViewById(R.id.isapprovedpmt);
        fpm = (RadioButton) rootView.findViewById(R.id.isapprovedpmf);

        spm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projman_dateapproved.setText(dateFormat.format(new Date()));
            }
        });

        rdogrpoe = (RadioGroup)  rootView.findViewById(R.id.rdogrpoe);
        int selected2 = rdogrpoe.getCheckedRadioButtonId();
        rdooe = (RadioButton) rootView.findViewById(selected2);
        soe = (RadioButton) rootView.findViewById(R.id.isapprovedoet);
        foe = (RadioButton) rootView.findViewById(R.id.isapprovedoef);

        soe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                officeengineer_dateapproved.setText(dateFormat.format(new Date()));
            }
        });

        rdogrppo = (RadioGroup)  rootView.findViewById(R.id.rdogrppo);
        int selected3 = rdogrppo.getCheckedRadioButtonId();
        rdopo = (RadioButton) rootView.findViewById(selected3);
        spo = (RadioButton) rootView.findViewById(R.id.isapprovedpot);
        fpo = (RadioButton) rootView.findViewById(R.id.isapprovedpof);

        spo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateauthorized_purchase.setText(dateFormat.format(new Date()));
            }
        });

        //Spinner Dialog
//        requestedby_preq = (EditText) rootView.findViewById(R.id.requestedby_preq);//
//        requestedby_preq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                reqDialog.showSpinerDialog();
//            }
//        });
//
//        preqProjMan = (EditText) rootView.findViewById(R.id.preqProjMan);
//        preqProjMan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pmDialog.showSpinerDialog();
//            }
//        });
//
//        preqOfficeEng = (EditText) rootView.findViewById(R.id.preqOfficeEng);
//        preqOfficeEng.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                oeDialog.showSpinerDialog();
//            }
//        });
//
//        preqPOfficer = (EditText) rootView.findViewById(R.id.preqPOfficer);
//        preqPOfficer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                poDialog.showSpinerDialog();
//            }
//        });

        preqID = (EditText) rootView.findViewById(R.id.preq_id);
        preqProjID = (EditText) rootView.findViewById(R.id.preq_proj_id);
        preq_daterequested = (EditText) rootView.findViewById(R.id.preq_daterequested);
        preq_dateapproved = (EditText) rootView.findViewById(R.id.preq_dateapproved);
        projman_dateapproved = (EditText) rootView.findViewById(R.id.projman_dateapproved);
        officeengineer_dateapproved = (EditText) rootView.findViewById(R.id.officeengineer_dateapproved);
        dateauthorized_purchase = (EditText) rootView.findViewById(R.id.dateauthorized_purchase);
        btnSavePReq = (Button) rootView.findViewById(R.id.btnSavePReq);
        preq_sub_total = (TextView) rootView.findViewById(R.id.preq_sub_total);
        preq_sales_tax = (TextView) rootView.findViewById(R.id.preq_sales_tax);
        preq_total = (TextView) rootView.findViewById(R.id.preq_total);

        requestedby_preq_id = (TextView) rootView.findViewById(R.id.requestedby_preq_id);
        preqProjMan_id = (TextView) rootView.findViewById(R.id.preqProjMan_id);
        preqOfficeEng_id = (TextView) rootView.findViewById(R.id.preqOfficeEng_id);
        preqPOfficer_id = (TextView) rootView.findViewById(R.id.preqPOfficer_id);

        preq_reqby = (Spinner) rootView.findViewById(R.id.preq_reqby);
        preq_proj_man = (Spinner) rootView.findViewById(R.id.preq_proj_man);
        preq_po = (Spinner) rootView.findViewById(R.id.preq_po);
        preq_office_eng = (Spinner) rootView.findViewById(R.id.preq_office_eng);
        preq_proj_name = (Spinner) rootView.findViewById(R.id.preq_proj_name);

        isApproved = (TextView) rootView.findViewById(R.id.textViewappr);
        isWaiting = (TextView) rootView.findViewById(R.id.textViewwait);
        isDeclined = (TextView) rootView.findViewById(R.id.textViewdec);


        Intent intent = getActivity().getIntent();
        preqIntent = (PurchaseRequestClass) intent.getSerializableExtra("preqs");
        Intent projsintent = getActivity().getIntent();
        projs = (ProjectClass) projsintent.getSerializableExtra("projects");
//        if (projs != null) {
//            Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
//        }
        try {
            if (preqIntent != null ) {


                preqID.setText(String.valueOf(preqIntent.getPreqID()));
                preqProjID.setText(String.valueOf(preqIntent.getProjectID().getProjID()));
//                preqProjID.setText(String.valueOf(projs.getProjID()));
                preq_dateapproved.setText(preqIntent.getPreqapproveddate());

                //Set Spinner
                for (int i = 0; i < preq_reqby.getCount(); i++) {
                    if (preq_reqby.getItemAtPosition(i).toString().equals(preqIntent.getPreqrequestedby())) {
                        preq_reqby.setSelection(i);
                        break;
                    }
                }

                for (int i = 0; i < preq_proj_name.getCount(); i++) {
                    if (preq_proj_name.getItemAtPosition(i).toString().equals(preqIntent.getProjectID())) {
                        preq_proj_name.setSelection(i);
                        break;
                    }
                }
//                requestedby_preq.setText(preqIntent.getPreqrequestedby().getLastname() + ", " + preqIntent.getPreqrequestedby().getFirstname());
                requestedby_preq_id.setText(String.valueOf(preqIntent.getPreqrequestedby().getUserID()));
                preq_daterequested.setText(preqIntent.getPreqrequesteddate());

                for (int i = 0; i < preq_proj_man.getCount(); i++) {
                    if (preq_proj_man.getItemAtPosition(i).toString().equals(preqIntent.getPreqprojman())) {
                        preq_proj_man.setSelection(i);
                        break;
                    }
                }
//                preqProjMan.setText(preqIntent.getPreqprojman().getLastname() +", "+ preqIntent.getPreqprojman().getFirstname());
                preqProjMan_id.setText(String.valueOf(preqIntent.getPreqprojman().getUserID()));
                projman_dateapproved.setText(preqIntent.getPreqpmdate());

                for (int i = 0; i < preq_office_eng.getCount(); i++) {
                    if (preq_office_eng.getItemAtPosition(i).toString().equals(preqIntent.getPreqofficeengr())) {
                        preq_office_eng.setSelection(i);
                        break;
                    }
                }

//                preqOfficeEng.setText(preqIntent.getPreqofficeengr().getLastname() + ", " + preqIntent.getPreqofficeengr().getFirstname());
                preqOfficeEng_id.setText(String.valueOf(preqIntent.getPreqofficeengr().getUserID()));
                dateauthorized_purchase.setText(preqIntent.getPreqpodate());
                officeengineer_dateapproved.setText(preqIntent.getPreqoedate());

                for (int i = 0; i < preq_po.getCount(); i++) {
                    if (preq_po.getItemAtPosition(i).toString().equals(preqIntent.getPreqpurchofficer())) {
                        preq_po.setSelection(i);
                        break;
                    }
                }

//                preqPOfficer.setText(preqIntent.getPreqpurchofficer().getLastname() + ", " + preqIntent.getPreqpurchofficer().getFirstname());
                preqPOfficer_id.setText(String.valueOf(preqIntent.getPreqpurchofficer().getUserID()));

                preq_sub_total.setText(String.valueOf(preqIntent.getPreqsubtotal()));
                preq_sales_tax.setText(String.valueOf(preqIntent.getPreqsalestax()));
                preq_total.setText(String.valueOf(preqIntent.getPreqtotal()));

//                if (preqIntent.getPreqstatus() == null) {
//
//                } else if (preqIntent.getPreqstatus() == true) {
//                    rdogrpstat.check(R.id.statt);
//                }
                checkStatus(preqIntent.getPreqstatus());
                if (preqIntent.getIsapprovedpo() == null) {

                } else if (preqIntent.getIsapprovedpo() == true) {
                    rdogrppo.check(R.id.isapprovedpot);
                    spo.setEnabled(false);
                    fpo.setEnabled(false);

                }
                if (preqIntent.getIsapprovedoe() == null) {

                } else if (preqIntent.getIsapprovedoe() == true) {
                    rdogrpoe.check(R.id.isapprovedoet);
                    foe.setEnabled(false);
                    soe.setEnabled(false);
                }
                if (preqIntent.getIsapprovedpm() == null) {

                } else if (preqIntent.getIsapprovedpm() == true) {
                    rdogrppm.check(R.id.isapprovedpmt);
                    spm.setEnabled(false);
                    fpm.setEnabled(false);
                }

                preq_reqby.setEnabled(false);
                preq_proj_name.setEnabled(false);
                preq_daterequested.setEnabled(false);
                preq_proj_man.setEnabled(false);
                preq_po.setEnabled(false);
                preq_office_eng.setEnabled(false);
                dateauthorized_purchase.setEnabled(false);
                officeengineer_dateapproved.setEnabled(false);
                projman_dateapproved.setEnabled(false);
            } else {
                if (preqIntent.getPreqstatus() == null) {
                    preqIntent.setPreqstatus(null);
                }
            }
        } catch (Exception eo) {
//            Toast.makeText(getActivity(), eo.toString(), Toast.LENGTH_LONG).show();
            eo.printStackTrace();
        }

        //Dialog
//        reqDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
//        reqDialog.bindOnSpinerListener(new OnSpinerItemClick() {
//            @Override
//            public void onClick(String s, int i) {
//                selectedReqBy = s;
//                requestedby_preq.setText(s+ " "+
//                        serviceImpl.usersList.get(i).getLastname() + ", " +
//                        serviceImpl.usersList.get(i).getFirstname());
//            }
//        });


//        pmDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
//        pmDialog.bindOnSpinerListener(new OnSpinerItemClick() {
//            @Override
//            public void onClick(String s, int i) {
//                selectedpm = s;
//                preqProjMan.setText(s + " "+
//                        serviceImpl.usersList.get(i).getLastname() + ", " +
//                        serviceImpl.usersList.get(i).getFirstname());
//            }
//        });


//        poDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
//        poDialog.bindOnSpinerListener(new OnSpinerItemClick() {
//            @Override
//            public void onClick(String s, int i) {
//                selectedpo = s;
//                preqPOfficer.setText(s+ " "+
//                        serviceImpl.usersList.get(i).getLastname() + ", " +
//                        serviceImpl.usersList.get(i).getFirstname());
////                Toast.makeText(getActivity(), String.valueOf(selectedReqBy), Toast.LENGTH_SHORT).show();
//            }
//        });


//        oeDialog = new SpinnerDialog(getActivity(), serviceImpl.userIDList, "Select User");
//        oeDialog.bindOnSpinerListener(new OnSpinerItemClick() {
//            @Override
//            public void onClick(String s, int i) {
//                selectedoe = s;
//                preqOfficeEng.setText(s+ " "+
//                        serviceImpl.usersList.get(i).getLastname() + ", " +
//                        serviceImpl.usersList.get(i).getFirstname());
//            }
//        });


        btnSavePReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                req = (UserClass) preq_reqby.getSelectedItem();
                pm = (UserClass) preq_proj_man.getSelectedItem();
                po = (UserClass) preq_po.getSelectedItem();
                oe = (UserClass) preq_office_eng.getSelectedItem();
                projects = (ProjectClass) preq_proj_name.getSelectedItem();

                /////// REAL CODE
                if(spo.isChecked()) {
                    ispo = true;
                } else {
                    ispo = false;
                }
                if(soe.isChecked()) {
                    isoe = true;
                } else {
                    isoe = false;
                }
                if(spm.isChecked()) {
                    ispm = true;
                } else {
                    ispm = false;
                }
//                if(sstat.isChecked()) {
//                    isstat =true;
//                } else {
//                    isstat=false;
//                }
                BigDecimal subTotal, total;
                Double salTax;
                String subtotals = preq_sub_total.getText().toString().trim();
                String totals =preq_total.getText().toString();
                String salesTax = preq_sales_tax.getText().toString();
//                Toast.makeText(getActivity(), subtotals + totals, Toast.LENGTH_LONG).show();

                if (subtotals == "") {
                    subTotal = new BigDecimal("0.0");
                } else {
                    subTotal= new BigDecimal(subtotals);
                }
                if (totals == "") {
                    total =BigDecimal.ZERO;
                } else {
                    total = new BigDecimal(totals);
                }
                if (total.equals(BigDecimal.ZERO)) {
                    total = new BigDecimal("0.0");
                }

                if (salesTax == "") {
                    salTax = new Double("0.0");
                } else {
                    salTax = new Double(salesTax);
                }

//                for (UserClass u : serviceImpl.usersList) {
//                    if (u.getUserID().equals(Integer.valueOf(requestedby_preq_id.getText().toString()))) {
//                        req = u;
//                    }
//                }
////                Toast.makeText(getActivity(), req.toString(), Toast.LENGTH_LONG).show();
//                for (UserClass u : serviceImpl.usersList) {
//                    if (u.getUserID().equals(Integer.valueOf(preqProjMan_id.getText().toString()))) {
//                        pm = u;
//                    }
//                }
//
////                Toast.makeText(getActivity(), pm.toString(), Toast.LENGTH_LONG).show();
//                for (UserClass u : serviceImpl.usersList) {
//                    if (u.getUserID().equals(Integer.valueOf(preqOfficeEng_id.getText().toString()))) {
//                        oe = u;
//                    }
//                }
////                Toast.makeText(getActivity(), oe.toString(), Toast.LENGTH_LONG).show();
//                for (UserClass u : serviceImpl.usersList) {
//                    if (u.getUserID().equals(Integer.valueOf(preqPOfficer_id.getText().toString()))) {
//                        po = u;
//                    }
//                }

                if(!preqID.getText().toString().matches("")) {
                    addPreq = new PurchaseRequestClass(preqIntent.getPreqID(),
                            projects,
                            preq_dateapproved.getText().toString().trim(),
                            preq_daterequested.getText().toString().trim(),
                            req,
                            preqIntent.getPreqstatus(),
                            pm,
                            projman_dateapproved.getText().toString().trim(),
                            ispm,
                            po,
                            dateauthorized_purchase.getText().toString().trim(),
                            ispo,
                            oe,
                            officeengineer_dateapproved.getText().toString().trim(),
                            isoe,
                            subTotal,
                            salTax,
                            total);
                    EditPReq(preqIntent.getPreqID(), addPreq);
                    Toast.makeText(getActivity(), "Request has been successfully edited!", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {

//                    Toast.makeText(getActivity(), po.toString(), Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), projs.toString(), Toast.LENGTH_LONG).show();
                    addPreq = new PurchaseRequestClass(projects,
                            preq_dateapproved.getText().toString().trim(),
                            preq_daterequested.getText().toString().trim(),
                            req,
                            pm,
                            projman_dateapproved.getText().toString().trim(),
                            po,
                            dateauthorized_purchase.getText().toString().trim(),
                            oe,
                            officeengineer_dateapproved.getText().toString().trim(),
                            subTotal,
                            salTax,
                            total);

                    SavePReq(addPreq);
                }
                ////////// REAL CODE
            }
        });
        return rootView;
    }

//    public void onRadioButtonPO(View view) {
//        checked = ((RadioButton) view).isChecked();
//
//        switch(view.getId()) {
//            case R.id.isapprovedpot:
//                if (checked)
//                    ispo = true;
//                break;
//            case R.id.isapprovedpof:
//                if (checked)
//                    ispo = false;
//                break;
//        }
//    }
//
//    public void onRadioButtonET(View view) {
//        checked = ((RadioButton) view).isChecked();
//
//        switch(view.getId()) {
//            case R.id.isapprovedoet:
//                if (checked)
//                    isoe = true;
//                break;
//            case R.id.isapprovedoef:
//                if (checked)
//                    isoe = false;
//                break;
//        }
//
//    }
//
//    public void onRadioButtonPM(View view) {
//
//        checked = ((RadioButton) view).isChecked();
//
//        switch(view.getId()) {
//            case R.id.isapprovedpmt:
//                if (checked)
//                    ispm = true;
//                break;
//            case R.id.isapprovedpmf:
//                if (checked)
//                    ispm = false;
//                break;
//        }
//    }
//
//    public void onRadioButtonStat(View view) {
//        checked = ((RadioButton) view).isChecked();
//
//        switch(view.getId()) {
//            case R.id.statt:
//                if (checked)
//                    isstat = true;
//                break;
//            case R.id.statf:
//                if (checked)
//                    isstat = false;
//                break;
//        }
//
//    }



    public void SavePReq(PurchaseRequestClass pr) {

        Call<PurchaseRequestClass> addPreq = preqService.addPreq(pr);

        addPreq.enqueue(new Callback<PurchaseRequestClass>() {
            @Override
            public void onResponse(Call<PurchaseRequestClass> call, Response<PurchaseRequestClass> response) {
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
//                    Toast.makeText(getActivity(), "Request has been successfully added!", Toast.LENGTH_SHORT).show();

                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<PurchaseRequestClass> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
//                Toast.makeText(getActivity(), "An error has been encountered while adding request", Toast.LENGTH_SHORT);
            }
        });
    }

    public void EditPReq(int id, PurchaseRequestClass pr) {

        Call<Void> addPreq = preqService.editPreq(id, pr);

        addPreq.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Request has been successfully edited!", Toast.LENGTH_SHORT).show();

                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has been encountered while editing request", Toast.LENGTH_SHORT);
            }
        });
    }

    public void onClickCancel(View v) {
        getActivity().finish();

    }

    public void checkStatus(boolean stat) {

        if (stat == true) {
            isApproved.setVisibility(View.VISIBLE);
        } else if (stat == false) {
            isDeclined.setVisibility(View.VISIBLE);
        } else {
            isWaiting.setVisibility(View.VISIBLE);
        }

    }
}
