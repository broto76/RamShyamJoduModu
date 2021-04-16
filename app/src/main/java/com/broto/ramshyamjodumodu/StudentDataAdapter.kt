package com.broto.ramshyamjodumodu

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentDataAdapter(private val list: List<Student>, private val mContext: Context):
    RecyclerView.Adapter<StudentDataAdapter.StudentViewHolder>() {

    private val TAG = "StudentDataAdapter"

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentDataAdapter.StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentDataAdapter.StudentViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.roll.text = list[position].rollNumber.toString()
        holder.className.text = list[position].studentClass.toString()
        holder.addr.text = list[position].address

//        if (position == 1) {
//            holder.delete.visibility = View.GONE
//        } else {
//            holder.delete.visibility = View.VISIBLE
//        }

        holder.list_item.setOnClickListener {
            val name = holder.name.text.toString()
            val rollNumber = holder.roll.text.toString()
            val className = holder.className.text.toString()
            val address = holder.addr.text.toString()

            if (name.isEmpty() || rollNumber.isEmpty() ||
                className.isEmpty() || address.isEmpty()) {
                Log.d(TAG, "Input is empty")
                return@setOnClickListener
            }

            val intent = Intent(mContext, UpdateDetailsActivity::class.java)
            intent.putExtra(Constants.KEY_NAME, name)
            intent.putExtra(Constants.KEY_ROLL_NO, rollNumber)
            intent.putExtra(Constants.KEY_CLASS, className)
            intent.putExtra(Constants.KEY_ADDRESS, address)
            mContext.startActivity(intent)
        }

        holder.delete.setOnClickListener {
            ioScope.launch {
                val db = Room.databaseBuilder(
                    mContext,
                    UserDatabase::class.java,
                    Constants.DB_NAME
                ).build()
                val studentdao = db.getStudentDAO()

                studentdao.deleteStudent(list[position])

                (mContext as HomePageActivity).refreshPage(list[position])
            }
        }

    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.name)
        var roll = itemView.findViewById<TextView>(R.id.roll)
        var className = itemView.findViewById<TextView>(R.id.className)
        var addr = itemView.findViewById<TextView>(R.id.addr)
        var list_item = itemView.findViewById<RelativeLayout>(R.id.ll_list_item)
        var delete = itemView.findViewById<ImageView>(R.id.iv_delete)
    }

}