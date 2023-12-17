import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication9.R
import com.example.myapplication9.app.MyDbContract.ActionsEntry
import com.example.myapplication9.app.Action

class ActionAdapter(private val actions: List<Action>) :
    RecyclerView.Adapter<ActionAdapter.ActionViewHolder>() {

    class ActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val stateTextView: TextView = itemView.findViewById(R.id.stateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_action, parent, false)
        return ActionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        val currentAction = actions[position]
        holder.timeTextView.text = currentAction.time
        holder.stateTextView.text = currentAction.state
    }

    override fun getItemCount(): Int {
        return actions.size
    }
}
