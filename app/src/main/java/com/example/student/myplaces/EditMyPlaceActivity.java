package com.example.student.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMyPlaceActivity extends ActionBarActivity implements View.OnClickListener {
boolean editMode=true;
    int position=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_place);

        try
        {
            Intent listIntent=getIntent();
            Bundle positionBundle=listIntent.getExtras();
            if(positionBundle!=null)
                position=positionBundle.getInt("position");
            else
                editMode=false;
        }
        catch (Exception e)
        {
            editMode=false;
        }

        final Button finishedButton = (Button)findViewById(R.id.editmyplace_finished_button);
        finishedButton.setOnClickListener(this);
        finishedButton.setEnabled(false);
        finishedButton.setText("Add");
        Button cancelButton = (Button)findViewById(R.id.editmyplace_cancel_button);
        EditText nameEditText = (EditText)findViewById(R.id.editmyplace_name_edit);
        if(!editMode)
        {
            finishedButton.setEnabled(false);
            finishedButton.setText("Add");
        }
        else if(position>=0)
        {
            finishedButton.setText("Save");
            MyPlace place=MyPlacesData.getInstance().getPlace(position);
            nameEditText.setText(place.getName());
            EditText descEditText=(EditText)findViewById(R.id.editmyplace_desc_edit);
            descEditText.setText(place.getDesc());
        }
        cancelButton.setOnClickListener(this);
        finishedButton.setOnClickListener(this);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                finishedButton.setEnabled(editable.length()>0);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.editmyplace_finished_button:
                EditText etName = (EditText)findViewById(R.id.editmyplace_name_edit);
                String nme = etName.getText().toString();
                EditText etDesc= (EditText)findViewById(R.id.editmyplace_desc_edit);
                String desc = etDesc.getText().toString();
                EditText latEdit=(EditText)findViewById(R.id.editmyplace_lat_edit);
                String lat=latEdit.getText().toString();
                EditText lonEdit=(EditText)findViewById(R.id.editmyplace_lon_edit);
                String lon=lonEdit.getText().toString();

                if(!editMode)
                {
                    MyPlace place = new MyPlace(nme, desc);
                    place.setLatitude(lat);
                    place.setLongitude(lon);
                    MyPlacesData.getInstance().addNewPlace(place);
                }
                else
                {
                    MyPlace place=MyPlacesData.getInstance().getPlace(position);
                    place.setName(nme);
                    place.setDesc(desc);
                    place.setLatitude(lat);
                    place.setLongitude(lon);
                    MyPlacesData.getInstance().updatePlace(place);
                }
                setResult(Activity.RESULT_OK);
                finish();
                break;
            case R.id.editmyplace_cancel_button:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }

    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_edit_my_place,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.show_map_item) {
            Toast.makeText(this, "Show Map!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.my_places_list_item) {
            Intent i = new Intent(this, MyPlacesList.class);
            startActivity(i);
        } else if (id == R.id.about_item) {
            Intent i = new Intent(this, About.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
