package com.example.bradj.eventit;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.bradj.eventit.Model.Entity.Event;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by ajibd on 1/7/2018.
 */

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Event event;
    private final View myContentsView;

    public MyInfoWindowAdapter(Activity activity) {
        myContentsView = activity.getLayoutInflater().inflate(R.layout.custom_info_contents, null);
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        Event event=(Event) marker.getTag();
        TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.info_window_title));
        tvTitle.setText(event.getName());
        TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.info_window_snippet));
        tvSnippet.setText(event.getDescription());
        TextView tvDate = ((TextView)myContentsView.findViewById(R.id.info_window_event_date));
        tvDate.setText(event.getDate());
        TextView tvOrg = ((TextView)myContentsView.findViewById(R.id.info_window_organization_name));
        tvOrg.setText(event.getOrganization().getName());
        TextView tvAddress = ((TextView)myContentsView.findViewById(R.id.info_window_address));
        tvAddress.setText(event.getAddress().getStreet());

        return myContentsView;
    }
}
