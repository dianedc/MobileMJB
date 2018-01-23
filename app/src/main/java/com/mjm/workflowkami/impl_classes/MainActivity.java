//package com.mjm.workflowkami.impl_classes;
//
//import android.content.DialogInterface;
//
//import com.mjm.workflowkami.Fragments.FormFragment;
//import com.mjm.workflowkami.Fragments.Instruction;
//import com.mjm.workflowkami.Fragments.TextFragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ivb.com.materialstepper.progressMobileStepper;
//
///**
// * Created by admin on 20 Nov 2017.
// */
//
//public class MainActivity extends progressMobileStepper {
//
//    List<Class> stepperFragmentList = new ArrayList<>();
//
//    @Override
//    public void onStepperCompleted() { showCompletedDialog(); }
//
//    protected void showCompletedDialog() {
//        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
//                MainActivity.this);
//
//        alertDialogBuilder.setTitle("HOOOORAYYYYY");
//        alertDialogBuilder
//                .setMessage("We've completed the stepper!")
//                .setCancelable(true)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
//
//        alertDialog.show();
//    }
//
//    @Override
//    public List<Class> init() {
//
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(FormFragment.class);
//        stepperFragmentList.add(Instruction.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(FormFragment.class);
//        stepperFragmentList.add(Instruction.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(FormFragment.class);
//        stepperFragmentList.add(Instruction.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(FormFragment.class);
//        stepperFragmentList.add(Instruction.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(FormFragment.class);
//        stepperFragmentList.add(Instruction.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(FormFragment.class);
//        stepperFragmentList.add(Instruction.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(TextFragment.class);
//        stepperFragmentList.add(FormFragment.class);
//        stepperFragmentList.add(Instruction.class);
//        stepperFragmentList.add(TextFragment.class);
//
//        return stepperFragmentList;
//    }
//}
