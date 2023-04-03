package com.bumbumapps.stylishtext.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.adapters.SheetAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Objects;

public  class  BottomSheet {
    BottomSheetDialog bottomSheetDialog;
    public static void showDialogbox(final Context context, final EditText editText){
        BottomSheetDialog bottomSheetDialog;
        ImageButton delete, back;
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.sheet_symbols, null);
        bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(view);
        back = view.findViewById(R.id.back_delete);
        back.setOnClickListener(v -> {
            int length = editText.getText().length();
            if (length > 0) {
                editText.getText().delete(length - 1, length);
            }
        });
        delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(v -> editText.getText().clear());
        RadioGroup group = view.findViewById(R.id.radio_group_sym);
        group.check(R.id.rd1);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerviewSheet);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        group.setOnCheckedChangeListener((group1, checkedId) -> {
            if (checkedId == R.id.rd1){
                SheetAdapter sheetAdapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM1)), (position, sym) -> editText.getEditableText().insert(Math.max(editText.getSelectionStart(), 0), sym));
                recyclerView.setAdapter(sheetAdapter);
            }
            if (checkedId == R.id.rd2){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM2)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd3){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM3)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd4){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM4)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd5){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM5)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd6){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM6)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd7){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM7)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd8){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM8)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd9){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM9)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd10){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM10)), (position, sym) -> editText.getEditableText().insert(Math.max(editText.getSelectionStart(), 0), sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd11){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM11)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd12){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM12)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
            if (checkedId == R.id.rd13){
                SheetAdapter sheet2Adapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM13)), (position, sym) -> editText.append(sym));
                recyclerView.setAdapter(sheet2Adapter);

            }
        });

        SheetAdapter sheetAdapter = new SheetAdapter(context, getSymbols(context.getString(R.string.SYM1)), (position, sym) -> editText.append(sym));
        recyclerView.setAdapter(sheetAdapter);
        Objects.requireNonNull(bottomSheetDialog.getWindow()).setDimAmount(0);
        bottomSheetDialog.show();

    }
    private static ArrayList<String> getSymbols(String ss)
    {
        String[] sym = ss.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String s : sym) {
            if (s != null && !s.trim().equals(""))
                list.add(s);
        }

        return list;
    }
    public  void styleBottom(Context context, final String text){
        ImageButton back,share,copy;
        final CopyHandler copyHandler = new CopyHandler(context);
        bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetDialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View view=inflater.inflate(R.layout.bottom_preview,null);
        TextView preview = view.findViewById(R.id.preview_font) ;
        back = view.findViewById(R.id.backbtn);
        share = view.findViewById(R.id.sharebtn);
        copy = view.findViewById(R.id.copybtn);
        copy.setOnClickListener(v -> copyHandler.copyText(text));
        share.setOnClickListener(v -> copyHandler.shareText(text));
        back.setOnClickListener(v -> bottomSheetDialog.hide());
        preview.setText(text);
        bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }


}
