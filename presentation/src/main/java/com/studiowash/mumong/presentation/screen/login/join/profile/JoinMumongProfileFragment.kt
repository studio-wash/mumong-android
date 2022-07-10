package com.studiowash.mumong.presentation.screen.login.join.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.constant.ImageTypes
import com.studiowash.mumong.presentation.databinding.FragmentJoinMumongProfileBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.widget.SelectionBottomSheet

class JoinMumongProfileFragment: MumongFragment(false) {
    private val binding get() = _binding!!
    private var _binding: FragmentJoinMumongProfileBinding? = null

    private val viewModel: JoinMumongProfileViewModel by viewModels()

    private val profileTakePictureResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val bitmap = it.data?.extras?.get("data") as? Bitmap
            binding.ivProfileSelectCustom.setImageBitmap(bitmap)
        }
    }
    private val profileImagePickerResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val uri = it.data?.data
            binding.ivProfileSelectCustom.load(uri?.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinMumongProfileBinding.inflate(inflater, container, false)

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }

    private fun initView() {
        binding.ivProfileSelectCustom.clipToOutline = true
        binding.etId.doAfterTextChanged {
            viewModel.checkDataAvailability(it.toString(), binding.etName.text.toString())
        }
        binding.etName.doAfterTextChanged {
            viewModel.checkDataAvailability(binding.etId.text.toString(), it.toString())
        }
        binding.etName.requestFocus()
    }

    private fun initOnClick() {
        binding.btnNext.setOnClickListener {
            viewModel.moveToNextPage()
        }
        binding.ctlProfileSelectCustom.setOnClickListener {
            SelectionBottomSheet.Builder(requireContext())
                .items(listOf(R.string.join_custom_image_select_from_gallery, R.string.join_custom_image_select_take_picture))
                .itemClickListener { position ->
                    when(position) {
                        0 -> {
                            val intent = Intent(Intent.ACTION_PICK).apply {
                                type = ImageTypes.IMAGE
                                putExtra(Intent.EXTRA_MIME_TYPES, ImageTypes.STILL_IMAGES)
                            }
                            profileImagePickerResultLauncher.launch(intent)
                        }
                        1 -> {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            profileTakePictureResultLauncher.launch(intent)
                        }
                    }
                }
                .show()
        }
    }

    private fun initObserve() {
        viewModel.moveNextPageEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_joinProfileFragmentNav_to_joinInstrumentFragmentNav)
        }
        viewModel.availableData.observe(viewLifecycleOwner) {
            binding.availableData = it
        }
    }
}