package com.metacoders.home;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class BcsActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView syllButton, qsnButton, banglaButton, englishButton,
            bdtopicButton, internationalButton, geoButton, generalButton,
            compuButton,mathButton,skillButton, mulloButton;

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ProgressBar BcS_progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcs);

        //defining cardButtons
       // syllButton = (CardView) findViewById(R.id.syll_button);
        qsnButton = (CardView) findViewById(R.id.qsn_Button);
        banglaButton = (CardView) findViewById(R.id.bangla_Button);
        englishButton = (CardView) findViewById(R.id.english_Button);
        bdtopicButton = (CardView) findViewById(R.id.bdtopic_Button);
       // internationalButton = (CardView) findViewById(R.id.geo_btn);
        geoButton = (CardView) findViewById(R.id.settings_Button);
        generalButton = (CardView) findViewById(R.id.general_btn);
       // compuButton = (CardView)findViewById(R.id.compu_btn);
        mathButton = (CardView)findViewById(R.id.math_btn);
       // skillButton = (CardView)findViewById(R.id.skill_btn);
      //  mulloButton = (CardView)findViewById(R.id.mullo_btn);

        //Click Listener to CardButton
        syllButton.setOnClickListener(this);
        syllButton.setOnClickListener(this);
        banglaButton.setOnClickListener(this);
        englishButton.setOnClickListener(this);
        bdtopicButton.setOnClickListener(this);
        internationalButton.setOnClickListener(this);
        geoButton.setOnClickListener(this);
        generalButton.setOnClickListener(this);
        compuButton.setOnClickListener(this);
        mathButton.setOnClickListener(this);
        skillButton.setOnClickListener(this);
        mulloButton.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick (View view){
        Intent i;

        switch (view.getId()) {
         //   case R.id.:
         //       i = new Intent(this, SyllabusActivity.class);
      //          startActivity(i);
         //       break;
            default:
                break;

        }

    }


}
