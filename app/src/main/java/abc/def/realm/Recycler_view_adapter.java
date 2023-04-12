package abc.def.realm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycler_view_adapter extends RecyclerView.Adapter<Recycler_view_adapter.viewHolder> {

    List<DataModal> dataModalList;

    public Recycler_view_adapter(List<DataModal> dataModalList, Context context) {
        this.dataModalList = dataModalList;
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public Recycler_view_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_course_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_view_adapter.viewHolder holder, int position) {

        DataModal modal = dataModalList.get(position);

        holder.vh_CourseName.setText(modal.getCourseName());
        holder.vh_CourseTrack.setText(modal.getCourseTracks());
        holder.vh_CourseDuration.setText(modal.getCourseDuration());
        holder.vh_CourseDescription.setText(modal.getCourseDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Update.class);
                intent.putExtra("coursename",modal.getCourseName());
                intent.putExtra("courseduration",modal.getCourseDuration());
                intent.putExtra("coursetrack",modal.getCourseTracks());
                intent.putExtra("coursedescription",modal.getCourseDescription());
                intent.putExtra("id",modal.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModalList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView vh_CourseName;
        private TextView vh_CourseDuration;
        private TextView vh_CourseTrack;
        private TextView vh_CourseDescription;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            vh_CourseName = itemView.findViewById(R.id.cv_courseNme);
            vh_CourseDescription = itemView.findViewById(R.id.cv_coursedesc);
            vh_CourseDuration = itemView.findViewById(R.id.courseDuration);
            vh_CourseTrack = itemView.findViewById(R.id.cv_coursetrack);


        }
    }
}
