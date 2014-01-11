package co.ensalsaverde.apps.greenback;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class SessionManager {
	// Shared Preferences
    SharedPreferences pref;
     
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";
     
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
     
    // User name (make variable public to access from outside)
    public static final String KEY_BUDGET = "budget";
    
    public static final String KEY_SAVINGS = "savings";
    
    public static final String KEY_FRAGMENTDEFAULT = "fragmentdefault";
    
    public static final String KEY_GOAL = "goal";
    
    public static final String KEY_GOALNAME = "goalname";
    
    public static final String KEY_GOALPRICE = "goalprice";
     
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
     
    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
}
    /**
     * Create login session
     * */
    //Escribir los datos en el editor
    public void createLoginSession(String budget){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);        
        // Storing name in pref
        editor.putString(KEY_BUDGET, budget);
        // commit changes
        editor.commit();
    }
    //este metodo sirve para actualizar el valor de lo que tengamos en savings
    public void transfer(String savings){
    	editor.putBoolean(IS_LOGIN, true);        
        
        editor.putString(KEY_SAVINGS, savings);
        // commit changes
        editor.commit();
    }
    public void fragmentDefault(String fragmentdefault){
    	editor.putBoolean(IS_LOGIN, true);        
        
        editor.putString(KEY_FRAGMENTDEFAULT, fragmentdefault);
       
        editor.commit();
    }
    public void goal(String goal){
    	editor.putBoolean(IS_LOGIN, true);        
        
        editor.putString(KEY_GOAL, goal);
       
        editor.commit();
    }
    public void goalName(String goalname){
    	editor.putBoolean(IS_LOGIN, true);        
        
        editor.putString(KEY_GOALNAME, goalname);
       
        editor.commit();
    }
    public void goalPrice(String goalprice){
    	editor.putBoolean(IS_LOGIN, true);        
        
        editor.putString(KEY_GOALPRICE, goalprice);
       
        editor.commit();
    }
    //Leer los datos del editor para crear la escena
    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user weekly budget
        user.put(KEY_BUDGET, pref.getString(KEY_BUDGET, "0"));
         
        // user savings id
        user.put(KEY_SAVINGS, pref.getString(KEY_SAVINGS, "0"));
        
     // user savings id
        user.put(KEY_FRAGMENTDEFAULT, pref.getString(KEY_FRAGMENTDEFAULT, "0"));
        
        user.put(KEY_GOAL, pref.getString(KEY_GOAL, "0"));
        user.put(KEY_GOALNAME, pref.getString(KEY_GOALNAME, "0"));
        user.put(KEY_GOALPRICE, pref.getString(KEY_GOALPRICE, "0"));
         
        // return user
        return user;
    }
    
    
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}