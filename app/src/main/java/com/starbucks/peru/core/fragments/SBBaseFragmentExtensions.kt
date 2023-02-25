package com.starbucks.peru.core.fragments

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.starbucks.peru.R
import com.starbucks.peru.core.constants.SBPermissionStatus

fun Fragment.setSbTitle(@StringRes title: Int) {
    view?.findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar_title)?.title =
        getString(title)
}

fun Fragment.setSbTitle(title: String) {
    view?.findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar_title)?.title = title
}

/*fun Fragment.setSbSubTitle(subTitle: String) {
    view?.findViewById<TextView>(R.id.textView_subtitle)?.text = subTitle
}*/

fun Fragment.showHomeTitle(@StringRes title: Int) {
    view?.findViewById<TextView>(R.id.textView_home_title)?.text =
        getString(title)
}

fun Fragment.showHomeTitle(title: String) {
    view?.findViewById<TextView>(R.id.textView_home_title)?.text = title
}

/*fun Fragment.getTabLayout(): TabLayout? {
    return view?.findViewById(R.id.tabs_options)
}*/

//**
// Always call on resume
// **/
fun Fragment.configureToolbar() {
    val toolBar = view?.findViewById<Toolbar>(R.id.toolbar_title)
    toolBar?.let {
        val activity = requireActivity() as? AppCompatActivity
        activity?.setSupportActionBar(toolBar)
        it.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
fun Fragment.configureToolbarWithoutBackHome() {
    val toolBar = view?.findViewById<Toolbar>(R.id.toolbar_title)
    toolBar?.let {
        val activity = requireActivity() as? AppCompatActivity
        activity?.setSupportActionBar(toolBar)
    }
}

fun Fragment.getMenu(): Menu? = view?.findViewById<Toolbar>(R.id.toolbar_title)?.menu

fun Fragment.getMenuItemAtPosition(position: Int): MenuItem? =
    view?.findViewById<Toolbar>(R.id.toolbar_title)?.menu?.getItem(position)

fun Fragment.changeNavigationIcon(drawable: Drawable?) {
    context?.let {
        drawable?.let { drawable ->
            DrawableCompat.setTint(drawable, ContextCompat.getColor(it, R.color.sb_base_gray))
        }
    }

    view?.findViewById<Toolbar>(R.id.toolbar_title)?.navigationIcon = drawable
}

fun Fragment.isVisibleNavigationIcon(visible: Boolean) {
    (requireActivity() as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(visible)
}

fun Fragment.disableScrollParent() {
    val params =
        view?.findViewById<CollapsingToolbarLayout>(
            R.id.collapsingToolbar_title
        )?.layoutParams as? AppBarLayout.LayoutParams
    params?.scrollFlags = 0
}

inline fun Fragment.checkLocationPermission(result: (SBPermissionStatus) -> Unit) {
    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
        result.invoke(SBPermissionStatus.PERMISSION_SUCCESS)
    } else {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            result.invoke(SBPermissionStatus.SHOW_RATIONAL_DIALOG)
        } else {
            result.invoke(SBPermissionStatus.SHOW_REQUEST_PERMISSION)
        }
    }
}

fun Fragment.hasLocationPermission(): Boolean {
    return (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)
}

fun Fragment.showLoading(show: Boolean) {
    SBProgressDialogHelper.showLoading(show, requireContext())
}

fun Fragment.showLoadingAnimation(show: Boolean) {
    if (show) view?.findViewById<ConstraintLayout>(R.id.claLoadingAnimation)?.visibility = View.VISIBLE
    else view?.findViewById<ConstraintLayout>(R.id.claLoadingAnimation)?.visibility = View.GONE
}

/*fun Fragment.showMessage(message: String) {
    if (message.trim().isNotEmpty()) {
        showSimpleMessage(requireContext(), getString(R.string.sb_text_notice), message)
    }
}

fun Fragment.showError(message: String) {
    if (message.trim().isNotEmpty()) {
        if(message == CONNECTION_ERROR) {
            Toast.makeText(requireContext(), R.string.sb_error_internet_connection, Toast.LENGTH_LONG).show()
        } else {
            showSimpleMessage(requireContext(), getString(R.string.sb_text_error), message)
        }
    }
}*/
/*

fun Fragment.showScrollDialog(
    title: String,
    subTitle: String,
    message: String,
    buttonContinueTitle: String,
    funButtonContinue: () -> Unit = {}
) {
    val dialog = SBScrollBaseDialog(
        requireContext(),
        title,
        subTitle,
        message,
        buttonContinueTitle,
        funButtonContinue
    )

    dialog.show()
}

fun Fragment.showConfirmationMessage(
    title: String,
    message: String,
    buttonYesText: String = "",
    buttonNoText: String = "",
    yesAction: () -> Unit = {},
    noAction: () -> Unit = {}
) {

    val dialog = SBBaseDialog(
        requireContext(),
        title,
        message,
        true,
        buttonYesText,
        buttonNoText,
        yesAction,
        noAction
    )

    dialog.show()
}

private fun showSimpleMessage(
    context: Context,
    title: String,
    message: String
) {
    val dialog = SBBaseDialog(
        context,
        title,
        message,
        false
    )

    dialog.show()
}


*/
private object SBProgressDialogHelper {
    private var progressDialog: Dialog? = null

    fun showLoading(show: Boolean, context: Context) {
        if (show) {
            progressDialog = Dialog(context, R.style.Sb_Dialog_Transparent)
            progressDialog?.setContentView(R.layout.sb_view_progress_dialog)
            progressDialog?.setCancelable(false)

            progressDialog?.show()
        } else {
            progressDialog?.dismiss()
        }
    }
}
const val CONNECTION_ERROR = "CONNECTION_ERROR"