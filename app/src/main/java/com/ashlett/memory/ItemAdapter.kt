import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.ashlett.memory.Item
import com.ashlett.memory.R


class ItemAdapter(
    private val itemList: List<Item>,
    private val listener: Listener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    interface Listener {
        fun onClick()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.btn_item)

        fun showContent() {
            this.button.textScaleX = 1f
        }

        fun hideContent() {
            this.button.textScaleX = 0f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partial_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.button.text = item.text

        holder.button.setOnClickListener {
            holder.showContent()
            item.isVisible = true
            listener.onClick()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
