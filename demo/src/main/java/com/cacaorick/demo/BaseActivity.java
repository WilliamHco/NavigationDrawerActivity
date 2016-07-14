package com.cacaorick.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.cacaorick.navigation_drawer_activity.NavigationDrawerActivity;

public class BaseActivity extends NavigationDrawerActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 因為必須等 ContentView 設定好才能對 NavigationView 做設定
		// 因此在這邊實做 OnContentViewSetListener，待 setContentView() 完成後再設定
		mListener = new OnContentViewSetListener() {
			@Override
			public void onContentViewSetted() {
				// 設定 Navigation Drawer Menu 的內容
				setDrawerMenu(R.menu.demo_navigation_drawer_menu);

				// 可以用自己做的 Navigation Header Layout
				//setHeaderLayout(R.layout.layout_navigation_default_header);

				// 設定 Navigation Drawer Menu Item 被按下後要做什麼事
				setOnNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
					@Override
					public boolean onNavigationItemSelected(MenuItem item) {
						// Handle navigation view item clicks here.
						int id = item.getItemId();

						// 根據選擇的項目前往 Activity
						Intent intent;
						switch (id) {
							case R.id.nav_demo1:
								intent = new Intent(BaseActivity.this, Demo1Activity.class);
								startActivity(intent);
								break;
							case R.id.nav_demo2:
								intent = new Intent(BaseActivity.this, Demo2Activity.class);
								startActivity(intent);
								break;
							case R.id.nav_demo3:
								intent = new Intent(BaseActivity.this, Demo3Activity.class);
								startActivity(intent);
								break;
							case R.id.nav_demo4:
								intent = new Intent(BaseActivity.this, Demo4Activity.class);
								startActivity(intent);
								break;
						}

						// 關閉 Drawer
						mDrawerLayout.closeDrawer(GravityCompat.START);
						return true;
					}
				});
			}
		};
	}
}
