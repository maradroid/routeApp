package com.maradroid.routeapp;



import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by mara on 3/11/15.
 */
public class NeewsFeedAdapter extends RecyclerView.Adapter<NeewsFeedAdapter.ViewHolder> {

    private ArrayList<RouteObject> routeObjectArrayList;
    private static ClickListener clickListener;


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView duration;
        private TextView content;
        private ImageView slika;



        View view;


        public ViewHolder(View v) {
            super(v);

            this.title = (TextView) v.findViewById(R.id.ime_rute_tv);
            this.duration = (TextView) v.findViewById(R.id.trajanja_rute_tv);
            this.content = (TextView) v.findViewById(R.id.opis_rute_tv);
            this.slika = (ImageView) v.findViewById(R.id.route_image_iv);
            this.slika.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null)
                clickListener.onClick(v, getPosition());
        }
    }



    public interface ClickListener {
        public void onClick(View v, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public NeewsFeedAdapter(ArrayList<RouteObject> objects) {
        this.routeObjectArrayList = objects;
    }

    @Override
    public NeewsFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;

            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_layout, parent, false);
            return new ViewHolder(v);

    }


    public void addItem(RouteObject object) {
        routeObjectArrayList.add(object);
        notifyItemInserted(routeObjectArrayList.size() - 1);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        ImageLoader.getInstance().displayImage("http://dravabicycleroutes.eu/cms-admin/photo/"+routeObjectArrayList.get(position).getSlikaId(), holder.slika);

        holder.title.setText(routeObjectArrayList.get(position).getTitle());
        holder.duration.setText("Duration: "+routeObjectArrayList.get(position).getDuration());
        holder.content.setText(routeObjectArrayList.get(position).getContent());


        }


    @Override
    public int getItemCount() {
        return routeObjectArrayList.size();
    }

}