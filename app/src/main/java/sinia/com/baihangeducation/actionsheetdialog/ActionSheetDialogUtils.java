package sinia.com.baihangeducation.actionsheetdialog;

import android.content.Context;
import android.widget.TextView;

import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialog.OnSheetItemClickListener;
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialog.SheetItemColor;

public class ActionSheetDialogUtils {

    public static void createCardDialog(Context context, final TextView tv) {
        new ActionSheetDialog(context)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("会计师证件", ActionSheetDialog.SheetItemColor.BLACK,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("会计师证件");
                            }
                        })
                .addSheetItem("中国精算师", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("中国精算师");
                            }
                        })
                .addSheetItem("特许金融分析师(CFA)", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("特许金融分析师(CFA)");
                            }
                        })
                .addSheetItem("一级建造师", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("一级建造师");
                            }
                        })
                .addSheetItem("一级建筑师", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("一级建筑师");
                            }
                        })
                .addSheetItem("执业医师资格考试", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("执业医师资格考试");
                            }
                        })
                .addSheetItem("全国司法考试", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("全国司法考试");
                            }
                        })
                .addSheetItem("人力资源管理师", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("人力资源管理师");
                            }
                        })
                .addSheetItem("心理咨询师", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("心理咨询师");
                            }
                        })
                .addSheetItem("其他", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("其他");
                            }
                        }).show();
    }

    public static void createSexDialog(Context context, final TextView tv) {
        new ActionSheetDialog(context)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("男", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("男");
                            }
                        })
                .addSheetItem("女", SheetItemColor.BLACK,
                        new OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv.setText("女");
                            }
                        }).show();
    }

}
