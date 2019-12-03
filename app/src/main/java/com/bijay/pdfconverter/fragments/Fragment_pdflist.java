package com.bijay.pdfconverter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bijay.pdfconverter.Model.Model_Class;
import com.bijay.pdfconverter.R;
import com.bijay.pdfconverter.Recycler_Adapter.Pdf_recycler_adapter;
import com.bijay.pdfconverter.RetrofitClient;
import com.bijay.pdfconverter.RetrofitInterface;
import com.bijay.pdfconverter.progressDialog.ShowProgress;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_pdflist extends Fragment {

    RecyclerView pdf_recycle;
    Pdf_recycler_adapter pdf_recycler_adapter;
    List<Model_Class> modeldata;
    ShowProgress showProgress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemview =  inflater.inflate(R.layout.fragment_pdflist,container,false);


        pdf_recycle = itemview.findViewById(R.id.pdfRecycle);
        modeldata = new ArrayList<>();

        getMessage();

        return itemview;


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
                       // Toast.makeText(getContext(), "I am here", Toast.LENGTH_SHORT).show();
                        pdf_recycler_adapter = new Pdf_recycler_adapter(getContext(), response.body().getData());
                        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
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

                        //Toast.makeText(Pdf_FromInternet.this, "I am out", Toast.LENGTH_SHORT).show();
                    }
                }else{
//                    if (showProgress !=null){
//                        showProgress.hideProgress();
//                    }
                   // Toast.makeText(getApplicationContext(), "I am null response body", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_Class> call, Throwable t) {

            }
        });
    }
}
