package com.example.testsip.ui.users

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testsip.R
import com.example.testsip.data.UsersItem
import com.example.testsip.databinding.ItemUserBinding

class UsersFragmentAdapter(val navController: NavController,val menuInflater: MenuInflater):
    ListAdapter<UsersItem, UsersFragmentAdapter.MainViewHolder>(DomainPersonModelCallback) {

    val KEY_USER = "key_user"
    var selectedIdItem: UsersItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MainViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {

        init {
            binding.root.setOnCreateContextMenuListener(this)
        }

        fun bind(position: Int) = with(binding) {
            userName.text = currentList[position].username
            name.text = currentList[position].name
            itemView.setOnClickListener {
                selectedIdItem = currentList[position]
                if(selectedIdItem != null){
                    val bundle = Bundle()
                    bundle.putParcelable(KEY_USER, currentList[position])
                    navController.navigate(R.id.action_navigation_users_to_navigation_details, bundle)
                }

            }
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
        }
    }

    companion object DomainPersonModelCallback : DiffUtil.ItemCallback<UsersItem>() {
        override fun areItemsTheSame(oldItem: UsersItem, newItem: UsersItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: UsersItem, newItem: UsersItem) =
            oldItem == newItem
    }
}