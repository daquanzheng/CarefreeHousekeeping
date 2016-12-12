package micro.com.carefreehousekeeping.Activity.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import micro.com.carefreehousekeeping.Activity.Adapter.NannyAdapter;
import micro.com.carefreehousekeeping.Activity.Model.NannyInfo;
import micro.com.carefreehousekeeping.R;

/**
 * Created by Administrator on 2016/12/12.
 */

public class NannyInformation extends Activity {

    private ListView pingLun;
    private List<NannyInfo> list;
    private NannyAdapter nannyAdapter;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nanny_information);
        createData();
        init();



    }

    private void init(){
        inflater=LayoutInflater.from(this);
        pingLun= (ListView) findViewById(R.id.pinglun_list);
        nannyAdapter=new NannyAdapter(list,this);
        pingLun.setAdapter(nannyAdapter);
        pingLun.addHeaderView(inflater.inflate(R.layout.nanny_information_head,null));
    }

    private void createData(){
        list=new ArrayList<>();
        list.add(new NannyInfo("1","2","3"));
        list.add(new NannyInfo("1","2","3"));
        list.add(new NannyInfo("1","2","3"));
    }
}
