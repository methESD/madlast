package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class Details extends AppCompatActivity {

    EditText txtbeauty,txtcusname,txtphone,phone;
    Button button,btnadd,btndelete,btnedits;
    TextView textView;
    TextView date;
    Appoinment appo;
    private TextView count;
    long updateDate;

    private static  final String TAG = "MainActivity";
    private TextView theDate;
    private Button btnGoCalendar;
    private DBHandler dbHandler;
    private Context context;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        theDate = (TextView) findViewById(R.id.date);
        btnGoCalendar = (Button) findViewById(R.id.btnGoCalendar);

        date = findViewById(R.id.date);
        txtbeauty = findViewById(R.id.edittxtbeauty);
        txtcusname = findViewById(R.id.edittxtcusname);
        txtphone = findViewById(R.id.edittxtphone);

        btnadd = findViewById(R.id.btnadd);
        btnedits = findViewById(R.id.btnedits);
        btndelete = findViewById(R.id.btndelete);

        context = this;
        dbHandler = new DBHandler(context);

        appo = new Appoinment();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        String txtbeautyname = txtbeauty.getText().toString();
                        String txtcustomername = txtcusname.getText().toString();
                        String txtphonenumber = txtphone.getText().toString();
                        long started = System.currentTimeMillis();
                        Toast.makeText(getApplicationContext(), "Successfully entered", Toast.LENGTH_SHORT).show();

                        Appoinment appoinment = new Appoinment(txtbeautyname, txtcustomername, txtphonenumber, started, 0);
                        dbHandler.addAppoinment(appoinment);
                       startActivity(new Intent(context, MainActivityMeth.class));
                    }
        });


        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        Button b1 = (Button) findViewById(R.id.btnGoCalendar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Details.this, MainActivity4.class);
                startActivity(intent);
            }
        });

        Button b2 = (Button) findViewById(R.id.btnedits);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Details.this, Edit_Appoinment.class);
                startActivity(intent);
            }
        });




    }
}

