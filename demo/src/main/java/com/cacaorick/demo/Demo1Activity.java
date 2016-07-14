package com.cacaorick.demo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

// 注意：需要使用 NoActionBar 的 Theme
public class Demo1Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 跟平常一樣呼叫 setContentView
		setContentView(R.layout.activity_demo);

		// 設定標題也跟平常一樣
		setTitle("Demo 1");

		// 設定 Navigation View 被選擇的項目
		setNavigationCheckItem(0);

		// 使用 FloatingActionButton
		setFab(R.drawable.ic_menu_camera, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 使用 mBaseLayout 作為 Snackbar 的 view
				Snackbar.make(mBaseLayout, "FloatingActionButton Click!", Snackbar.LENGTH_SHORT).show();
			}
		});
	}
}
