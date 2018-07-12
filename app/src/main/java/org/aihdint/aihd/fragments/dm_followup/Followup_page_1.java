package org.aihdint.aihd.fragments.dm_followup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import org.aihdint.aihd.R;
import org.aihdint.aihd.Forms.JSONFormBuilder;
import org.aihdint.aihd.app.Alerts;
import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Developed by Rodney on 24/04/2018.
 */

public class Followup_page_1 extends Fragment {

    private EditText dm_followup_date, supporter_nameEditText, supporter_phoneEditText, supporter_phone_otherEditText;
    private EditText dmDiagnosisDateEditText, dmClinicDateEditText;
    private EditText htnDiagnosisDateEditText, htnClinicDateEditText;
    private EditText editTextTBDate, editTextTBComment;
    private String dm_diagnosis, hypertension, nhif, diabetes_type, hypertension_type, tb_treatment, tb_screen, tb_status, hiv_status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dm_followup_fragment_1, container, false);

        dm_followup_date = view.findViewById(R.id.dm_followup_date);
        supporter_nameEditText = view.findViewById(R.id.supporter_name);
        supporter_phoneEditText = view.findViewById(R.id.supporter_telephone);
        supporter_phone_otherEditText = view.findViewById(R.id.supporter_telephone_other);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dm_followup_date.setText(dateFormat.format(new Date())); // current date

        dmDiagnosisDateEditText = view.findViewById(R.id.dm_diagnosis_date);
        dmClinicDateEditText = view.findViewById(R.id.dm_clinic_date);

        htnDiagnosisDateEditText = view.findViewById(R.id.htn_diagnosis_date);
        htnClinicDateEditText = view.findViewById(R.id.htn_clinic_date);

        editTextTBDate = view.findViewById(R.id.tb_treatment_start);
        editTextTBComment = view.findViewById(R.id.tb_comment);

        textWatcher(dm_followup_date);
        textWatcher(supporter_nameEditText);
        textWatcher(supporter_phoneEditText);
        textWatcher(supporter_phone_otherEditText);
        textWatcher(dmDiagnosisDateEditText);
        textWatcher(dmClinicDateEditText);
        textWatcher(htnDiagnosisDateEditText);
        textWatcher(htnClinicDateEditText);
        textWatcher(editTextTBDate);
        textWatcher(editTextTBComment);

        RadioButton radioButtonDMNew = view.findViewById(R.id.radio_diabetes_new);
        RadioButton radioButtonDMKnown = view.findViewById(R.id.radio_diabetes_known);
        RadioButton radioButtonDMNA = view.findViewById(R.id.radio_diabetes_na);
        RadioButton radioButtonDMType1 = view.findViewById(R.id.radio_diabetes_type_1);
        RadioButton radioButtonDMType = view.findViewById(R.id.radio_diabetes_type_2);
        RadioButton radioButtonDMGDM = view.findViewById(R.id.radio_diabetes_GDM);
        RadioButton radioButtonDMSecondary = view.findViewById(R.id.radio_diabetes_secondary);
        RadioButton radioButtonHTNNew = view.findViewById(R.id.radio_hypertension_new);
        RadioButton radioButtonHTNKnown = view.findViewById(R.id.radio_hypertension_known);
        RadioButton radioButtonHTNNA = view.findViewById(R.id.radio_hypertension_na);
        RadioButton radioButtonHTNMild = view.findViewById(R.id.radio_hypertension_mild);
        RadioButton radioButtonHTNModerate = view.findViewById(R.id.radio_hypertension_moderate);
        RadioButton radioButtonHTNSevere = view.findViewById(R.id.radio_hypertension_severe);
        RadioButton radioButtonHTNPreeclampsia = view.findViewById(R.id.radio_hypertension_preeclampsia);
        RadioButton radioButtonNHIFYes = view.findViewById(R.id.radio_nhif_yes);
        RadioButton radioButtonNHIFNo = view.findViewById(R.id.radio_nhif_no);
        RadioButton radioButtonHIVNegative = view.findViewById(R.id.radio_hiv_negative);
        RadioButton radioButtonHIVPositive = view.findViewById(R.id.radio_hiv_positive);
        RadioButton radioButtonHIVUknown = view.findViewById(R.id.radio_hiv_unknown);
        RadioButton radioButtonTBYes = view.findViewById(R.id.radio_tb_yes);
        RadioButton radioButtonTBNo = view.findViewById(R.id.radio_tb_no);
        RadioButton radioButtonTBNa = view.findViewById(R.id.radio_tb_na);
        RadioButton radioButtonTBNegative = view.findViewById(R.id.radio_tb_status_negative);
        RadioButton radioButtonTBPositive = view.findViewById(R.id.radio_tb_status_positive);
        RadioButton radioButtonTBUknown = view.findViewById(R.id.radio_tb_status_unknown);

        radioButtonClicked(radioButtonDMNew);
        radioButtonClicked(radioButtonDMKnown);
        radioButtonClicked(radioButtonDMNA);
        radioButtonClicked(radioButtonDMNA);
        radioButtonClicked(radioButtonDMType1);
        radioButtonClicked(radioButtonDMType);
        radioButtonClicked(radioButtonDMGDM);
        radioButtonClicked(radioButtonDMSecondary);
        radioButtonClicked(radioButtonHTNNew);
        radioButtonClicked(radioButtonHTNKnown);
        radioButtonClicked(radioButtonHTNNA);
        radioButtonClicked(radioButtonHTNMild);
        radioButtonClicked(radioButtonHTNModerate);
        radioButtonClicked(radioButtonHTNSevere);
        radioButtonClicked(radioButtonHTNPreeclampsia);
        radioButtonClicked(radioButtonNHIFYes);
        radioButtonClicked(radioButtonNHIFNo);
        radioButtonClicked(radioButtonHIVNegative);
        radioButtonClicked(radioButtonHIVPositive);
        radioButtonClicked(radioButtonHIVUknown);
        radioButtonClicked(radioButtonTBYes);
        radioButtonClicked(radioButtonTBNo);
        radioButtonClicked(radioButtonTBNa);
        radioButtonClicked(radioButtonTBNegative);
        radioButtonClicked(radioButtonTBPositive);
        radioButtonClicked(radioButtonTBUknown);

        return view;
    }

    public void dmDiagnosis(String diagnosis) {
        if (diagnosis.matches("165087")) {
            dmDiagnosisDateEditText.setVisibility(View.GONE);
            dmClinicDateEditText.setVisibility(View.GONE);
        } else {
            dmDiagnosisDateEditText.setVisibility(View.VISIBLE);
            dmClinicDateEditText.setVisibility(View.VISIBLE);
        }
    }

    public void htnDiagnosis(String status) {
        Log.d("HTN Diagnosis", status);
        if (status.matches("165092")) {
            htnDiagnosisDateEditText.setVisibility(View.GONE);
            htnClinicDateEditText.setVisibility(View.GONE);
        } else {
            htnDiagnosisDateEditText.setVisibility(View.VISIBLE);
            htnClinicDateEditText.setVisibility(View.VISIBLE);
        }
    }

    public void textWatcher(EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(final Editable editable) {
                updateValues();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
    }

    public void radioButtonClicked(final RadioButton radioButton) {

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                boolean checked = (buttonView).isChecked();
                switch (radioButton.getId()) {
                    case R.id.radio_diabetes_new:
                        if (checked)
                            dm_diagnosis = "165087";
                        dmDiagnosis(dm_diagnosis);
                        break;
                    case R.id.radio_diabetes_known:
                        if (checked)
                            dm_diagnosis = "165088";
                        dmDiagnosis(dm_diagnosis);
                        break;
                    case R.id.radio_diabetes_na:
                        if (checked)
                            dm_diagnosis = "1175";
                        dmDiagnosis(dm_diagnosis);
                        break;
                    case R.id.radio_hypertension_new:
                        if (checked)
                            hypertension = "165092";
                        htnDiagnosis(hypertension);
                        break;
                    case R.id.radio_hypertension_known:
                        if (checked)
                            hypertension = "165093";
                        htnDiagnosis(hypertension);
                        break;
                    case R.id.radio_hypertension_na:
                        if (checked)
                            hypertension = "1175";
                        htnDiagnosis(hypertension);
                        break;
                    case R.id.radio_nhif_yes:
                        if (checked)
                            nhif = "1065";
                        break;
                    case R.id.radio_nhif_no:
                        if (checked)
                            nhif = "1066";
                        Alerts.alert_msg(getContext(), "NHIF Registration", "Encourage Client to Register for NHIF");
                        break;
                    case R.id.radio_diabetes_type_1:
                        if (checked)
                            diabetes_type = "142474";
                        break;
                    case R.id.radio_diabetes_GDM:
                        if (checked)
                            diabetes_type = "1449";
                        break;
                    case R.id.radio_diabetes_type_2:
                        if (checked)
                            diabetes_type = "142473";
                        break;
                    case R.id.radio_hypertension_mild:
                        if (checked)
                            hypertension_type = "165194";
                        break;
                    case R.id.radio_hypertension_moderate:
                        if (checked)
                            hypertension_type = "165195";
                        break;
                    case R.id.radio_hypertension_severe:
                        if (checked)
                            hypertension_type = "165196";
                        break;
                    case R.id.radio_hypertension_preeclampsia:
                        if (checked)
                            hypertension_type = "165197";
                        break;
                    case R.id.radio_tb_yes:
                        if (checked)
                            tb_screen = "1065";
                        break;
                    case R.id.radio_tb_no:
                        if (checked)
                            tb_screen = "1066";
                        break;
                    case R.id.radio_tb_na:
                        if (checked)
                            tb_screen = "1175";
                        break;
                    case R.id.radio_tb_status_negative:
                        if (checked)
                            tb_status = "664";
                        break;
                    case R.id.radio_tb_status_positive:
                        if (checked)
                            tb_status = "138571";
                        break;
                    case R.id.radio_tb_status_unknown:
                        if (checked)
                            tb_status = "1067";
                        break;
                    case R.id.radio_hiv_negative:
                        if (checked)
                            hiv_status = "664";
                        break;
                    case R.id.radio_hiv_positive:
                        if (checked)
                            hiv_status = "138571";
                        break;
                    case R.id.radio_hiv_unknown:
                        if (checked)
                            hiv_status = "1067";
                        break;
                }
                updateValues();
            }
        });
    }


    public void checkBox(final CheckBox checkBox) {

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                boolean checked = (buttonView).isChecked();

                //Check which checkbox was clicked
                switch (checkBox.getId()) {
                    case R.id.checkbox_tb_status:
                        if (checked) {
                            tb_treatment = "1659";
                        } else {
                            tb_treatment = "";
                        }
                        break;
                }

                updateValues();
            }
        });
    }

    public void updateValues() {
        String encounter_date = dm_followup_date.getText().toString().trim();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current_date = dateFormat.format(new Date());

        JSONArray jsonArry = new JSONArray();

        jsonArry.put(JSONFormBuilder.observations("160638", "", "string", supporter_nameEditText.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("160642", "", "string", supporter_phoneEditText.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165209", "", "string", supporter_phone_otherEditText.getText().toString().trim(), current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("165086", "", "valueCoded", dm_diagnosis, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165094", "", "valueCoded", diabetes_type, current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("165091", "", "valueCoded", hypertension, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165094", "", "valueCoded", hypertension_type, current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("1917", "", "valueCoded", nhif, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("138405", "", "valueCoded", hiv_status, current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("164800", "", "valueCoded", tb_screen, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("", "", "valueCoded", tb_status, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("1659", "", "valueCoded", tb_treatment, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165172", "", "string", editTextTBDate.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165173", "", "string", editTextTBComment.getText().toString().trim(), current_date, ""));


        jsonArry.put(JSONFormBuilder.observations("165089", "", "string", dmDiagnosisDateEditText.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165150", "", "string", dmClinicDateEditText.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165090", "", "string", htnDiagnosisDateEditText.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165151", "", "string", htnClinicDateEditText.getText().toString().trim(), current_date, ""));

        try {
            jsonArry = JSONFormBuilder.concatArray(jsonArry);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON FollowUp Page 1", jsonArry.toString() + " ");

        FragmentModelFollowUp.getInstance().followUpOne(encounter_date, jsonArry);
    }
}
