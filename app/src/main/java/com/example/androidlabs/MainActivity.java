package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "cst2335.lab3.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("Email", "");
        EditText emailEditText = (EditText)findViewById(R.id.editText);
        emailEditText.setText(email);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        EditText emailEditText = (EditText)findViewById(R.id.editText);
        editor.putString("Email", emailEditText.getText().toString());
        editor.commit();
    }

    public void gotoProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        EditText emailAddr = (EditText)findViewById(R.id.editText);
        String msg = emailAddr.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, msg);
        startActivityForResult(intent,3);
    }
}
