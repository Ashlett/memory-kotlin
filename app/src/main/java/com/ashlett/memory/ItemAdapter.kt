import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.ashlett.memory.GameActivity
import com.ashlett.memory.Item
import com.ashlett.memory.R


class ItemAdapter(var context: GameActivity, private val itemList: Array<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.btn_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partial_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.button.text = item.text

        holder.button.setOnClickListener {
            holder.button.textScaleX = 1f
            item.isVisible = true
            context.checkGameIsWon()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
