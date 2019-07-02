package in.co.one.angry.acrservice;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import in.co.one.angry.acrservice.adapter.HomeRecyclerAdapter;
import in.co.one.angry.acrservice.database.DatabaseController;
import in.co.one.angry.acrservice.model.FilePojo;

public class FileShowActivity extends AppCompatActivity {

    private List<FilePojo> allfiles;
    private DatabaseController controller;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.opener_hide_files);
       // Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        setDataTo();

    }

    private void setDataTo() {
        allfiles=new ArrayList<>();
        controller=new DatabaseController(this);
        Cursor cursor=controller.read_file();
        if(cursor.moveToFirst()){
            do{
                FilePojo filePojo=new FilePojo();
                filePojo.setFile_name(cursor.getString(cursor.getColumnIndex(sBase.FILE_NAME)));
                filePojo.setFile_number(cursor.getString(cursor.getColumnIndex(sBase.FILE_NUMBER)));
                filePojo.setFile_date(cursor.getString(cursor.getColumnIndex(sBase.DATE)));
                allfiles.add(filePojo);
            }while (cursor.moveToNext());
            setUpUI();
        }
    }

    private void setUpUI() {
        RecyclerView recyclerView=findViewById(R.id.file_opener_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new HomeRecyclerAdapter(allfiles,this, new HomeRecyclerAdapter.OnFileClicked() {
            @Override
            public void onFileClick(int position) {
                //Toast.makeText(FileShowActivity.this, "file "+allfiles.get(position).getFile_name(), Toast.LENGTH_SHORT).show();

                File file=new File(allfiles.get(position).getFile_name());
                Intent viewMediaIntent = new Intent();
                viewMediaIntent.setAction(android.content.Intent.ACTION_VIEW);
                viewMediaIntent.setDataAndType(Uri.fromFile(file), "audio/*");
                viewMediaIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(viewMediaIntent);
            }
        }));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN_MR1)
//            if(!isDestroyed())
//                finish();
//        else
            finish();
    }
}
