package me.Epidamic.newsfeed.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import me.Epidamic.rssreader.R;
import nl.matshofman.saxrssreader.RssItem;

/**
 * This class adapts data gathered from the RSS Feed to
 * a format suitable for a List View.
 */
public class RSSItemAdapter extends ArrayAdapter<RssItem> {

    public RSSItemAdapter(Context context, int resource, List<RssItem> objects) {
        super(context, resource, objects);
    }

    /**
     * {@inheritDoc}
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;

        // If the view is null, we need to inflate it from XML layout
        if (listItem == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listItem = inflater.inflate(R.layout.feed_item_layout, null);
        }

        RssItem rssItem = getItem(position);
        if(rssItem != null) {
            // We need to get handles to each element of the row layout
            ImageView itemImage = (ImageView) listItem
                    .findViewById(R.id.itemImage);
            TextView itemTitle = (TextView) listItem
                    .findViewById(R.id.item_title);
            TextView itemTagline = (TextView) listItem
                    .findViewById(R.id.item_tagline);

            itemTitle.setText(rssItem.getTitle());
            itemTagline.setText(stripHtml(Html.fromHtml(rssItem.getDescription()).toString()));

            dowloadItemImageAsync(rssItem, itemImage);
        }

        return listItem;
    }

    private void dowloadItemImageAsync(RssItem item, ImageView imageView) {
        // Parse the description to find the first image, this will be the item image
        Document doc = Jsoup.parse(item.getDescription());
        String firstImageLink = doc.select("img").first().attr("src");
        // Download image asynchronously
        Ion.with(getContext()).load(firstImageLink).withBitmap()
                              .placeholder(R.drawable.photoload)
                              .intoImageView(imageView);
    }

    /**
     * Hack to remove [OBJ] characters in strings to avoid them show in widgets.
     * It is a bit ugly but quite effective!
     *
     * Taken from: http://stackoverflow.com/a/10581020
     */
    public static CharSequence stripHtml(String s) {
        return Html.fromHtml(s).toString().replace('\n', (char) 32)
                .replace((char) 160, (char) 32).replace((char) 65532, (char) 32).trim();
    }
}
