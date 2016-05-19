package android.ig2i.ig2cast;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


public class Tab1 extends Fragment {

    ListView mListView;

    private static final String INTERNAL_STORAGE = new String("/cust/Samples/Music/");
    //private static final String SD_PATH = new String ("");
    private List<String> songs = new ArrayList<String>();
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

    private List<String> updateMusicList() {
        File home = new File(INTERNAL_STORAGE);

        if(home.listFiles(new Mp3Filter()).length >0) {
            for (File file : home.listFiles(new Mp3Filter())){
                songs.add((String) file.getName());
            }
            //songs.add("coucou test");
        }

        //File home = new File(SD_PATH);
        return songs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1,container,false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        songs = updateMusicList();
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

