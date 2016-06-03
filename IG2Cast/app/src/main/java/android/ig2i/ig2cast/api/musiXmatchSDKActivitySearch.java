package android.ig2i.ig2cast.api;

import android.app.Activity;
import android.content.Intent;
import android.ig2i.ig2cast.R;
import android.ig2i.ig2cast.activity.ViewSearch;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.musixmatch.lyrics.MissingPluginException;
import com.musixmatch.lyrics.musiXmatchLyricsConnector;


/**
 * Created by kevin on 02/06/2016.
 */
public class musiXmatchSDKActivitySearch extends ViewSearch {

        private musiXmatchLyricsConnector mLyricsPluginSearch = null;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Intent intent = getIntent();

            final String artistName = intent.getStringExtra("mArtistTextView"); //if it's a string you stored.
            final String trackName = intent.getStringExtra("mTitleTextView"); //if it's a string you stored.


            mLyricsPluginSearch = new musiXmatchLyricsConnector(this);
            mLyricsPluginSearch.setLoadingMessage("Chargement des paroles", "Les paroles sont en cours de chargement avec MusiXmatch");


            findViewById(R.id.showLyricsSearch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(v.getContext(),artistName.toString(),Toast.LENGTH_LONG).show();
                    //Toast.makeText(v.getContext(),trackName.toString(),Toast.LENGTH_LONG).show();
                    try {
                        mLyricsPluginSearch.startLyricsActivity(artistName, trackName);
                    } catch (MissingPluginException e) {
                        mLyricsPluginSearch.downloadLyricsPlugin();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    @Override
    protected void onResume() {
        mLyricsPluginSearch.doBindService();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mLyricsPluginSearch.doUnbindService();
        super.onPause();
    }



}
