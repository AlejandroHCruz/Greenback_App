package co.ensalsaverde.apps.greenback;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class Main extends SherlockFragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mFragmentTitles;
	
	//PARA SHARED PREFERENCES
	//private final String SpinnerValue = "SpinnerValue";
	//private final String UniqueIncome = "UniqueIncome";

	// Alert Dialog Manager declaration
	//AddExpensesAlertDialogManager alertExpenses = new AddExpensesAlertDialogManager();

	// Session Manager Class
	// SessionManager session;

	// SHARED PREFERENCESS
	//ESTO DA UN PUTO ERROR!!!!!!!!!!
	
	//SharedPreferences myPrefs = this.getSharedPreferences("myPrefs",
		//	MODE_WORLD_READABLE);
	//SharedPreferences.Editor prefsEditor = myPrefs.edit();

	// prefsEditor.putString(UniqueIncome, "f664.PNG");
	// prefsEditor.commit();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
				

		// Session Manager
		// session = new SessionManager(getApplicationContext());

		// SHARED PREFERENCES
		//prefsEditor.putString(SpinnerValue, "");
		//prefsEditor.commit();

		// NAV DRAWER
		mTitle = mDrawerTitle = getTitle();
		mFragmentTitles = getResources().getStringArray(R.array.fragments);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mFragmentTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// ACTION BAR SHERLOCK
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// ICONS NAV DRAWER
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View v) {
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View v) {
				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//para el Settints/INFO
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Save the Value of the Spinner to the shared preferences
	//public void SavePrefsSpinner(String value) {
		//prefsEditor.putString(SpinnerValue, value);
		//prefsEditor.commit();

//	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			return true;
			
			
		//Connect and send to to GOOGLE DRIVE
		case R.id.driveConnection:
			Intent i = new Intent(Main.this, SendToDrive.class);
			startActivity(i);
			
			return true;
			
			// If settings is clicked
			
		/*case R.id.action_settings:
			Intent i = new Intent(Main.this, Sources.class);
			startActivity(i);
			return true;*/ 

		case R.id.addExpense:

			// -------------THIS WORKED WITH AddExpensesAlertDialogManager Class----------
			// Show Alert Dialog
			// alertExpenses.showAlertDialog(Main.this, "Add a new expense",
			// "Please enter the data", false);
			// before the return.... Spinner spinnerExpenses =
			// (Spinner)findViewById(R.id.spinnerexpenses);

			
			//-----------------------ClickOn Expenses---------------------
			LayoutInflater li = LayoutInflater.from(Main.this);

			View promptsView = li.inflate(R.layout.popupaddexpenses, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					Main.this);

			alertDialogBuilder.setView(promptsView);

			 //Set dialog message

			alertDialogBuilder.setTitle("Add a new expense");
			alertDialogBuilder
					.setMessage("Enter the amount and select a category");
			alertDialogBuilder.setPositiveButton("Save",
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(Main.this, "Your expense has been safely stored.", Toast.LENGTH_LONG).show();

						}
					});
			
			alertDialogBuilder.setNegativeButton("Cancel", 					
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});

			alertDialogBuilder.setIcon(R.drawable.outcomeicon);
			
			//create alert dialog
			final AlertDialog alertDialog = alertDialogBuilder.create();

			final Spinner SpinnerExpenses = (Spinner) promptsView
					.findViewById(R.id.spinnerexpenses);
			
			/*final Button SaveExpenses = (Button) promptsView
					.findViewById(R.id.buttonSaveExpenses);
			final Button CancelExpenses = (Button) promptsView
					.findViewById(R.id.buttonSaveExpenses); */

			//Reference UI elements from my_dialog_layout in similar fashion

			//String value; para esto de abajo
			// Pasar un objeto, recibirlo alla como constructor para poder
			// acceder a él.
			
			SpinnerExpenses
					.setOnItemSelectedListener(new OnExpensesSpinnerItemClicked());

			alertDialog.show();

			return true;
			
			//--------------Termina el Expenses------------------

			// INCOME YEAH!!!!!!!!!!!
			
			//------------------ClickOn Income--------------------

		case R.id.addIncome:
			// THIS WORKED WITH AddExpensesAlertDialogManager Class
			// Show Alert Dialog
			// alertExpenses.showAlertDialog(Main.this, "Add a new expense",
			// "Please enter the data", false);
			// before the return.... Spinner spinnerExpenses =
			// (Spinner)findViewById(R.id.spinnerexpenses);

			LayoutInflater li2 = LayoutInflater.from(Main.this);

			View promptsView2 = li2.inflate(R.layout.popupaddincome, null);

			AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(
					Main.this);

			alertDialogBuilder2.setView(promptsView2);

			// Set dialog message

			alertDialogBuilder2.setTitle("Add a new income");
			alertDialogBuilder2
					.setMessage("Enter the amount and enjoy your new money!");
			alertDialogBuilder2.setIcon(R.drawable.incomeicon);
			alertDialogBuilder2.setCancelable(true);	
			alertDialogBuilder2.setPositiveButton("Save",
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(Main.this, "Your income has been safely stored.", Toast.LENGTH_LONG).show();
						}
					});
			
			alertDialogBuilder2.setNegativeButton("Cancel", 					
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});

			// create alert dialog
			final AlertDialog alertDialog2 = alertDialogBuilder2.create();

			// Reference UI elements from my_dialog_layout in similar fashion

				//DECLARANDO BOTON QUE YA NO USO
			 //Button SaveIncome = (Button) promptsView2
			 //	.findViewById(R.id.buttonSaveIncome);
			
			
			 //INTENTO DE ONTOUCH LISTENER!!!
			 
			// SaveIncome.setOnTouchListener(new
			// OnIncomeSaveButton(promptsView2, alertDialog2));

			 //Creo que esto no jala
			 
			/*
			 * SaveIncome.setOnTouchListener(new View.OnTouchListener() { public
			 * boolean onTouch(View arg0, MotionEvent arg1) { if
			 * (arg1.getAction()==1){ alertDialog2.dismiss(); return true;} else
			 * return false; } });
			 */

			/*
			 * Button CancelIncome = (Button) promptsView2
			 * .findViewById(R.id.buttonCancelIncome);
			 * CancelIncome.setOnTouchListener(new View.OnTouchListener() {
			 * public boolean onTouch(View arg0, MotionEvent arg1) { if
			 * (arg1.getAction() == 1) { alertDialog2.cancel(); return true; }
			 * else return false; } });
			 */

			alertDialog2.show();

			return true;
			
			//----------------------Termina income--------------------

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			selectItem(position);
		}
	}

	public void selectItem(int position) {
		Fragment newFragment = new Fragment_1();
		FragmentManager fm = getSupportFragmentManager();
		switch (position) {
		case 0:
			newFragment = new Fragment_1();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.commit();
			
			/*TextView's FAIL
			TextView t1, t4;
			t1 = (TextView)findViewById(R.id.txtview1);
			t1.setText("Fun");
			t4 = (TextView)findViewById(R.id.txtview1);
			t4.setText("50");*/
			
			break;
		case 1:
			newFragment = new Fragment_2();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.commit();
			break;
		case 2:
			newFragment = new Fragment_3();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.commit();
			break;
		case 3:
			newFragment = new Fragment_4();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.commit();
			break;
		case 4:
			newFragment = new Fragment_5();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.addToBackStack(null) // Permite regresar al fragmento
											// anterior con "back" DISQUE
					.commit();
			break;
		}
		/*
		 * fm.beginTransaction() .replace(R.id.content_frame, newListFragment)
		 * .commit();
		 */

		mDrawerList.setItemChecked(position, true);
		setTitle(mFragmentTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}