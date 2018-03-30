package io.github.abhimanbhau.wikinfofragment.layout;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.github.abhimanbhau.wikinfofragment.R;
import io.github.abhimanbhau.wikinfofragment.utils.Constants;

/**
 * Created by akolte on 2/15/18.
 */

public class ListFragment extends Fragment {

    String[] listItems;
    int id;
    OnSelectedItemListener listener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("group");
            switch (id) {
                case 0:
                    listItems = Constants.get_bigCats();
                    SubListActivity.currentItem = "https://en.wikipedia.org/wiki/"
                            + Constants.get_bigCats()[0];
                    break;
                case 1:
                    listItems = Constants.get_macOSVersions();
                    SubListActivity.currentItem = "https://en.wikipedia.org/wiki/"
                            + Constants.get_macOSVersions()[0];
                    break;
                case 2:
                    listItems = Constants.get_comedyYoutubers();
                    SubListActivity.currentItem = "https://en.wikipedia.org/wiki/"
                            + Constants.get_comedyYoutubers()[0];
                    break;
            }

            ListView listView = (ListView) getView().findViewById(R.id.subListView);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, listItems);

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    SubListActivity.currentItem = "https://en.wikipedia.org/wiki/" +
                            adapterView.getItemAtPosition(i).toString();
                    listener.onItemSelected("https://en.wikipedia.org/wiki/" +
                            adapterView.getItemAtPosition(i).toString());
                }
            });
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.activity_sub_list, container, false);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSelectedItemListener) {
            listener = (OnSelectedItemListener) context;
        } else
            throw new ClassCastException(context.toString() + " must implement MyListFragment.OnSelectedItemListener");

    }

    public interface OnSelectedItemListener {
        void onItemSelected(String url);
    }
}
