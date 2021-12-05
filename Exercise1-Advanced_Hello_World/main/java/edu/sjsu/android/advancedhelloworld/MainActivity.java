package edu.sjsu.android.advancedhelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle myInput = this.getIntent().getExtras() ;

        TextView t = (TextView) findViewById(R.id.textView2) ;
        t.setText("Hello " + (myInput.getString("uname"))) ;
    }
}