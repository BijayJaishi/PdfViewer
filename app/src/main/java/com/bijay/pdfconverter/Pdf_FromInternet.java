package com.bijay.pdfconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.bijay.pdfconverter.Model.Data;
import com.bijay.pdfconverter.Model.Model_Class;
import com.bijay.pdfconverter.Recycler_Adapter.Pdf_recycler_adapter;
import com.bijay.pdfconverter.progressDialog.ShowProgress;

import java.util.ArrayList;
import java.util.List;

public class Pdf_FromInternet extends AppCompatActivity {

    RecyclerView pdf_recycle;
    Pdf_recycler_adapter pdf_recycler_adapter;
    List<Model_Class> modeldata;
    ShowProgress showProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf__from_internet);

        pdf_recycle = findViewById(R.id.pdfRecycle);

        modeldata = new ArrayList<>();


//        showProgress = new ShowProgress(this);
//        showProgress.showProgress();
        getMessage();

    }
    public void getMessage(){



        RetrofitInterface retrofitInterface = RetrofitClient.getRetrofit().create(RetrofitInterface.class);
        Call<Model_Class> modelCall = retrofitInterface.getMessage();
        modelCall.enqueue(new Callback<Model_Class>() {
            @Override
            public void onResponse(Call<Model_Class> call, Response<Model_Class> response) {

                if (response.isSuccessful()){
                    if (response.body().getData().size() != 0){
//                        modeldata = response.body().getData();
                        Toast.makeText(getApplicationContext(), "I am here", Toast.LENGTH_SHORT).show();
                        pdf_recycler_adapter = new Pdf_recycler_adapter(getApplicationContext(), response.body().getData());
                        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(),2,LinearLayoutManager.VERTICAL,false);
                        pdf_recycle.setLayoutManager(linearLayoutManager);
                        pdf_recycle.setAdapter(pdf_recycler_adapter);
                        pdf_recycler_adapter.notifyDataSetChanged();
//                        if (showProgress !=null){
//                           showProgress.hideProgress();
//                        }
                    }else{
//                        if (showProgress !=null){
//                            showProgress.hideProgress();
//                        }

                        Toast.makeText(Pdf_FromInternet.this, "I am out", Toast.LENGTH_SHORT).show();
                    }
                }else{
//                    if (showProgress !=null){
//                        showProgress.hideProgress();
//                    }
                    Toast.makeText(getApplicationContext(), "I am null response body", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_Class> call, Throwable t) {

            }
        });
    }

}
