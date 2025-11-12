package com.example.androidandnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String webMessage = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView hello = (TextView) findViewById(R.id.txt_hello);

        Button search = (Button) findViewById(R.id.btn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchFor = ((EditText) findViewById(R.id.edit_search)).getText().toString();

                Intent i = new Intent(MainActivity.this, WebSearch.class);
                i.putExtra(webMessage, searchFor);
                startActivity(i);
            }
        });

        Button text = (Button) findViewById(R.id.btn_text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, txtMessage.class);
                startActivity(i);
            }
        });
    }

    public void DialPhone(View view){
        String number = "123456789";
        if (checkPermission("android.permission.CALL_PHONE")==false){
            Toast toast = Toast.makeText(this, "No Permissions to Call", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: " + number));
            startActivity(intent);
        }
    }

    private boolean checkPermission(String permission){
        int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
        return(permissionCheck == getPackageManager().PERMISSION_GRANTED);
    }
}