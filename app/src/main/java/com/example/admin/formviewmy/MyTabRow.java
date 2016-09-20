package com.example.admin.formviewmy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/20.
 */
public class MyTabRow extends TableRow {

    private List<BorderTextView> mTextViewList = new ArrayList<>();
    private Context mContext;
    private List<Integer> widthList;


    public MyTabRow(Context context, List<Integer> widthList) {
        super(context);
        this.mContext = context;
        this.widthList = widthList;
    }

    public void addViewToPosition(int position){
        TextView textView = getTextView(position);
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        if(viewGroup != null){
            viewGroup.removeView(textView);
        }
        addView(textView);
    }

    public BorderTextView getTextView(int position){

        BorderTextView textView;
        if (position < mTextViewList.size()) {
            textView = mTextViewList.get(position);
        } else {
            textView = new BorderTextView(mContext);
            textView.setMaxEms(12);
            textView.setWidth(FeViewUtils.dpToPx(widthList.get(position)));
            textView.setSingleLine(true);
            textView.setPadding(5,2,5,2);
            mTextViewList.add(textView);
        }

        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        if(viewGroup != null){
            viewGroup.removeView(textView);
        }
        return textView;
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

}
