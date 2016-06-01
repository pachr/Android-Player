package android.ig2i.ig2cast.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Paul-Alexandre on 01/06/2016.
 */
public class BuildingMusicPlayerActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String first = intent.getStringExtra("firstKeyName"); //if it's a string you stored.
        String second = intent.getStringExtra("secondKeyName"); //if it's a string you stored.

        System.out.println(second);


        Toast.makeText(this,"New Activity",Toast.LENGTH_SHORT);

    }


}
