package com.example.tacademy.sampleadapterlistchoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list_view);
        mAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(mAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Button btn=(Button)findViewById(R.id.btn_delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.getChoiceMode()==ListView.CHOICE_MODE_SINGLE){
                    int position=listView.getCheckedItemPosition();
                    String s=(String)listView.getItemAtPosition(position);
                    mAdapter.remove(s);
                }
                else if(listView.getChoiceMode()==ListView.CHOICE_MODE_MULTIPLE) {
                    SparseBooleanArray array = listView.getCheckedItemPositions();
                    ArrayList<Integer> s=new ArrayList<Integer>();
                    for (int index = 0; index < array.size(); index++) {
                        int position=array.keyAt(index);
                        if(array.get(position))
                        {
                            s.add((Integer) position);
                        }
                    }
                    for(Integer str : s)
                    {
                        mAdapter.remove((String)listView.getItemAtPosition(str));
                    }
                    for(Integer str : s)
                    {
                       listView.setItemChecked(str,false);
                    }


                }

            }
        });
        initData();

    }
    private void initData()
    {
        String[] items=getResources().getStringArray(R.array.list_item);
        for(String s : items)
        {
            mAdapter.add(s);
        }
    }
}
