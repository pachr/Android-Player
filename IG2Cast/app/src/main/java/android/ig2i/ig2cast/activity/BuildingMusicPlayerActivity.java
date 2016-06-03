package android.ig2i.ig2cast.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.ig2i.ig2cast.MainActivity;
import android.ig2i.ig2cast.R;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Paul-Alexandre on 01/06/2016.
 */
public class BuildingMusicPlayerActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public TextView mSongTitle;
    public TextView mSongArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player);

        Intent intent = getIntent();
        String title = intent.getStringExtra("mTitleTextView"); //if it's a string you stored.
        String artist = intent.getStringExtra("mArtistTextView"); //if it's a string you stored.
        String duration = intent.getStringExtra("mDurationTextView"); //if it's a string you stored.

        //TextView, ImageView declaration
        mSongTitle = (TextView) findViewById(R.id.songTitle);
        mSongArtist = (TextView) findViewById(R.id.songArtist);

        mSongTitle.setText(title);
        mSongArtist.setText(artist);


        /*
        /*MP3 player Buttons
         */

        final ImageButton stopPlay = (ImageButton) findViewById(R.id.BtnStop);
        stopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.MP.isPlaying()) {
                    MainActivity.MP.stop();
                }
            }
        });


        final ImageButton startPlay = (ImageButton) findViewById(R.id.BtnPlay);
        startPlay.setActivated(false);
        startPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.MP.isPlaying()) {
                    MainActivity.MP.pause();
                    startPlay.setActivated(!startPlay.isActivated());

                } else {
                    MainActivity.MP.start();
                    startPlay.setActivated(!startPlay.isActivated());
                }


            }
        });

    }


}
