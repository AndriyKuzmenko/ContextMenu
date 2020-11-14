package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    ListView sequenceView;
    Intent gi;
    boolean type;
    double a1,d, currentItem, previousItem;
    double[] sums;
    String[] sequence;
    TextView label;
    EditText resultview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        sums=new double[20];
        sequence=new String[20];

        sequenceView=(ListView)findViewById(R.id.sequenceView);
        gi=getIntent();
        type=gi.getBooleanExtra("type",false);
        a1=gi.getDoubleExtra("a1",0);
        d=gi.getDoubleExtra("d",0);
        label=(TextView)findViewById(R.id.label);
        resultview=(EditText)findViewById(R.id.resultview);

        sequenceView.setOnItemClickListener(this);
        sequenceView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if (!type) arithmetic();
        else geometric();

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sequence);
        sequenceView.setAdapter(adp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }

    public void arithmetic()
    {
        currentItem=a1;
        sums[0]=a1;
        sequence[0]=currentItem+"";

        for (int i=1; i<20; i++)
        {
            previousItem=currentItem;
            currentItem=previousItem+d;
            sums[i]=sums[i-1]+currentItem;
            sequence[i]=currentItem+"";
        }
    }

    public void geometric()
    {
        currentItem=a1;
        sums[0]=a1;
        sequence[0]=currentItem+"";

        for (int i=1; i<20; i++)
        {
            previousItem=currentItem;
            currentItem=previousItem*d;
            sums[i]=sums[i-1]+currentItem;
            sequence[i]=currentItem+"";
        }
    }
}