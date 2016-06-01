package android.ig2i.ig2cast;


import android.Manifest;
import android.app.Activity;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.ig2i.ig2cast.adapter.ExpandableListAdapter;
import android.widget.ExpandableListView;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Tab2 extends Fragment {

    private List<String> songs = new ArrayList<String>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


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
        final TextView playerTitle = (TextView) v.findViewById(R.id.player_title);
        playerTitle.setText("--");


       // RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_recycler_view);
        //rv.setHasFixedSize(true);


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


        //MyAdapter adapter = new MyAdapter(songs);
        //rv.setAdapter(adapter);


        // get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        //BEUUUUUUUUUG
        Toast.makeText(getContext(),listAdapter.toString(),Toast.LENGTH_LONG).show();
        // setting list adapter
        //expListView.setAdapter(listAdapter);


        ImageButton stopPlay = (ImageButton) v.findViewById(R.id.BtnStop);
        stopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.MP.isPlaying()) {
                    MainActivity.MP.stop();
                    playerTitle.setText("--");


                }
            }
        });


        ImageButton startPlay = (ImageButton) v.findViewById(R.id.BtnPlay);
        startPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.MP.isPlaying()) {
                    MainActivity.MP.pause();

                } else {
                    MainActivity.MP.start();

                }


            }
        });


       // LinearLayoutManager llm = new LinearLayoutManager(getActivity());
       // rv.setLayoutManager(llm);


        //mListView = (ListView) v.findViewById(R.id.listView);
        // mListView.setAdapter(new OrderAdapter(MixView.mixContext.getBaseContext(), R.layout.searchinnerlistlayout, MixView.dataView.dataHandler.markerList));
        //setContentView(R.layout.activity_main);

        return v;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);


    }

}
