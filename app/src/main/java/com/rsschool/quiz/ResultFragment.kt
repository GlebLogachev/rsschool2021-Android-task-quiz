package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rsschool.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            playAgain()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.twResult.text = getString(R.string.quiz_result, getCorrectAnswers().toString())

        binding.btnAgain.setOnClickListener {
            playAgain()
        }

        binding.btnExit.setOnClickListener {
            requireActivity().finish()
        }

        binding.btnShare.setOnClickListener {
            shareResult()
        }

    }

    private fun shareResult() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz result")
        intent.putExtra(Intent.EXTRA_TEXT, setMessage())
        requireActivity().startActivity(Intent.createChooser(intent, "поделиться"))
    }

    private fun setMessage(): String {
        var count = 0
        var string = getString(R.string.quiz_result, getCorrectAnswers().toString())
        mainActivity().listOfQuestion.sortBy { it.questionNumber }
        mainActivity().listOfQuestion.forEach {
            count += 1
            string += "\n $count) Вопрос: ${it.questionText}" +
                "\nВаш ответ: ${it.answerText}"
        }
        return string
    }

    private fun playAgain() {
        val action = ResultFragmentDirections.actionResultFragmentToQuizQuestionOneFragment()
        findNavController().navigate(action)
    }

    private fun getCorrectAnswers(): Int {
        var correctAnswersCount: Int = 0
        mainActivity().listOfQuestion.forEach {
            if (it.isRight)
                correctAnswersCount += 1
        }
        return correctAnswersCount
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mainActivity() = requireActivity() as MainActivityInterface

}
