package org.listViewEx;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyListView extends Activity {
    private ListView listView;
    
	private class ImageTextAdapter extends BaseAdapter {
    	private LayoutInflater layoutInflater;
		private Integer[] images = {
			R.drawable.p01, R.drawable.p02, R.drawable.p03, R.drawable.p04, 
			R.drawable.p05, R.drawable.p06, R.drawable.p07, R.drawable.p08, 
			R.drawable.p09, R.drawable.p10, R.drawable.p11, R.drawable.p12
		};
		
	    private class ViewHolder {
	    	ImageView imageView;
	    	TextView textView;    	
	    }
		
		public ImageTextAdapter(Context context) {
			layoutInflater = 
				(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.listview_item, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.imageView.setImageResource(images[position]);
            viewHolder.textView.setText("image " + (position + 1));
            return convertView;
        }
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();             
    }
    
    private void findViews() {
		listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(new ImageTextAdapter(this));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				TextView textView = (TextView)((LinearLayout)view).getChildAt(1);
				Toast.makeText(getApplicationContext(), textView.getText(),
				          Toast.LENGTH_SHORT).show();				
			}
		});  
	}
}