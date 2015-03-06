package com.bugchain.android.development.sampleconnectserver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class SignActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonMember:
                    startActivity(new Intent(getApplicationContext(),Member.class));
                break;
            case R.id.buttonCreateNewOne:
                    startActivity(new Intent(getApplicationContext(),CreateNewOne.class));
                break;
            case R.id.buttonUpdate:

                break;
            case R.id.buttonDelete:

                break;
        }
    }
}
