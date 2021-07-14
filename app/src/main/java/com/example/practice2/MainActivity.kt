package com.example.practice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.practice2.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout

data class BusinessCard(val name:String,val contents:String, val img : Int,)

class MainActivity : AppCompatActivity() {

    var businessCardArrayList = ArrayList<BusinessCard>()
    var i : Boolean = true
    private lateinit var customAdapter : CustomAdapter
    //private lateinit var customAdapter2 : CustomAdapter2
    lateinit var binding:ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var slidePanel = binding.mainFrame
        slidePanel.addPanelSlideListener(PanelEventListener())

        for(x in 0..30)
        {
            businessCardArrayList.add(BusinessCard("WHAT WOULD YOU DO?(feat. Pink Sweat$)","HONNE",R.drawable.music1))
            businessCardArrayList.add(BusinessCard("Understand(Feat. GIST)","MELOH",R.drawable.music2))
            businessCardArrayList.add(BusinessCard("Good News","Mac Miller",R.drawable.music3))
            businessCardArrayList.add(BusinessCard("LONELY DIVER","Zior Park",R.drawable.music4))
            businessCardArrayList.add(BusinessCard("Blueline","트웰브(twlv)",R.drawable.music5))
            businessCardArrayList.add(BusinessCard("Tommy Lee(feat.Post Malone)","Tyla Yaweh",R.drawable.music6))
            businessCardArrayList.add(BusinessCard("See You Again(feat. Charlie Puth) ","Wiz Khalifa",R.drawable.music7))
        }

        customAdapter = CustomAdapter(this,businessCardArrayList)
        //customAdapter2 = CustomAdapter2(this,businessCardArrayList)
        binding.listviewMain.adapter=customAdapter
        binding.listviewMain.setNestedScrollingEnabled(true)
        //binding.recyclerview.adapter=customAdapter2

        binding.img1Main.setOnClickListener()
        {
            if(i == true) {
                binding.img1Main.setImageResource(R.drawable.arrow_up)
                binding.listviewMain.visibility = View.GONE
                i = false
            }
            else
            {
                binding.img2Main.setImageResource(R.drawable.arrow_down)
                i = true
            }
        }

        binding.txt2Main.setOnClickListener()
        {
            val state = slidePanel.panelState

            if(state == SlidingUpPanelLayout.PanelState.COLLAPSED){
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }
            else if(state == SlidingUpPanelLayout.PanelState.EXPANDED){
                slidePanel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
        }

        /*
        binding.listviewMain.setOnClickListener(){
            binding.img3Main.setImageResource(customAdapter.getItem())
        }
        */
    }
}