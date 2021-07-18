package com.devje.secondaryspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.devje.secondaryspinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PrimarySpinnerObservable, SecondarySpinnerObservable {

    // spinner 변수
    private val PRIMARY_SPINNER = 0
    private val SECONDARY_SPINNER = 1

    // 테마일 때 2차 어레어
    private val SECONDARY_SPINNER_ARRAY1 = listOf("첫번째1", "첫번째2", "첫번째3", "첫번째4")
    private val SECONDARY_SPINNER_ARRAY2 = listOf("두번째1", "두번째2", "두번째3", "두번째4")

    private lateinit var primarySpinnerListener: PrimarySpinnerListener
    private lateinit var secondarySpinnerListener: SecondarySpinnerListener


    private var _binding: ActivityMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()

        // 초기 뷰 셋팅
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // 초기 뷰 셋팅
    private fun initView(){
        primarySpinnerListener = PrimarySpinnerListener()
        secondarySpinnerListener = SecondarySpinnerListener()

        // feedListner 구독
        primarySpinnerListener.subscribe(this)
        secondarySpinnerListener.subscribe(this)

        binding.spinnerPrimary.onItemSelectedListener = primarySpinnerListener
        binding.spinnerSecondary.onItemSelectedListener = secondarySpinnerListener

        // 첫번째 스피너 셋팅하는 코드
        val arrayAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item, listOf("일번", "이번")
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPrimary.adapter = arrayAdapter
        //
    }

    // 1차 스피너로부터 포지션이 온다.
    override fun updatePrimary(position: Int) {
        Log.d("test", "updatePrimary")
        when (position) {
            PRIMARY_SPINNER -> {
                Log.d("test", "updatePrimary 1")
                // 일번일 경우
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, SECONDARY_SPINNER_ARRAY1
                )
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerSecondary.adapter = arrayAdapter
            }
            SECONDARY_SPINNER -> {
                Log.d("test", "updatePrimary 2")
                // 이번일 경우
                val arrayAdapter = ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, SECONDARY_SPINNER_ARRAY2
                )
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerSecondary.adapter = arrayAdapter
            }
            else -> {
                // error
                Log.d("test", "error")
                return
            }
        }
    }

    // 2차 스피너로부터 포지션이 온다.
    override fun updateSecondary(position: Int) {
        val adapter = binding.spinnerSecondary.adapter
        Log.d("test", "updateSecondary item : ${adapter.getItem(position).toString()}")
    }

}