package id.ac.unhas.crudroom.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.ac.unhas.crudroom.R
import id.ac.unhas.crudroom.data.User
import kotlinx.android.synthetic.main.items.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false))
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.imageView.load(userList[position].profilePhoto)
        holder.itemView.firstname.text = currentItem.firstName
        holder.itemView.lastname.text = currentItem.lastName
        holder.itemView.age.text = "(${currentItem.age})"
        holder.itemView.email.text = currentItem.email

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}