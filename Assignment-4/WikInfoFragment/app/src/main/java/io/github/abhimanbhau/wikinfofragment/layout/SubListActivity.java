package io.github.abhimanbhau.wikinfofragment.layout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.webkit.WebView;
import android.widget.ListView;

import io.github.abhimanbhau.wikinfofragment.R;

public class SubListActivity extends FragmentActivity implements ListFragment.OnSelectedItemListener {

    ListView listView;
    String[] arrString;
    String keyword;
    boolean detailPage = false;

    public static String currentItem = null;

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view);

        listView = findViewById(R.id.subListView);
        keyword = getIntent().getExtras().getString("keyword");
        arrString = getIntent().getExtras().getStringArray(keyword);



        Bundle bundle = new Bundle();
        bundle.putString("keyword", keyword);
        bundle.putStringArray(keyword, arrString);
        bundle.putInt("group", getIntent().getExtras().getInt("group"));


        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);

        if (savedInstanceState == null) {
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.listDetail, listFragment, "List_Fragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        if (findViewById(R.id.webDetail) != null) {
            detailPage = true;
            getFragmentManager().popBackStack();

            WebFragment webFragment = (WebFragment) getFragmentManager().findFragmentById(R.id.webDetail);
            if (webFragment == null) {
                webFragment = new WebFragment();
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.webDetail, webFragment, "Web_Fragment");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onItemSelected(String url) {
        if (detailPage) {

            WebFragment webFragment = (WebFragment) getFragmentManager().findFragmentById(R.id.webDetail);
            WebView webView = webFragment.getView().findViewById(R.id.webView);
            webFragment.updateURl(url, webView);
        } else {
            WebFragment webFragment = new WebFragment();
            webFragment.setUrlContent(url);
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.listDetail, webFragment, "Web_Fragment2");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}