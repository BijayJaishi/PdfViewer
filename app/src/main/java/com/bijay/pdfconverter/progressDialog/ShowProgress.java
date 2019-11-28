package com.bijay.pdfconverter.progressDialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;

import com.bijay.pdfconverter.R;

public class ShowProgress {

    Context context;
    ProgressDialog pDialog;

    public ShowProgress(Context context) {
        this.context = context;
    }

    ////// show progress dialog /////
    public void showProgress(){

        pDialog = ProgressDialog.show(new ContextThemeWrapper(context, R.style.NewDialog),"Fetching Pdf", "Please Wait...",true);

        pDialog.show();

    }

    public void hideProgress(){
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
