package com.bijay.pdfconverter.Recycler_Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bijay.pdfconverter.Model.Data;
import com.bijay.pdfconverter.Model.Model_Class;
import com.bijay.pdfconverter.PdfViewer_activity;
import com.bijay.pdfconverter.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Pdf_recycler_adapter extends RecyclerView.Adapter<Pdf_recycler_adapter.MyViewHolder> {

    Context context;
    List<Model_Class.Datum> model_classList;

    public Pdf_recycler_adapter(Context context, List<Model_Class.Datum> model_classList) {
        this.context = context;
        this.model_classList = model_classList;
    }



    @NonNull
    @Override
    public Pdf_recycler_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pdf_recycler, viewGroup,false);
        return new Pdf_recycler_adapter.MyViewHolder(view) ;
}

    @Override
    public void onBindViewHolder(@NonNull Pdf_recycler_adapter.MyViewHolder holder, int position) {

        final Model_Class.Datum model_class = model_classList.get(position);
       holder.pdf_titile.setText(model_class.getName());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, PdfViewer_activity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("name",model_class.getName());
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return model_classList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView pdf_titile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pdf_titile = itemView.findViewById(R.id.title_pdf);
        }
    }
}
