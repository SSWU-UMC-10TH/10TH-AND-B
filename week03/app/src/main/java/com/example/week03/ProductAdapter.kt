import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week03.R
import com.example.week03.Product

class ProductAdapter(
    private var productList: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.item_image)
        val name = itemView.findViewById<TextView>(R.id.item_name)
        val price = itemView.findViewById<TextView>(R.id.item_price)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.image.setImageResource(product.imageRes)
        holder.name.text = product.name
        holder.price.text = product.price
    }


    override fun getItemCount(): Int = productList.size
}