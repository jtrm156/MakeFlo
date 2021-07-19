import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.practice2.*
import com.example.practice2.databinding.ItemLayout2Binding

data class UserCheckStatus(val position: Int, var isChecked: Boolean)

class CustomAdapter3(private val context: Context, private val businessCardArrayList: MutableList<BusinessCard>, private val listener: ItemDragListener) :
    RecyclerView.Adapter<CustomAdapter3.ItemViewHolder>(), ItemActionListener {
    //Layout들과 RecyclerView를 연결시킬 Adapter(데이터를 받아오고 이를 레이아웃에 직접 연결하는 함수를 실행시키는 클래스)

    companion object {
        const val Music = "app_preferences"
    }
    private val userCheckBoxStatus = arrayListOf<UserCheckStatus>()

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }
    private lateinit var mPreferences: SharedPreferences
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ItemViewHolder(private val binding: ItemLayout2Binding, listener: ItemDragListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(businessCard: BusinessCard, context: Context) {

            mPreferences = context.getSharedPreferences(Music, AppCompatActivity.MODE_PRIVATE);
            val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()

            val resourceId = context.resources.getIdentifier(
                businessCard.img.toString(),
                "drawable",
                context.packageName
            )
            binding.imgListviewItem11.setImageResource(resourceId)
            binding.contentsListviewItem12.text = businessCard.contents
            binding.nameListviewItem12.text = businessCard.name

            binding.checkBox2.setOnClickListener{
                if(binding.checkBox2.isChecked){
                    preferencesEditor.putBoolean("checkbox_check",binding.checkBox2.isChecked)
                    preferencesEditor.apply()
                }
                else{
                    preferencesEditor.putBoolean("checkbox_check",binding.checkBox2.isChecked)
                    preferencesEditor.apply()
                }
                binding.checkBox2.isChecked = mPreferences.getBoolean("checkbox_check",true)
            }
        }
        init{
            binding.imgListviewItem13.setOnTouchListener{v, event ->
                if(event.action == MotionEvent.ACTION_DOWN){
                    listener.onStartDrag(this)
                }
                false
            }
        }
    } //ViewHolder는 클래스 내에 View를 저장하는 변수를 만들어 그 안에 데이터를 직접 연결시킬 수 있는 클래스, 디자인 패턴

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemLayout2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, listener)
    } //화면을 최초 로딩하여 만들어진 View가 없는 경우 레이아웃을 inflate하여 viewHolder를 생성

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(businessCardArrayList[position], context)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    } //layout의 view와 데이터를 연결

    override fun getItemCount(): Int {
        return businessCardArrayList.size
    }

    override fun onItemMoved(from : Int, to : Int){
        if(from == to){
            return
        }

        val fromItem = businessCardArrayList.removeAt(from)
        businessCardArrayList.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int){
        businessCardArrayList.removeAt(position)
        notifyItemRemoved(position)
    }

} //아이템 갯수를 리턴처리하면 된다.