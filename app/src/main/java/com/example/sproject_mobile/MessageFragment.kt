package com.example.sproject_mobile

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_message.*
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
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
    // TODO: Rename and change types of parameters
    private var dateTime: String? = null
    private var text: String? = null
    private var isRead: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dateTime = it.getString(ARG_PARAM1)
            text = it.getString(ARG_PARAM2)
            isRead = it.getString(ARG_PARAM3)
        }
    }

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_message, container, false)

        val dateTimeFormatted =
            dateTime?.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_formatter)))
        dateTimeTextView.text = dateTimeFormatted
        messageTextView.text = text

        if (isRead == "Read") {
            messageTextView.setTypeface(null, Typeface.BOLD)
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
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(dateTime: String, text: String, isRead: String) =
            MessageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, dateTime)
                    putString(ARG_PARAM2, text)
                    putString(ARG_PARAM3, isRead)
                }
            }
    }
}