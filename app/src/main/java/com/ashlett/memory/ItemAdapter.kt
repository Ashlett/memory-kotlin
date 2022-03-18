import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.ashlett.memory.Item
import com.ashlett.memory.R

class ItemAdapter(
    var itemList: List<Item>,
    private val listener: Listener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    interface Listener {
        fun onClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.btn_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partial_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.button.text = item.text
        holder.button.textScaleX = if (item.isVisible) 1f else 0f

        holder.button.setOnClickListener {
            listener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
