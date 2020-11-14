package com.example.contextmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main Activity
 * Created by Andriy Kuzmenko on 09.11.2020
 * This activity gets numbers from the users and sens them to another activity that calaculates the sequence.
 *
 * @Author Andriy Kuzmenko
 * @Version 1.1
 * @Since 1.1
 */

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
        si=new Intent(this, ResultsActivity.class);

        type=false;
    }

    /**
     * @param view
     * @return Updates the boolean variable that stores the type of progression, and also the text views to say b1, q or a1,d depending on the type of progression
     */

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

    /**
     * @param view
     * @return makes sure that the input given is valid, and sends it Results activity.
     */

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


    /**
     * @param s - a string variable
     * @return true if the the string is a valid number, false if it isn't
     */
    public boolean check(String s)
    {
        if (s==null||s.equals("")||s.equals("-")||s.equals(".")||s.equals("-.")||s.charAt(0)=='.'||s.charAt(s.length()-1)=='.')
        {
            Toast.makeText(getApplicationContext(), "The input you entered is not valid", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
