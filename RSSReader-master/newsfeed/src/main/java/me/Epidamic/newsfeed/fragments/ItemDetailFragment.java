
package me.Epidamic.newsfeed.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import me.Epidamic.newsfeed.activities.ItemDetailActivity;
import me.Epidamic.newsfeed.activities.ItemListActivity;
import me.Epidamic.rssreader.R;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item title that this fragment
     * receives
     */
    public static final String ARG_ITEM_TITLE = "item_title";
    /**
     * The fragment argument representing the item content that this fragment
     * receives
     */
    public static final String ARG_ITEM_CONTENT = "item_content";
    /**
     * The fragment argument representing the item content that this fragment
     * receives
     */
    public static final String ARG_ITEM_LINK = "item_link";
    /**
     * The item title
     */
    private String mItemTitle;
    /**
     * The item content
     */
    private String mItemContent;
    /**
     * The item link
     */
    private String mItemLink;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_TITLE)) {
            mItemTitle = getArguments().getString(ARG_ITEM_TITLE);
        }
        if (getArguments().containsKey(ARG_ITEM_CONTENT)) {
            mItemContent = getArguments().getString(ARG_ITEM_CONTENT);
        }
        if (getArguments().containsKey(ARG_ITEM_LINK)) {
            mItemLink = getArguments().getString(ARG_ITEM_LINK);
            setHasOptionsMenu(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(mItemTitle);

        // Show the dummy content as text in a TextView.
        if (mItemContent != null) {
            WebView webView = (WebView) rootView.findViewById(R.id.item_detail);
            webView.loadDataWithBaseURL("", mItemContent, "text/html", "UTF-8", "");
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.details_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_showInBrowser:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mItemLink));
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
