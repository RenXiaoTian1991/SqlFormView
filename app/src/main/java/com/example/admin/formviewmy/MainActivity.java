package com.example.admin.formviewmy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    TableLayout header;
    List<Integer> widthList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

        header = (TableLayout) findViewById(R.id.header);
        initTitleWidth();
    }

    private void initTitleWidth() {
        widthList = new ArrayList<>();
        for (int i = 0;i<5;i++){
            widthList.add(new Integer(40*(i+1)));
        }
        MyTabRow myTabRow = new MyTabRow(this,widthList);
        for (int col = 0; col < 5; col++) {
            BorderTextView borderTextView = myTabRow.getTextView(col);
            borderTextView.setGravity(Gravity.CENTER);
            borderTextView.setText("标题");
            myTabRow.addView(borderTextView);
        }

        header.addView(myTabRow);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        private List<TableRow> mDatas = new ArrayList<>();
        private int paddingPx = MainActivity.this.getResources().getDimensionPixelSize(R.dimen.dbinspector_row_padding);

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.dbinspector_item,null));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tableLayout.removeAllViews();
            holder.tableRow.removeAllViews();
            BorderTextView textView;
            if(position<=500){
                for (int col = 0; col < 5; col++) {
                    textView = holder.tableRow.getTextView(col);
                    textView.setText("datartursdfgdfhgdfghdfhdfhghhgghgrdhghdf");
                    textView.setPadding(paddingPx, paddingPx / 2, paddingPx, paddingPx / 2);
                    holder.tableRow.addView(textView);
                }
            }else {
                for (int col = 0; col < 5; col++) {
                    textView = holder.tableRow.getTextView(col);
                    textView.setText("sffdffggg");
                    textView.setPadding(paddingPx, paddingPx / 2, paddingPx, paddingPx / 2);
                    holder.tableRow.addView(textView);
                }
            }
            ViewGroup viewGroup = (ViewGroup) holder.tableRow.getParent();
            if(viewGroup != null){
                viewGroup.removeAllViews();
            }
            holder.tableLayout.addView(holder.tableRow);
        }

        @Override
        public int getItemCount() {
            return 5000;
        }

        public void setDatas(List<TableRow> datas) {
            mDatas = datas;
            notifyDataSetChanged();
        }

        public class MyHolder extends RecyclerView.ViewHolder{

            public TableLayout tableLayout;
            public MyTabRow tableRow;

            public MyHolder(View itemView) {
                super(itemView);
                tableLayout = (TableLayout) itemView.findViewById(R.id.dbinspector_table_layout);
                tableRow = new MyTabRow(MainActivity.this,widthList);
            }
        }

    }

}
