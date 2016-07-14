package com.cacaorick.demo;

import android.os.Bundle;

public class Demo4Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);

		setTitle("Demo 4");
		setNavigationCheckItem(3);
	}
}
