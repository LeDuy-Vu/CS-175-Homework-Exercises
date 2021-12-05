package edu.sjsu.android.planetsdirectory ;

import androidx.appcompat.app.AppCompatActivity ;
import androidx.recyclerview.widget.LinearLayoutManager ;
import androidx.recyclerview.widget.RecyclerView ;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle ;
import android.view.Menu ;
import android.view.MenuItem ;

import java.util.ArrayList ;
import java.util.List ;

public class MainActivity extends AppCompatActivity
{
	private RecyclerView recyclerView ;
	private RecyclerView.Adapter myAdapter ;
	private RecyclerView.LayoutManager layoutManager ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
		
		recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;
		recyclerView.setHasFixedSize(true) ;    // improve performance
		
		layoutManager = new LinearLayoutManager(this) ;
		recyclerView.setLayoutManager(layoutManager) ;
		
		myAdapter = new MyAdapter(mkdir(), this) ;
		recyclerView.setAdapter(myAdapter) ;
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
	
	/*
		Create a list of planets information.
	 */
	private List<int[]> mkdir()
	{
		List<int[]> dir = new ArrayList<>() ;
		
		int[] mercury = {R.drawable.mercury, R.string.mercury, R.string.mercuryDescription} ;
		int[] venus = {R.drawable.venus, R.string.venus, R.string.venusDescription} ;
		int[] mars = {R.drawable.mars, R.string.mars, R.string.marsDescription} ;
		int[] jupiter = {R.drawable.jupiter, R.string.jupiter, R.string.jupiterDescription} ;
		int[] earth = {R.drawable.earth, R.string.earth, R.string.earthDescription} ;
		
		dir.add(mercury) ;
		dir.add(venus) ;
		dir.add(mars) ;
		dir.add(jupiter) ;
		dir.add(earth) ;
		
		return dir;
	}
}