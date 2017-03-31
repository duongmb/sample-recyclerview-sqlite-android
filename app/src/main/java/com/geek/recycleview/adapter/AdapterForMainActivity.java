package com.geek.recycleview.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geek.recycleview.ActionActivity;
import com.geek.recycleview.ViewActivity;
import com.geek.recycleview.database.DatabaseOpenHelper;
import com.geek.recycleview.model.Person;
import com.geek.recycleview.Constant.Code;
import com.geek.recycleview.R;

import java.util.List;

/**
 * Created by PK on 3/30/2017.
 */

public class AdapterForMainActivity extends RecyclerView.Adapter<AdapterForMainActivity
        .ViewHolder> {
    private Activity mainActivity;
    private DatabaseOpenHelper utils;

    public AdapterForMainActivity(Activity mainActivity, DatabaseOpenHelper utils) {
        this.mainActivity = mainActivity;
        this.utils = utils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyler_main_activity, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        List<Person> listPerson = utils.getListPerson();
        final Person person = listPerson.get(position);

        holder.tv_id_name.setText("ID : " + person.getId() + " - Name : " + person.getName());
        holder.tv_tel_mail.setText("Tel: " + person.getPhone() + "\nEmail: " + person.getMail());

        holder.imgView_DelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(person);
                notifyItemRemoved(position);
            }
        });


        holder.imgView_EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUpdate = new Intent(mainActivity, ActionActivity.class);
                intentUpdate.putExtra(Code.FUNCTION_UPDATE, Code.FUNCTION_UPDATE);
                intentUpdate.putExtra(Code.PERSON_OLD_DATA, person);
                intentUpdate.putExtra(Code.PERSON_POSITION, position);
                mainActivity.startActivityForResult(intentUpdate, Code.REQUEST_CODE);
            }
        });

        holder.layout_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, ViewActivity.class);
                intent.putExtra(Code.PERSON_DATA, person);
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return utils.getPersonCounter();
    }

    public void add(Person person) {
        utils.addNewPerson(person);
        notifyDataSetChanged();
    }

    public void update(Person newPerson) {
        utils.update(newPerson.getId(), newPerson);
        notifyDataSetChanged();
    }

    public void delete(Person remove) {
        utils.delete(remove);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgView_EditButton;
        public ImageView imgView_DelButton;
        public TextView tv_id_name;
        public TextView tv_tel_mail;
        private RelativeLayout layout_info;

        public ViewHolder(View itemView) {
            super(itemView);
            imgView_DelButton = (ImageView) itemView.findViewById(R.id.image_view_delete_button);
            imgView_EditButton = (ImageView) itemView.findViewById(R.id.image_view_edit_button);
            tv_id_name = (TextView) itemView.findViewById(R.id.text_view_id_name);
            tv_tel_mail = (TextView) itemView.findViewById(R.id.text_view_tel_mail);
            layout_info = (RelativeLayout) itemView.findViewById(R.id.layout_info);
        }
    }
}
