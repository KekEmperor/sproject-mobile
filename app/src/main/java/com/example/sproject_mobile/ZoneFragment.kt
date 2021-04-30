package com.example.sproject_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_zone.*
import kotlinx.android.synthetic.main.fragment_zone.view.*
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "startTime"
private const val ARG_PARAM2 = "finishTime"
private const val ARG_PARAM3 = "type"

/**
 * A simple [Fragment] subclass.
 * Use the [ZoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ZoneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var startTime: String? = null
    private var finishTime: String? = null
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            startTime = it.getString(ARG_PARAM1)
            finishTime = it.getString(ARG_PARAM2)
            type = it.getString(ARG_PARAM3)
        }
    }

    private lateinit var root: View

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_zone, container, false)

        val startTimeParsed = ZonedDateTime.parse(startTime)
        val finishTimeParsed = ZonedDateTime.parse(finishTime)

        val startTimeFormatted =
            startTimeParsed.format(DateTimeFormatter.ofPattern(getString(R.string.time_formatter)))
        val finishTimeFormatted =
            finishTimeParsed.format(DateTimeFormatter.ofPattern(getString(R.string.time_formatter)))

        root.timeTextView.text = startTimeFormatted + " - " + finishTimeFormatted

        val dateFormatted =
            startTimeParsed.format(DateTimeFormatter.ofPattern(getString(R.string.date_formatter)))
        root.dateTextView.text = dateFormatted

        root.typeTextView.text = if (type == "hyper") {
            getString(R.string.crit_high)
        } else {
            getString(R.string.crit_low)
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ZoneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(startTime: String, finishTime: String, type: String) =
            ZoneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, startTime)
                    putString(ARG_PARAM2, finishTime)
                    putString(ARG_PARAM3, type)
                }
            }
    }
}