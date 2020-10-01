package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Edit_Appoinment extends AppCompatActivity {

            EditText txtbeautyname,txtcusname,txtphone;
            Button btnupdate;
            DBHandler dbHandler;
            Context context;
            Long updateDate;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit__appoinments);

            context = this;
            dbHandler = new DBHandler(context);

            txtbeautyname = findViewById(R.id.updatebeautyname);
            txtcusname = findViewById(R.id.updatecusname);
            txtphone = findViewById(R.id.updatecusname);
            btnupdate = findViewById(R.id.btnupdate);

            final String id = getIntent().getStringExtra("id");
            Appoinment appoinment = dbHandler.getSingleAppoinment(Integer.parseInt(id));


            txtbeautyname.setText(appoinment.getBeautyName());
            txtcusname.setText(appoinment.getCusName());
            txtphone.setText(appoinment.getPhone());

            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String beautytxt = txtbeautyname.getText().toString();
                    String cusnametxt = txtcusname.getText().toString();
                    String phonetxt = txtphone.getText().toString();
                    updateDate = System.currentTimeMillis();


                    Appoinment appoinment = new Appoinment(Integer.parseInt(id),beautytxt,cusnametxt,phonetxt,updateDate,0);
                    int state = dbHandler.updateAppoinment(appoinment);
                    System.out.println(state);
                    startActivity(new Intent(context,MainActivityMeth.class));
                }
            });
    }
        }