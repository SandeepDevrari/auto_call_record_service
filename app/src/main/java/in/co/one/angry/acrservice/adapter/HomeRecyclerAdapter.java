package in.co.one.angry.acrservice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import in.co.one.angry.acrservice.R;
import in.co.one.angry.acrservice.model.FilePojo;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HolderView> {
    private List<FilePojo>allFiles;
    private Context context;
    private OnFileClicked onFileClicked;

    public HomeRecyclerAdapter(List<FilePojo> allFiles, Context context, OnFileClicked onFileClicked) {
        this.allFiles = allFiles;
        this.context = context;
        this.onFileClicked = onFileClicked;
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HolderView(LayoutInflater.from(context).inflate(R.layout.file_item_view,parent,false),onFileClicked);
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {
        FilePojo filePojo=allFiles.get(position);
//        String fileName=filePojo.getFile_name();
//        String files=fileName.substring(fileName.lastIndexOf("/"));
        holder.file_name.setText(filePojo.getFile_number());
        holder.file_number.setText(filePojo.getFile_date());
    }

    @Override
    public int getItemCount() {
        return allFiles.size();
    }

    class HolderView extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView file_name,file_number;
        private Button play;
        private OnFileClicked onFileClicked;
        private HolderView(View itemView,OnFileClicked onFileClicked) {
            super(itemView);
            this.onFileClicked=onFileClicked;
            file_name=itemView.findViewById(R.id.file_name_icon);
            //file_name.setOnClickListener(this);
            file_number=itemView.findViewById(R.id.file_number_icon);
            play=itemView.findViewById(R.id.play_file_icon);
            //file_icon.setOnClickListener(this);
            play.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                    onFileClicked.onFileClick(getAdapterPosition());
        }
    }
    public interface OnFileClicked{
        void onFileClick(int position);
        //void onFileIconclicked(int position);
    }
}


