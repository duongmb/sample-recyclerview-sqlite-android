package com.geek.recycleview.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.geek.recycleview.model.Person;
import com.geek.recycleview.Constant.Code;
import com.geek.recycleview.R;

/**
 * Created by PK on 3/30/2017.
 */

public class AdapterForViewActivity extends RecyclerView.Adapter<AdapterForViewActivity
        .ViewHolder> {

    private Activity activity;
    private Person personInfo;

    public AdapterForViewActivity(Activity activity) {
        this.activity = activity;
        personInfo = (Person) activity.getIntent().getSerializableExtra(Code.PERSON_DATA);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View view = from.inflate(R.layout.recycler_view_activity, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_id.setText(personInfo.getId() + "");
        holder.tv_name.setText(personInfo.getName());
        holder.tv_phone.setText(personInfo.getPhone());
        holder.tv_mail.setText(personInfo.getMail());
        holder.tv_age.setText(personInfo.getAge() + "");
        holder.tv_address.setText(personInfo.getAddress());
        holder.tv_gender.setText(personInfo.getGender() == null ? "Male" : personInfo.getGender());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_mail;
        private TextView tv_phone;
        private TextView tv_address;
        private TextView tv_gender;
        private TextView tv_age;
        private Button button;

        public ViewHolder(View view) {
            super(view);
            tv_id = (TextView) view.findViewById(R.id.tv_id_show);
            tv_name = (TextView) view.findViewById(R.id.tv_name_show);
            tv_mail = (TextView) view.findViewById(R.id.tv_email_show);
            tv_phone = (TextView) view.findViewById(R.id.tv_phone_show);
            tv_address = (TextView) view.findViewById(R.id.tv_address_show);
            tv_gender = (TextView) view.findViewById(R.id.tv_gender_show);
            tv_age = (TextView) view.findViewById(R.id.tv_age_show);
            button = (Button) view.findViewById(R.id.btn_cancel_show);
        }
    }
}
