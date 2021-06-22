package com.rsschool.quiz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rsschool.quiz.databinding.FragmentQuizQuestionOneBinding

class QuizQuestionOneFragment : Fragment() {
    private var _binding: FragmentQuizQuestionOneBinding? = null
    private val binding: FragmentQuizQuestionOneBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //mainActivity().listOfQuestion = mutableListOf()
        requireActivity().window.statusBarColor =
            getColorHelper(requireActivity(), R.color.deep_orange_100_dark)
        _binding = FragmentQuizQuestionOneBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.optionOne.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            1,
                            binding.optionOne.text.toString(),
                            false
                        )
                    )
                }
                binding.optionTwo.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            1,
                            binding.optionTwo.text.toString(),
                            false
                        )
                    )
                }
                binding.optionThree.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            1,
                            binding.optionThree.text.toString(),
                            true
                        )
                    )
                }
                binding.optionFour.id -> {
                    updateAnswerList(
                        Answer(
                            checkedId,
                            binding.question.text.toString(),
                            1,
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
                            1,
                            binding.optionFive.text.toString(),
                            false
                        )
                    )
                }
            }

        }


        binding.nextButton.setOnClickListener {
            if (mainActivity().listOfQuestion.none { it.questionNumber == 1 }) {
                Toast.makeText(requireActivity(), "Выберете ответ", Toast.LENGTH_LONG).show()
            } else {
                val action =
                    QuizQuestionOneFragmentDirections.actionQuizQuestionOneFragmentToQuizQuestionTwoFragment()
                findNavController().navigate(action)
            }

        }
        binding.previousButton.setOnClickListener {
            Toast.makeText(requireActivity(), "Действие невозможно", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateAnswerList(answer: Answer) {
        if (mainActivity().listOfQuestion.none { it == answer }) {
            mainActivity().listOfQuestion.removeAll { it.questionNumber == 1 }
            mainActivity().updateAnswerList(answer = answer)

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity().listOfQuestion = mutableListOf()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mainActivity() = requireActivity() as MainActivityInterface
}
