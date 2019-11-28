package com.bijay.pdfconverter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int PICK_UP_PDF =1000 ;
    Button device_button,web_button,internet_button,check_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request Read and Write External Storage

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new BaseMultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        super.onPermissionsChecked(report);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        super.onPermissionRationaleShouldBeShown(permissions, token);
                    }
                }).check();

        device_button = findViewById(R.id.device_button);
        web_button = findViewById(R.id.web_button);
        internet_button = findViewById(R.id.internet_button);
        check_button = findViewById(R.id.check_button);

        web_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentweb = new Intent(MainActivity.this,PdfViewer_activity.class);
                intentweb.putExtra("ViewType","fromWeb");
                startActivity(intentweb);

            }
        });

        internet_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentweb = new Intent(MainActivity.this,PdfViewer_activity.class);
                intentweb.putExtra("ViewType","fromInternet");
                startActivity(intentweb);

            }
        });




        device_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserPdf = new Intent(Intent.ACTION_GET_CONTENT);
                browserPdf.setType("application/pdf");
                browserPdf.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(browserPdf,"Select Pdf"),PICK_UP_PDF);
            }
        });

       check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              startActivity(new Intent(MainActivity.this,Pdf_FromInternet.class));

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_UP_PDF && resultCode == RESULT_OK && data != null){

            Uri selectedPdf = data.getData();
            Intent intent = new Intent(MainActivity.this,PdfViewer_activity.class);
            intent.putExtra("ViewType","fromDevice");
            intent.putExtra("FileUri",selectedPdf.toString());
            startActivity(intent);

        }
    }


}
