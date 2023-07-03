 package com.example.bakolwifiapp.ui

 import android.view.LayoutInflater
 import android.view.View
 import android.view.ViewGroup
 import android.widget.AdapterView.OnItemClickListener
 import android.widget.TextView
 import androidx.recyclerview.widget.DiffUtil
 import androidx.recyclerview.widget.ListAdapter
 import androidx.recyclerview.widget.RecyclerView
 import com.example.bakolwifiapp.R
 import com.example.bakolwifiapp.model.Wifi

 class WIfiListAdapter(
     private val onItemClickListener: (Wifi) -> Unit
 ):ListAdapter<Wifi, WIfiListAdapter.WifiViewHolder>(WORDS_COMPARATOR) {
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiViewHolder {
         return WifiViewHolder.create(parent)
     }

     override fun onBindViewHolder(holder: WifiViewHolder, position: Int) {
         val wifi = getItem(position)
         holder.bind(wifi)
         holder.itemView.setOnClickListener {
             onItemClickListener(wifi)
         }
     }
     class WifiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         private val nameTextView: TextView = itemView.findViewById(R.id.namaTextView)
         private val telpTextView: TextView = itemView.findViewById(R.id.tlpTextView)
         private val addressTextView3: TextView = itemView.findViewById(R.id.addressTextView3)

         fun bind(wifi: Wifi?) {
             nameTextView.text = wifi?.name
             telpTextView.text = wifi?.tlp.toString()
             addressTextView3.text = wifi?.address

         }

         companion object {
             fun create(parent: ViewGroup): WIfiListAdapter.WifiViewHolder {
                 val view: View = LayoutInflater.from(parent.context)
                     .inflate(R.layout.item_wifi, parent, false)
                 return WifiViewHolder(view)
             }
         }

     }

     companion object {
         private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Wifi>() {
             override fun areItemsTheSame(oldItem: Wifi, newItem: Wifi): Boolean {
                 return oldItem == newItem
             }

             override fun areContentsTheSame(oldItem: Wifi, newItem: Wifi): Boolean {
                 return oldItem.id == newItem.id
             }
         }
     }
 }