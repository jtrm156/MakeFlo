package com.example.practice2

import CustomAdapter2
import CustomAdapter3
import android.content.Intent
import android.content.SharedPreferences
import android.drm.DrmStore
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice2.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.json.JSONArray

data class BusinessCard(val name:String,val contents:String, val img : Int, val length : Int)

class MainActivity : AppCompatActivity(), ItemDragListener {

    var businessCardArrayList = mutableListOf<BusinessCard>()

    var i : Boolean = true
    var j : Boolean = true
    var z : Boolean = true
    //private lateinit var customAdapter : CustomAdapter

    lateinit var binding: ActivityMainBinding

    companion object{
        const val Music = "app_preferences"
    }
    private lateinit var mPreferences: SharedPreferences

    private lateinit var itemTouchHelper : ItemTouchHelper

    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // 패널이 슬라이드 중일 때

        override fun onPanelSlide(panel: View?, slideOffset: Float) {
            //binding.tvSlideOffset.text = slideOffset.toString()
        }

        // 패널의 상태가 변했을 때
        override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {
            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                //binding.bt.text = "열기"
            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                //binding.btnToggle.text = "닫기"
            }

        }
    }
    var checkboxbtnck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPreferences = getSharedPreferences(Music, MODE_PRIVATE);
        val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var slidePanel = binding.mainFrame
        slidePanel.addPanelSlideListener(PanelEventListener())

        for (x in 0..10) {
            businessCardArrayList.add(BusinessCard("WHAT WOULD YOU DO?(feat. Pink Sweat$)", "HONNE", R.drawable.music1,340))
            businessCardArrayList.add(BusinessCard("Understand(Feat. GIST)", "MELOH", R.drawable.music2,340))
            businessCardArrayList.add(BusinessCard("Good News", "Mac Miller", R.drawable.music3,340))
            businessCardArrayList.add(BusinessCard("LONELY DIVER", "Zior Park", R.drawable.music4,340))
            businessCardArrayList.add(BusinessCard("Blueline", "트웰브(twlv)", R.drawable.music5,340))
            businessCardArrayList.add(BusinessCard("Tommy Lee(feat.Post Malone)", "Tyla Yaweh", R.drawable.music6,340))
            businessCardArrayList.add(BusinessCard("See You Again(feat. Charlie Puth) ", "Wiz Khalifa", R.drawable.music7,340))
        }

        /*리스트뷰 사용
        //customAdapter = CustomAdapter(this,businessCardArrayList)
        //binding.listviewMain.adapter=customAdapter
        //binding.listviewMain.setNestedScrollingEnabled(true)
        //binding.listviewMain2.adapter=customAdapter
        //binding.listviewMain2.setNestedScrollingEnabled(true)
        */

        if(mPreferences.getInt("music_img",0) != 0) {
            binding.img3Main.setImageResource(mPreferences.getInt("music_img", 0))
        }

        val customAdapter2 = CustomAdapter2(this, businessCardArrayList, this)
        val customAdapter3 = CustomAdapter3(this, businessCardArrayList, this)

        customAdapter2.setItemClickListener(object : CustomAdapter2.ItemClickListener{
            override fun onClick(view: View,position:Int)
            {
                val item = businessCardArrayList[position]
                binding.img3Main.setImageResource(item.img)
                preferencesEditor.putInt("music_img",item.img)
                preferencesEditor.putString("music_name",item.name)
                preferencesEditor.putString("music_contents",item.contents)
                preferencesEditor.putInt("music_length",item.length)
                binding.progressBar.setProgress(mPreferences.getInt("music_length",0))
                preferencesEditor.apply()
            }

            override fun onLongClick(view: View, position: Int) {
                TODO("Not yet implemented")
            }
        })

        customAdapter3.setItemClickListener(object : CustomAdapter3.ItemClickListener{
            override fun onClick(view: View,position:Int)
            {
                val item = businessCardArrayList[position]
            }
            override fun onLongClick(view: View, position: Int) {
                TODO("Not yet implemented")
            }
        })

        binding.recyclerview.adapter = customAdapter2
        binding.recyclerview2.adapter = customAdapter3

        val layout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layout
        binding.recyclerview.setHasFixedSize(true)

        binding.checkBox.setOnClickListener()
        {
            if(binding.checkBox.isChecked){
                binding.checkBox.setText("선택해제")
                customAdapter3.selectAll(true)
            }
            else{
                binding.checkBox.setText("전체선택")
                customAdapter3.selectAll(false)
            }
        }

        binding.txt4Main2.setOnClickListener()
        {
            val state2 = slidePanel.panelState

            if (state2 == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            } else if (state2 == SlidingUpPanelLayout.PanelState.EXPANDED) {
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                /*데이터를저장*/
                val gson = Gson()
                val json = gson.toJson(customAdapter3.businessCardArrayList)
                preferencesEditor.putString("data", json)
                preferencesEditor.apply()
                Log.d("debug", "Data saved")
            }
            /*arraylist를 로드*/
            if(mPreferences.contains("data")){
                val gson = Gson()
                val json = mPreferences.getString("data","")
                try{
                    val typeToken = object : TypeToken<MutableList<BusinessCard>>() {}.type
                    customAdapter2.businessCardArrayList = gson.fromJson(json, typeToken)
                }
                catch ( e: JsonParseException){
                    e.printStackTrace()
                }
                customAdapter2.notifyItemChanged(0,customAdapter2.businessCardArrayList.size)
                Log.d("debug", "Data loaded")
            }
        }

        binding.img1Main.setOnClickListener()
        {
            if (i == true) {
                binding.img1Main.setImageResource(R.drawable.arrow_up)
                //binding.listviewMain.visibility = View.GONE
                binding.recyclerview.visibility = View.GONE
                i = false
            } else {
                binding.img1Main.setImageResource(R.drawable.arrow_down)
                //binding.listviewMain.visibility = View.VISIBLE
                binding.recyclerview.visibility = View.VISIBLE
                i = true
            }
        }

        binding.img1Main2.setOnClickListener()
        {
            if (j == true) {
                binding.img1Main.setImageResource(R.drawable.arrow_up)
                //binding.listviewMain.visibility = View.GONE
                binding.recyclerview2.visibility = View.GONE
                j = false
            } else {
                binding.img1Main.setImageResource(R.drawable.arrow_down)
                //binding.listviewMain.visibility = View.VISIBLE
                binding.recyclerview2.visibility = View.VISIBLE
                j = true
            }
        }

        binding.txt5Main2.setOnClickListener(){
            if (z == true) {
                binding.img1Main.setImageResource(R.drawable.arrow_up)
                //binding.listviewMain.visibility = View.GONE
                binding.recyclerview2.visibility = View.GONE
                z = false
            } else {
                binding.img1Main.setImageResource(R.drawable.arrow_down)
                //binding.listviewMain.visibility = View.VISIBLE
                binding.recyclerview2.visibility = View.VISIBLE
                z = true
            }
        }

        binding.txt2Main.setOnClickListener()
        {
            val state = slidePanel.panelState

            if (state == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                slidePanel.isTouchEnabled = false
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            } else if (state == SlidingUpPanelLayout.PanelState.EXPANDED) {
                slidePanel.isTouchEnabled = true
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }

        binding.drawer.setOnClickListener()
        {
            val enabled = slidePanel.isEnabled
            if (enabled) {
                slidePanel.isEnabled = false
            } else {
                slidePanel.isEnabled = true
            }
        }

        /*
        binding.listviewMain.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id -> Long
            val item = parent.getItemAtPosition(position) as BusinessCard
            binding.img3Main.setImageResource(item.img)
        }
        */

        binding.img3Main.setOnClickListener()
        {
            var intent = Intent(this,SubActivity::class.java)
            //intent.putExtra("data", mPreferences.getInt("music_img",0))
            startActivity(intent)
        }
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(customAdapter2))
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(customAdapter3))
        itemTouchHelper.attachToRecyclerView(binding.recyclerview2)
        //Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show()
    }
    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder){
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onStart() {
        super.onStart()
        val customAdapter3 = CustomAdapter3(this, businessCardArrayList, this)
        /*arraylist를 로드*/
        if(mPreferences.contains("data")){
            val gson = Gson()
            val json = mPreferences.getString("data","")
            try{
                val typeToken = object : TypeToken<MutableList<BusinessCard>>() {}.type
                customAdapter3.businessCardArrayList = gson.fromJson(json, typeToken)
            }
            catch ( e: JsonParseException){
                e.printStackTrace()
            }
            customAdapter3.notifyItemChanged(0,customAdapter3.businessCardArrayList.size)
            Log.d("debug", "Data loaded")
        }
        //Toast.makeText(this, "onstart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
       // Toast.makeText(this, "onresume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause(){
        super.onPause()
        //Toast.makeText(this, "onpause", Toast.LENGTH_SHORT).show()
    }
    override fun onStop() {
        super.onStop()
        mPreferences = getSharedPreferences(Music, MODE_PRIVATE);
        val preferencesEditor: SharedPreferences.Editor = mPreferences.edit()
        val customAdapter2 = CustomAdapter2(this, businessCardArrayList, this)

        /*arraylist로 저장*/
        val gson = Gson()
        val json = gson.toJson(customAdapter2.businessCardArrayList)
        preferencesEditor.putString("data", json)
        preferencesEditor.apply()
        Log.d("debug", "Data saved")


        //Toast.makeText(this, "onstop", Toast.LENGTH_SHORT).show()
    }
}