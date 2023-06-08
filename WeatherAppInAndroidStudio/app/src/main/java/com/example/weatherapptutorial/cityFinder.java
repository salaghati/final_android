package com.example.weatherapptutorial;    // package name

import androidx.appcompat.app.AppCompatActivity;    // import appcompat activity

import android.content.Intent;    // import intent
import android.os.Bundle;
import android.view.KeyEvent;    // import key event
import android.view.View;    // import view
import android.widget.EditText;    // import edit text
import android.widget.ImageButton;    // import image button
import android.widget.ImageView;    // import image view
import android.widget.TextView;    // import text view

public class cityFinder extends AppCompatActivity {    // class city finder

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // oncreate method
    /*
    Function to create the activity.

    Parameters
    ----------
    savedInstanceState: Bundle
        Bundle to save the state of the activity.

    Returns
    -------
    None

    */
        super.onCreate(savedInstanceState);    // call super method
        setContentView(R.layout.activity_city_finder);    // set content view
        final EditText editText=findViewById(R.id.searchCity);    // initializing edittext
        ImageView backButton=findViewById(R.id.backButton);    // initializing image view

        backButton.setOnClickListener(new View.OnClickListener() {    // set on click listener
            @Override
            public void onClick(View v) {    // on click method
                finish();    // finish current activity
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {    // set on editor action listener
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {    // on editor action method
                String newCity= editText.getText().toString();    // get text from edittext
                Intent intent=new Intent(cityFinder.this,MainActivity.class);    // create intent
                intent.putExtra("City",newCity);    // put extra in intent
                startActivity(intent);    // start activity



                return false;
            }
        });





    }
}
