package com.example.courseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText edt1,edt2,edt3,edt4,edt5;
AppCompatButton btn1;
String getTitle,getDes,getDur,getVen,getDate;
String apiUrl="https://mountzioncollege.herokuapp.com/addcourse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=(EditText) findViewById(R.id.edt1);
        edt2=(EditText) findViewById(R.id.edt2);
        edt3=(EditText) findViewById(R.id.edt3);
        edt4=(EditText) findViewById(R.id.edt4);
        edt5=(EditText) findViewById(R.id.edt5);
        btn1=(AppCompatButton) findViewById(R.id.btn1);

   btn1.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           getTitle=edt1.getText().toString();
           getDes=edt2.getText().toString();
           getDur=edt3.getText().toString();
           getVen=edt4.getText().toString();
           getDate=edt5.getText().toString();

           StringRequest sr=new StringRequest(Request.Method.POST, apiUrl, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   edt1.setText("");
                   edt2.setText("");
                   edt3.setText("");
                   edt4.setText("");
                   edt5.setText("");
                   Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
               }
           })
           {
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   HashMap<String,String> params=new HashMap<>();
                   params.put("courseTitle",getTitle);
                   params.put("courseDuration",getDur);
                   params.put("courseVenue",getVen);
                   params.put("courseDescription",getDes);
                   params.put("courseDate",getDate);
                   return params;
               }
           };
           RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
           rq.add(sr);


       }
   });



    }
}