package co.ensalsaverde.apps.greenback;

import java.util.HashMap;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_2 extends Fragment{
	// Session Manager Class
    SessionManager session;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

		View view = inflater.inflate(R.layout.fragment_2, container, false);
	//	String intent = getActivity().getIntent().getExtras().getString(Main.EXTRA_MESSAGE);
	//	int intIncome = Integer.parseInt(intent.toString());
		session = new SessionManager(getActivity());
		// get user data from session
        HashMap<String, String> user = session.getUserDetails();       
        // dinero
        String ahorros = user.get(SessionManager.KEY_SAVINGS);
        int intahorros = Integer.parseInt(ahorros.toString());

		TextView tvSavings = (TextView) view.findViewById(R.id.fragment2TitleSavings);
		//int savings = Integer.parseInt(tvSavings.getText().toString()); //Lo que tenemos ahorrado
		//int totalSavings = savings + intIncome;							//Sumamos el income que traeremos del alertDialog
		tvSavings.setText(""+intahorros);							//desplegamos la sumatoria
		
		return view;	
	}
	
		
}
