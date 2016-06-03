package android.ig2i.ig2cast.activity;

import android.app.Activity;
import android.content.Intent;
import android.ig2i.ig2cast.R;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by kevin on 02/06/2016.
 */
public class ViewSearch extends Activity {

        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */

        public TextView mSongTitle;
        public TextView mSongArtist;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.lyrics);

            Intent intent = getIntent();
            String title = intent.getStringExtra("mTitleTextView"); //if it's a string you stored.
            String artist = intent.getStringExtra("mArtistTextView"); //if it's a string you stored.
            //String duration = intent.getStringExtra("mDurationTextView"); //if it's a string you stored.

            //TextView, ImageView declaration
            mSongTitle = (TextView) findViewById(R.id.songTitle);
            mSongArtist = (TextView) findViewById(R.id.songArtist);

            mSongTitle.setText(title);
            mSongArtist.setText(artist);


        }







}
