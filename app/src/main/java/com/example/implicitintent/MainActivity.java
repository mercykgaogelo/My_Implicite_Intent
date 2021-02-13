package com.example.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.core.app.ShareCompat;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextWebsite;
    private  EditText mEditTextLocation;
    private  EditText mEditTextShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextWebsite = findViewById(R.id.website_editText);
        mEditTextLocation= findViewById(R.id.location_editText);
        mEditTextShare = findViewById(R.id.share_editText);
    }

    public void openWebsite(View view) {
        //GETTING THE URI TEXT
        String uriString = mEditTextWebsite.getText().toString();
      //  Encode and parse that string into a Uri object
        Uri webPage = Uri.parse(uriString);
     //CREATING INTENT WITH(Intent.ACTION_VIEW AS ACTION AND URI AS DATA
        Intent intentUri = new Intent(Intent.ACTION_VIEW, webPage);
        //FINDING THE ACTIVITY THAT CAN HANDLE THE IMPLICIT INTENT USING METHOD resolveACTIVITY
        // AND ANDROID PACKAGE MANAGER
        if(intentUri.resolveActivity(getPackageManager())!=null){
            startActivity(intentUri);
        }else {
            Log.d("ImplicitIntent","Intent Could not resolve");
        }
    }

    public void openLocation(View view) {
        String stringLocation = mEditTextLocation.getText().toString();
        //    PARSE A STRING INTO URI OBJECT WIT GEO SEARCH QUERY
        Uri locationUri = Uri.parse("geo:0,0?=" + stringLocation);
        //CREATING INTENT WITH INTENT.ACTION.VIEW
        Intent intentLocation = new Intent(Intent.ACTION_VIEW, locationUri);
        //RESOLVING THE INTENT AND CHECK IF IT WAS RESOLVED SUCCESSFULLY
        if (intentLocation.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intentLocation);
        } else
            {
            Log.d("ImplicitIntent", "Did not resolve successfully");
        }
    }

    public void shareText(View view) {
        String stringShare = mEditTextShare.getText().toString();
        //Define the mime type of the text to share
          String mimeType = "text/plain";
          //Call ShareCompat.IntentBuilder with these methods:
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.chooser_title)
                .setText(stringShare)
                .startChooser();
    }
}