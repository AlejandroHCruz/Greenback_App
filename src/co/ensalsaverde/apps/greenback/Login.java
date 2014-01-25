package co.ensalsaverde.apps.greenback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class Login extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		// Button buttonMain= (Button)findViewById(R.id.buttonMain);

		// Setup the ViewPager
		MyPagerAdapter adapter = new MyPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.loginviewpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);

		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);

		// Start Main Activity

		/*
		 * OLD CONTINUE/LOGIN BUTTON FUNCTIONS buttonMain.setOnTouchListener(new
		 * View.OnTouchListener() { public boolean onTouch(View arg0,
		 * MotionEvent arg1) {
		 * 
		 * 
		 * Intent i = new Intent(getApplicationContext(), Main.class);
		 * startActivity(i); return false;
		 * 
		 * }});
		 */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.continueLogin:
			Intent i = new Intent(Login.this, Main.class);
			startActivity(i);

			return true;

		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this, Main.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
