package edu.sjsu.android.planetsdirectory ;

import android.app.AlertDialog ;
import android.content.Context ;
import android.content.DialogInterface ;
import android.content.Intent ;
import android.view.LayoutInflater ;
import android.view.View ;
import android.view.ViewGroup ;
import android.widget.ImageView ;
import android.widget.TextView ;

import androidx.recyclerview.widget.RecyclerView ;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
	private List<int[]> values ; //Dataset list
	private Context context ;   //from main activity, used for dialog
	
	// Provide a suitable constructor (depends on the kind of dataset)
	public MyAdapter(List<int[]> myDataset, Context context)
	{
		values = myDataset ;
		this.context = context ;
	}
	
	// Return the size of your dataset (invoked by the layout manager)
	@Override public int getItemCount() {return values.size() ; }
	
	// Provide a reference to the views for each data item
	// Complex data items may need more than one view per item, and
	// you provide access to all the views for a data item in a view holder
	public class ViewHolder extends RecyclerView.ViewHolder
	{
		public ImageView icon ;
		public TextView title ;
		public View layout ;
		
		public ViewHolder(View v)
		{
			super(v) ;
			layout = v ;
			icon = (ImageView) v.findViewById(R.id.icon) ;
			title = (TextView) v.findViewById(R.id.title) ;
		}
	}
	
	// Create new views (invoked by the layout manager)
	@Override
	public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		// create a new view
		LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
		View v = inflater.inflate(R.layout.row_layout, parent, false) ;
		
		// set the view's size, margins, paddings and layout parameters
		MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(v) ;
		return vh;
	}
	
	/*
		- Replace the contents of a view (invoked by the layout manager).
		- Define behavior when click an item in the list.
	 */
	@Override
	public void onBindViewHolder(ViewHolder holder, final int position)
	{
		//replace the contents of a view
		final int[] data = values.get(position) ;   //get data from the clicked item
		holder.icon.setImageResource(data[0]) ;     //set the correct icon
		holder.title.setText(data[1]) ;             //set the correct title
		
		//define behavior when click an item in the list
		holder.itemView.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), PlanetDetailActivity.class) ;
				
				if (position != getItemCount() - 1) //if a normal item in the list
				{
					proceed(intent, data, v) ;
				}
				else    //if last item in the list, show an alert dialog
				{
					makeDialog(intent, data, v) ;
				}
			}
		}) ;
	}
	
	/*
		Helper function to create an alert dialog and start new activity.
	 */
	private void makeDialog(Intent intent, int[] data, View v)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context) ;
		builder.setMessage(R.string.warning)    //the content of the alert
				.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()  //positive button = proceed
				{
					public void onClick(DialogInterface dialog, int id)
					{
						proceed(intent, data, v) ;
					}
				})
				.setNegativeButton(R.string.no, new DialogInterface.OnClickListener()   //negative button = cancel
				{
					public void onClick(DialogInterface dialog, int id) { dialog.cancel(); }
				}) ;
		
		//make and show the dialog
		AlertDialog alert = builder.create() ;
		alert.show() ;
	}
	
	/*
		Helper function to start new activity.
	 */
	private void proceed(Intent intent, int[] data, View v)
	{
		intent.putExtra("image", data[0]) ;
		intent.putExtra("name", data[1]) ;
		intent.putExtra("description", data[2]) ;
		v.getContext().startActivity(intent) ;  //open the planet detail page
	}
}