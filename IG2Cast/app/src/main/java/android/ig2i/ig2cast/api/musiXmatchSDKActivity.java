package android.ig2i.ig2cast.api;

/**
 * Created by Paul-Alexandre on 01/06/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.ig2i.ig2cast.R;
import android.ig2i.ig2cast.activity.BuildingMusicPlayerActivity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;

import com.musixmatch.lyrics.MissingPluginException;
import com.musixmatch.lyrics.musiXmatchLyricsConnector;

public class musiXmatchSDKActivity extends BuildingMusicPlayerActivity {
    private musiXmatchLyricsConnector mLyricsPlugin = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        final String artistName = intent.getStringExtra("mArtistTextView"); //if it's a string you stored.
        final String trackName = intent.getStringExtra("mTitleTextView"); //if it's a string you stored.


        mLyricsPlugin = new musiXmatchLyricsConnector(this);
        mLyricsPlugin.setLoadingMessage("Chargement des paroles", "Les paroles sont en cours de chargement avec MusiXmatch");

        findViewById(R.id.showLyrics).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mLyricsPlugin.startLyricsActivity(artistName, trackName);
                } catch (MissingPluginException e) {
                    mLyricsPlugin.downloadLyricsPlugin();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        mLyricsPlugin.doBindService();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mLyricsPlugin.doUnbindService();
        super.onPause();
    }
}