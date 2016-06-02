package android.ig2i.ig2cast;

import android.content.Intent;
import android.ig2i.ig2cast.activity.BuildingMusicPlayerActivity;
import android.ig2i.ig2cast.api.musiXmatchSDKActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTitleTextView;
        public TextView mArtistTextView;
        public TextView mDurationTextView;
        public String mDataSource;

        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTitleTextView = (TextView) v.findViewById(R.id.tv_title);
            mArtistTextView = (TextView) v.findViewById(R.id.tv_artist);
            mDurationTextView= (TextView) v.findViewById(R.id.tv_duration);

            v.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v){

                    String arrayText;
                    arrayText = mTitleTextView.getText().toString();
                    Toast.makeText(v.getContext(), arrayText, Toast.LENGTH_SHORT).show();

                    try {
                        MainActivity.MP.reset();
                        MainActivity.MP.setDataSource(mDataSource);
                        MainActivity.MP.prepare();
                        MainActivity.MP.start();


                        /*Intent MusicPlayerIntent = new Intent(v.getContext(), BuildingMusicPlayerActivity.class);
                        MusicPlayerIntent.putExtra("mTitleTextView",mTitleTextView.getText().toString());
                        MusicPlayerIntent.putExtra("mArtistTextView",mArtistTextView.getText().toString());
                        MusicPlayerIntent.putExtra("mDurationTextView", mDurationTextView.getText().toString());
                        v.getContext().startActivity(MusicPlayerIntent);*/


                        Intent MusicXMatchAPI = new Intent(v.getContext(), musiXmatchSDKActivity.class);
                        MusicXMatchAPI.putExtra("mTitleTextView",mTitleTextView.getText().toString());
                        MusicXMatchAPI.putExtra("mArtistTextView",mArtistTextView.getText().toString());
                        MusicXMatchAPI.putExtra("mDurationTextView", mDurationTextView.getText().toString());
                        v.getContext().startActivity(MusicXMatchAPI);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String[] separated = mDataset.get(position).toString().split("\\|\\|");
        holder.mArtistTextView.setText(separated[1]);
        holder.mTitleTextView.setText(separated[2]);
        holder.mDurationTextView.setText(separated[5]);
        holder.mDataSource = separated[3];
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}