package org.drawableAnimationEx;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DrawableAnimationExActivity extends Activity {
	AnimationDrawable animDrawable;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setBackgroundResource(R.drawable.image_set);
		animDrawable = (AnimationDrawable) imageView.getBackground();
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!animDrawable.isRunning()) {
				animDrawable.start();
			} else {
				animDrawable.stop();
			}	
			return true;
		}
		return super.onTouchEvent(event);
	}
}