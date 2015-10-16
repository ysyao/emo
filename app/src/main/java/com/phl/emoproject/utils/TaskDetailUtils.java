package com.phl.emoproject.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.phl.emoproject.R;
import com.phl.emoproject.core.Constans;
import com.phl.emoproject.pojo.ActionListHolder;
import com.phl.emoproject.pojo.TaskListDetail;
import com.phl.emoproject.ui.UserSearchActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TaskDetailUtils {

    public static View getControlViewById(ViewGroup container, String id) {
        for (int i=0; i < container.getChildCount(); i++) {
            View child = container.getChildAt(i);
            TaskListDetail.Control control = (TaskListDetail.Control)child.getTag();
            if (control.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }


    public static void generateViewByControl(Activity context, TaskListDetail.Control control, ViewGroup container) {
        String controlType = control.getControlType();
        View v;
        if ("".equals(controlType) || control.getIsHidden().equals("1")) {
            return;
        } else if (controlType.equals("combobox_h")) {
            v = TaskDetailUtils.generateSpinner(context, control);
            container.addView(v);
        } else if (controlType.equals("checkbox_h")) {
            v = TaskDetailUtils.generateCheckBox(context, control);
            container.addView(v);
        } else if (controlType.equals("notice_h")) {
            v = TaskDetailUtils.generateNotifyForms(context, control);
            container.addView(v);
        }else if (controlType.equals("textarea")) {
            v = TaskDetailUtils.generateTextArea(context, control);
            container.addView(v);
        } else if (controlType.equals("date_h")) {
            v = TaskDetailUtils.generateDatePicker(context, control);
            container.addView(v);
        } else if (controlType.equals("time_h")) {
            v = TaskDetailUtils.generateTimePicker(context, control);
            container.addView(v);
        } else {
            v = TaskDetailUtils.generateTextField(context, control);
            container.addView(v);
        }
    }

    public static View generateTextField(final Activity context, TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_text_filed, null);
        v.setTag(control);
        getTextFieldName(v).setText(control.getLabelText());
        if (control.getControlType().equals("staff_h")) {
            String[] values = control.getValue().split("#");
            EditText et = getTextFieldValue(v);
            et.setText(values[2]);
//            et.setKeyListener(null);
            et.setFocusable(false);
            et.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.list_selector_bg));
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UserSearchActivity.class);
                    context.startActivityForResult(intent, Constans.REQUEST_CODE_SEARCH);
                }
            });
        } else {
            getTextFieldValue(v).setText(control.getValue());
        }
        return v;
    }

    public static View generateTextArea(Context context, TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_text_filed, null);
        v.setTag(control);
        getTextFieldName(v).setText(control.getLabelText());
        getTextFieldValue(v).setMinLines(4);
        getTextFieldValue(v).setText(control.getValue());
        return v;
    }

    public static TextView getTextFieldName(View view) {
        return (TextView) view.findViewById(R.id.text_field_name);
    }

    public static EditText getTextFieldValue(View view) {
        return (EditText) view.findViewById(R.id.text_field_value);
    }

    public static View generateCheckBox(Context context, TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_check_box, null);
        v.setTag(control);
        getCheckBoxName(v).setText(control.getLabelText());
        getCheckBoxValue(v).setChecked("1".equals(control.getValue()));
        return v;
    }

    public static TextView getCheckBoxName(View view) {
        return (TextView) view.findViewById(R.id.checkbox_name);
    }

    public static CheckBox getCheckBoxValue(View view) {
        return (CheckBox) view.findViewById(R.id.checkbox_value);
    }

    public static View generateSpinner(Context context, TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_spinner, null);
        v.setTag(control);
        getSpinnerName(v).setText(control.getLabelText());
        List<String> strings = new ArrayList<>();
        strings.add(control.getValue());
        getSpinnerValue(v).setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, strings));
        return v;
    }

    public static TextView getSpinnerName(View view) {
        return (TextView) view.findViewById(R.id.spinner_name);
    }
    public static Spinner getSpinnerValue(View view) {
        return (Spinner) view.findViewById(R.id.spinner_value);
    }


    public static View generateDatePicker(final Context context, TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_date_pic, null);
        v.setTag(control);
        getDateName(v).setText(control.getLabelText());
        final TextView date = getDateValue(v);
        date.setText(control.getValue());
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(zeroAdd(year)+"-"+zeroAdd(monthOfYear+1)+"-"+zeroAdd(dayOfMonth));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return v;
    }

    public static View generateTimePicker(final Context context, TaskListDetail.Control control) {
        final String date = control.getValue().split(" ")[0];
        View v = LayoutInflater.from(context).inflate(R.layout.view_time_pic, null);
        v.setTag(control);
        getDateName(v).setText(control.getLabelText());
        final TextView time = getDateValue(v);
        time.setText(control.getValue());
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view,int hourOfDay, int minute) {
                        String hour = zeroAdd(hourOfDay);
                        String min = zeroAdd(minute);
                        time.setText(date + " " + hour + ":" + min + ":" + "00");
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),true).show();
            }
        });
        return v;
    }

    public static TextView getDateName(View view) {
        return (TextView) view.findViewById(R.id.date_name);
    }
    public static TextView getDateValue(View view) {
        return (TextView) view.findViewById(R.id.date_value);
    }

    public static View generateNotifyForms(final Context context,TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_radio_group, null);
        v.setTag(control);
        TextView tv = (TextView) v.findViewById(R.id.date_name);
        tv.setText(control.getLabelText());
        View daiban = v.findViewById(R.id.daiban);
        final View daibanIcon = v.findViewById(R.id.daiban_icon);
