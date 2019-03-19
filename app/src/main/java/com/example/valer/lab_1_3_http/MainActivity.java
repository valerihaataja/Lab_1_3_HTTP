package com.example.valer.lab_1_3_http;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public String osoite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.network_text);
        Button button = findViewById(R.id.go_button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.text_editor);

                osoite = editText.getText().toString();
                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try{
                    URL url = new URL(osoite);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    String htmlString = convertStreamToString(in);
                    textView.setText(htmlString);
                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        });

    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
