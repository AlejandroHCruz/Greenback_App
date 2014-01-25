package co.ensalsaverde.apps.greenback;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;




public class Main extends SherlockFragmentActivity {
	public final static String EXTRA_MESSAGE = "co.ensalsaverde.apps.greenback.MESSAGE";
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mFragmentTitles;
	// Session Manager Class
    SessionManager session;
	
	//Spinner Setup
	LayoutInflater li = null;

	View promptsView = null;
	
	Spinner SpinnerExpenses = null;
	Spinner SpinnerExpenses2 = null;
	Spinner SpinnerIncome = null;
	
	TextView tvSavings, tvBudget = null;
	
	int intIncome = 0;
	int intOutcome = 0;
	int intEditTextSavings = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		getSupportActionBar().setIcon(R.drawable.alertdialogicon);
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Main.this);
        SharedPreferences.Editor editor = preferences.edit();
        session = new SessionManager(getBaseContext());				       		
        HashMap<String, String> user = session.getUserDetails();             

        

        String fragmentDefault = user.get(SessionManager.KEY_FRAGMENTDEFAULT);
        int intFragmentDefault = Integer.parseInt(fragmentDefault.toString());

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
			selectItem(intFragmentDefault);
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
		case R.id.savingsmenu:
			//antes era el men� para mandar a drive
			//Intent i = new Intent(Main.this, SendToDrive.class);
			//startActivity(i);
			//return true;
			LayoutInflater li3 = LayoutInflater.from(Main.this);	
			final View promptsView3 = li3.inflate(R.layout.popupaddtransfer, null);
			final Spinner SpinnerSavings = (Spinner) promptsView3
					.findViewById(R.id.spinnersavings);
			AlertDialog.Builder alertDialogBuilder3 = new AlertDialog.Builder(
					Main.this);

			alertDialogBuilder3.setView(promptsView3);

			       //Set dialog message
			alertDialogBuilder3.setIcon(R.drawable.driveconnecticon);
			alertDialogBuilder3.setTitle("Transfer Your Money");
			alertDialogBuilder3
					.setMessage("Enter the amount:");
			alertDialogBuilder3.setPositiveButton("Save",
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// WHEN SAVE SAVINGS IS CLICKED
							final EditText EditTextSavings =(EditText) promptsView3.findViewById(R.id.NumberSavingsTextView);
							 intEditTextSavings = Integer.parseInt(EditTextSavings.getText().toString());
							//agarrar la cantidad actual desde shared preferences, restarle esta nueva cantidad, sum�rselo a savings y volver a guardarla.
							 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Main.this);
				               SharedPreferences.Editor editor = preferences.edit();
				               String selectedItemSavings = SpinnerSavings.getSelectedItem().toString();
				            // get user data from session
				               session = new SessionManager(getBaseContext());				       		
				               HashMap<String, String> user = session.getUserDetails();       
				               // dinero
				               String StringBudget = user.get(SessionManager.KEY_BUDGET);
				               int intBudget = Integer.parseInt(StringBudget.toString());
				               String StringSavings = user.get(SessionManager.KEY_SAVINGS);
				               int intSavings = Integer.parseInt(StringSavings.toString());
				               
				               if (selectedItemSavings.toString().equals("Weekly Budget to Savings")){
				            	   int intMinusBudget = intBudget - intEditTextSavings;
				            	   String StringTotalBudget = ""+intMinusBudget; 
				            	   int intTotalSavings = intSavings + intEditTextSavings;
				            	   String StringTotalSavings = ""+intTotalSavings;				            	   
				            	   session.createLoginSession(StringTotalBudget);
				            	   session.transfer(StringTotalSavings);
				            	   System.out.println("Total savings = " + StringTotalSavings);	
				            	   int numberOfFragment = 1;
					               String StringNumberOfFragment = ""+numberOfFragment;
					               session.fragmentDefault(StringNumberOfFragment);
				            	   Toast.makeText(Main.this, "$"+intEditTextSavings +" were transfered to your savings", Toast.LENGTH_LONG).show();
				               		}
				               if (selectedItemSavings.toString().equals("Savings to Weekly Budget")){
				            	   int intMinusSavings = intSavings - intEditTextSavings;
				            	   String StringTotalSavings = ""+intMinusSavings; 
				            	   int intTotalBudget = intBudget + intEditTextSavings;
				            	   String StringTotalBudget = ""+intTotalBudget;				            	   
				            	   session.createLoginSession(StringTotalBudget);
				            	   session.transfer(StringTotalSavings);
				            	   System.out.println("Total savings = " + StringTotalSavings);	
				            	   int numberOfFragment = 0;
					               String StringNumberOfFragment = ""+numberOfFragment;
					               session.fragmentDefault(StringNumberOfFragment);
				            	   Toast.makeText(Main.this, "$"+intEditTextSavings +" were transfered to your budget", Toast.LENGTH_LONG).show();
				               		}
				               
							  //Hace refresh a la actividad para que el contenido se actualice din�micamente.
							  Intent intent = getIntent();
							  intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				               finish();
				               startActivity(intent);
				               
							 
							
								

						}
					});
			
			alertDialogBuilder3.setNegativeButton("Cancel", 					
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});

			
			//create alert dialog
			final AlertDialog alertDialog3 = alertDialogBuilder3.create();
			alertDialog3.show();
			return true;

		case R.id.addExpense:	
			//-----------------------ClickOn Expenses (Alert Dialog)---------------------			
			
			LayoutInflater li = LayoutInflater.from(Main.this);			
			promptsView = li.inflate(R.layout.popupaddexpenses, null);
			final Spinner SpinnerExpenses = (Spinner) promptsView
					.findViewById(R.id.spinnerexpenses);
			final Spinner SpinnerExpenses2 = (Spinner) promptsView
					.findViewById(R.id.spinnerexpenses2);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					Main.this);

			alertDialogBuilder.setView(promptsView);

			       //Set dialog message

			alertDialogBuilder.setTitle("Add a new expense");
			alertDialogBuilder.setIcon(R.drawable.expense);
			alertDialogBuilder
					.setMessage("Enter the amount and select a category");
			alertDialogBuilder.setPositiveButton("Save",
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// WHEN SAVE EXPENSES IS CLICKED
							final EditText outcome =(EditText) promptsView.findViewById(R.id.NumberExpensesTextView);
							 intOutcome = Integer.parseInt(outcome.getText().toString());
							//agarrar la cantidad actual desde shared preferences, sumarle el income y volver a guardarla.
							 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Main.this);
				               SharedPreferences.Editor editor = preferences.edit();
				               String selectedItem2 = SpinnerExpenses2.getSelectedItem().toString();
				            // get user data from session
				               session = new SessionManager(getBaseContext());				       		
				               HashMap<String, String> user = session.getUserDetails();       
				               // dinero
				               String StringBudget = user.get(SessionManager.KEY_BUDGET);
				               String StringSavings = user.get(SessionManager.KEY_SAVINGS);
				               
				               if (selectedItem2.toString().equals("From Weekly Budget")){
				            	   int intBudget = Integer.parseInt(StringBudget.toString());				               
				            	   int intMinusBudget = intBudget - intOutcome;
				            	   String StringMinusBudget = ""+intMinusBudget;
				            	   session.createLoginSession(StringMinusBudget);				             			              
				            	   System.out.println(intOutcome);
				            	   String selectedItem = SpinnerExpenses.getSelectedItem().toString();
				            	   Toast.makeText(Main.this, "$"+intOutcome +" were spent on " + selectedItem + " from your weekly budget", Toast.LENGTH_LONG).show();
				               }
				               if (selectedItem2.toString().equals("From Savings")){
				            	   int intSavings = Integer.parseInt(StringSavings.toString());				               
				            	   int intMinusSavings = intSavings - intOutcome;
				            	   String StringMinusSavings = ""+intMinusSavings;
				            	   session.transfer(StringMinusSavings);				             			              
				            	   System.out.println(intOutcome);
				            	   String selectedItem = SpinnerExpenses.getSelectedItem().toString();
				            	   Toast.makeText(Main.this, "$"+intOutcome +" were spent on " + selectedItem + " from your savings", Toast.LENGTH_LONG).show();
				               }
							  //Hace refresh a la actividad para que el contenido se actualice din�micamente.
							  Intent intent = getIntent();
							  intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				               finish();
				               startActivity(intent);
				               
							 
							
								

						}
					});
			
			alertDialogBuilder.setNegativeButton("Cancel", 					
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});

			alertDialogBuilder.setIcon(R.drawable.expense);
			
			//create alert dialog
			final AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			return true;
			
			//--------------Termina el Expenses (Alert Dialog)------------------


			
			//------------------ClickOn Income--------------------

		case R.id.addIncome:
			//-------------------Income (Alert Dialog)-----------------------------
			 LayoutInflater li2 = LayoutInflater.from(Main.this);
			final View promptsView2 = li2.inflate(R.layout.popupaddincome, null);
			final Spinner SpinnerIncome = (Spinner) promptsView2
					.findViewById(R.id.spinnerincome);
			 tvSavings = (TextView) findViewById(R.id.fragment1TitleSavings);
			 tvBudget = (TextView) findViewById(R.id.fragment2TitleSavings);
			
			AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(Main.this);
			alertDialogBuilder2.setView(promptsView2);

			// Set dialog message
			alertDialogBuilder2.setTitle("Add a new income");
			alertDialogBuilder2.setMessage("Enter the amount and enjoy your new money!");
			alertDialogBuilder2.setIcon(R.drawable.income);
			alertDialogBuilder2.setCancelable(true);	
			alertDialogBuilder2.setPositiveButton("Save",
					new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							final EditText income =(EditText) promptsView2.findViewById(R.id.NumberIncomeTextView);
							 intIncome = Integer.parseInt(income.getText().toString());
							 
							// Intent intent = new Intent();
						    //	intent.putExtra(EXTRA_MESSAGE, intIncome);
						    //	startActivity(intent);
							 
							 //agarrar la cantidad actual desde shared preferences, sumarle el income y volver a guardarla.
							 SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Main.this);
				               SharedPreferences.Editor editor = preferences.edit();
				               String selectedItem = SpinnerIncome.getSelectedItem().toString();
				               
				            // get user data from session
				               session = new SessionManager(getBaseContext());				       		
				               HashMap<String, String> user = session.getUserDetails();  
				               String StringSavings = user.get(SessionManager.KEY_SAVINGS);
				               String StringBudget = user.get(SessionManager.KEY_BUDGET);
				             
				               if (selectedItem.toString().equals("To Savings")){
					               int intSavings = Integer.parseInt(StringSavings.toString());
					               int intTotalSavings = intSavings + intIncome;
					               String StringTotalSavings = ""+intTotalSavings;
					               session.transfer(StringTotalSavings);
					               int numberOfFragment = 1;
					               String StringNumberOfFragment = ""+numberOfFragment;
					               session.fragmentDefault(StringNumberOfFragment);
					               System.out.println(intTotalSavings);
					              // tvSavings.setText(""+intTotalSavings);
					               
					               Toast.makeText(Main.this, "yay! you saved $"+intIncome +" more", Toast.LENGTH_LONG).show();
				             }
				             if (selectedItem.toString().equals("To Weekly Budget")){
					            // weekly Budget  
					               int intBudget = Integer.parseInt(StringBudget.toString());
					               int intTotalBudget = intBudget + intIncome;
					               String StringTotalBudget = ""+intTotalBudget;
					               session.createLoginSession(StringTotalBudget);
					               int numberOfFragment = 0;
					               String StringNumberOfFragment = ""+numberOfFragment;
					               session.fragmentDefault(StringNumberOfFragment);
					               System.out.println(intTotalBudget);
					             //  tvBudget.setText(StringTotalBudget);
					               Toast.makeText(Main.this, "yay! you got $"+intIncome +" more", Toast.LENGTH_LONG).show();
				             }
							  //Hace refresh a la actividad para que el contenido se actualice din�micamente.
							  Intent intent = getIntent();
							  intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				               finish();
				               startActivity(intent);
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
						
			//The font is setted in Fragment_1.java
			
			int numberOfFragment0 = 0;
            String StringNumberOfFragment0 = ""+numberOfFragment0;
            session.fragmentDefault(StringNumberOfFragment0);
		
			
			break;
		case 1:
			newFragment = new Fragment_2();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.commit();
			int numberOfFragment = 1;
            String StringNumberOfFragment = ""+numberOfFragment;
            session.fragmentDefault(StringNumberOfFragment);
			break;
		case 2:
			newFragment = new Fragment_3();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.commit();
			int numberOfFragment2 = 2;
            String StringNumberOfFragment2 = ""+numberOfFragment2;
            session.fragmentDefault(StringNumberOfFragment2);
			break;
		case 3:
			newFragment = new Fragment_4();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
					.commit();
			Intent intent = new Intent(Main.this, Login.class);	    	
	    	startActivity(intent);
			break;
		case 4:
			newFragment = new Fragment_5();
			fm.beginTransaction().replace(R.id.content_frame, newFragment)
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
	public void onGoalButtonClicked(View view){
		Intent a = new Intent(view.getContext(), Goals.class);
        startActivity(a); 
        
	}
	
}