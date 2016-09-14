package com.example.student.myplaces;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ViewMyPlaceActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_place);
        int position=-1;
        try
        {
            Intent listIntent=getIntent();
            Bundle positionBundle=listIntent.getExtras();
            position=positionBundle.getInt("position");
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            finish();
        }
        if(position>=0)
        {
            MyPlace place=MyPlacesData.getInstance().getPlace(position);
            TextView twName=(TextView)findViewById(R.id.viewmyplace_name_text);
            twName.setText(place.getName());
            TextView twDesc=(TextView)findViewById(R.id.viewmyplace_desc_text);
            twDesc.setText(place.getDesc());
        }
        final Button finishedButton=(Button)findViewById(R.id.viewplace_finished_button);
        finishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
