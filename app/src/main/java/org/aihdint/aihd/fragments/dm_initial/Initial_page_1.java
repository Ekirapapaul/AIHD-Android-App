package org.aihdint.aihd.fragments.dm_initial;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.aihdint.aihd.Forms.JSONFormBuilder;
import org.aihdint.aihd.R;
import org.aihdint.aihd.app.Alerts;
import org.aihdint.aihd.fragments.dm_followup.FragmentModelFollowUp;
import org.aihdint.aihd.model.KeyValue;
import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Developed by Rodney on 24/04/2018.
 */

@SuppressWarnings("FieldCanBeLocal")
public class Initial_page_1 extends Fragment {

    private LinearLayout nhif_other_details;

    private String occupation, education_level;

    private EditText dm_initial_dateEditText, occupation_otherEditText, editTextDiagnosisDiabetes, editTextDiagnosisHypertention;

    private EditText editTextPatientStatus, editTextTBDate, editTextTBComment, editTextNHIFOther, editTextReferralComment, editTextReferralDetails,
            editTextComplaintOther, editTextLMP, editTextRiskOther;

    private RadioGroup hiv_enrolled, referral_patient;

    //Checkboxes
    private CheckBox checkBoxTBStatus, checkBoxBreathing, checkBoxPalpitations, checkBoxDizziness, checkBoxFainting, checkBoxLegSwell, checkBoxUrinationFatigue,
            checkBoxLoseConsciousness, checkBoxBlurrVision, checkBoxFocalWeakness, checkBoxFootComplaint, checkBoxHeadacheMigraines, checkBoxComplaintOther;

    private String tb_status, breathing, palpitations, dizziness, fainting, leg_swell, urination_fatigue, lose_consciousness,
            blurr_vision, focal_weakness, foot_complaint, headache_migraines, complaint_other;

    //RadioButtons
    private RadioButton radioButtonDMNew, radioButtonDNKnown, radioButtonDMNa, radioButtonDMFamYes, radioButtonDMFamNo, radioButtonDMType1, radioButtonDMType2,
            radioButtonDMGDM, radioButtonDMSecondary, radioButtonHTNNew, radioButtonHTNKnown, radioButtonHTNNa, radioButtonHTNFamYes, radioButtonHTNFamNo, radioButtonHTNMild,
            radioButtonHTNModerate, radioButtonHTNSevere, radioButtonHTNPreeclampsia, radioButtonHIV_N, radioButtonHIV_P, radioButtonHIVUknown, radioButtonErolledYes,
            radioButtonEnrolledNo, radioButtonNHIFYes, radioButtonNHIFNo, radioButtonNHIFOther, radioButtonReferralYes, radioButtonReferralNo, radioButtonReferralCHW,
            radioButtonDispensary, radioButtonMaternity, radioButtonInPatient, radioButtonOPD, radioButtonCasualty, radioButtonCCC, radioButtonOther, radioButtonExerciseYes,
            radioButtonExerciseNo, radioButtonExerciseStopped, radioButtonDietYes, radioButtonDietNo, radioButtonDietStopped, radioButtonCigarettesYes, radioButtonCigarettesNo,
            radioButtonCigarettesStopped, radioButtonAlcoholYes, radioButtonAlcoholNo, radioButtonAlcoholStopped;

    private String diabetes_status, diabetes_family, diabetes_type, htn_status, htn_family, htn_type, hiv_status, enrolled_to_hiv_care, nhif_status, referral_status,
            referral_inter, referral_intra, exercise, diet, smoking, drinking;

