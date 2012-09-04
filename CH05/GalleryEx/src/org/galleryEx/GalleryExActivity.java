package org.galleryEx;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryExActivity extends Activity {
	ImageView imageView;
	Gallery gallery;
	private Integer[] images = {
			R.drawable.p01, R.drawable.p02, R.drawable.p03, R.drawable.p04, 
			R.drawable.p05, R.drawable.p06, R.drawable.p07
	};

	public class ImageAdapter extends BaseAdapter {
        int galleryItemBackground;
        private Context context;        

        public ImageAdapter(Context c) {
            context = c;
            TypedArray attr = context.obtainStyledAttributes(R.styleable.GalleryEx);
            galleryItemBackground = attr.getResourceId(
                    R.styleable.GalleryEx_android_galleryItemBackground, 0);
            attr.recycle();
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
        	 ImageView imageView;
             if (convertView == null) {  
                 imageView = new ImageView(context);
                 imageView.setLayoutParams(new Gallery.LayoutParams(150, 150));
                 imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                 imageView.setBackgroundResource(galleryItemBackground);
             } else {
                 imageView = (ImageView)convertView;
             }
             imageView.setImageResource(images[position]);
             return imageView;
        }
    }
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViews();        
    }

    private void findViews() {
    	imageView = (ImageView)findViewById(R.id.imageView);
    	gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));

        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(GalleryExActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                imageView.setImageResource(images[position]);
            }
        });
		
	}
}