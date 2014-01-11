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
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment_2 extends Fragment{
	// Session Manager Class
    SessionManager session;
    
     private ProgressBar progressBar;
	 private int progressStatus = 0;
	 private Handler handler = new Handler();
	 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

		View view = inflater.inflate(R.layout.fragment_2, container, false);
		progressBar = (ProgressBar) view.findViewById(R.id.regularprogressbar);
		session = new SessionManager(getActivity());
		
		//Get all user data from session
        HashMap<String, String> user = session.getUserDetails();  
        
       //Use specific data from session 
        String savings = user.get(SessionManager.KEY_SAVINGS);
        String Goal = user.get(SessionManager.KEY_GOAL);
        String GoalName = user.get(SessionManager.KEY_GOALNAME);
        String GoalPrice = user.get(SessionManager.KEY_GOALPRICE);
        
        System.out.println(savings);
        System.out.println(Goal);
        System.out.println(GoalName);
        System.out.println(GoalPrice);
        
        TextView goaltxt1=(TextView) view.findViewById(R.id.textView1);
        TextView goaltxt2=(TextView) view.findViewById(R.id.Goal);
        TextView goalinitial=(TextView) view.findViewById(R.id.initial);
        TextView goalslash=(TextView) view.findViewById(R.id.slash);
        TextView goalend=(TextView) view.findViewById(R.id.end);
        TextView goaldots=(TextView) view.findViewById(R.id.dots);
        
      
       
		
        if(Goal.toString().equals("1")&& savings!=GoalPrice){
        	 int intsavings = Integer.parseInt(savings.toString());
             int intGoalPrice = Integer.parseInt(GoalPrice.toString());
             progressStatus = (intsavings*100)/intGoalPrice;
             
        	goaltxt1.setVisibility(View.VISIBLE);
        	goaltxt2.setText(GoalName);
        	goaltxt2.setVisibility(View.VISIBLE);
        	goalinitial.setText(savings);
        	goalinitial.setVisibility(View.VISIBLE);
        	goalslash.setText("/");
        	goalslash.setVisibility(View.VISIBLE);
        	goalend.setText(GoalPrice);
        	goalend.setVisibility(View.VISIBLE);
        	goaldots.setVisibility(View.VISIBLE);
        	progressBar.setProgress(progressStatus);
        	
        	
        } else if(Goal.toString().equals("1") && savings==GoalPrice){
       	 int intsavings = Integer.parseInt(savings.toString());
         int intGoalPrice = Integer.parseInt(GoalPrice.toString());
         progressStatus = (intsavings*100)/intGoalPrice;
         
    	goaltxt1.setVisibility(View.VISIBLE);
    	goaltxt2.setText(GoalName);
    	goaltxt2.setVisibility(View.VISIBLE);
    	goalinitial.setText(savings);
    	goalinitial.setVisibility(View.VISIBLE);
    	goalslash.setText("/");
    	goalslash.setVisibility(View.VISIBLE);
    	goalend.setText(GoalPrice);
    	goalend.setVisibility(View.VISIBLE);
    	progressBar.setProgress(progressStatus);	
        } else if(Goal.toString().equals("0")){
        	goaltxt1.setVisibility(View.INVISIBLE);
        	goaltxt2.setText(GoalName);
        	goaltxt2.setVisibility(View.INVISIBLE);
        	goalinitial.setText("/");
        	goalinitial.setVisibility(View.INVISIBLE);
        	goalslash.setText(savings);
        	goalslash.setVisibility(View.INVISIBLE);
        	goalend.setText(GoalPrice);
        	goalend.setVisibility(View.INVISIBLE);
        	progressBar.setVisibility(View.INVISIBLE);
        	goalend.setVisibility(View.INVISIBLE);
        }
        
        
        //If I need to parse form string to int:
        //int intsavings = Integer.parseInt(savings.toString());

        //Show savings on screen
		TextView tvSavings = (TextView) view.findViewById(R.id.fragment2TitleSavings);
		tvSavings.setText(savings);									
		return view;	
		
	}
	
		
}
