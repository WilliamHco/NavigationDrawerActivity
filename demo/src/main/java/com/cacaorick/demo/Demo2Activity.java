package com.cacaorick.demo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

public class Demo2Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);

		setTitle("Demo 2");

		setNavigationCheckItem(1);

		setFab(R.drawable.ic_menu_gallery, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Snackbar.make(mBaseLayout, "FloatingActionButton Click!!", Snackbar.LENGTH_SHORT).show();
			}
		});
	}
}
