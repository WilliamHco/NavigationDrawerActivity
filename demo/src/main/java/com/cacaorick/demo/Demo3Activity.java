package com.cacaorick.demo;

import android.os.Bundle;

public class Demo3Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);

		setTitle("Demo 3");
		setNavigationCheckItem(2);
	}
}
