package com.example.contextmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    ToggleButton typeToggleButton;
    TextView firstItem, difference;
    EditText firstItemInput, differenceInput;
    Intent si;
    String dString, a1String;
    double d,a1;
    boolean type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeToggleButton=(ToggleButton)findViewById(R.id.typeToggleButton);
        firstItem=(TextView)findViewById(R.id.firstItem);
        difference=(TextView)findViewById(R.id.difference);
        firstItemInput=(EditText)findViewById(R.id.firstItemInput);
        differenceInput=(EditText)findViewById(R.id.differenceInput);
        //si=new Intent(this, ResultsActivity.class);

        type=false;
    }
}
