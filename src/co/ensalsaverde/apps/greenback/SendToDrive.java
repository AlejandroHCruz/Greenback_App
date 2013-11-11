package co.ensalsaverde.apps.greenback;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

public class SendToDrive extends SherlockActivity {
  static final int REQUEST_ACCOUNT_PICKER = 1;
  static final int REQUEST_AUTHORIZATION = 2;
  static final int CAPTURE_IMAGE = 3;

  private static Uri fileUri;
  private static Drive service;
  private GoogleAccountCredential credential;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    credential = GoogleAccountCredential.usingOAuth2(this, Arrays.asList(DriveScopes.DRIVE));
    startActivityForResult(credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
  }

  @Override
  protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    switch (requestCode) {
    case REQUEST_ACCOUNT_PICKER:
      if (resultCode == RESULT_OK && data != null && data.getExtras() != null) {
        String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        if (accountName != null) {
          credential.setSelectedAccountName(accountName);
          service = getDriveService(credential);
          startCameraIntent();
         
        }
      }
      break;
    case REQUEST_AUTHORIZATION:
      if (resultCode == Activity.RESULT_OK) {
        saveFileToDrive();
      } else {
        startActivityForResult(credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
      }
      break;
    case CAPTURE_IMAGE:
      if (resultCode == Activity.RESULT_OK) {
        saveFileToDrive();
      }
    }
  }

  private void startCameraIntent() {
	  
    String mediaStorageDir = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES).getPath();
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
    fileUri = Uri.fromFile(new java.io.File(mediaStorageDir + java.io.File.separator + "IMG_"
        + timeStamp + ".jpg"));
   // Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
   //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
   // startActivityForResult(cameraIntent, CAPTURE_IMAGE);
  //Día de hoy
		Calendar currentDate = Calendar.getInstance();
		System.out.println("Current time => " + currentDate.getTime());
		SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate1 = df1.format(currentDate.getTime());
		
    String uno = " Expenses     ";
    String dos = " Amount      ";
    String tres = " Percentage ";
    String cuatro = "             October";
    String cinco = " Fun     ";
    String seis = "       $50      ";
    String siete = "   5.43% ";
    String ocho = " Food     ";
    String nueve = "       $100      ";
    String diez = "   10.86% ";
    String once = " School     ";
    String doce = "       $150      ";
    String trece = "   16.30% ";
    String catorce = " Home     ";
    String quince = "       $300      ";
    String dieciseis = "   32.60% ";
    String diecisiete = " Transport     ";
    String dieciocho = "       $100      ";
    String diecinueve = "   10.86% ";
    String veinte = " Savings     ";
    String veintiuno = "       $20      ";
    String veintidos = "   2.17% ";
    String veintitres = " Others     ";
    String veinticuatro = "       $200      ";
    String veinticinco = "   21.74% ";
    String veintiseis = "             September";
    String veintisiete = " Fun     ";
    String veintiocho = "       $100      ";
    String veintinueve = "   10.86% ";
    String treinta = " Food     ";
    String treintayuno = "       $100      ";
    String treintaydos = "   10.86% ";
    String treintaytres = " School     ";
    String treintaycuatro = "       $50      ";
    String treintaycinco = "   5.43% ";
    String treintayseis = " Home     ";
    String treintaysiete = "       $150      ";
    String treintayocho = "   16.40% ";
    String treintaynueve = " Transport     ";
    String cuarenta = "       $300      ";
    String cuarentayuno = "   32.60% ";
    String cuarentaydos = " Savings     ";
    String cuarentaytres = "       $200      ";
    String cuarentaycuatro = "   21.74% ";
    String cuarentaycinco = " Others     ";
    String cuarentayseis = "       $20      ";
    String cuarentaysiete = "   2.17% ";
    
    
    String exportacion = uno + dos + tres +'\n'+'\n'+cuatro+'\n'+cinco+seis+siete+'\n'+ocho+nueve+diez+'\n'+once+doce+trece+'\n'+catorce+quince+dieciseis+'\n'+diecisiete+dieciocho+diecinueve+'\n'+veinte+veintiuno+veintidos+'\n'+veintitres+veinticuatro+veinticinco+'\n'+'\n'+veintiseis+'\n'+veintisiete+veintiocho+veintinueve+'\n'+treinta+treintayuno+treintaydos+'\n'+treintaytres+treintaycuatro+treintaycinco+'\n'+treintayseis+treintaysiete+treintayocho+'\n'+treintaynueve+cuarenta+cuarentayuno+'\n'+cuarentaydos+cuarentaytres+cuarentaycuatro+'\n'+cuarentaycinco+cuarentayseis+cuarentaysiete;
   
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, exportacion);
    sendIntent.setType("application/vnd.google-apps.file");
    
    startActivity(Intent.createChooser(sendIntent, exportacion));
	  
  }

  private void saveFileToDrive() {
    Thread t = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          // File's binary content
          java.io.File fileContent = new java.io.File(fileUri.getPath());
          FileContent mediaContent = new FileContent("application/vnd.google-apps.spreadsheet", fileContent);

          // File's metadata.
          File body = new File();
          body.setTitle(fileContent.getName());
          body.setMimeType("image/jpeg");

          File file = service.files().insert(body, mediaContent).execute();
          if (file != null) {
            showToast("Photo uploaded: " + file.getTitle());
            startCameraIntent();
          }
        } catch (UserRecoverableAuthIOException e) {
          startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
          
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
    t.start();
  }

  private Drive getDriveService(GoogleAccountCredential credential) {
    return new Drive.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), credential)
        .build();
  }
 

  public void showToast(final String toast) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
      }
    });
  }
}