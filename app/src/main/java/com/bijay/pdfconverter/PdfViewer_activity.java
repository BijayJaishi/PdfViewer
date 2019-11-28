package com.bijay.pdfconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.bijay.pdfconverter.progressDialog.ShowProgress;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class PdfViewer_activity extends AppCompatActivity {
    PDFView pdfView;
    WebView webview;
    ShowProgress showProgress;
    Integer Pagenumber = 0;
    String pageNO;
    TextView pageNumber;
    View viewbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer_activity);

        pdfView = findViewById(R.id.pdfView);
        webview = findViewById(R.id.webview);
        pageNumber = findViewById(R.id.pageNumber);
        viewbar = findViewById(R.id.viewbar);
        showProgress = new ShowProgress(PdfViewer_activity.this);

//        String url = "http://pdf.halfwaiter.com/assets/pdf_file/L3RtcC9waHBhUzNsbEo=.pdf";

        webview.setVisibility(View.VISIBLE);
        showProgress.showProgress();

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + "http://pdf.halfwaiter.com/assets/pdf_file/"+getIntent().getExtras().getString("name"));

        webview.setScrollbarFadingEnabled(true); // Explicitly, however it's a default, I think.
        webview.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);

        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url){


                // do your stuff here

                if(showProgress != null){
                    showProgress.hideProgress();
                }
            }
        });




//        if(getIntent()!=null){
//
//            final String viewType = getIntent().getStringExtra("ViewType");
//            if(viewType !=null || !TextUtils.isEmpty(viewType)){
//
//                if(viewType.equals("fromDevice")){
//                    showProgress.showProgress();
//
//                    Uri pdffile = Uri.parse(getIntent().getStringExtra("FileUri"));
//
//                    pdfView.fromUri(pdffile)
//                            .password(null)
//                            .defaultPage(0)
//                            .enableSwipe(true)
//                            .swipeHorizontal(false)
//                            .enableDoubletap(true)
//                            .onDraw(new OnDrawListener() {
//                                @Override
//                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//
//                                }
//                            }).onDrawAll(new OnDrawListener() {
//                        @Override
//                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//
//                        }
//                    }).onPageError(new OnPageErrorListener() {
//                        @Override
//                        public void onPageError(int page, Throwable t) {
//                            Toast.makeText(PdfViewer_activity.this, "Error on Page"+page, Toast.LENGTH_SHORT).show();
//                        }
//                    }).onPageChange(new OnPageChangeListener() {
//                        @Override
//                        public void onPageChanged(int page, int pageCount) {
//
//                            Pagenumber = page;
//                            pageNO = Integer.toString(Pagenumber);
//                            pageNumber.setText(pageNO);
//                            viewbar.setVisibility(View.VISIBLE);
//
//                        }
//                    }).onTap(new OnTapListener() {
//                        @Override
//                        public boolean onTap(MotionEvent e) {
//                            return true;
//                        }
//                    })
//                            .onRender(new OnRenderListener() {
//                        @Override
//                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
//
//                            pdfView.fitToWidth(Pagenumber);
//                        }
//                    })
//
//                            .load();
//                    if(showProgress != null){
//                        showProgress.hideProgress();
//                    }
//
//                }else if(viewType.equals("fromWeb")){
//                    showProgress.showProgress();
//
//             webview.setVisibility(View.VISIBLE);
//
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setBuiltInZoomControls(true);
//        webview.getSettings().setDisplayZoomControls(false);
//
//        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + "http://pdf.halfwaiter.com/assets/pdf_file/L3RtcC9waHBhUzNsbEo=.pdf");
//
//        webview.setScrollbarFadingEnabled(true); // Explicitly, however it's a default, I think.
//        webview.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
//
//        webview.setWebViewClient(new WebViewClient() {
//
//            public void onPageFinished(WebView view, String url){
//
//
//                // do your stuff here
//
//                if(showProgress != null){
//                    showProgress.hideProgress();
//                }
//            }
//        });
//
//                }else if(viewType.equals("fromInternet")){
//                    showProgress.showProgress();
//
//                    FileLoader.with(this)
//                            .load("http://pdf.halfwaiter.com/assets/pdf_file/L3RtcC9waHBhUzNsbEo=.pdf")
//                            .fromDirectory("PDFFiles",FileLoader.DIR_EXTERNAL_PUBLIC)
//                            .asFile(new FileRequestListener<File>() {
//                                @Override
//                                public void onLoad(FileLoadRequest request, FileResponse<File> response) {
//
//                                    if(showProgress != null){
//                                        showProgress.hideProgress();
//                                    }
//
//                                     final File pdffile = response.getBody();
//
//                                    pdfView.fromFile(pdffile)
//
//                                            .password(null)
//                                            .defaultPage(0)
//                                            .enableSwipe(true)
//                                            .swipeHorizontal(false)
//                                            .enableDoubletap(true)
//                                            .onDraw(new OnDrawListener() {
//                                                @Override
//                                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//
//                                                }
//                                            }).onDrawAll(new OnDrawListener() {
//                                        @Override
//                                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
//
//                                        }
//                                    }).onPageError(new OnPageErrorListener() {
//                                        @Override
//                                        public void onPageError(int page, Throwable t) {
//                                            Toast.makeText(PdfViewer_activity.this, "Error on Page"+page, Toast.LENGTH_SHORT).show();
//                                        }
//                                    }).onPageChange(new OnPageChangeListener() {
//                                        @Override
//                                        public void onPageChanged(int page, int pageCount) {
//
//                                            Pagenumber = page;
//                                            String pageNO = Integer.toString(Pagenumber);
//                                            pageNumber.setText(pageNO);
//                                            viewbar.setVisibility(View.VISIBLE);
//
//                                            //setTitle(String.format("%s %s / %s",pdffile,page+1,pageCount));
//
//                                        }
//                                    }).onTap(new OnTapListener() {
//                                        @Override
//                                        public boolean onTap(MotionEvent e) {
//                                            return true;
//                                        }
//                                    })
//                                            .onRender(new OnRenderListener() {
//                                        @Override
//                                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
//
//
//                                            nbPages = Pagenumber;
//                                            pdfView.fitToWidth(nbPages);
//
//                                        }
//                                    })
//
//
//                                            .invalidPageColor(Color.WHITE)
//                                            .load();
//
//                                }
//
//                                @Override
//                                public void onError(FileLoadRequest request, Throwable t) {
//
//                                    Toast.makeText(PdfViewer_activity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                                    if(showProgress != null){
//                                        showProgress.hideProgress();
//                                    }
//
//                                }
//                            });
//
//
//                }
//
//            }
//
//        }

    }
}
