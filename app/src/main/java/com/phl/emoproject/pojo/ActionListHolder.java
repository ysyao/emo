package com.phl.emoproject.pojo;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActionListHolder {
    View view;
    TextView approval;
    TextView reject;
    TextView consult;
    TextView assign;
    TextView submitConsultButton;
    EditText submitConsultText;
    View submitConsultContainer;

    public TextView getSubmitConsultButton() {
        return submitConsultButton;
    }

    public void setSubmitConsultButton(TextView submitConsultButton) {
        this.submitConsultButton = submitConsultButton;
    }

    public View getSubmitConsultContainer() {
        return submitConsultContainer;
    }

    public void setSubmitConsultContainer(View submitConsultContainer) {
        this.submitConsultContainer = submitConsultContainer;
    }

    public EditText getSubmitConsultText() {
        return submitConsultText;
    }

    public void setSubmitConsultText(EditText submitConsultText) {
        this.submitConsultText = submitConsultText;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public TextView getApproval() {
        return approval;
    }

    public void setApproval(TextView approval) {
        this.approval = approval;
    }

    public TextView getReject() {
        return reject;
    }

    public void setReject(TextView reject) {
        this.reject = reject;
    }

    public TextView getConsult() {
        return consult;
    }

    public void setConsult(TextView consult) {
        this.consult = consult;
    }

    public TextView getAssign() {
        return assign;
    }

    public void setAssign(TextView assign) {
        this.assign = assign;
    }
}
