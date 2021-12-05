package edu.sjsu.android.mortgagecalculator ;

import androidx.appcompat.app.AppCompatActivity ;

import android.os.Bundle ;
import android.view.View ;
import android.widget.CheckBox ;
import android.widget.EditText ;
import android.widget.RadioButton ;
import android.widget.SeekBar ;
import android.widget.SeekBar.OnSeekBarChangeListener ;
import android.widget.TextView;
import android.widget.Toast ;
import static java.lang.Math.pow ;

public class MainActivity extends AppCompatActivity
{
	private EditText edittext ;
	private TextView textview ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		edittext = (EditText) findViewById(R.id.amountEditText) ;
		textview = (TextView) findViewById(R.id.payment) ;
		
		//reveal the progress of the seekbar
		SeekBar bar = (SeekBar) findViewById(R.id.interestBar) ;
		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				Toast.makeText(seekBar.getContext(), "Interest Rate: " + seekBar.getProgress() / 10.0 + "%", Toast.LENGTH_SHORT).show() ;
			}
		}) ;
	}
	
	// this method is called at button click because we assigned the name to the
	// "OnClick property" of the button
	public void onClick(View view)
	{
		switch (view.getId())
		{
			case R.id.calculate:
				//check if input is valid
				if (edittext.getText().length() == 0)
				{
					Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_LONG).show() ;
					return;
				}
				
				//assign numerical values
				double amount = Double.parseDouble(edittext.getText().toString()) ;
				double rate = ((SeekBar) findViewById(R.id.interestBar)).getProgress() / 12000.0 ;
				boolean tax = ((CheckBox) findViewById(R.id.taxCheckBox)).isChecked() ;
				double payment = 0 ;
				
				//check if each radio button is checked
				if (((RadioButton) findViewById(R.id.radioButton15)).isChecked())
				{
					if (rate == 0) {payment = amount / (15 * 12) ;}
					else {payment = amount * rate / (1 - pow(1 + rate, 15 * -12)) ;}
				}
				else if (((RadioButton) findViewById(R.id.radioButton20)).isChecked())
				{
					if (rate == 0) {payment = amount / (20 * 12) ;}
					else {payment = amount * rate / (1 - pow(1 + rate, 20 * -12)) ;}
				}
				else
				{
					if (rate == 0) {payment = amount / (30 * 12) ;}
					else {payment = amount * rate / (1 - pow(1 + rate, 30 * -12)) ;}
				}
				
				if (tax) {payment += amount * 0.001 ;}
				textview.setText(String.valueOf("Monthly Payment: $" + ((int) (payment * 100)) / 100.0)) ;
				
				break;
		}
	}
}