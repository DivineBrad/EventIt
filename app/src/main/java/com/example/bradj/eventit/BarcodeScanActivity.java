package com.example.bradj.eventit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class BarcodeScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scan);

        Intent newIntent=getIntent();
        int regEveId = getIntent().getIntExtra("event", 0);
        ImageView imageView=findViewById(R.id.barCodeImage);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(""+regEveId, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error Checking in", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
