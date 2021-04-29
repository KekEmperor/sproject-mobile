package com.example.sproject_mobile

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_message.view.*
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "dateTime"
private const val ARG_PARAM2 = "text"
private const val ARG_PARAM3 = "isRead"

/**
 * A simple [Fragment] subclass.
 * Use the [MessageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageFragment : Fragment() {
    private var dateTime: String? = null
    private var text: String? = null
    private var isRead: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dateTime = it.getString(ARG_PARAM1)
            text = it.getString(ARG_PARAM2)
            isRead = it.getBoolean(ARG_PARAM3)
        }
    }

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_message, container, false)

        val dateTimeParsed = ZonedDateTime.parse(dateTime)
        val dateTimeFormatted =
            dateTimeParsed.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_formatter)))

        root.dateTimeTextView.text = dateTimeFormatted
        root.messageTextView.text = text

        if (!isRead!!) {
            root.messageTextView.setTypeface(null, Typeface.BOLD)
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param dateTime dateTime.
         * @param text text.
         * @param isRead isRead.
         * @return A new instance of fragment MessageFragment.
         */
        @JvmStatic
        fun newInstance(dateTime: String, text: String, isRead: Boolean) =
            MessageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, dateTime)
                    putString(ARG_PARAM2, text)
                    putBoolean(ARG_PARAM3, isRead)
                }
            }
    }
}