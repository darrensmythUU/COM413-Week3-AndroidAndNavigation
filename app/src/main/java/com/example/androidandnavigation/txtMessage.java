package com.example.androidandnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class txtMessage extends AppCompatActivity {
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt_message);

        send = (Button) findViewById(R.id.btnSendMessage);
        send.setEnabled(false);

        if (checkPermission(Manifest.permission.SEND_SMS)){
            send.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(String permission){
        int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
        return(permissionCheck == getPackageManager().PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case SEND_SMS_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    send.setEnabled(true);
                }
                return;
            }
        }
    }

    public void send(View view){
        String phoneNumber = ((EditText) findViewById(R.id.editPhone)).getText().toString();
        String msg = ((EditText) findViewById(R.id.editMessage)).getText().toString();

        if (phoneNumber == null || phoneNumber.length() == 0 || msg == null || msg.length() == 0){
            return;
        }

        if (checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, msg, null, null);
            Toast.makeText(this, "Your message has been sent", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "No permission", Toast.LENGTH_SHORT).show();
        }
    }
}