package android.ig2i.ig2cast;

import android.app.Activity;
import android.content.Intent;
import android.ig2i.ig2cast.api.musiXmatchSDKActivity;
import android.ig2i.ig2cast.api.musiXmatchSDKActivitySearch;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kevin on 03/06/2016.
 */
public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_search);

        Button search = (Button) findViewById(R.id.searchLyrics);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText songSearch = (EditText) findViewById(R.id.song_search);
                EditText artistSearch = (EditText) findViewById(R.id.artist_search);
                //Toast.makeText(getBaseContext(),songSearch.getText().toString(),Toast.LENGTH_LONG).show();
                //Toast.makeText(getBaseContext(),songSearch.getText().toString(),Toast.LENGTH_LONG).show();
                if(songSearch.getText().length()!=0 && artistSearch.getText().length()!=0){
                    Intent MusicXMatchAPI = new Intent(v.getContext(), musiXmatchSDKActivitySearch.class);
                    MusicXMatchAPI.putExtra("mTitleTextView", songSearch.getText().toString());
                    MusicXMatchAPI.putExtra("mArtistTextView", artistSearch.getText().toString());
                    v.getContext().startActivity(MusicXMatchAPI);
                } else{
                    Toast.makeText(getBaseContext(),"Veuillez renseigner les champs",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
