package co.ensalsaverde.apps.greenback;


import java.util.HashMap;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_1 extends Fragment{
	
	// Session Manager Class
    SessionManager session;
    
	//int intIncome = 10;
	
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.fragment_1, container, false);
	//	String intent = getActivity().getIntent().getExtras().getString(Main.EXTRA_MESSAGE);
	//	int intIncome = Integer.parseInt(intent.toString());
		session = new SessionManager(getActivity());
		// get user data from session
        HashMap<String, String> user = session.getUserDetails();       
        // dinero
        String Income = user.get(SessionManager.KEY_BUDGET);
        int intIncome = Integer.parseInt(Income.toString());

		TextView tvSavings = (TextView) view.findViewById(R.id.fragment1TitleSavings);
		//int savings = Integer.parseInt(tvSavings.getText().toString()); //Lo que tenemos ahorrado
		//int totalSavings = savings + intIncome;							//Sumamos el income que traeremos del alertDialog
		tvSavings.setText(""+intIncome);							//desplegamos la sumatoria
		
		
		//Set font
		TextView tv = (TextView) view.findViewById(R.id.fragment1SuperTitle);
		Typeface tf = Typeface.createFromAsset(tv.getContext().getAssets(),
				"fonts/Brush_Script_Std.otf");
		tv.setTypeface(tf);

		
		
		return view;

	}
}