//        daiban.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (daibanIcon.getVisibility() == View.VISIBLE) {
//                    daibanIcon.setVisibility(View.GONE);
//                } else {
//                    daibanIcon.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        View duanxin = v.findViewById(R.id.duanxin);
        final View duanxinIcon = v.findViewById(R.id.duanxin_icon);
        duanxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (duanxinIcon.getVisibility()==View.VISIBLE) {
                    duanxinIcon.setVisibility(View.GONE);
                } else {
                    duanxinIcon.setVisibility(View.VISIBLE);
                }
            }
        });

        View emal = v.findViewById(R.id.email);
        final View emailIcon = v.findViewById(R.id.email_icon);
        emal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailIcon.getVisibility()==View.VISIBLE) {
                    emailIcon.setVisibility(View.GONE);
                } else {
                    emailIcon.setVisibility(View.VISIBLE);
                }
            }
        });
        return v;
    }

    public static boolean isNotifyDaiBanSelected(View view) {
        View daiban = view.findViewById(R.id.daiban);
        return daiban.getVisibility() == View.VISIBLE;
    }

    public static boolean isNotifyDuanXinSelected(View view) {
        View duanxin = view.findViewById(R.id.duanxin);
        return duanxin.getVisibility() == View.VISIBLE;
    }

    public static boolean isNotifyEmailSelected(View view) {
        View email = view.findViewById(R.id.email);
        return email.getVisibility() == View.VISIBLE;
    }

    public static ActionListHolder generateIdeaByControls(Context context, List<TaskListDetail.Control> controls, ViewGroup container) {
        View actionList = LayoutInflater.from(context).inflate(R.layout.view_idea_action_list, null);
        LinearLayout actionListContainer = (LinearLayout) actionList.findViewById(R.id.actionlist_container);

        ActionListHolder holder = new ActionListHolder();
        holder.setView(actionList);
        for (TaskListDetail.Control control : controls) {
            String controlType = control.getControlType();
            String id = control.getId();
            if ("".equals(controlType) || control.getIsHidden().equals("1")) {
                continue;
            }
            if (id.equals("approveIdeaTextarea")) {
                View v = TaskDetailUtils.generateApprovalText(context, control);
                container.addView(v);
            } else if (id.equals("argeeButton")) {
                TextView tv = generateActionButton(context, control);
                actionListContainer.addView(tv);
                holder.setApproval(tv);
            } else if (id.equals("rejectButton")) {
                TextView tv = generateActionButton(context, control);
                actionListContainer.addView(tv);
                holder.setReject(tv);
            } else if (id.equals("consultButton")) {
                TextView tv = generateActionButton(context, control);
                actionListContainer.addView(tv);
                holder.setConsult(tv);
            } else if (id.equals("assignButton")) {
                TextView tv = generateActionButton(context, control);
                actionListContainer.addView(tv);
                holder.setAssign(tv);
            } else if (id.equals("submitConsultButton")) {
                TextView tv = generateActionButton(context, control);
                actionListContainer.addView(tv);
                holder.setSubmitConsultButton(tv);
            }
        }
        container.addView(actionList);
        return holder;
    }

    public static View generateApprovalText(final Context context, TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_approval_textarea, null);
        v.setTag(control);
        return v;
    }

    public static TextView generateActionButton(final Context context, TaskListDetail.Control control) {
        TextView v = (TextView)LayoutInflater.from(context).inflate(R.layout.view_actionlist_item, null);
        v.setTag(control);
        v.setText(control.getValue());
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(4, 0, 4, 0);
        v.setLayoutParams(params);
        return v;
    }

    public static View generateIdeaActionList(final Context context, TaskListDetail.Control control) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_idea_action_list, null);
//        v.setTag(control);
        return v;
    }




    public static String getApprovalText(View view) {
        EditText input = (EditText) view.findViewById(R.id.approval_text);
        return input.getText().toString();
    }

    private static String zeroAdd(int number) {
        String str = ""+number;
        if (number < 10) {
            str = "0"+number;
        }
        return str;
    }

}
