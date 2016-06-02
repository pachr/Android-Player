package android.ig2i.ig2cast.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.ig2i.ig2cast.MainActivity;
import android.ig2i.ig2cast.R;
import android.ig2i.ig2cast.activity.BuildingMusicPlayerActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 01/06/2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private View _view;

    public ExpandableListAdapter(View v, Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._view = v;



    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        final TextView txtListChild = (TextView) convertView
                .findViewById(R.id.tv_title);

        final TextView txtListChildArtist = (TextView) convertView
                .findViewById(R.id.tv_artist);
        final TextView txtListChildDuration = (TextView) convertView.findViewById(R.id.tv_duration);


        txtListChild.setText(childText);

        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arrayText;
                arrayText = childText.toString();
                //Toast.makeText(v.getContext(), arrayText, Toast.LENGTH_LONG).show();
                String[] separated = arrayText.split("\\|\\|");

                try {
                    MainActivity.MP.reset();
                    MainActivity.MP.setDataSource(separated[3]);
                    MainActivity.MP.prepare();
                    MainActivity.MP.start();

                    Intent MusicPlayerIntent = new Intent(v.getContext(), BuildingMusicPlayerActivity.class);
                    MusicPlayerIntent.putExtra("mTitleTextView", txtListChild.getText().toString());
                    MusicPlayerIntent.putExtra("mArtistTextView", txtListChildArtist.getText().toString());
                    MusicPlayerIntent.putExtra("mDurationTextView", txtListChildDuration.getText().toString());
                    v.getContext().startActivity(MusicPlayerIntent);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        txtListChildArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arrayText;
                arrayText = childText.toString();
                //Toast.makeText(v.getContext(), arrayText, Toast.LENGTH_LONG).show();
                String[] separated = arrayText.split("\\|\\|");

                try {
                    MainActivity.MP.reset();
                    MainActivity.MP.setDataSource(separated[3]);
                    MainActivity.MP.prepare();
                    MainActivity.MP.start();

                    Intent MusicPlayerIntent = new Intent(v.getContext(), BuildingMusicPlayerActivity.class);
                    MusicPlayerIntent.putExtra("mTitleTextView", txtListChild.getText().toString());
                    MusicPlayerIntent.putExtra("mArtistTextView", txtListChildArtist.getText().toString());
                    MusicPlayerIntent.putExtra("mDurationTextView", txtListChildDuration.getText().toString());
                    v.getContext().startActivity(MusicPlayerIntent);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        txtListChildDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arrayText;
                arrayText = childText.toString();
                //Toast.makeText(v.getContext(), arrayText, Toast.LENGTH_LONG).show();
                String[] separated = arrayText.split("\\|\\|");

                try {
                    MainActivity.MP.reset();
                    MainActivity.MP.setDataSource(separated[3]);
                    MainActivity.MP.prepare();
                    MainActivity.MP.start();

                    Intent MusicPlayerIntent = new Intent(v.getContext(), BuildingMusicPlayerActivity.class);
                    MusicPlayerIntent.putExtra("mTitleTextView", txtListChild.getText().toString());
                    MusicPlayerIntent.putExtra("mArtistTextView", txtListChildArtist.getText().toString());
                    MusicPlayerIntent.putExtra("mDurationTextView", txtListChildDuration.getText().toString());
                    v.getContext().startActivity(MusicPlayerIntent);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        String arrayText;
        arrayText = txtListChild.getText().toString();
        String[] separated =  arrayText.split("\\|\\|");

        txtListChild.setText(separated[2].toString());


        txtListChildArtist.setText(separated[1].toString());


        txtListChildDuration.setText(separated[5].toString());



        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
