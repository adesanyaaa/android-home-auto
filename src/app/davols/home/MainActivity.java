package app.davols.home;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import app.davols.home.data.DataManager;
import app.davols.home.data.HomeUnit;
import app.davols.home.data.MyLog;

import com.fasterxml.jackson.core.JsonParseException;

public class MainActivity extends Activity implements OnClickListener {

	private TableLayout mLayout;
	
	private List<HomeUnit> myList; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLayout=(TableLayout) findViewById(R.id.tablelayout);
		new UpdateClass().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_refresh:
			new UpdateClass().execute();
			break;
		case R.id.action_settings:

			break;
		}
		return super.onOptionsItemSelected(item);
	}


	private class UpdateClass extends AsyncTask<Integer, String, List<HomeUnit>> {


		@Override
		protected List<HomeUnit> doInBackground(Integer... params) {
			DataManager dm = new DataManager();
			try {
				return dm.getHomeUnits();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(List<HomeUnit> result) {
			super.onPostExecute(result);
			if(result!=null) {
				myList = result;
				Toast.makeText(MainActivity.this, "Found "+result.size()+" units", Toast.LENGTH_LONG).show();
				mLayout.removeAllViews();
				TableRow row=null;
				for(int i=0;i<result.size();i++) {
					final HomeUnit myUnit = result.get(i);
					if(i==0) {
						row= new TableRow(MainActivity.this);
						row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
						row.setWeightSum(2);
						MyLog.d("DataManager","I dunno man11");
						
						mLayout.addView(row);
					}
					else if(i%2==0) {
						row= new TableRow(MainActivity.this);
						row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
						MyLog.d("DataManager","I dunno man");
						
						row.setWeightSum(2);
						mLayout.addView(row);
					}
					if(row!=null) {
						final Button btn = new Button(MainActivity.this);
						btn.setText(myUnit.getName());
						btn.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT, 1));
						btn.setPadding(10, 10,10,10);
						if(myUnit.getStatus()>0) {
							Drawable icon= MainActivity.this.getResources().getDrawable(R.drawable.ic_action_lamp_on);
							btn.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
						}
						else {
							Drawable icon= MainActivity.this.getResources().getDrawable(R.drawable.ic_action_lamp_off);
							btn.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
						}
						btn.setOnClickListener(MainActivity.this);
						btn.setId(i);
					//	setContentView(btn);
						row.addView(btn);
						
						MyLog.d("DataManager","added view");
						//mLayout.invalidate();
					}

				}

			}
			else {
				Toast.makeText(MainActivity.this, "Error, check internets?", Toast.LENGTH_LONG).show();
			}

		}




	}


	@Override
	public void onClick(final View v) {
		if(v.getId()<myList.size()) {
			new UpdateClassWithStatus().execute(v.getId(),(myList.get(v.getId()).getStatus()==0 ? 255:0));
		}
		MyLog.d("DataManager", "clicked:"+v.getId());
	}
	

	private class UpdateClassWithStatus extends AsyncTask<Integer, String, List<HomeUnit>> {
	

		@Override
		protected List<HomeUnit> doInBackground(Integer... params) {
			int id = params[0];
			int status=params[1];
			DataManager dm = new DataManager();
			try {
				MyLog.d("DataManager","tried first time");
				return dm.setStatus(id,status);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				MyLog.d("DataManager","trying again");
				return dm.setStatus(id,status);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(List<HomeUnit> result) {
			super.onPostExecute(result);
			if(result!=null) {
				myList = result;
				//Toast.makeText(MainActivity.this, "Found "+result.size()+" units", Toast.LENGTH_LONG).show();
				mLayout.removeAllViews();
				TableRow row=null;
				for(int i=0;i<result.size();i++) {
					final HomeUnit myUnit = result.get(i);
					if(i==0) {
						row= new TableRow(MainActivity.this);
						row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
						row.setWeightSum(2);
						MyLog.d("DataManager","I dunno man11");
						
						mLayout.addView(row);
					}
					else if(i%2==0) {
						row= new TableRow(MainActivity.this);
						row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
						MyLog.d("DataManager","I dunno man");
						
						row.setWeightSum(2);
						mLayout.addView(row);
					}
					if(row!=null) {
						final Button btn = new Button(MainActivity.this);
						btn.setText(myUnit.getName());
						btn.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT, 1));
						btn.setPadding(10, 10,10,10);
						if(myUnit.getStatus()>0) {
							Drawable icon= MainActivity.this.getResources().getDrawable(R.drawable.ic_action_lamp_on);
							btn.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
						}
						else {
							Drawable icon= MainActivity.this.getResources().getDrawable(R.drawable.ic_action_lamp_off);
							btn.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
						}
						btn.setOnClickListener(MainActivity.this);
						btn.setId(i);
					//	setContentView(btn);
						row.addView(btn);
						
						MyLog.d("DataManager","added view");
						//mLayout.invalidate();
					}

				}

			}
			else {
				Toast.makeText(MainActivity.this, "Error, check internets?", Toast.LENGTH_LONG).show();
			}

		}




	}

}
