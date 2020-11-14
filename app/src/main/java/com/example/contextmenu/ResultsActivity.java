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

public class ResultsActivity extends AppCompatActivity implements View.OnCreateContextMenuListener, AdapterView.OnItemClickListener
{
    ListView sequenceView;
    Intent gi;
    boolean type;
    double a1,d, currentItem, previousItem;
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

        sums=new double[20];
        sequence=new String[20];

        sequenceView=(ListView)findViewById(R.id.sequenceView);
        gi=getIntent();
        type=gi.getBooleanExtra("type",false);
        a1=gi.getDoubleExtra("a1",0);
        d=gi.getDoubleExtra("d",0);
        label=(TextView)findViewById(R.id.label);
        resultview=(EditText)findViewById(R.id.resultview);

        sequenceView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        sequenceView.setOnItemClickListener(this);

        if (!type) arithmetic();
        else geometric();

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sequence);
        sequenceView.setAdapter(adp);
        sequenceView.setOnCreateContextMenuListener(this);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.setHeaderTitle("What do you want to know about this item?");
        menu.add("It's position");
        menu.add("The total of all items till this one");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        String s=item.getTitle().toString();

        if (s.equals("It's position"))
        {
            label.setText("n=");
            resultview.setText(""+position);
            return true;
        }
        if (s.equals("The total of all items till this one"))
        {
            label.setText("Sn=");
            resultview.setText(""+sums[position-1]);
        }

        return super.onContextItemSelected(item);
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        this.position=position+1;
    }

    public void back(View view)
    {
        finish();
    }
}
