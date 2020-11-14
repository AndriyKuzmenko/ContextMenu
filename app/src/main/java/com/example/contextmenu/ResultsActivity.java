package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * ResultsActivity
 * Created by Andriy Kuzmenko on 14.11.2020
 * This activity gets numbers from the users and sens them to another activity that calaculates the sequence.
 *
 * @Author Andriy Kuzmenko
 * @Version 1.1
 * @Since 1.1
 */

public class ResultsActivity extends AppCompatActivity implements View.OnCreateContextMenuListener
{
    ListView sequenceView;
    Intent gi;
    boolean type;
    double a1, d, currentItem, previousItem;
    double[] sums;
    String[] sequence;
    TextView label;
    EditText resultview;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        sums = new double[20];
        sequence = new String[20];

        sequenceView = (ListView) findViewById(R.id.sequenceView);
        gi = getIntent();
        type = gi.getBooleanExtra("type", false);
        a1 = gi.getDoubleExtra("a1", 0);
        d = gi.getDoubleExtra("d", 0);
        label = (TextView) findViewById(R.id.label);
        resultview = (EditText) findViewById(R.id.resultview);

        sequenceView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if (!type) arithmetic();
        else geometric();

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sequence);
        sequenceView.setAdapter(adp);
        sequenceView.setOnCreateContextMenuListener(this);
    }

    /**
     * @return calculates arithmetic dequence
     */

    public void arithmetic()
    {
        currentItem = a1;
        sums[0] = a1;
        sequence[0] = currentItem + "";

        for (int i = 1; i < 20; i++)
        {
            previousItem = currentItem;
            currentItem = previousItem + d;
            sums[i] = sums[i - 1] + currentItem;
            sequence[i] = currentItem + "";
        }
    }

    /**
     * @return calculates gemoetric sequence
     */

    public void geometric()
    {
        currentItem = a1;
        sums[0] = a1;
        sequence[0] = currentItem + "";

        for (int i = 1; i < 20; i++)
        {
            previousItem = currentItem;
            currentItem = previousItem * d;
            sums[i] = sums[i - 1] + currentItem;
            sequence[i] = currentItem + "";
        }
    }

    /**
     * @param menu
     * @param v
     * @param menuInfo
     * @return shows the menu
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.setHeaderTitle("What do you want to know about this item?");
        menu.add("It's position");
        menu.add("The total of all items till this one");
    }

    /**
     * @param item
     * @return shows the results on the screen
     */

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        String s = item.getTitle().toString();

        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        position=menuInfo.position+1;

        if (s.equals("It's position"))
        {
            label.setText("n=");
            resultview.setText("" + position);
            return true;
        }
        if (s.equals("The total of all items till this one"))
        {
            label.setText("Sn=");
            resultview.setText("" + sums[position - 1]);
        }

        return super.onContextItemSelected(item);
    }

    public void back(View view)
    {
        finish();
    }
}