    private String intra_referral, inter_referral, extremities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dm_initial_fragment_1, container, false);

        //EditText
        dm_initial_dateEditText = view.findViewById(R.id.dm_initial_date);
        occupation_otherEditText = view.findViewById(R.id.occupation_other);
        editTextDiagnosisDiabetes = view.findViewById(R.id.diagnosis_diabetes_date);
        editTextDiagnosisHypertention = view.findViewById(R.id.diagnosis_hypertension_date);
        editTextPatientStatus = view.findViewById(R.id.hiv_other_status);
        editTextTBDate = view.findViewById(R.id.tb_treatment_start);
        editTextTBComment = view.findViewById(R.id.tb_comment);
        editTextNHIFOther = view.findViewById(R.id.nhif_other);
        editTextReferralComment = view.findViewById(R.id.referral_other);
        editTextReferralDetails = view.findViewById(R.id.referral_other_details);
        editTextComplaintOther = view.findViewById(R.id.complaint_other);
        editTextLMP = view.findViewById(R.id.complaint_lmp);
        editTextRiskOther = view.findViewById(R.id.abuse_other);

        textWatcher(dm_initial_dateEditText);
        textWatcher(occupation_otherEditText);
        textWatcher(editTextDiagnosisDiabetes);
        textWatcher(editTextDiagnosisHypertention);
        textWatcher(editTextPatientStatus);
        textWatcher(editTextTBDate);
        textWatcher(editTextTBComment);
        textWatcher(editTextNHIFOther);
        textWatcher(editTextReferralComment);
        textWatcher(editTextReferralDetails);
        textWatcher(editTextComplaintOther);
        textWatcher(editTextLMP);
        textWatcher(editTextRiskOther);

        //RadioButton
        radioButtonDMNew = view.findViewById(R.id.radio_new_dm_patient);
        radioButtonDNKnown = view.findViewById(R.id.radio_known_dm_patient);
        radioButtonDMNa = view.findViewById(R.id.radio_na_dm_patient);
        radioButtonDMFamYes = view.findViewById(R.id.radio_dm_patient_family_yes);
        radioButtonDMFamNo = view.findViewById(R.id.radio_dm_patient_family_no);
        radioButtonDMType1 = view.findViewById(R.id.radio_diabetes_type_1);
        radioButtonDMType2 = view.findViewById(R.id.radio_diabetes_type_2);
        radioButtonDMGDM = view.findViewById(R.id.radio_diabetes_GDM);
        radioButtonDMSecondary = view.findViewById(R.id.radio_diabetes_secondary);
        radioButtonHTNNew = view.findViewById(R.id.radio_new_htn_patient);
        radioButtonHTNKnown = view.findViewById(R.id.radio_known_htn_patient);
        radioButtonHTNNa = view.findViewById(R.id.radio_na_htn_patient);
        radioButtonHTNFamYes = view.findViewById(R.id.radio_htn_patient_family_yes);
        radioButtonHTNFamNo = view.findViewById(R.id.radio_htn_patient_family_no);
        radioButtonHTNMild = view.findViewById(R.id.radio_hypertension_mild);
        radioButtonHTNModerate = view.findViewById(R.id.radio_hypertension_moderate);
        radioButtonHTNSevere = view.findViewById(R.id.radio_hypertension_severe);
        radioButtonHTNPreeclampsia = view.findViewById(R.id.radio_hypertension_preeclampsia);
        radioButtonHIV_N = view.findViewById(R.id.radio_hiv_negative);
        radioButtonHIV_P = view.findViewById(R.id.radio_hiv_positive);
        radioButtonHIVUknown = view.findViewById(R.id.radio_hiv_unknown);
        radioButtonErolledYes = view.findViewById(R.id.radio_enrolled_yes);
        radioButtonEnrolledNo = view.findViewById(R.id.radio_enrolled_no);
        radioButtonNHIFYes = view.findViewById(R.id.radio_nhif_yes);
        radioButtonNHIFNo = view.findViewById(R.id.radio_nhif_no);
        radioButtonNHIFOther = view.findViewById(R.id.radio_nhif_other);
        radioButtonReferralYes = view.findViewById(R.id.radio_referral_yes);
        radioButtonReferralNo = view.findViewById(R.id.radio_referral_no);
        radioButtonReferralCHW = view.findViewById(R.id.radio_referral_chw);
        radioButtonDispensary = view.findViewById(R.id.radio_referral_dispensary);
        radioButtonMaternity = view.findViewById(R.id.radio_referral_maternity);
        radioButtonInPatient = view.findViewById(R.id.radio_referral_inpatient);
        radioButtonOPD = view.findViewById(R.id.radio_referral_opd);
        radioButtonCasualty = view.findViewById(R.id.radio_referral_casualty);
        radioButtonCCC = view.findViewById(R.id.radio_referral_ccc);
        radioButtonOther = view.findViewById(R.id.radio_referral_other);
        radioButtonExerciseYes = view.findViewById(R.id.radio_exercise_yes);
        radioButtonExerciseNo = view.findViewById(R.id.radio_exercise_no);
        radioButtonExerciseStopped = view.findViewById(R.id.radio_exercise_stopped);
        radioButtonDietYes = view.findViewById(R.id.radio_diet_yes);
        radioButtonDietNo = view.findViewById(R.id.radio_diet_no);
        radioButtonDietStopped = view.findViewById(R.id.radio_diet_stopped);
        radioButtonCigarettesYes = view.findViewById(R.id.radio_smoke_yes);
        radioButtonCigarettesNo = view.findViewById(R.id.radio_smoke_no);
        radioButtonCigarettesStopped = view.findViewById(R.id.radio_smoke_stopped);
        radioButtonAlcoholYes = view.findViewById(R.id.radio_drink_yes);
        radioButtonAlcoholNo = view.findViewById(R.id.radio_drink_no);
        radioButtonAlcoholStopped = view.findViewById(R.id.radio_drink_stopped);

        radioCheck(radioButtonDMNew);
        radioCheck(radioButtonDNKnown);
        radioCheck(radioButtonDMNa);
        radioCheck(radioButtonDMFamYes);
        radioCheck(radioButtonDMFamNo);
        radioCheck(radioButtonDMType1);
        radioCheck(radioButtonDMType2);
        radioCheck(radioButtonDMGDM);
        radioCheck(radioButtonDMSecondary);
        radioCheck(radioButtonHTNNew);
        radioCheck(radioButtonHTNKnown);
        radioCheck(radioButtonHTNNa);
        radioCheck(radioButtonHTNFamYes);
        radioCheck(radioButtonHTNFamNo);
        radioCheck(radioButtonHTNMild);
        radioCheck(radioButtonHTNModerate);
        radioCheck(radioButtonHTNSevere);
        radioCheck(radioButtonHTNPreeclampsia);
        radioCheck(radioButtonHIV_N);
        radioCheck(radioButtonHIV_P);
        radioCheck(radioButtonHIVUknown);
        radioCheck(radioButtonErolledYes);
        radioCheck(radioButtonEnrolledNo);
        radioCheck(radioButtonNHIFYes);
        radioCheck(radioButtonNHIFNo);
        radioCheck(radioButtonNHIFOther);
        radioCheck(radioButtonReferralYes);
        radioCheck(radioButtonReferralNo);
        radioCheck(radioButtonReferralCHW);
        radioCheck(radioButtonDispensary);
        radioCheck(radioButtonMaternity);
        radioCheck(radioButtonInPatient);
        radioCheck(radioButtonOPD);
        radioCheck(radioButtonCasualty);
        radioCheck(radioButtonCCC);
        radioCheck(radioButtonOther);
        radioCheck(radioButtonExerciseYes);
        radioCheck(radioButtonExerciseNo);
        radioCheck(radioButtonExerciseStopped);
        radioCheck(radioButtonDietYes);
        radioCheck(radioButtonDietNo);
        radioCheck(radioButtonDietStopped);
        radioCheck(radioButtonCigarettesYes);
        radioCheck(radioButtonCigarettesNo);
        radioCheck(radioButtonCigarettesStopped);
        radioCheck(radioButtonAlcoholYes);
        radioCheck(radioButtonAlcoholNo);
        radioCheck(radioButtonAlcoholStopped);

        //Checkboxes
        checkBoxTBStatus = view.findViewById(R.id.checkbox_tb_status);
        checkBoxBreathing = view.findViewById(R.id.checkbox_complaint_breath);
        checkBoxPalpitations = view.findViewById(R.id.checkbox_complaint_palpitations);
        checkBoxDizziness = view.findViewById(R.id.checkbox_complaint_dizziness);
        checkBoxFainting = view.findViewById(R.id.checkbox_complaint_fainting);
        checkBoxLegSwell = view.findViewById(R.id.checkbox_complaint_leg_swell);
        checkBoxUrinationFatigue = view.findViewById(R.id.checkbox_complaint_urination_fatigue);
        checkBoxLoseConsciousness = view.findViewById(R.id.checkbox_complaint_lose_consciousness);
        checkBoxBlurrVision = view.findViewById(R.id.checkbox_complaint_blurr_vision);
        checkBoxFocalWeakness = view.findViewById(R.id.checkbox_complaint_focal_weakness);
        checkBoxFootComplaint = view.findViewById(R.id.checkbox_foot_complaint);
        checkBoxHeadacheMigraines = view.findViewById(R.id.checkbox_complaint_headache_migraines);
        checkBoxComplaintOther = view.findViewById(R.id.checkbox_complaint_other);

        checkBox(checkBoxTBStatus);
        checkBox(checkBoxBreathing);
        checkBox(checkBoxPalpitations);
        checkBox(checkBoxDizziness);
        checkBox(checkBoxFainting);
        checkBox(checkBoxLegSwell);
        checkBox(checkBoxUrinationFatigue);
        checkBox(checkBoxLoseConsciousness);
        checkBox(checkBoxBlurrVision);
        checkBox(checkBoxFocalWeakness);
        checkBox(checkBoxFootComplaint);
        checkBox(checkBoxHeadacheMigraines);
        checkBox(checkBoxComplaintOther);

        //Spinner
        Spinner educationLevelSpinner = view.findViewById(R.id.spinnerEducation);
        Spinner occupationSpinner = view.findViewById(R.id.spinnerOccupation);

        //RadioGroup
        hiv_enrolled = view.findViewById(R.id.hiv_enrolled);
        referral_patient = view.findViewById(R.id.interreferral_details);

        //LinearLayout
        nhif_other_details = view.findViewById(R.id.nhif_other_details);
        //hiv_status_details = view.findViewById(R.id.hiv_status_details);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dm_initial_dateEditText.setText(dateFormat.format(new Date())); // it will show 16/07/2013

        ArrayList<KeyValue> keyvalueOccupation = new ArrayList<>();
        ArrayList<KeyValue> keyvalueEducation = new ArrayList<>();
        //Add locations
        // adding each child node to HashMap key => value
        keyvalueOccupation.add(new KeyValue("", "Select Occupation"));
        keyvalueOccupation.add(new KeyValue("1540", "Employed"));
        keyvalueOccupation.add(new KeyValue("165170", "Unemployed"));
        keyvalueOccupation.add(new KeyValue("161382", "Self Employed"));
        keyvalueOccupation.add(new KeyValue("159465", "Student"));
        keyvalueOccupation.add(new KeyValue("5622", "Other"));

        // adding each child node to HashMap key => value
        keyvalueEducation.add(new KeyValue("", "Select Education Level"));
        keyvalueEducation.add(new KeyValue("1107", "None"));
        keyvalueEducation.add(new KeyValue("160290", "Incomplete Primary"));
        keyvalueEducation.add(new KeyValue("1713", "Primary"));
        keyvalueEducation.add(new KeyValue("1714", "Secondary"));
        keyvalueEducation.add(new KeyValue("160292", "Tertiary education"));

        //fill data in spinner
        ArrayAdapter<KeyValue> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, keyvalueOccupation);
        occupationSpinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //occupationSpinner.setSelection(adapter.getPosition(keyvalueOccupation.get(2)));//Optional to set the selected item.

        occupationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                KeyValue value = (KeyValue) parent.getSelectedItem();
                occupation = value.getId();
                updateValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //fill data in spinner
        ArrayAdapter<KeyValue> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, keyvalueEducation);
        educationLevelSpinner.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
        //spinnerLocation.setSelection(adapter.getPosition(keyvalue.get(2)));//Optional to set the selected item.

        educationLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                KeyValue value = (KeyValue) parent.getSelectedItem();
                education_level = value.getId();
                updateValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        updateValues();

        return view;
    }

    public void dmDiagnosis(String diagnosis) {
        if (diagnosis.matches("165088")) {
            editTextDiagnosisDiabetes.setVisibility(View.VISIBLE);
        } else {
            editTextDiagnosisDiabetes.setVisibility(View.GONE);
        }
    }

    public void htnDiagnosis(String status) {
        if (status.matches("165093")) {
            editTextDiagnosisHypertention.setVisibility(View.VISIBLE);
        } else {
            editTextDiagnosisHypertention.setVisibility(View.GONE);
        }
    }

    public void hivStatus(String status) {
        if (status.matches("138571")) {
            hiv_enrolled.setVisibility(View.VISIBLE);
        } else {
            hiv_enrolled.setVisibility(View.GONE);
        }
    }

    public void nhifStatus(String status) {
        if (status.matches("5622")) {
            nhif_other_details.setVisibility(View.VISIBLE);
        } else {
            nhif_other_details.setVisibility(View.GONE);
        }
    }

    public void referralStatus(String status) {
        if (status.matches("1065")) {
            referral_patient.setVisibility(View.VISIBLE);
        } else {
            referral_patient.setVisibility(View.GONE);
        }
    }

    public void complaintStatus(String status) {
        if (status.matches("5622")) {
            editTextComplaintOther.setVisibility(View.VISIBLE);
        } else {
            editTextComplaintOther.setVisibility(View.GONE);
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

    public void radioCheck(final RadioButton radioButton) {

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Is the button now checked?
                boolean checked = (buttonView).isChecked();

                // Check which radio button was clicked
                switch (radioButton.getId()) {
                    case R.id.radio_new_dm_patient:
                        if (checked)
                            diabetes_status = "165087";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_known_dm_patient:
                        if (checked)
                            diabetes_status = "165088";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_na_dm_patient:
                        if (checked)
                            diabetes_status = "1175";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_dm_patient_family_yes:
                        if (checked)
                            diabetes_family = "1065";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_dm_patient_family_no:
                        if (checked)
                            diabetes_family = "1066";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_diabetes_type_1:
                        if (checked)
                            diabetes_type = "142474";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_diabetes_type_2:
                        if (checked)
                            diabetes_type = "142473";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_diabetes_GDM:
                        if (checked)
                            diabetes_type = "1449";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_diabetes_secondary:
                        if (checked)
                            diabetes_type = "165199";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_new_htn_patient:
                        if (checked)
                            htn_status = "165092";
                        htnDiagnosis(htn_status);
                        break;
                    case R.id.radio_known_htn_patient:
                        if (checked)
                            htn_status = "165093";
                        htnDiagnosis(htn_status);
                        break;
                    case R.id.radio_na_htn_patient:
                        if (checked)
                            htn_status = "1175";
                        htnDiagnosis(htn_status);
                        break;
                    case R.id.radio_htn_patient_family_yes:
                        if (checked)
                            htn_family = "1065";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_htn_patient_family_no:
                        if (checked)
                            htn_family = "1066";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_hypertension_mild:
                        if (checked)
                            htn_type = "165194";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_hypertension_moderate:
                        if (checked)
                            htn_type = "165195";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_hypertension_severe:
                        if (checked)
                            htn_type = "165196";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_hypertension_preeclampsia:
                        if (checked)
                            htn_type = "165197";
                        dmDiagnosis(diabetes_status);
                        break;
                    case R.id.radio_hiv_negative:
                        if (checked)
                            hiv_status = "664";
                        hivStatus(hiv_status);
                        break;
                    case R.id.radio_hiv_positive:
                        if (checked)
                            hiv_status = "138571";
                        hivStatus(hiv_status);
                        break;
                    case R.id.radio_hiv_unknown:
                        if (checked)
                            hiv_status = "1067";
                        hivStatus(hiv_status);
                        break;
                    case R.id.radio_enrolled_yes:
                        if (checked)
                            enrolled_to_hiv_care = "1065";
                        break;
                    case R.id.radio_enrolled_no:
                        if (checked)
                            enrolled_to_hiv_care = "1066";
                        break;
                    case R.id.radio_nhif_yes:
                        if (checked)
                            nhif_status = "1065";
                        nhifStatus(nhif_status);
                        break;
                    case R.id.radio_nhif_no:
                        if (checked)
                            nhif_status = "1066";
                        nhifStatus(nhif_status);
                        Alerts.alert_msg(getContext(), "NHIF Registration", "Encourage Client to Register for NHIF");
                        break;
                    case R.id.radio_nhif_other:
                        if (checked)
                            nhif_status = "5622";
                        nhifStatus(nhif_status);
                        break;
                    case R.id.radio_referral_yes:
                        if (checked)
                            referral_status = "1065";
                        referralStatus(referral_status);
                        break;
                    case R.id.radio_referral_no:
                        if (checked)
                            referral_status = "1066";
                        referralStatus(referral_status);
                        break;
                    case R.id.radio_smoke_yes:
                        if (checked)
                            smoking = "1065";
                        break;
                    case R.id.radio_smoke_no:
                        if (checked)
                            smoking = "1066";
                        break;
                    case R.id.radio_smoke_stopped:
                        if (checked)
                            smoking = "158939";
                        break;
                    case R.id.radio_drink_yes:
                        if (checked)
                            drinking = "159450";
                        break;
                    case R.id.radio_drink_no:
                        if (checked)
                            drinking = "1090";
                        break;
                    case R.id.radio_drink_stopped:
                        if (checked)
                            drinking = "159452";
                        break;
                    case R.id.radio_referral_chw:
                        if (checked)
                            intra_referral = "1759";
                        break;
                    case R.id.radio_referral_dispensary:
                        if (checked)
                            intra_referral = "165107";
                        break;
                    case R.id.radio_referral_maternity:
                        if (checked)
                            inter_referral = "163146";
                        break;
                    case R.id.radio_referral_inpatient:
                        if (checked)
                            inter_referral = "160551";
                        break;
                    case R.id.radio_referral_opd:
                        if (checked)
                            inter_referral = "160542";
                        break;
                    case R.id.radio_referral_casualty:
                        if (checked)
                            inter_referral = "160473";
                        break;
                    case R.id.radio_referral_ccc:
                        if (checked)
                            inter_referral = "162050";
                        break;
                    case R.id.radio_referral_other:
                        if (checked)
                            inter_referral = "5622";
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
                            tb_status = "1659";
                        } else {
                            tb_status = "";
                        }
                        break;
                    case R.id.checkbox_complaint_breath:
                        if (checked) {
                            breathing = "110265";
                        } else {
                            breathing = "";
                        }
                        break;
                    case R.id.checkbox_complaint_palpitations:
                        if (checked) {
                            palpitations = "158627";
                        } else {
                            palpitations = "";
                        }
                        break;
                    case R.id.checkbox_complaint_dizziness:
                        if (checked) {
                            dizziness = "156046";
                        } else {
                            dizziness = "";
                        }
                        break;
                    case R.id.checkbox_complaint_fainting:
                        if (checked) {
                            fainting = "112961";
                        } else {
                            fainting = "";
                        }
                        break;
                    case R.id.checkbox_complaint_leg_swell:
                        if (checked) {
                            leg_swell = "135966";
                        } else {
                            leg_swell = "";
                        }
                        break;
                    case R.id.checkbox_complaint_urination_fatigue:
                        if (checked) {
                            urination_fatigue = "134185";
                        } else {
                            urination_fatigue = "";
                        }
                        break;
                    case R.id.checkbox_complaint_lose_consciousness:
                        if (checked) {
                            lose_consciousness = "135592";
                        } else {
                            lose_consciousness = "";
                        }
                        break;
                    case R.id.checkbox_complaint_blurr_vision:
                        if (checked) {
                            blurr_vision = "147104";
                        } else {
                            blurr_vision = "";
                        }
                        break;
                    case R.id.checkbox_complaint_focal_weakness:
                        if (checked) {
                            focal_weakness = "6005";
                        } else {
                            focal_weakness = "";
                        }
                        break;
                    case R.id.checkbox_foot_complaint:
                        if (checked) {
                            foot_complaint = "164529";
                        } else {
                            foot_complaint = "";
                        }
                        break;
                    case R.id.checkbox_complaint_headache_migraines:
                        if (checked) {
                            headache_migraines = "115782";
                        } else {
                            headache_migraines = "";
                        }
                        break;
                    case R.id.checkbox_complaint_other:
                        if (checked) {
                            complaint_other = "5622";
                            complaintStatus(complaint_other);
                        } else {
                            complaint_other = "";
                            complaintStatus(complaint_other);
                        }
                        break;
                }

                updateValues();
            }
        });
    }


    public void updateValues() {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String current_date = dateFormat.format(new Date());

        JSONArray jsonArry = new JSONArray();

        jsonArry.put(JSONFormBuilder.observations("1712", "", "valueCoded", education_level, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("1542", "", "valueCoded", occupation, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165171", "", "string", occupation_otherEditText.getText().toString().trim(), current_date, ""));


        jsonArry.put(JSONFormBuilder.observations("165086", "", "valueCoded", diabetes_status, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("140228", "", "valueCoded", diabetes_family, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165153", "", "string", editTextDiagnosisDiabetes.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165094", "", "valueCoded", diabetes_type, current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("165091", "", "valueCoded", htn_status, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165191", "", "valueCoded", htn_family, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165154", "", "string", editTextDiagnosisHypertention.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165198", "", "valueCoded", htn_type, current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("138405", "", "valueCoded", hiv_status, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("159811", "", "valueCoded", enrolled_to_hiv_care, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165155", "", "string", editTextPatientStatus.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("1659", "", "valueCoded", tb_status, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165172", "", "string", editTextTBDate.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165173", "", "string", editTextTBComment.getText().toString().trim(), current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("1917", "", "valueCoded", nhif_status, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165162", "", "string", editTextNHIFOther.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("1648", "", "valueCoded", referral_status, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("164408", "", "valueCoded", referral_inter, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165163", "", "valueCoded", referral_intra, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165164", "", "string", editTextReferralComment.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165156", "", "string", editTextReferralDetails.getText().toString().trim(), current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", breathing, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", palpitations, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", dizziness, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", fainting, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", leg_swell, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", urination_fatigue, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", lose_consciousness, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", blurr_vision, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", focal_weakness, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", foot_complaint, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", headache_migraines, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("6042", "", "valueCoded", complaint_other, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165174", "", "string", editTextComplaintOther.getText().toString().trim(), current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("1427", "", "string", editTextLMP.getText().toString().trim(), current_date, ""));

        jsonArry.put(JSONFormBuilder.observations("165208", "", "valueCoded", exercise, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165207", "", "valueCoded", diet, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("152722", "", "valueCoded", smoking, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("159449", "", "valueCoded", drinking, current_date, ""));
        jsonArry.put(JSONFormBuilder.observations("165165", "", "string", editTextRiskOther.getText().toString().trim(), current_date, ""));

        try {
            jsonArry = JSONFormBuilder.concatArray(jsonArry);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON FollowUp Page 1", jsonArry.toString() + " ");

        FragmentModelFollowUp.getInstance().followUpOne(dm_initial_dateEditText.getText().toString().trim(), jsonArry);
    }

}
