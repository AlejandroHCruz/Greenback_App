package co.ensalsaverde.apps.greenback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Fragment_3 extends Fragment{
	// Delete button
    Button DeleteButton;
     
    // Session Manager Class
    SessionManager session;
    
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.deleteprogress, container, false);		
		session = new SessionManager(getActivity());
		
		// Login button
	    DeleteButton = (Button) view.findViewById(R.id.deletebutton);
	     
	     
	    // Login button click event
	    DeleteButton.setOnClickListener(new View.OnClickListener() {
	         
	        @Override
	        public void onClick(View arg0) {
	            // Get username, password from EditText
	            
	        	session.createLoginSession("0");
	        	session.transfer("0");
	        	session.fragmentDefault("0");
	        	session.goal("0");
                session.goalName("0");
                session.goalPrice("0");
                
               
       //Toast.makeText(Fragment_3.this, "$ were spent on " +" from your savings", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(),"Your progress was deleted.",Toast.LENGTH_SHORT).show();
                // Returning to Savings
                //Intent i = new Intent(getApplicationContext(), Main.class);
               // startActivity(i);
               // finish();
	        }
	    });
		
		return view;	
	}

}
