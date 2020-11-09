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

    public void changeTypeOfProgression(View view)
    {
        type=typeToggleButton.isChecked();
        if (type)
        {
            firstItem.setText("b1=");
            difference.setText("q=");
        }
        else
        {
            firstItem.setText("a1=");
            difference.setText("d=");
        }
    }

    public void countResults(View view)
    {
        dString=differenceInput.getText().toString();
        a1String=firstItemInput.getText().toString();
        if (!(check(dString) && check(a1String))) return;

        a1=Double.parseDouble(a1String);
        d=Double.parseDouble(dString);
        si.putExtra("type",type);
        si.putExtra("a1",a1);
        si.putExtra("d",d);
        startActivity(si);
    }

    public boolean check(String s)
    {
        if (s==null||s.equals("")||s.equals("-")||s.equals("."))
        {
            Toast.makeText(getApplicationContext(), "The input you entered is not valid", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
