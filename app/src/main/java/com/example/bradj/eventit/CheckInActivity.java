package com.example.bradj.eventit;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CheckInActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        NfcAdapter mAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mAdapter == null) {
            Toast.makeText(this,"Sorry this device does not have NFC.", Toast.LENGTH_SHORT);
            return;
        }

        if (!mAdapter.isEnabled()) {
            Toast.makeText(this, "Please enable NFC via Settings.", Toast.LENGTH_LONG).show();
        }

        mAdapter.setNdefPushMessageCallback(this, this);
    }

    public void onClickCancel(View view) {
        finish();
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        String message = "New Message";
        NdefRecord ndefRecord = NdefRecord.createMime("text/plain", message.getBytes());
        NdefMessage ndefMessage = new NdefMessage(ndefRecord);
        return ndefMessage;
    }
}
