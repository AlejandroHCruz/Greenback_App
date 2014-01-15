package co.ensalsaverde.apps.greenback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class Goals extends Activity{

    EditText txtGoalName, txtGoalPrice;
   
     
    // login button
    Button GoalButton;
     
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
     
    // Session Manager Class
    SessionManager session;
    
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals);
     // Session Manager
        session = new SessionManager(getApplicationContext());  
        
       
    txtGoalName = (EditText) findViewById(R.id.goalname);
    txtGoalPrice = (EditText) findViewById(R.id.goalprice);
     
     
    // Login button
    GoalButton = (Button) findViewById(R.id.button1);
     
     
    // Login button click event
    GoalButton.setOnClickListener(new View.OnClickListener() {
         
        @Override
        public void onClick(View arg0) {
            // Get username, password from EditText
            
            String StringGoalName = txtGoalName.getText().toString();
            String StringGoalPrice = txtGoalPrice.getText().toString();
                            
            if(StringGoalName.trim().length() > 0 && StringGoalPrice.trim().length() > 0){
            	    String txtgoal = "1";    
            	    session.goal(txtgoal);
                    session.goalName(StringGoalName);
                    session.goalPrice(StringGoalPrice);
                    
                    // Returning to Savings
                    Intent i = new Intent(getApplicationContext(), Main.class);
                    startActivity(i);
                    finish();
                 
            }else{
                // user didn't entered Data
                // Show alert asking him to enter the details
                alert.showAlertDialog(Goals.this, "Commit failed", "Please enter a Financial goal and what you want to buy", false);
            }
             
        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

}
}
