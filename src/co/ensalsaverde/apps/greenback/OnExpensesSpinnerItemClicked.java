package co.ensalsaverde.apps.greenback;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


//Esta Clase si jala, pero es donde debería de regresar el valor al Main con un constructor
public class OnExpensesSpinnerItemClicked implements OnItemSelectedListener{	
		
	
	@Override
    public void onItemSelected(AdapterView<?> parent,
            View view, int pos, long id) {			
		
				//TOAST FROM HELL!!!
       // Toast.makeText(parent.getContext(), "Clicked : " +
       //         parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
         
    }

	
	
    @Override
    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    }

	
}

