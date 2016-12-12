package micro.com.carefreehousekeeping.Activity.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import micro.com.carefreehousekeeping.Activity.Adapter.ElderAdapter;
import micro.com.carefreehousekeeping.R;

/**
 * Created by Administrator on 2016/12/10.
 */

public class Elderly extends Activity{

    private  ListView listView;
    private ElderAdapter adapter;
    private List list;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elderly);
        init();
        getDate();
        adapter=new ElderAdapter(this,list);
        listView.setAdapter(adapter);
        View view=inflater.inflate(R.layout.elderly_item_head,null);
        listView.addHeaderView(view);

    }

    private void init(){
       listView= (ListView) findViewById(R.id.list_view);
        inflater=LayoutInflater.from(this);
    }

    public void getDate()
    {
        list=new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
    }
}
