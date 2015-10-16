package com.phl.emoproject.pojo;

import android.view.View;
import android.widget.TextView;

public class ActionListHolder {
    View view;
    TextView approval;
    TextView reject;
    TextView consult;
    TextView assign;
    TextView submitConsultButton;

    public TextView getSubmitConsultButton() {
        return submitConsultButton;
    }

    public void setSubmitConsultButton(TextView submitConsultButton) {
        this.submitConsultButton = submitConsultButton;
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
