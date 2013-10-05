package co.ensalsaverde.apps.greenback;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

//THIS CLASS IS NO LONGER USED, BUT IS GOOD TO HAVE IT AROUND
public class AddExpensesAlertDialogManager{
	

//This alert dialog is used to add a new expense
@SuppressWarnings("deprecation")
public void showAlertDialog(Context context, String title, String message,
            Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
  
        // Setting Dialog Title
        alertDialog.setTitle(title);
  
        // Setting Dialog Message
        alertDialog.setMessage(message);
  
        if(status != null)
            // Setting alert dialog icon; First drawable is for succes, second one for fail.
            alertDialog.setIcon((status) ? R.drawable.incomeicon : R.drawable.incomeicon);
                
        //SPINNERS
        //final Spinner mSpinner= (Spinner) promptsView.findViewById(R.id.mySpinner);
        
        //Spinner spinnerExpenses = (Spinner)findViewById(R.id.spinnerexpenses);
        
        
        //final Button mButton = (Button) promptsView.findViewById(R.id.myButton);
  
        // Setting SEND Button
        alertDialog.setButton("Send", new DialogInterface.OnClickListener() {
        	//Save info to DB
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        
        // Setting SEND Button
        alertDialog.setButton2("Cancel", new DialogInterface.OnClickListener(){
        	
        	public void onClick(DialogInterface dialog, int which) {
            }
        });
        
  
        // Showing Alert Message
        alertDialog.show();
    }
}
