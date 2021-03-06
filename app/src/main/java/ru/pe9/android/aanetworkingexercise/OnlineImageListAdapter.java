package ru.pe9.android.aanetworkingexercise;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class OnlineImageListAdapter extends RecyclerView.Adapter<OnlineImageListAdapter.OnlineImageHolder> {

    class OnlineImageHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        private OnClickListener OnListItemClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OnlineImageListItem imageListItem = (OnlineImageListItem)v.getTag();

                SingleImageActivity.StartMe(v.getContext(), imageListItem);
            }
        };

        public OnlineImageHolder(View itemView) {
            super(itemView);

            this.photo = itemView.findViewById(R.id.photo);
        }

        public void bind(OnlineImageListItem item) {
            Picasso.get().load(item.getStillImgSrc())
                    .placeholder(R.drawable.import_placeholder)
                    .into(photo);

            itemView.setTag(item);
            itemView.setOnClickListener(OnListItemClickListener);
        }
    } // OnlineImageHolder ///



    private List<OnlineImageListItem> dataList = null;

    public void SetData(List<OnlineImageListItem> list) {
        this.dataList = list;
    }


    @NonNull
    @Override
    public OnlineImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item_layout, parent, false);

        return new OnlineImageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineImageHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

} // end of class ///
