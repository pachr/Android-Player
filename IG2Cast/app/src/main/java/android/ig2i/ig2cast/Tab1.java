package android.ig2i.ig2cast;


import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.support.v7.widget.RecyclerView;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Tab1 extends Fragment {

    private List<String> songs = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    class Mp3Filter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3"));
        }
    }

    private List<String> updateMusicList(Cursor cur) {

        while (cur.moveToNext()) {

            if (String.valueOf(cur.getString(5)) != null) {
                try {
                    Long time = Long.valueOf(cur.getString(5));
                    long seconds = time / 1000;
                    long minutes = seconds / 60;
                    seconds = seconds % 60;

                    if (seconds < 10) {
                        String csongs_duration = String.valueOf(minutes) + ":0" + String.valueOf(seconds);

                        songs.add(cur.getString(0) + "||"
                                + cur.getString(1) + "||"
                                + cur.getString(2) + "||"
                                + cur.getString(3) + "||"
                                + cur.getString(4) + "||"
                                + csongs_duration);

                    } else {
                        String ccsongs_duration = String.valueOf(minutes) + ":" + String.valueOf(seconds);

                        songs.add(cur.getString(0) + "||"
                                + cur.getString(1) + "||"
                                + cur.getString(2) + "||"
                                + cur.getString(3) + "||"
                                + cur.getString(4) + "||"
                                + ccsongs_duration);

                    }
                } catch (NumberFormatException e) {
                    songs.add(cur.getString(0) + "||"
                            + cur.getString(1) + "||"
                            + cur.getString(2) + "||"
                            + cur.getString(3) + "||"
                            + cur.getString(4) + "||"
                            + cur.getString(5));
                }
            } else {
                String csongs_duration = "0";
                songs.add(cur.getString(0) + "||"
                        + cur.getString(1) + "||"
                        + cur.getString(2) + "||"
                        + cur.getString(3) + "||"
                        + cur.getString(4) + "||"
                        + csongs_duration);
                ;
            }

        }

        return songs;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1, container, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);


        ContentResolver cr = getActivity().getContentResolver();

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
            String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
            String[] projection = {
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DURATION
            };

            Cursor cur = cr.query(uri, projection, selection, null, sortOrder);

            songs = updateMusicList(cur);
        } else {
            // No explanation needed, we can request the permission.


            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }


        MyAdapter adapter = new MyAdapter(songs);
        rv.setAdapter(adapter);

        /*
        /*MP3 player Buttons
         */

        final ImageButton stopPlay = (ImageButton) v.findViewById(R.id.BtnStop);
        stopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.MP.isPlaying()) {
                    MainActivity.MP.stop();
                }
            }
        });


        final ImageButton startPlay = (ImageButton) v.findViewById(R.id.BtnPlay);
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


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);


        return v;
    }


}


