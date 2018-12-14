package com.bawei.www.zhoukao3lian.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.www.zhoukao3lian.Apis;
import com.bawei.www.zhoukao3lian.R;
import com.bawei.www.zhoukao3lian.bean.OtherBean;
import com.bawei.www.zhoukao3lian.presonter.Ipresonter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DetailsActivity extends AppCompatActivity implements Iview {

    private Ipresonter ipresonter;
    private TextView t_title;
    private ImageView t_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ipresonter = new Ipresonter(this);

        t_title = findViewById(R.id.t_title);
        t_img = findViewById(R.id.t_img);

        ipresonter.setotherrequset(Apis.OTHER);

    }

    @Override
    public void setonData(Object data) {
        OtherBean otherBean = (OtherBean) data;

        t_title.setText(otherBean.getData().getTitle());

        List<String> imgs =new ArrayList<>();

        String images = otherBean.getData().getImages();

        Pattern pen = Pattern.compile("\\|");

        String[] img = pen.split(images);

        Glide.with(DetailsActivity.this).load(img[0]).into(t_img);

    }
}
