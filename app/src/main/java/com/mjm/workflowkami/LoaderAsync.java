package com.mjm.workflowkami;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by DC on 24/11/2017.
 */

public class LoaderAsync extends Activity {

    private ProgressDialog progressDialog;
    private boolean destroyed = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyed = true;
    }


    public void showLoadingDialog() {
        this.showProgressDialog("Loading. Please wait...");

    }

    public void showProgressDialog(CharSequence message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
        }

        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && !destroyed) {
            progressDialog.dismiss();
        }
    }
}
