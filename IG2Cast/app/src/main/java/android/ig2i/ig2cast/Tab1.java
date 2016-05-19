package android.ig2i.ig2cast;


import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Tab1 extends Fragment {

    ListView mListView;

   // private static final String INTERNAL_STORAGE = new String("/cust/Samples/Music/");
    //private static final String SD_PATH = new String ("");
    private List<String> songs = new ArrayList<String>();

    /*String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

    String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
    };

    cursor = this.managedQuery(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        null,
        null);*/


    private MediaPlayer mp = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    class Mp3Filter implements FilenameFilter{
        public boolean accept(File dir, String name){
            return (name.endsWith(".mp3"));
        }
    }

    private List<String> updateMusicList(Cursor cur) {
        //File home = new File(INTERNAL_STORAGE);

        /*if(home.listFiles(new Mp3Filter()).length >0) {
            for (File file : home.listFiles(new Mp3Filter())){
                songs.add((String) file.getName());
            }

            //songs.add("coucou test");
        }*/

        while(cur.moveToNext()) {
            songs.add(cur.getString(0) + "||"
                    + cur.getString(1) + "||"
                    + cur.getString(2) + "||"
                    + cur.getString(3) + "||"
                    + cur.getString(4) + "||"
                    + cur.getString(5));
        }

        //File home = new File(SD_PATH);
        return songs;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1,container,false);

        Button stopPlay = (Button) v.findViewById(R.id.BtnStop);
        stopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();

            }
        });

        Button startPlay = (Button) v.findViewById(R.id.BtnPlay);
        startPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });



        ContentResolver cr = getActivity().getContentResolver();

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
        //MyAdapter adapter = new MyAdapter(new String[]{"test one", "test two", "test three", "test four", "test five" , "test six" , "test seven"});
        //songs.add(null);



        MyAdapter adapter = new MyAdapter(songs);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        


        //mListView = (ListView) v.findViewById(R.id.listView);
       // mListView.setAdapter(new OrderAdapter(MixView.mixContext.getBaseContext(), R.layout.searchinnerlistlayout, MixView.dataView.dataHandler.markerList));
        //setContentView(R.layout.activity_main);

        return v;
    }


}


