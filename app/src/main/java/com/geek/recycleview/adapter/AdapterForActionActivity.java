package com.geek.recycleview.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.geek.recycleview.Constant.Code;
import com.geek.recycleview.model.Person;
import com.geek.recycleview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PK on 3/30/2017.
 */

public class AdapterForActionActivity extends RecyclerView.Adapter<AdapterForActionActivity
        .ViewHolder> {

    private List<String> listAge = new ArrayList<>();
    private Activity activity;
    private String functionEdit;
    private String functionUpdate;
    private Person oldPerson;
    private int idOldPerson;

    public AdapterForActionActivity(Activity activity) {
        this.activity = activity;
        functionEdit = activity.getIntent().getStringExtra(Code.FUNCTION_ADD_NEW);
        functionUpdate = activity.getIntent().getStringExtra(Code.FUNCTION_UPDATE);
        for (int i = 18; i <= 100; i++) {
            listAge.add(i + "");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_action_activity, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (functionUpdate != null && functionEdit == null) {
            prepareIfUpdateMode(holder);
        }

        holder.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Person person = getNewPerson(holder);
                Intent resultIntent = new Intent();
                if (functionEdit != null && functionUpdate == null) {
                    resultIntent.putExtra(Code.FUNCTION_ADD_NEW, Code.FUNCTION_ADD_NEW);
                } else if (functionUpdate != null && functionEdit == null) {
                    resultIntent.putExtra(Code.FUNCTION_UPDATE, Code.FUNCTION_UPDATE);
                }
                resultIntent.putExtra(Code.PERSON_DATA, person);
                activity.setResult(Code.REQUEST_CODE, resultIntent);
                activity.finish();
            }
        });

        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm(holder)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setMessage("Are you sure to cancel?");
                    builder.setTitle("Warning!!!");
                    builder.setCancelable(true)
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    activity.finish();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    activity.finish();
                }

            }
        });

    }

    private boolean checkForm(ViewHolder holder) {
        String name = holder.edt_name.getText().toString();
        String address = holder.edt_address.getText().toString();
        String phone = holder.edt_phone.getText().toString();
        String mail = holder.edt_mail.getText().toString();
        if (name.equals("") && address.equals("") && phone.equals("") && mail.equals("")) {
            return false;
        }
        return true;
    }

    private void prepareIfUpdateMode(ViewHolder holder) {
        oldPerson = (Person) activity.getIntent().getSerializableExtra(Code.PERSON_OLD_DATA);
        if (oldPerson != null) {
            idOldPerson = oldPerson.getId();
            holder.edt_name.setText(oldPerson.getName());
            holder.edt_address.setText(oldPerson.getAddress());
            holder.edt_phone.setText(oldPerson.getPhone());
            holder.edt_mail.setText(oldPerson.getMail());
            if (oldPerson.getGender().equals("FeMale")) {
                holder.radio_female.setChecked(true);
                holder.radio_male.setChecked(false);
            } else if (oldPerson.getGender().equals("Male")) {
                holder.radio_male.setChecked(true);
                holder.radio_female.setChecked(false);
            }
            int age = oldPerson.getAge();
            int position_spinner = 82 - (100 - age);
            holder.spinner_age.setSelection(position_spinner);
        }
    }

    @NonNull
    private Person getNewPerson(ViewHolder holder) {
        String name = holder.edt_name.getText().toString();
        String address = holder.edt_address.getText().toString();
        String phone = holder.edt_phone.getText().toString();
        String mail = holder.edt_mail.getText().toString();
        String gender = holder.radio_gender.getText().toString();
        int age = Integer.parseInt(holder.spinner_age.getSelectedItem().toString());

        final Person person = new Person();
        person.setId(idOldPerson);
        person.setName(name);
        person.setAddress(address);
        person.setPhone(phone);
        person.setMail(mail);
        person.setGender(gender);
        person.setAge(age);
        return person;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private EditText edt_name;
        private EditText edt_address;
        private EditText edt_mail;
        private EditText edt_phone;
        private RadioGroup radio_group;
        private RadioButton radio_male;
        private RadioButton radio_female;
        private RadioButton radio_gender;
        private Spinner spinner_age;
        private Button btn_save;
        private Button btn_cancel;

        public ViewHolder(View view) {
            super(view);
            edt_name = (EditText) view.findViewById(R.id.edt_name);
            edt_address = (EditText) view.findViewById(R.id.edt_address);
            edt_mail = (EditText) view.findViewById(R.id.edt_mail);
            edt_phone = (EditText) view.findViewById(R.id.edt_phone);

            radio_female = (RadioButton) view.findViewById(R.id.radio_btn_female);
            radio_male = (RadioButton) view.findViewById(R.id.radio_btn_male);
            radio_group = (RadioGroup) view.findViewById(R.id.radio_btn_group);
            int checkedRadioButtonId = radio_group.getCheckedRadioButtonId();
            if (checkedRadioButtonId == radio_female.getId()) {
                radio_gender = radio_female;
            } else if (checkedRadioButtonId == radio_male.getId()) {
                radio_gender = radio_male;
            }
            spinner_age = (Spinner) view.findViewById(R.id.spinner_age);
            spinner_age.setAdapter(new ArrayAdapter<>(activity, android.R.layout
                    .simple_spinner_item, listAge));
            btn_save = (Button) view.findViewById(R.id.btn_add_new);
            btn_cancel = (Button) view.findViewById(R.id.btn_cancel_save_new);
        }
    }

}
