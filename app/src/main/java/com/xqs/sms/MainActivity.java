package com.xqs.sms;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText mAddress;
    private EditText mSmsContent;
    private Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddress = (EditText)findViewById(R.id.et_address);
        mSmsContent = (EditText)findViewById(R.id.et_content);
        mStart = (Button)findViewById(R.id.btn_start);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addressText = mAddress.getText().toString();
                String smsText = mSmsContent.getText().toString();
                if(TextUtils.isEmpty(addressText)||TextUtils.isEmpty(smsText)){
                    Toast.makeText(MainActivity.this, "不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                createSms(addressText,smsText);
            }
        });
    }
    
    public void createSms(String address, String text){
            ContentResolver resolver = getContentResolver();
            Uri uri = Uri.parse("content://sms/");
            ContentValues values = new ContentValues();
            values.put("address", address);
            values.put("type", "1");
            values.put("body", text);
            values.put("date",System.currentTimeMillis()-3600000);
            resolver.insert(uri, values);
    }
}
