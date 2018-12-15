package com.bawei.www.dome1215.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.www.dome1215.R;
import com.bawei.www.dome1215.bean.NewsBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NewsBean.DataBean> mlist;

    public RviewAdapter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = View.inflate(context, R.layout.list_item, null);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    public void setData(List<NewsBean.DataBean> newsBeanData) {
        this.mlist = newsBeanData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView item_img;
        private final TextView item_tt;
        private final ConstraintLayout cLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            item_tt = itemView.findViewById(R.id.item_tt);
            cLayout = itemView.findViewById(R.id.con);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final ViewHolder vh = (ViewHolder) viewHolder;
        vh.item_tt.setText(mlist.get(i).getTitle());

        //截取字符串
        String images = mlist.get(i).getImages();

        Pattern pen = Pattern.compile("\\|");

        String[] img = pen.split(images);

        Glide.with(context).load(img[0]).into(vh.item_img);

        //条目单击
        vh.cLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCallBack != null) {
                    clickCallBack.setItemOnClick(vh.item_tt.getText().toString(),i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    private onClickCallBack clickCallBack;

    public interface onClickCallBack {
        void setItemOnClick(String tt,int i);
    }

    public void setItemOnClicklisenter(onClickCallBack clicklisenter) {
        this.clickCallBack = clicklisenter;
    }
}
