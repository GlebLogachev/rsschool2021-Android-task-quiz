package com.rsschool.quiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rsschool.quiz.databinding.FragmentQuizQuestionTwoBinding

class QuizQuestionTwoFragment : Fragment() {
    private var _binding: FragmentQuizQuestionTwoBinding? = null
    private val binding: FragmentQuizQuestionTwoBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.statusBarColor =
            getColorHelper(requireActivity(), R.color.yellow_100_dark)
        _binding = FragmentQuizQuestionTwoBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCachedValue()
        binding.previousButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.optionOne.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            2, binding.optionOne.text.toString(), false
                        )
                    )
                }
                binding.optionTwo.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            2,
                            binding.optionTwo.text.toString(),
                            true
                        )
                    )
                }
                binding.optionThree.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            2,
                            binding.optionThree.text.toString(),
                            false
                        )
                    )
                }
                binding.optionFour.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            2,
                            binding.optionFour.text.toString(),
                            false
                        )
                    )
                }
                binding.optionFive.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            2,
                            binding.optionFive.text.toString(),
                            false
                        )
                    )
                }
            }
        }
        binding.nextButton.setOnClickListener {
            if (mainActivity().listOfQuestion.none { it.questionNumber == 2 }) {
                Toast.makeText(requireActivity(), "Выберете ответ", Toast.LENGTH_LONG).show()
            } else {
                val action =
                    QuizQuestionTwoFragmentDirections.actionQuizQuestionTwoFragmentToQuizQuestionThreeFragment()
                findNavController().navigate(action)
            }

        }
    }

    private fun setCachedValue() {
        val cachedAnswer = mainActivity().listOfQuestion.filter { it.questionNumber == 2 }
        if (cachedAnswer.isNotEmpty())
            binding.radioGroup.check(cachedAnswer.last().id)

    }

    private fun updateAnswerList(answer: Answer) {
        if (mainActivity().listOfQuestion.none { it == answer }) {
            mainActivity().listOfQuestion.removeAll { it.questionNumber == 2 }
            mainActivity().updateAnswerList(answer = answer)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mainActivity() = requireActivity() as MainActivityInterface

}