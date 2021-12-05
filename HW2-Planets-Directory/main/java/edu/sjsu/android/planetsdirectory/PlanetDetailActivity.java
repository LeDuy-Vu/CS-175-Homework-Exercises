package edu.sjsu.android.planetsdirectory ;

import android.content.Intent ;
import android.net.Uri ;
import android.os.Bundle ;
import android.view.Menu ;
import android.view.MenuItem ;
import android.widget.ImageView ;
import android.widget.TextView ;

import androidx.appcompat.app.AppCompatActivity ;

public class PlanetDetailActivity extends AppCompatActivity
{
	private ImageView imageView ;
	private TextView nameView ;
	private TextView descriptionView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.planet_detail) ;
		
		// Get data from main activity
		Intent intent = getIntent() ;
		Bundle extras = intent.getExtras() ;
		int image = extras.getInt("image") ;
		int name = extras.getInt("name") ;
		int description = extras.getInt("description") ;
		
		imageView = (ImageView) findViewById(R.id.image) ;
		nameView = (TextView) findViewById(R.id.name) ;
		descriptionView = (TextView) findViewById(R.id.description) ;
		
		//display on screen
		imageView.setImageResource(image) ;
		nameView.setText(name) ;
		descriptionView.setText(description) ;
	}
	
	/*
		Inflate an option menu.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu) ;
		return true;
	}
	
	/*
		Define behavior of each item in the menu.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.information: //click Information
				Intent infoIntent = new Intent(this, InformationActivity.class) ;
				startActivity(infoIntent) ; //go to Information page
				return true;
			
			case R.id.uninstall: //click Uninstall
				Uri link = Uri.parse("package:" + getApplicationContext().getPackageName()) ; //package URI
				Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, link) ;
				startActivity(uninstallIntent) ;    //uninstall the app from device
				return true;
			
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
