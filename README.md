# NavigationDrawerActivity

[ ![Download](https://api.bintray.com/packages/cacaorick/maven/navigation-drawer-activity/images/download.svg) ](https://bintray.com/cacaorick/maven/navigation-drawer-activity/_latestVersion)

方便讓各個 Activity 使用相同的 Navigation Drawer、ToolBar 和 FloatingActionButton

## 使用說明

在 `build.gradle` 加入

```xml
dependencies {
    compile 'com.cacaorick:navigation-drawer-activity:1.0.0'
}
```

Activity 的主題需使用 NoActionBar，否則會與 ToolBar 衝突。

需要建立一個 `BaseActivity`，繼承 `NavigationDrawerActivity`，並在 `onCreate()` 中實作 `OnContentViewSetListener.onContentViewSetted`，Navigation Drawer 共同的設定要在裡面完成。

```java
// 設定 Navigation Drawer Menu Item 被按下後要做什麼事
setOnNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		// 根據選擇的項目前往 Activity
		Intent intent;
		switch (id) {
			case R.id.ooo1:
				
				...
				
				break;
			case R.id.ooo2:
				
				...
				
				break;
		}

		// 關閉 Drawer
		mDrawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}
});
```

需使用 `setDrawerMenu()` 傳入 Navigaiton View 的 Menu xml resource id 來設定要顯示的 Menu。

```java
setDrawerMenu(R.menu.xxx);
```

使用 `setOnNavigationItemSelectedListener()` 傳入 `OnNavigationItemSelectedListener` 來設定 Navigation Item 被選取時的行為。

另外可以使用 `setHeaderLayout()` 傳入 layout resource id 來設定 Navigation Header 的外觀，若不呼叫會直接套用預設的 `layout_navigation_default_header.xml`。

```java
setHeaderLayout(R.layout.xxx);
```

要使用共同的 Navigation Drawer 之 Activity 改為繼承 `BaseActivity`，之後像平常一樣呼叫 `setContentView()` 和 `setTitle()` 即可。

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// 跟平常一樣呼叫 setContentView
	setContentView(R.layout.activity_demo);

	// 設定標題也跟平常一樣
	setTitle("Demo Activity");
}
```

可使用 `setNavigationCheckItem()` 傳入 `index` 來將 Navigation View 中的項目設為選取狀態。

```java
// 設定 Navigation View 被選擇的項目
setNavigationCheckItem(0);
```

若要使用 `FloatingActionButton` 呼叫 `setFab()` 並傳入 `iconResId` 與 `OnClickListener`，另外使用 `Snackbar` 時可以直接用 `mBaseLayout` 做當作 `View` 傳入第一個參數就好。

```java
setFab(R.drawable.ic_add_black_24dp, new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		// 使用 mBaseLayout 作為 Snackbar 的 view
		Snackbar.make(mBaseLayout, "FloatingActionButton Click!!", Snackbar.LENGTH_SHORT).show();
	}
});
```

完整的使用範例詳見 [demo](https://github.com/CacaoRick/NavigationDrawerActivity/tree/master/demo)。
